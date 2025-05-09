package ifrs.edu.br;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import ifrs.edu.br.dao.FuncionarioDAO;
import ifrs.edu.br.models.Funcionario;

/**
 * Client
 */
public class Client {
    private Scanner scanner;
    private FuncionarioDAO dao;

    public Client(FuncionarioDAO dao) {
        this.scanner = new Scanner(System.in);
        this.dao = dao;
    }

    public void insert() {
        System.out.print("name: ");
        String name = scanner.nextLine();

        System.out.print("email: ");
        String email = scanner.nextLine();

        System.out.print("horas extras: ");
        int horasExtras = scanner.nextInt();
        scanner.nextLine();

        Funcionario func = new Funcionario(horasExtras, LocalDate.now(), name, email);
        dao.inserir(func);

        System.out.println();
        System.out.println("New Employee created! Probably...");
        System.out.println();
    }

    public void find() {
        System.out.print("id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Funcionario funcionario = dao.buscar(id);
        System.out.println();

        System.out.println(funcionario != null ? funcionario.toString() : "Employee not found!");
    }

    public void list() {
        System.out.print("offset: ");
        int offset = scanner.nextInt();
        scanner.nextLine();

        System.out.print("limit: ");
        int limit = scanner.nextInt();
        scanner.nextLine();

        List<Funcionario> funcionarios = dao.listar(limit, offset);

        System.out.println();
        System.out.println("## Funcionários ##");
        System.out.println();
        for (Funcionario funcionario : funcionarios) {
            System.out.println("\t" + funcionario.toString());
            System.out.println();
        }
        System.out.println("##################");
        System.out.println();
    }

    public void update() {
        System.out.print("id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("name: ");
        String name = scanner.nextLine();

        System.out.print("email: ");
        String email = scanner.nextLine();

        System.out.print("horas extras: ");
        int horasExtras = scanner.nextInt();
        scanner.nextLine();

        Funcionario func = new Funcionario(id, horasExtras, LocalDate.now(), name, email);
        dao.alterar(func);
        System.out.println();

        System.out.println("Employee updated! Probably...");
        System.out.println();
    }

    public void delete() {
        System.out.print("id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        dao.deletar(id);
        System.out.println();
        System.out.println("Employee deleted! Probably...");
    }

    public void close() {
        scanner.close();
    }
}
