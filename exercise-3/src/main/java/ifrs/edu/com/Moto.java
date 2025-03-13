package ifrs.edu.com;

public class Moto {
    // atributos
    private final String modelo;
    private double velocidade;
    private double qtyGasolina;
    private static final int VEL_FINAL = 150;
    private static final int TAMANHO_TANQUE = 30;

    public Moto(String modelo) {
        this.modelo = modelo;
        this.velocidade = 0;
        this.qtyGasolina = 0;
    }

    public String getModelo() {
        return this.modelo;
    }

    public double getVelocidade() {
        return this.velocidade;
    }

    public double getQtyGasolina() {
        return qtyGasolina;
    }

    // aumenta a velocidade até a velocidade máxima
    void acelera(int vel) {
        if (vel < 0)
            throw new RuntimeException("vel deve ser positiva");

        if (this.qtyGasolina <= 0)
            return;

        double litPerKm = 0.01;
        double gasSpended = litPerKm * vel;
        double remainingGas = this.qtyGasolina - gasSpended;

        if (remainingGas < 0) {
            gasSpended = this.qtyGasolina;
            this.qtyGasolina = 0;

            vel = (int) Math.round(gasSpended / litPerKm);
        } else
            this.qtyGasolina -= gasSpended;

        if (this.velocidade + vel > VEL_FINAL)
            this.velocidade = VEL_FINAL;
        else {
            this.velocidade += vel;
        }
    }

    // diminui velocidade até parar
    void freia(int vel) {
        if (vel < 0)
            throw new RuntimeException("vel deve ser positiva");
        else if (this.velocidade - vel < 0)
            this.velocidade = 0;
        else
            this.velocidade -= vel;
    }

    void abastecer(double qty) {
        if (qty < 0)
            throw new RuntimeException("qty deve ser positiva");
        else if (this.qtyGasolina + qty > TAMANHO_TANQUE)
            this.qtyGasolina = TAMANHO_TANQUE;
        else
            this.qtyGasolina += qty;
    }
}
