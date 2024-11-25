import java.util.List;

// Construtor
public class Retangulo extends FiguraGeometrica2D {
    Ponto2D pontoA;
    Ponto2D pontoB;
    Ponto2D pontoC;
    Ponto2D pontoD;

    public Retangulo (List<Ponto2D> pontos) {
        // Construtor da superclasse FIguraGeometrica2D
        super(pontos);
        // Separando as coordenadas da lista em variaveis
        pontoA = pontos.get(0);
        pontoB = pontos.get(1);
        pontoC = pontos.get(2);
        pontoD = pontos.get(3);

        // Reordenando os pontos para que dois pares de coordenadas em sequencia tenham o mesmo valor de x ou y
        if(pontoA.getX() == pontoC.getX()) {
            Ponto2D temp = pontoB;
            pontoB = pontoC;
            pontoC = temp;
        } else if (pontoA.getX() == pontoD.getX()) {
            Ponto2D temp = pontoB;
            pontoB = pontoD;
            pontoD = temp;
        } 
    }

    @Override
    public float calcularArea() {
        float altura;
        float largura;

        altura = Math.abs(pontoA.getY() - pontoB.getY());
        largura = Math.abs(pontoA.getX() - pontoC.getX());

        return altura * largura;
    }
}
