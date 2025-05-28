package ifrs.edu.br;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ifrs.edu.br.dao.DependenteDAO;
import ifrs.edu.br.dao.FuncionarioDAO;
import ifrs.edu.br.dao.ProjetoDAO;
import io.github.cdimascio.dotenv.Dotenv;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Dotenv dotenv = Dotenv.load();

        Map<String, Object> config = new HashMap<>();
        config.put("javax.persistence.jdbc.url", dotenv.get("POSTGRES_DATABASE_JDBC"));
        config.put("javax.persistence.jdbc.user", dotenv.get("POSTGRES_DATABASE_USER"));
        config.put("javax.persistence.jdbc.password", dotenv.get("POSTGRES_DATABASE_PASSWORD"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PostgresSQLDefaultPU", config);
        EntityManager entityMan = emf.createEntityManager();

        DependenteDAO dependenteDAO = new DependenteDAO(entityMan);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(entityMan);
        ProjetoDAO projetoDAO = new ProjetoDAO(entityMan);

        Client client = new Client(funcionarioDAO, dependenteDAO, projetoDAO);

        while (true) {
            System.out.println();
            System.out.println();
            System.out.print("> ");
            String res = scan.nextLine();

            switch (res.toLowerCase()) {
                case "insert":
                    client.insert();
                    break;
                case "add dependente":
                    client.addDependente();
                    break;
                case "add projeto":
                    client.addProjeto();
                    break;
                case "add participante in projeto":
                    client.insertEmpregadoInProjeto();
                    break;
                case "complete projeto":
                    client.insertEmpregadoInProjeto();
                    break;
                case "find projeto":
                    client.findProjeto();
                    break;
                case "find empregado":
                    client.findEmpregado();
                    break;
                case "list":
                    client.list();
                    break;
                case "update":
                    client.update();
                    break;
                case "delete":
                    client.delete();
                    break;
                case "help":
                    System.out.println("# commands");
                    System.out.println("> insert");
                    System.out.println("> add dependente");
                    System.out.println("> add projeto");
                    System.out.println("> add participante in projeto");
                    System.out.println("> complete projeto");
                    System.out.println("> find projeto");
                    System.out.println("> find empregado");
                    System.out.println("> list");
                    System.out.println("> update");
                    System.out.println("> delete");
                    System.out.println("> quit");
                    System.out.println("> help");
                    System.out.println();
                    break;
                case "quit":
                    System.out.println("Exiting...");
                    scan.close();
                    client.close();
                    entityMan.close();
                    emf.close();
                    return;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }
}
