package ifrs.edu.com;

public class Conta {
	private int id;
	private Cliente titular;
	private double saldo;
	private int limite;

	public Conta() {}

	public Conta(Cliente titular, double saldo, int limite) {
		this.setId(0);
		this.setTitular(titular);
		this.setSaldo(saldo);
		this.setLimite(limite);
	}

	public Conta(int id, Cliente titular, double saldo, int limite) {
		this.setId(id);
		this.setTitular(titular);
		this.setSaldo(saldo);
		this.setLimite(limite);
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

	public int getId() {
		return id;
	}

	public double getSaldo() {
		return saldo;
	}

	public int getLimite() {
		return limite;
	}

	public Cliente getTitular() {
		return titular;
	}
}
