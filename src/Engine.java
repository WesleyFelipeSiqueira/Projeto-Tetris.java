import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import Matrix.Color;
import Matrix.Board.Board;
import Matrix.Pieces.Ipiece;
import Matrix.Pieces.Jpiece;
import Matrix.Pieces.Lpiece;
import Matrix.Pieces.Opiece;
import Matrix.Pieces.Piece;
import Matrix.Pieces.Spiece;
import Matrix.Pieces.Tpiece;
import Matrix.Pieces.Zpiece;

public class Engine {

    private static final int FPS = 30; 
    private static final long DELTA_TIME_MS = 1000 / FPS; 
    private static final int GRAVITY_TICK_RATE = 6; 
    private static final int SINGLE_LINE_SCORE = 100;
    private static final int DOUBLE_LINE_SCORE = 300;
    private static final int TRIPLE_LINE_SCORE = 500;
    private static final int TETRIS_SCORE = 800;
    private static final int DROP_SCORE = 4; 

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    private static Piece spawnRandomPiece() {
        int rand_piece = ThreadLocalRandom.current().nextInt(1, 8);
        switch (rand_piece) {
            case 1: return new Ipiece();
            case 2: return new Opiece();
            case 3: return new Lpiece();
            case 4: return new Tpiece();
            case 5: return new Zpiece();
            case 6: return new Jpiece();
            case 7: return new Spiece();
            default: return new Ipiece();
        }
    }

    /**
     * Função principal do jogo que contém o loop de execução.
     * CORRIGIDO: Aceita "GameWindow W" em vez de "Window W"
     */
    public static void run(Board B, GameWindow W) throws IOException {
        long tickCounter = 0;

        B.setNextPiece(spawnRandomPiece());
        
        while (true) {
            if (B.getFallingPiece() == null) {
                
                Piece P = B.getNextPiece();
                B.setFallingPiece(P);
                B.setNextPiece(spawnRandomPiece());

                if (checkCollision(P, B, 0, 0, P.getForm())) {
                    clearScreen();
                    W.update_Widow(B);
                    break; 
                }
            }
            
            Piece P = B.getFallingPiece();

            if (tickCounter % GRAVITY_TICK_RATE == 0) {
                if (checkCollisionUnder(P, B)) {
                    B.updateLanded();
                    
                    if (P.getY() <= 0) {
                        clearScreen();
                        B.update();
                        W.update_Widow(B);
                        break; 
                    }
                    
                    B.clearPiece();
                }
                else {
                    B.clear();
                    P.addY(1);
                    B.update();
                }
            }

            clearScreen();
            System.out.println(B);
            W.update_Widow(B);

            int linesClearedCount = 0;
            for (int i = B.getHeight() - 1; i >= 0; i--) {
                if (checkRowCompletion(B, i)) {
                    B.deleteLine(i);
                    linesClearedCount++;
                    i++; 
                }
            }
            
            if (linesClearedCount == 1)
                B.addScore(SINGLE_LINE_SCORE);
            else if(linesClearedCount == 2)
                B.addScore(DOUBLE_LINE_SCORE);
            else if (linesClearedCount == 3)
                B.addScore(TRIPLE_LINE_SCORE);
            else if (linesClearedCount == 4)
                B.addScore(TETRIS_SCORE);


            tickCounter++;
            try {
                Thread.sleep(DELTA_TIME_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Game Loop Interrompido.");
                break;
            }
        }
    }
    
    //
    // O resto das funções (Colisão, Rotação, Movimento) permanecem iguais
    //
    private static boolean checkCollision(Piece P, Board B, int dx, int dy, Color[][] form) {
        int piece_height = form.length;
        if (piece_height == 0) return false;
        int piece_width = form[0].length;
        if (piece_width == 0) return false;
        
        int next_y = P.getY() + dy;
        int next_x = P.getX() + dx;

        for (int i = 0; i < piece_height; i++) {
            for (int j = 0; j < piece_width; j++) {
                if (form[i][j] != Color.WHITE) {
                    int target_y = next_y + i;
                    int target_x = next_x + j;

                    if (target_y >= B.getHeight() || target_x < 0 || target_x >= B.getWidth())
                        return true;

                    if (target_y >= 0 && B.getLanded()[target_y][target_x] != Color.WHITE)
                        return true;
                }
            }
        }
        return false;
    }
    
    public static boolean checkCollisionUnder(Piece P, Board B) {
        return checkCollision(P, B, 0, 1, P.getForm());
    }
    
    public static boolean checkCollisionLeft(Piece P, Board B) {
        return checkCollision(P, B, -1, 0, P.getForm());
    }

    public static boolean checkCollisionRight(Piece P, Board B) {
        return checkCollision(P, B, 1, 0, P.getForm());
    }


    public static void rotate(Board B) {
        Piece P = B.getFallingPiece();
        if (P == null) return;
        
        int originalX = P.getX();
        P.rotateClockWise();

        if (P.getX() + P.getWidth() > B.getWidth()) {
            P.setX(B.getWidth() - P.getWidth());
        }
        if (P.getX() < 0) {
            P.setX(0);
        }

        if (checkCollision(P, B, 0, 0, P.getForm())) {
            P.rotateCounterClockWise();
            P.setX(originalX);
        } else {
            B.clear();
            B.update();
        }
    }
    
    public static void moveLeft(Board B, Piece P) {
        if (P == null) return;
        if (!checkCollisionLeft(P, B)) {
            B.clear();
            P.moveLeft();
            B.update();
        }
    }

    public static void moveRight(Board B, Piece P) {
        if (P == null) return;
        if (!checkCollisionRight(P, B)) {
            B.clear();
            P.moveRight();
            B.update();
        }
    }

    public static void dropDown(Board B, Piece P) {
        if (P == null) return;
        int dropBonus = 0;
        while (!checkCollisionUnder(P, B)) {
            B.clear();
            P.addY(1);
            B.update();
            dropBonus++;
        }
        B.addScore(Math.min(dropBonus, DROP_SCORE * 5));
    }

    public static boolean checkRowCompletion(Board B, int y) {
        for (int x = 0; x < B.getWidth(); x++) {
            if (B.getLanded()[y][x] == Color.WHITE)
                return false;
        }
        return true;
    }
}