/*
 * Matrix.java
 * *
 * Ultima modificação: 08/11/2025 (Refatorado)
 *
 * Material utilizado para o Projeto Pratico 01 [Tetris] aula de MC322 - Programacao Orientada a Objetos
 */

package Matrix;

/**
 * Classe abstrata base para estruturas matriciais, como o Board e as Pieces.
 * Define a estrutura básica de uma matriz de cores com altura e largura.
 */
public abstract class Matrix {
    protected Color[][] form; // A matriz de cores que representa a forma.
    protected int height;     // Altura da matriz.
    protected int width;      // Largura da matriz.


    /**
     * Construtor da classe Matrix.
     * @param height Altura da matriz.
     * @param width Largura da matriz.
     */
    public Matrix(int height, int width) {
        this.height = height;
        this.width = width;
        this.form = new Color[height][width];
    }

    // Getters
    public Color[][] getForm() { return form; }
    public int getHeight() { return height; }
    public int getWidth() { return width; }

    // Setters
    public void setForm(Color[][] form) { this.form = form; }
    public void setHeight(int height) { this.height = height; }
    public void setWidth(int width) { this.width = width; }

    /**
     * Retorna uma representação em String da matriz, utilizando o método toString() de cada Color (ANSI).
     * @return String com a matriz formatada para console.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                out.append(form[i][j]);
            }
            out.append('\n');
        }
        return out.toString();
    }
}