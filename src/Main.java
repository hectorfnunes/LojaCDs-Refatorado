import modelo.CD;
import modelo.Cliente;
import modelo.Funcionario;
import repository.CDRepository;
import repository.ClienteRepository;
import repository.FuncionarioRepository;
import service.CDService;
import service.ClienteService;
import service.FuncionarioService;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== TESTES DO SISTEMA LOJA DE CDs ===\n");

        CDService cdService             = new CDService(new CDRepository());
        ClienteService clienteService   = new ClienteService(new ClienteRepository());
        FuncionarioService funcService  = new FuncionarioService(new FuncionarioRepository());

        // Teste: listar CDs
        try {
            System.out.println("--- CDs cadastrados ---");
            List<CD> cds = cdService.listar("");
            if (cds.isEmpty()) {
                System.out.println("Nenhum CD encontrado.");
            } else {
                for (CD cd : cds) {
                    System.out.println("ID: " + cd.getId() + " | " + cd.getTitulo() + " - " + cd.getArtista());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar CDs: " + e.getMessage());
        }

        // Teste: listar Clientes
        try {
            System.out.println("\n--- Clientes cadastrados ---");
            List<Cliente> clientes = clienteService.listar();
            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado.");
            } else {
                for (Cliente c : clientes) {
                    System.out.println("ID: " + c.getId() + " | " + c.getNome());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }

        // Teste: listar Funcionários
        try {
            System.out.println("\n--- Funcionários cadastrados ---");
            List<Funcionario> funcs = funcService.listar();
            if (funcs.isEmpty()) {
                System.out.println("Nenhum funcionário encontrado.");
            } else {
                for (Funcionario f : funcs) {
                    System.out.println("ID: " + f.getId() + " | " + f.getNome() + " - " + f.getCargo());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }

        // Teste: validação de negócio
        try {
            System.out.println("\n--- Teste de validação ---");
            CD cdInvalido = new CD("", "Artista Teste", "Rock", 2024, BigDecimal.valueOf(29.90), 10);
            cdService.cadastrar(cdInvalido);
        } catch (IllegalArgumentException e) {
            System.out.println("Validação funcionando: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        System.out.println("\n=== FIM DOS TESTES ===");
    }
}