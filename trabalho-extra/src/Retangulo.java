class Retangulo extends FiguraGeometrica2D {
    // Construtor
    public Retangulo(List<Ponto2D> pontos) {
        // Separando os pontos do retângulo
        Ponto2D pontoA = pontos[0];
        Ponto2D pontoB = pontos[1];
        Ponto2D pontoC = pontos[2];
        Ponto2D pontoD = pontos[3];

        /* --------------Visualização--------------
            Lógica de pontos: x + 3
            [x,x][x,x+3][x+3,x][x+3,x+3]
            Ordem de inserção:
            [1,1]   pontoA
            [1,4]   pontoB
            [4,1]   pontoC
            [4,4]   pontoD

            Ordem no retângulo:

            [1,4]               [4,4]




            [1,1]               [4,1]

            Largura:    pontoC - pontoA
            Altura:     pontoB - pontoA
         */
    }

    /*public void desenhar(GL2 gl) {

    }*/

    @Override
    public float calcularArea() {
        return ((pontoC - pontoA) * (pontoB - pontoA));
    }
}