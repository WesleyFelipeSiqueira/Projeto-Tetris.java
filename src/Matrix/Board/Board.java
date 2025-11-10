package Matrix.Board;
import Matrix.Color;
import Matrix.Matrix;
import Matrix.Pieces.Piece;

public class Board extends Matrix {
    private int score;
    private String playerName; 
    private Piece fallingPiece;
    private Piece nextPiece; // <-- NOVO: Para a pré-visualização
    private final Color[][] landed;

    // Construtor antigo
    public Board(int height, int width) {
        this(height, width, "Player 1"); 
    }

    // Construtor principal
    public Board(int height, int width, String playerName) {
        super(height, width);
        this.playerName = playerName; 
        this.landed = new Color[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                form[i][j] = landed[i][j] = Color.WHITE;
            }
        }
        this.score = 0;
        this.fallingPiece = null;
        this.nextPiece = null; // Inicia como nulo
    }

    // Getters
    public int getScore() { return score; }
    public Piece getFallingPiece() { return fallingPiece; }
    public Color[][] getLanded() { return landed; }
    public String getPlayerName() { return playerName; } 
    public Piece getNextPiece() { return nextPiece; } // <-- NOVO

    // Setters e Modificadores
    public void addScore(int points) { this.score += points; }
    public void setFallingPiece(Piece fallingPiece) { this.fallingPiece = fallingPiece; }
    public void clearPiece() { this.setFallingPiece(null); }
    public void setNextPiece(Piece p) { this.nextPiece = p; } // <-- NOVO

    // O resto do ficheiro (update, clear, updateLanded, deleteLine)
    // permanece EXATAMENTE igual a antes.
    
    public void update() {
        for (int i = 0; i < height; i++) {
            System.arraycopy(landed[i], 0, form[i], 0, width);
        }
        if (fallingPiece != null) {
            int x = fallingPiece.getX();
            int y = fallingPiece.getY();
            int m = fallingPiece.getHeight();
            int n = fallingPiece.getWidth();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (fallingPiece.getForm()[i][j] != Color.WHITE) {
                        int board_y = y + i;
                        int board_x = x + j;
                        if (board_y >= 0 && board_y < height && board_x >= 0 && board_x < width) {
                            form[board_y][board_x] = fallingPiece.getForm()[i][j];
                        }
                    }
                }
            }
        }
    }

    public void clear() {
        if (fallingPiece != null) {
            int x = fallingPiece.getX();
            int y = fallingPiece.getY();
            int m = fallingPiece.getHeight();
            int n = fallingPiece.getWidth();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (fallingPiece.getForm()[i][j] != Color.WHITE) {
                        int board_y = y + i;
                        int board_x = x + j;
                        if (board_y >= 0 && board_y < height && board_x >= 0 && board_x < width) {
                            form[board_y][board_x] = landed[board_y][board_x];
                        }
                    }
                }
            }
        }
    }

    public void updateLanded() {
        for (int i = 0; i < height; i++) {
            System.arraycopy(form[i], 0, landed[i], 0, width);
        }
    }

    public void deleteLine(int y) {
        for (int i = y; i > 0; i--) {
            System.arraycopy(landed[i - 1], 0, landed[i], 0, width);
        }
        for (int x = 0; x < width; x++) {
            landed[0][x] = Color.WHITE;
        }
        for (int i = 0; i < height; i++) {
            System.arraycopy(landed[i], 0, form[i], 0, width);
        }
    }
}