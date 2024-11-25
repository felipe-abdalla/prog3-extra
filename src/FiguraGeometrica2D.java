import java.util.List;

abstract  class FiguraGeometrica2D implements IAritimeticaGeometrica2D{
    private List<Ponto2D> pontos;

    // Construtor
    public FiguraGeometrica2D(List<Ponto2D> pontos) {
        this.pontos = pontos;
    }

    // Classe abstrata que vem da interface IAritimeticaGeometrica2D
    @Override
    public abstract float calcularArea();
}
