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
 *  Esta classe é referente à peça S. Contém seu construtor e métodos para a implementação.
 */
public class Spiece extends Piece {
    // Construtor
    public Spiece() {
        super(2, 3, 2, 8);
        Color G = Color.GREEN;
        Color W = Color.WHITE;
        Color[][] form = {{G, G, W}, {W, G, G}};
        this.setForm(form);
    }
}
