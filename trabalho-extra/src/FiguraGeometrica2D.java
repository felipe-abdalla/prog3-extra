import java.util.ArrayList;

abstract class FiguraGeometrica2D implements IAritimeticaGeometrica2D {
    private List<Ponto2D> pontos;

    // Construtor
    public FiguraGeometrica2D(List<Ponto2D> pontos) {
        this.pontos = pontos;
    }

    // Getter
    public List<Ponto2D> getPontos() {
        return pontos;
    }

    //public void desenhar(GL2 gl);

    public float calcularArea();

    public void imprimirCalculoArea(float calculoArea) {
        System.out.println("Area: " + calculoArea);
    }
}