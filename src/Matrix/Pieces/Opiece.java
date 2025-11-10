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
 *  Esta classe é referente à peça O. Contém seu construtor e métodos para a implementação.
 */
public class Opiece extends Piece {
    // Construtor
    public Opiece() {
        super(2, 2, 1, 9);
        Color Y = Color.YELLOW;
        Color[][] form = {{Y, Y}, {Y, Y}};
        this.setForm(form);
    }
}
