import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.*;
import java.util.Arrays;

public class Principal implements GLEventListener {
    private Retangulo retangulo;
    private Circunferencia circunferencia;

    public Principal(Retangulo retangulo, Circunferencia circunferencia) {
        this.retangulo = retangulo;
        this.circunferencia = circunferencia;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Inicializa o Retângulo
                Retangulo retangulo = new Retangulo(Arrays.asList(
                    new Ponto2D(1, 1),
                    new Ponto2D(1, 4),
                    new Ponto2D(4, 4),
                    new Ponto2D(4, 1)
                ));

                // Inicializa a Circunferência
                Circunferencia circunferencia = new Circunferencia(new Ponto2D(6, 6), 2.0f);

                // Configura JOGL
                GLProfile profile = GLProfile.get(GLProfile.GL2);
                GLCapabilities capabilities = new GLCapabilities(profile);
                capabilities.setDoubleBuffered(true);
                capabilities.setHardwareAccelerated(true);

                GLCanvas canvas = new GLCanvas(capabilities);
                Principal principal = new Principal(retangulo, circunferencia);
                canvas.addGLEventListener(principal);

                JFrame frame = new JFrame("Formas Geométricas - JOGL");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(canvas);
                frame.setSize(800, 800);
                frame.setVisible(true);

                FPSAnimator animator = new FPSAnimator(canvas, 60);
                animator.start();

                // Listener para fechar corretamente
                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        if (animator.isStarted()) {
                            animator.stop();
                        }
                        canvas.destroy();  // Libera os recursos do canvas
                        System.exit(0);    // Encerra a aplicação
                    }
                });
            } catch (GLException e) {
                System.err.println("Erro ao inicializar o contexto OpenGL: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 1); // Fundo preto
        System.out.println("OpenGL inicializado com sucesso.");
        System.out.println("Area do retangulo: " + retangulo.calcularArea());
        System.out.println("Area da circunferencia: " + circunferencia.calcularArea());
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        drawAxes(gl);
        drawRectangle(gl);
        drawCircunferencia(gl);

        gl.glFlush();  // Garante a renderização imediata
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        System.out.println("Recursos OpenGL liberados.");
        GL2 gl = drawable.getGL().getGL2();
        gl.glFinish();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        if (height == 0) height = 1; // Evitar divisão por zero
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 10, 0, 10, -1, 1);  // Define a projeção ortográfica
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
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
        gl.glColor3f(0, 1, 0);  // Cor verde
        gl.glBegin(GL2.GL_QUADS);

        gl.glVertex2f(retangulo.pontoA.getX(), retangulo.pontoA.getY());
        gl.glVertex2f(retangulo.pontoB.getX(), retangulo.pontoB.getY());
        gl.glVertex2f(retangulo.pontoC.getX(), retangulo.pontoC.getY());
        gl.glVertex2f(retangulo.pontoD.getX(), retangulo.pontoD.getY());

        gl.glEnd();
    }

    private void drawCircunferencia(GL2 gl) {
        gl.glColor3f(1, 0, 0);  // Cor vermelha
        gl.glBegin(GL2.GL_LINE_LOOP);

        int numSegments = 100;
        for (int i = 0; i < numSegments; i++) {
            double theta = 2.0 * Math.PI * i / numSegments;
            float x = (float) (circunferencia.getRaio() * Math.cos(theta) + circunferencia.getCentro().getX());
            float y = (float) (circunferencia.getRaio() * Math.sin(theta) + circunferencia.getCentro().getY());
            gl.glVertex2f(x, y);
        }

        gl.glEnd();
    }
}
