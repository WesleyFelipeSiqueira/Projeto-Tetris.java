/*
 * Lpiece.java
 *
 * última modificação: 09/05/2024
 *
 * Material utilizado para o Projeto Prático 01 [Tetris] aula de MC322 - Programação Orientada a Objetos
 */
package Matrix.Pieces;
import Matrix.Color;

/**
 *  Esta classe é referente à peça L. Contém seu construtor e métodos para a implementação.
 */
public class Lpiece extends Piece {
    // Construtor
    public Lpiece() {
        super(3, 2, 2, 8);
        Color O = Color.ORANGE;
        Color W = Color.WHITE;
        Color[][] form = {{O, W}, {O, W}, {O, O}};
        this.setForm(form);
    }
}
