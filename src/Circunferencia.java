import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Circunferencia extends FiguraGeometrica2D {
    private Ponto2D centro;
    private float raio;

    // Construtor
    public Circunferencia(Ponto2D ponto, float raio) {
        super(new ArrayList<>(Arrays.asList(ponto)));
        centro = ponto;
        this.raio = raio;
    }

    // Getters
    public float getRaio() {
        return raio;
    }

    public Ponto2D getCentro() {
        return centro;
    }

    // Calculo da area da circunferencia
    @Override
    public float calcularArea() {
        return 2*(float)Math.PI*raio;
    }
}