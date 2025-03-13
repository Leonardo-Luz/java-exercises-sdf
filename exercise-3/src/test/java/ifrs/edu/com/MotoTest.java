package ifrs.edu.com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MotoTest {
    private Moto moto;

    @BeforeEach
    public void setupMoto() {
        this.moto = new Moto("modelo de teste");
    }

    @Test
    @DisplayName("Should increase speed by 10")
    public void shouldIncreaseSpeedBy10() {
        this.moto.abastecer(30);
        this.moto.acelera(10);
        assertEquals(10, moto.getVelocidade(), 0.0001);
    }

    @Test
    @DisplayName("Should increase speed by 0")
    public void shouldIncreaseSpeedBy0() {
        this.moto.abastecer(30);
        this.moto.acelera(0);
        assertEquals(0, moto.getVelocidade(), 0.0001);
    }

    @Test
    @DisplayName("Should not increase speed above 150")
    public void shouldNotIncreaseSpeedAbove150() {
        this.moto.abastecer(30);
        this.moto.acelera(200);
        assertEquals(150, moto.getVelocidade(), 0.0001);
    }

    @Test
    @DisplayName("Should not increase without gas")
    public void shouldNotIncreaseSpeedWithoutGas() {
        this.moto.acelera(200);
        assertEquals(0, moto.getVelocidade(), 0.0001);
    }

    @Test
    @DisplayName("Should not increase speed above the gas spend")
    public void shouldNotIncreaseSpeedAboveTheGasSpend() {
        this.moto.abastecer(1);
        this.moto.acelera(200);
        assertEquals(100, moto.getVelocidade(), 0.0001);
    }

    @Test
    @DisplayName("Should not increase speed by negative number")
    public void shouldNotIncreaseSpeedByNegativeNumber() {
        assertThrows(RuntimeException.class, () -> {
            this.moto.abastecer(30);
            this.moto.acelera(-10);
        });
    }

    @Test
    @DisplayName("Should decrease speed by 10")
    public void shouldDecreaseSpeedBy10() {
        this.moto.abastecer(30);
        this.moto.acelera(20);
        this.moto.freia(10);
        assertEquals(10, moto.getVelocidade(), 0.0001);
    }

    @Test
    @DisplayName("Should decrease speed by 0")
    public void shouldDecreaseSpeedBy0() {
        this.moto.abastecer(30);
        this.moto.acelera(10);
        this.moto.freia(0);
        assertEquals(10, moto.getVelocidade(), 0.0001);
    }

    @Test
    @DisplayName("Should not decrease speed below 0")
    public void shouldNotDecreaseSpeedBelow0() {
        this.moto.freia(100);
        assertEquals(0, moto.getVelocidade(), 0.0001);
    }

    @Test
    @DisplayName("Should not decrease speed by negative number")
    public void shouldNotDecreaseSpeedByNegativeNumber() {
        assertThrows(RuntimeException.class, () -> {
            this.moto.freia(-10);
        });
    }

    @Test
    @DisplayName("Should increase gas by 10")
    public void shouldIncreaseGasBy10() {
        this.moto.abastecer(10);
        assertEquals(10, moto.getQtyGasolina(), 0.0001);
    }

    @Test
    @DisplayName("Should increase gas by 0")
    public void shouldIncreaseGasBy0() {
        this.moto.abastecer(0);
        assertEquals(0, moto.getQtyGasolina(), 0.0001);
    }

    @Test
    @DisplayName("Should not increase gas above 30")
    public void shouldNotIncreaseGasAbove150() {
        this.moto.abastecer(50);
        assertEquals(30, moto.getQtyGasolina(), 0.0001);
    }

    @Test
    @DisplayName("Should not increase gas by negative number")
    public void shouldNotIncreaseGasByNegativeNumber() {
        assertThrows(RuntimeException.class, () -> {
            this.moto.abastecer(-10);
        });
    }
}
