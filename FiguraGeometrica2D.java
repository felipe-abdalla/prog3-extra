import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import java.util.List;

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

    public abstract void desenhar(GL2 gl);

    public abstract float calcularArea();

    public void imprimirCalculoArea(float calculoArea) {
        String tipoObjeto = this.getClass().getSimpleName();
        System.out.println("Área do objeto " + tipoObjeto + ": " + calculoArea);
    }
}