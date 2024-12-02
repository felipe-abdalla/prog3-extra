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

    public Principal(List<Ponto2D> pontos, Ponto2D centroCircunferencia, float raioCircunferencia) {
        this.pontos = pontos;
        this.centroCircunferencia = centroCircunferencia;
        this.raioCircunferencia = raioCircunferencia;
    }

    public static void main(String[] args) {
        try {
            // Inicializa as variáveis
            List<Ponto2D> pontos = new ArrayList<>();
            pontos.add(new Ponto2D(1, 1));
            pontos.add(new Ponto2D(1, 5));
            pontos.add(new Ponto2D(8, 1));
            pontos.add(new Ponto2D(8, 5));

            Ponto2D centroCircunferencia = new Ponto2D(0, 0);
            float raioCircunferencia = 5.0f;

            // Configura o JOGL com GLProfile específico (GL2)
            GLProfile profile = GLProfile.get(GLProfile.GL2);  // Alterado para GL2
            GLCapabilities capabilities = new GLCapabilities(profile);

            // Força o uso de buffer duplo e aceleração de hardware
            capabilities.setDoubleBuffered(true);
            capabilities.setHardwareAccelerated(true);

            GLCanvas canvas = new GLCanvas(capabilities);
            canvas.addGLEventListener(new Principal(pontos, centroCircunferencia, raioCircunferencia));

            JFrame frame = new JFrame("Formas Geométricas - JOGL");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(canvas);
            frame.setSize(800, 800);
            frame.setVisible(true);

            FPSAnimator animator = new FPSAnimator(canvas, 60);
            animator.start();

        } catch (GLException e) {
            System.err.println("Erro ao inicializar o contexto OpenGL: " + e.getMessage());
            e.printStackTrace();
        }
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

        drawAxes(gl);
        drawRectangle(gl);
        drawCircunferencia(gl);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 10, 0, 10, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    private void drawAxes(GL2 gl) {
        gl.glColor3f(1, 1, 1);
        gl.glBegin(GL2.GL_LINES);

        gl.glVertex2f(0, 5);
        gl.glVertex2f(10, 5);

        gl.glVertex2f(5, 0);
        gl.glVertex2f(5, 10);

        gl.glEnd();
    }

    private void drawRectangle(GL2 gl) {
        gl.glColor3f(0, 1, 0);
        gl.glBegin(GL2.GL_QUADS);

        for (Ponto2D ponto : pontos) {
            gl.glVertex2f(ponto.getX(), ponto.getY());
        }

        gl.glEnd();
    }

    private void drawCircunferencia(GL2 gl) {
        gl.glColor3f(1, 0, 0);
        gl.glBegin(GL2.GL_LINE_LOOP);

        int numSegments = 100;
        for (int i = 0; i < numSegments; i++) {
            double theta = 2.0 * Math.PI * i / numSegments;
            float x = (float) (raioCircunferencia * Math.cos(theta) + centroCircunferencia.getX());
            float y = (float) (raioCircunferencia * Math.sin(theta) + centroCircunferencia.getY());
            gl.glVertex2f(x, y);
        }

        gl.glEnd();
    }
}
