/*
 * Color.java
 *
 * última modificação: 08/11/2025 (Refatorado)
 *
 * Material utilizado para o Projeto Prático 01 [Tetris] aula de MC322 - Programação Orientada a Objetos
 */

package Matrix;

/**
 * Define as cores dos blocos do jogo (Tetrominoes) e seus códigos ANSI
 * para representação no console (método toString()).
 */
public enum Color {
    // Cores das peças Tetris e do tabuleiro
    WHITE,   // Fundo do tabuleiro ou célula vazia
    BLACK,   // Borda/Contraste
    RED,     // Peça Z
    ORANGE,  // Peça L
    GREEN,   // Peça S
    YELLOW,  // Peça O
    BLUE,    // Peça J
    PURPLE,  // (Não utilizado)
    CYAN,    // Peça I
    PINK;    // Peça T

    /**
     * Retorna o código ANSI para formatação de fundo de terminal.
     * O '   ' (três espaços) garante que a célula tenha largura suficiente para visualização.
     * @return String com o código ANSI e espaços, seguido pelo RESET.
     */
    @Override
    public String toString() {
        String RESET = "\u001B[0m";
        // Códigos ANSI (48;5;[código]m para cor de fundo)
        switch (this) {
            case WHITE:
                return "\033[48;5;15m" + "   " + RESET;
            case BLACK:
                return "\033[48;5;0m" + "   " + RESET;
            case RED:
                return "\033[48;5;160m" + "   " + RESET;
            case ORANGE:
                return "\033[48;5;208m" + "   " + RESET;
            case GREEN:
                return "\033[48;5;46m" + "   " + RESET;
            case YELLOW:
                return "\033[48;5;220m" + "   " + RESET;
            case BLUE:
                return "\033[48;5;21m" + "   " + RESET;
            case PURPLE:
                return "\033[48;5;5m" + "   " + RESET;
            case CYAN:
                return "\033[48;5;45m" + "   " + RESET;
            case PINK:
                return "\033[48;5;170m" + "   " + RESET;
            default:
                return " " + RESET;
        }
    }
}