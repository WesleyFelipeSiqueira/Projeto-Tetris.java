/*
 * Piece.java
 *
 * Ultima modificação: 08/11/2025 (Refatorado)
 *
 * Material utilizado para o Projeto Prático 01 [Tetris] aula de MC322 - Programação Orientada a Objetos
 */

package Matrix.Pieces;

import java.util.concurrent.ThreadLocalRandom;

import Matrix.Color;
import Matrix.Matrix;

/**
 * Esta classe abstrata é o modelo para todas as peças do jogo;
 * Contém assim os atributos e métodos para a implementação delas.
 * Responsável por gerenciar a forma, posição e rotação pura da peça.
 */
public abstract class Piece extends Matrix {
    protected int x;          // Posição da coluna (elemento [0][0] da peça no tabuleiro).
    protected int y;          // Posição da linha (elemento [0][0] da peça no tabuleiro).
    protected int rotation;   // Contagem de rotações (0 a 3).


    /**
     * Construtor da peça.
     * @param height Altura inicial da forma da peça.
     * @param width Largura inicial da forma da peça.
     * @param origin Limite inferior (inclusivo) para a posição X inicial.
     * @param bound Limite superior (exclusivo) para a posição X inicial.
     */
    public Piece(int height, int width, int origin, int bound) {
        super(height, width);
        this.spawn(origin, bound); // define x inicial randomico
        this.y = 0;
        this.rotation = 0;
    }

    // Getters e Setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getRotation() { return rotation; }

    public void setRotation(int rotation) {
        this.rotation = (this.rotation + rotation) % 4;
    }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    /**
     * A função adiciona i na posição x da peça.
     */
    public void addX(int i){ x += i; }

    /**
     * A função adiciona i na posição y da peça.
     */
    public void addY(int i){ y += i; }

    /**
     * A função movimenta a peça para esquerda.
     */
    public void moveLeft(){ this.addX(-1); }

    /**
     * A função movimenta a peça para direita.
     */
    public void moveRight(){ this.addX(1); }


    /**
     * Define x inicial da peça de forma aleatória dentro dos limites definidos.
     * @param origin Limite inferior (inclusivo) para X.
     * @param bound Limite superior (exclusivo) para X.
     */
    public void spawn(int origin, int bound) {
        x = ThreadLocalRandom.current().nextInt(origin, bound);
    }

    /**
     * Troca os valores de altura e largura da matriz-peça.
     * Usado na rotação, pois a matriz muda de dimensão.
     */
    private void switchDimensions() {
        int aux = height;
        height = width;
        width = aux;
    }


    /**
     * Aplica a rotação da peça no sentido horário, atualizando a forma e as dimensões.
     * A checagem de colisão (wall kick) é responsabilidade do Engine.
     */
    public void rotateClockWise() {
        int newHeight = width;
        int newWidth = height;
        Color[][] rotatedForm = new Color[newHeight][newWidth];

        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                rotatedForm[i][j] = form[newWidth - j - 1][i];
            }
        }

        this.switchDimensions();
        this.setForm(rotatedForm);
        this.setRotation(1);
    }

    /**
     * Aplica a rotação da peça no sentido anti-horário, atualizando a forma e as dimensões.
     * Usado para reverter uma rotação não permitida ou para rotação de "undo".
     */
    public void rotateCounterClockWise(){
        int newHeight = width;
        int newWidth = height;
        Color[][] rotatedForm = new Color[newHeight][newWidth];

        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                rotatedForm[i][j] = form[j][newHeight - i - 1];
            }
        }
        this.switchDimensions();
        this.setForm(rotatedForm);
        this.setRotation(-1);
    }
}