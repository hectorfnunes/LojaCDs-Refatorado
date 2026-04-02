import modelo.CD;
import modelo.Cliente;
import modelo.Funcionario;
import repository.CDRepository;
import repository.ClienteRepository;
import repository.FuncionarioRepository;
import service.AuthService;
import service.CDService;
import service.ClienteService;
import service.FuncionarioService;
import sessao.UserSession;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== TESTES DO SISTEMA LOJA DE CDs ===\n");

        CDService cdService            = new CDService(new CDRepository());
        ClienteService clienteService  = new ClienteService(new ClienteRepository());
        FuncionarioService funcService = new FuncionarioService(new FuncionarioRepository());
        AuthService authService        = new AuthService(new FuncionarioRepository());

        // -------------------------------------------------------
        // 1. LISTAGEM GERAL
        // -------------------------------------------------------
        System.out.println("--- CDs cadastrados ---");
        try {
            List<CD> cds = cdService.listar("");
            if (cds.isEmpty()) System.out.println("Nenhum CD encontrado.");
            else cds.forEach(cd -> System.out.println("ID: " + cd.getId() + " | " + cd.getTitulo() + " - " + cd.getArtista()));
        } catch (Exception e) {
            System.out.println("Erro ao listar CDs: " + e.getMessage());
        }

        System.out.println("\n--- Clientes cadastrados ---");
        try {
            List<Cliente> clientes = clienteService.listar();
            if (clientes.isEmpty()) System.out.println("Nenhum cliente encontrado.");
            else clientes.forEach(c -> System.out.println("ID: " + c.getId() + " | " + c.getNome()));
        } catch (Exception e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }

        System.out.println("\n--- Funcionários cadastrados ---");
        try {
            List<Funcionario> funcs = funcService.listar();
            if (funcs.isEmpty()) System.out.println("Nenhum funcionário encontrado.");
            else funcs.forEach(f -> System.out.println("ID: " + f.getId() + " | " + f.getNome() + " - " + f.getCargo()));
        } catch (Exception e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }

        // -------------------------------------------------------
        // 2. CICLO CRUD COMPLETO: inserir -> buscar -> atualizar -> deletar
        // -------------------------------------------------------
        System.out.println("\n--- Ciclo CRUD de CD ---");
        try {
            // Inserir com titulo unico para nao colidir com dados existentes
            CD novo = new CD("CD_TESTE_TEMP", "Artista Teste", "Pop", 2024, BigDecimal.valueOf(39.90), 5);
            cdService.cadastrar(novo);
            System.out.println("Cadastro: OK");

            // Buscar pelo titulo para pegar o ID gerado
            List<CD> encontrados = cdService.listar("CD_TESTE_TEMP");
            if (encontrados.isEmpty()) {
                System.out.println("buscarPorId: FALHOU (nao encontrou apos insercao)");
            } else {
                int idInserido = encontrados.get(0).getId();
                CD encontrado = cdService.buscarPorId(idInserido);
                System.out.println("buscarPorId (" + idInserido + "): " + encontrado.getTitulo() + " - OK");

                // Atualizar
                encontrado.setTitulo("CD_TESTE_ATUALIZADO");
                encontrado.setPreco(BigDecimal.valueOf(49.90));
                cdService.atualizar(encontrado);
                String tituloAtualizado = cdService.buscarPorId(idInserido).getTitulo();
                System.out.println("Atualizacao: " + tituloAtualizado + " - OK");

                // Deletar e verificar
                cdService.deletar(idInserido);
                CD deletado = cdService.buscarPorId(idInserido);
                System.out.println("Delecao: " + (deletado == null ? "OK (registro removido)" : "FALHOU"));
            }
        } catch (Exception e) {
            System.out.println("Erro no ciclo CRUD: " + e.getMessage());
        }

        // -------------------------------------------------------
        // 3. VALIDACOES DE NEGOCIO
        // -------------------------------------------------------
        System.out.println("\n--- Testes de validacao ---");

        // CD: titulo vazio
        try {
            cdService.cadastrar(new CD("", "Artista", "Rock", 2024, BigDecimal.valueOf(29.90), 10));
        } catch (IllegalArgumentException e) {
            System.out.println("Titulo vazio: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        // CD: preco zero
        try {
            cdService.cadastrar(new CD("Titulo OK", "Artista", "Rock", 2024, BigDecimal.ZERO, 10));
        } catch (IllegalArgumentException e) {
            System.out.println("Preco zero: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        // CD: estoque negativo
        try {
            cdService.cadastrar(new CD("Titulo OK", "Artista", "Rock", 2024, BigDecimal.valueOf(29.90), -1));
        } catch (IllegalArgumentException e) {
            System.out.println("Estoque negativo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        // Cliente: nome vazio
        try {
            clienteService.cadastrar(new Cliente("", "123.456.789-00", "email@teste.com", "(11)99999-9999"));
        } catch (IllegalArgumentException e) {
            System.out.println("Cliente sem nome: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        // Funcionario: salario zero
        try {
            funcService.cadastrar(new Funcionario("Nome OK", "111.111.111-11", "Vendedor", 0));
        } catch (IllegalArgumentException e) {
            System.out.println("Salario zero: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        // -------------------------------------------------------
        // 4. AUTENTICACAO
        // -------------------------------------------------------
        System.out.println("\n--- Testes de autenticacao ---");
        try {
            // Login invalido (CPF inexistente)
            boolean loginInvalido = authService.login("000.000.000-00", "Vendedor");
            System.out.println("Login invalido bloqueado: " + (!loginInvalido ? "OK" : "FALHOU"));

            // Login valido com dados do banco (Func1 / Vendedor)
            boolean loginValido = authService.login("987.654.321-00", "Vendedor");
            System.out.println("Login valido (Func1): " + (loginValido ? "OK" : "FALHOU"));

            // Sessao ativa apos login
            System.out.println("Sessao ativa: " + (UserSession.getInstance().isSessaoAtiva() ? "OK" : "FALHOU"));

            // Logout
            authService.logout();
            System.out.println("Logout: " + (!UserSession.getInstance().isSessaoAtiva() ? "OK" : "FALHOU"));

        } catch (Exception e) {
            System.out.println("Erro na autenticacao: " + e.getMessage());
        }

        // -------------------------------------------------------
        // 5. SINGLETON UserSession
        // -------------------------------------------------------
        System.out.println("\n--- Teste Singleton UserSession ---");
        UserSession s1 = UserSession.getInstance();
        UserSession s2 = UserSession.getInstance();
        System.out.println("Mesma instancia (s1 == s2): " + (s1 == s2 ? "OK" : "FALHOU"));

        s1.iniciarSessao(99, "Gerente");
        System.out.println("Escrito via s1, lido via s2 - cargo: " + s2.getCargo()
                + " | isGerente: " + s2.isGerente());
        s1.encerrarSessao();
        System.out.println("Apos encerrar via s1 - isSessaoAtiva via s2: " + s2.isSessaoAtiva());

        System.out.println("\n=== FIM DOS TESTES ===");
    }
}