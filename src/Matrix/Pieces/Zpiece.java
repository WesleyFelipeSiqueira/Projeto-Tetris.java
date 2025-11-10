/*
 * Piece.java
 *
 * última modificação: 09/05/2024
 *
 * Material utilizado para o Projeto Prático 01 [Tetris] aula de MC322 - Programação Orientada a Objetos
 */
package Matrix.Pieces;
import Matrix.Color;


/**
 *  Esta classe é referente à peça T. Contém seu construtor e métodos para a implementação.
 */
public class Zpiece extends Piece{
    // Construtor
    public Zpiece() {
        super(3, 2, 2, 8);
        Color R = Color.RED;
        Color W = Color.WHITE;
        Color[][] form = {{R, W}, {R, R}, {W, R}};
        this.setForm(form);
    }
}
