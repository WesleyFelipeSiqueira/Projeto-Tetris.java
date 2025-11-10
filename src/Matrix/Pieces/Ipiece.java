/*
 * Ipiece.java
 *
 * última modificação: 09/05/2024
 *
 * Material utilizado para o Projeto Prático 01 [Tetris] aula de MC322 - Programação Orientada a Objetos
 */
package Matrix.Pieces;
import Matrix.Color;

/**
 *  Esta classe é referente à peça I. Contém seu construtor e métodos para a implementação.
 */
public class Ipiece extends Piece {
    // Construtor.
    public Ipiece() {
        super(4, 1, 0, 10);
        Color C = Color.CYAN;
        Color[][] form = {{C}, {C}, {C}, {C}};
        this.setForm(form);
    }
}
