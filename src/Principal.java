import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class Principal implements GLEventListener {
    private List<Ponto2D> pontos;
    private Ponto2D centroCircunferencia;
    private float raioCircunferencia; 

    public Principal (List<Ponto2D> pontos, Ponto2D centroCircunferencia, float raioCircunferencia) {
        this.pontos = pontos;
        this.centroCircunferencia = centroCircunferencia;
        this.raioCircunferencia = raioCircunferencia;
    }

    public static void main (String[] args){
        // Inicializa as variáveis trabalhadas
        List<Ponto2D> pontos = new ArrayList<>();
        pontos.add(new Ponto2D(1, 1));
        pontos.add(new Ponto2D(1, 5));
        pontos.add(new Ponto2D(8, 1));
        pontos.add(new Ponto2D(8, 5));

        Ponto2D centroCircunferencia = new Ponto2D(0,0);
        float raioCircunferencia = 5.0f;
    
        // Configura o JOGL
        JFrame frame = new JFrame("Formas Geométricas - JOGL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new Principal(pontos, centroCircunferencia, raioCircunferencia));
 
        frame.getContentPane().add(canvas);
        frame.setSize(800, 800);
        frame.setVisible(true);
 
        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 1); // Fundo preto
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // Desenha os eixos do plano cartesiano
        drawAxes(gl);

        // Desenha o retângulo
        drawRectangle(gl);

        // Desenha a circunferência
        drawCircunferencia(gl);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // Não é necessário liberar recursos adicionais
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 10, 0, 10, -1, 1); // Define o plano cartesiano de 0 a 10
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    private void drawAxes(GL2 gl) {
        gl.glColor3f(1, 1, 1); // Cor branca para os eixos
        gl.glBegin(GL2.GL_LINES);

        // Eixo X
        gl.glVertex2f(0, 5);
        gl.glVertex2f(10, 5);

        // Eixo Y
        gl.glVertex2f(5, 0);
        gl.glVertex2f(5, 10);

        gl.glEnd();
    }

    private void drawRectangle(GL2 gl) {
        gl.glColor3f(0, 1, 0); // Cor verde
        gl.glBegin(GL2.GL_QUADS);

        for (Ponto2D ponto : pontos) {
            gl.glVertex2f(ponto.getX(), ponto.getY());
        }

        gl.glEnd();
    }

    private void drawCircunferencia(GL2 gl) {
        gl.glColor3f(1, 0, 0); // Cor vermelha
        gl.glBegin(GL2.GL_LINE_LOOP);

        int numSegments = 100;
        for (int i = 0; i < numSegments; i++) {
            double theta = 2.0 * Math.PI * i / numSegments; // Ângulo
            float x = (float) (raioCircunferencia * Math.cos(theta) + centroCircunferencia.getX());
            float y = (float) (raioCircunferencia * Math.sin(theta) + centroCircunferencia.getY());
            gl.glVertex2f(x, y);
        }

        gl.glEnd();
    }

        //FiguraGeometrica2D circunferencia = new Circunferencia(centroCircunferencia, raioCircunferencia);
        //FiguraGeometrica2D retangulo = new Retangulo(pontos);

        //System.out.println("Área da circunferência: " + circunferencia.calcularArea());
        //System.out.println("Área do retângulo: " + retangulo.calcularArea());
    
}
