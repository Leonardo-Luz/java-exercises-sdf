package ifrs.edu.br;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import ifrs.edu.br.dao.FuncionarioDAO;
import ifrs.edu.br.dao.DependenteDAO;
import ifrs.edu.br.models.Funcionario;
import ifrs.edu.br.models.Dependente;

/**
 * Client
 */
public class Client {
    private Scanner scanner;
    private FuncionarioDAO funcDao;
    private DependenteDAO depDao;

    public Client(FuncionarioDAO funcDao, DependenteDAO depDao) {
        this.scanner = new Scanner(System.in);
        this.funcDao = funcDao;
        this.depDao = depDao;
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
        funcDao.inserir(func);

        System.out.println();
        System.out.println("New Employee created! Probably...");
        System.out.println();
    }

    public void addDependente() {
        System.out.print("cpf (somente os números): ");
        String cpf = scanner.nextLine();

        System.out.print("nome: ");
        String nome = scanner.nextLine();

        System.out.print("profissão: ");
        String profissao = scanner.nextLine();

        System.out.print("Funcionario ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Funcionario funcionario = funcDao.buscar(id);

        Dependente dep = new Dependente(cpf, LocalDate.now(), nome, profissao, funcionario);
        depDao.inserir(dep);

        System.out.println();
        System.out.println("New Employee created! Probably...");
        System.out.println();
    }

    public void find() {
        System.out.print("id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Funcionario funcionario = funcDao.buscar(id);
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

        List<Funcionario> funcionarios = funcDao.listarDependentes(limit, offset);

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
        funcDao.alterar(func);
        System.out.println();

        System.out.println("Employee updated! Probably...");
        System.out.println();
    }

    public void delete() {
        System.out.print("id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        funcDao.deletar(id);
        System.out.println();
        System.out.println("Employee deleted! Probably...");
    }

    public void close() {
        scanner.close();
    }
}
