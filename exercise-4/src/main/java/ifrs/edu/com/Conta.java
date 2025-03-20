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

	private void setId(int id) {
		this.id = id;
	}

	private void setSaldo(double saldo) {
		if (saldo < 0 && -saldo > this.limite) {
			throw new ContaException("Saldo não pode ser menor que o limite!");
		}
		this.saldo = saldo;
	}

	private void setLimite(int limite) {
		this.limite = limite;
	}

	private void setTitular(Cliente titular) {
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

	public double saca(double val) {
		boolean valIsNegative = val < 0;

		if (valIsNegative) {
			throw new ContaException("Não pode sacar um valor negativo.");
		}

		this.setSaldo(this.saldo - val);

		return val;
	}

	public void deposita(double val) {
		boolean valIsNegative = val < 0;

		if (valIsNegative) {
			throw new ContaException("Não pode depositar um valor negativo.");
		}

		this.setSaldo(this.saldo + val);
	}

	public void transfere(double val, Conta conta) {
		boolean valIsNegative = val < 0;

		if (conta == null) {
			throw new NullPointerException("Não pode transferir para uma conta nula.");
		}

		if (valIsNegative) {
			throw new ContaException("Não pode transferir um valor negativo.");
		}

		conta.deposita(this.saca(val));
	}
}
