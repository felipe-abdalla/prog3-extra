import java.util.Arrays;
import java.util.List;
import com.jogamp.opengl.GL2;

class Retangulo extends FiguraGeometrica2D {
    /* --------------Visualização--------------
            Exemplo de lógica de pontos: x + 3
            [x,x][x,x+3][x+3,x][x+3,x+3]
            Ordem de inserção:
            [1,1]   pontoA
            [4,4]   pontoB

            Ordem no retângulo:
              D                   B
            [1,4]               [4,4]



              A                   C
            [1,1]               [4,1]

            pontoC = (pontoB.getX(), pontoA.getY());
            pontoD = (pontoA.getX(), pontoB.getY());

            Largura:    x.pontoC - x.pontoA
            Altura:     y.pontoB - y.pontoA
     */

    private Ponto2D pontoA;
    private Ponto2D pontoB;
    private Ponto2D pontoC;
    private Ponto2D pontoD;

    // Construtor
    public Retangulo(List<Ponto2D> pontos) {
        // A partir de dois pontos (A e B) conseguimos calcular os outros dois (C e D) de forma que sempre forme um retângulo
        super(Arrays.asList(
            pontos.get(0),                                                 // pontoA
            pontos.get(1),                                                 // pontoB
            new Ponto2D(pontos.get(1).getX(), pontos.get(0).getY()), // pontoC
            new Ponto2D(pontos.get(0).getX(), pontos.get(1).getY())  // pontoD
        ));

        // Atribuir os pontos
        pontoA = pontos.get(0);
        pontoB = pontos.get(1);
        pontoC = new Ponto2D(pontoB.getX(), pontoA.getY());
        pontoD = new Ponto2D(pontoA.getX(), pontoB.getY());

        imprimirCalculoArea(calcularArea());
    }

    @Override
    public void desenhar(GL2 gl) {
        // Cor vermelha
        gl.glColor3f(1, 0, 0); 
        gl.glBegin(GL2.GL_QUADS);

        gl.glVertex2d(pontoA.getX(), pontoA.getY());
        gl.glVertex2d(pontoD.getX(), pontoD.getY());
        gl.glVertex2d(pontoB.getX(), pontoB.getY());
        gl.glVertex2d(pontoC.getX(), pontoC.getY());

        gl.glEnd();
    }

    @Override
    public float calcularArea() {
        return (((float)pontoC.getX() - (float)pontoA.getX()) * ((float)pontoB.getY() - (float)pontoA.getY()));
    }
}