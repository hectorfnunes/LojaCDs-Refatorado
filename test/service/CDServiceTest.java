package service;

import modelo.CD;
import repository.FakeCDRepository;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes unitários para as validações do CDService.
 * Usa o FakeCDRepository para não precisar de banco de dados.
 */
public class CDServiceTest {

    private CDService cdService;
    private FakeCDRepository fakeRepository;

    /**
     * Executado antes de cada teste.
     * Cria instâncias limpas para garantir que os testes não se influenciem.
     */
    @Before
    public void setUp() {
        fakeRepository = new FakeCDRepository();
        cdService = new CDService(fakeRepository);
    }

    // ---------------------------------------------------------------
    // Testes de casos válidos
    // ---------------------------------------------------------------

    @Test
    public void testCadastrar_CDValido_DeveCadastrarSemErro() throws Exception {
        CD cd = new CD("Abbey Road", "The Beatles", "Rock", 1969,
                       new BigDecimal("39.90"), 10);
        cdService.cadastrar(cd);
        assertEquals(1, fakeRepository.totalCadastrados());
    }

    // ---------------------------------------------------------------
    // Testes de validação do Título
    // ---------------------------------------------------------------

    @Test(expected = IllegalArgumentException.class)
    public void testCadastrar_TituloNulo_DeveLancarExcecao() throws Exception {
        CD cd = new CD(null, "The Beatles", "Rock", 1969,
                       new BigDecimal("39.90"), 10);
        cdService.cadastrar(cd);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCadastrar_TituloEmBranco_DeveLancarExcecao() throws Exception {
        CD cd = new CD("   ", "The Beatles", "Rock", 1969,
                       new BigDecimal("39.90"), 10);
        cdService.cadastrar(cd);
    }

    // ---------------------------------------------------------------
    // Testes de validação do Preço
    // ---------------------------------------------------------------

    @Test(expected = IllegalArgumentException.class)
    public void testCadastrar_PrecoNulo_DeveLancarExcecao() throws Exception {
        CD cd = new CD("Abbey Road", "The Beatles", "Rock", 1969, null, 10);
        cdService.cadastrar(cd);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCadastrar_PrecoZero_DeveLancarExcecao() throws Exception {
        CD cd = new CD("Abbey Road", "The Beatles", "Rock", 1969,
                       BigDecimal.ZERO, 10);
        cdService.cadastrar(cd);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCadastrar_PrecoNegativo_DeveLancarExcecao() throws Exception {
        CD cd = new CD("Abbey Road", "The Beatles", "Rock", 1969,
                       new BigDecimal("-1.00"), 10);
        cdService.cadastrar(cd);
    }

    // ---------------------------------------------------------------
    // Testes de validação do Estoque
    // ---------------------------------------------------------------

    @Test(expected = IllegalArgumentException.class)
    public void testCadastrar_EstoqueNegativo_DeveLancarExcecao() throws Exception {
        CD cd = new CD("Abbey Road", "The Beatles", "Rock", 1969,
                       new BigDecimal("39.90"), -1);
        cdService.cadastrar(cd);
    }

    @Test
    public void testCadastrar_EstoqueZero_DevePermitir() throws Exception {
        // Estoque zero é válido (produto sem unidades em estoque)
        CD cd = new CD("Abbey Road", "The Beatles", "Rock", 1969,
                       new BigDecimal("39.90"), 0);
        cdService.cadastrar(cd);
        assertEquals(1, fakeRepository.totalCadastrados());
    }
}