import java.util.Collections;
import com.jogamp.opengl.GL2;

class Circunferencia extends FiguraGeometrica2D {
    private float raio;

    public Circunferencia(Ponto2D centro, float raio) {
        super(Collections.singletonList(centro));
        this.raio = raio;

        imprimirCalculoArea(calcularArea());
    }

    @Override
    public void desenhar(GL2 gl) {
        // Cor de preenchimento
        gl.glColor3f(0, 0, 1); // Azul
        gl.glBegin(GL2.GL_TRIANGLE_FAN);

        // Centro da circunferência
        gl.glVertex2d(getPontos().get(0).getX(), getPontos().get(0).getY());

        int numSegments = 100;
        for (int i = 0; i <= numSegments; i++) {
            double theta = 2.0 * Math.PI * i / numSegments;
            double x = raio * Math.cos(theta) + getPontos().get(0).getX();
            double y = raio * Math.sin(theta) + getPontos().get(0).getY();
            gl.glVertex2d(x, y);
        }
        gl.glEnd();
    }

    @Override
    public float calcularArea() {
        return ((float)Math.PI * raio * raio);
    }
}