import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class Principal implements GLEventListener{
    private int quantidadeRetangulos;
    private int quantidadeCircunferencias;
    private List<Retangulo> retangulos;
    private List<Circunferencia> circunferencias;
    private final float raioCircunferencia = 0.1f;
    private final double tamanhoRetangulo = 0.2f;
    
    public Principal(int quantidadeRetangulos, int quantidadeCircunferencias) {
        this.quantidadeRetangulos = quantidadeRetangulos;
        this.quantidadeCircunferencias = quantidadeCircunferencias;
        this.retangulos = new ArrayList<>();
        this.circunferencias = new ArrayList<>();
        //criarObjetos();
    }
    
    private void criarObjetos() {
        retangulos.clear();
        circunferencias.clear();

        // Criando retângulos
        for (int i = 0; i < quantidadeRetangulos; i++) {
            List<Ponto2D> pontos = new ArrayList<>();
            double x = Math.random() * 2 - 1; // Coordenada x inicial
            double y = Math.random() * 2 - 1; // Coordenada y inicial
            pontos.add(new Ponto2D(x, y));
            pontos.add(new Ponto2D(x + tamanhoRetangulo, y + tamanhoRetangulo));
            retangulos.add(new Retangulo(pontos));
        }

        // Criando circunferências
        for (int i = 0; i < quantidadeCircunferencias; i++) {
            Ponto2D centro = new Ponto2D(Math.random() * 2 - 1, Math.random() * 2 - 1);
            circunferencias.add(new Circunferencia(centro, raioCircunferencia));
        }
    }

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // Desenhando os retângulos
        for (Retangulo retangulo : retangulos) {
            retangulo.desenhar(gl);
        }
        

        // Desenhando as circunferências
        for (Circunferencia circunferencia : circunferencias) {
            circunferencia.desenhar(gl);
        }
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        // Fundo branco
        gl.glClearColor(1, 1, 1, 1);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-1, 1, -1, 1, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

        criarObjetos();
    }
    
    public static void main(String[] args) {
        try {
            int quantidadeRetangulos = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de retângulos:"));
            int quantidadeCircunferencias = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de circunferências:"));

            // Configuração do canvas OpenGL
            GLProfile profile = GLProfile.get(GLProfile.GL2);
            GLCapabilities capabilities = new GLCapabilities(profile);
            GLCanvas canvas = new GLCanvas(capabilities);

            // Criando instância de Principal e associando ao canvas
            Principal principal = new Principal(quantidadeRetangulos, quantidadeCircunferencias);
            canvas.addGLEventListener(principal);

            // Criando a janela com Swing
            JFrame frame = new JFrame("Representação Gráfica");
            frame.setSize(800, 800);
            frame.add(canvas);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            // Animação para manter a atualização constante
            FPSAnimator animator = new FPSAnimator(canvas, 60);
            animator.start();
        } catch (Exception e) {
            System.err.println("Erro ao inicializar OpenGL: " + e.getMessage());
            System.exit(1);
        }
    }
}