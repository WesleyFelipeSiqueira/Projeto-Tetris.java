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
public class Tpiece extends Piece {
    // Construtor
    public Tpiece() {
        super(2, 3, 2, 8);
        Color P = Color.PINK;
        Color W = Color.WHITE;
        Color[][] form = {{P, P, P}, {W, P, W}};
        this.setForm(form);
    }
}
