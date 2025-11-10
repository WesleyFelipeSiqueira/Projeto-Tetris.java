import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JOptionPane; // <-- CORRIGIDO: Importação correta

import Matrix.Board.Board;
import Matrix.Pieces.Ipiece;
import Matrix.Pieces.Jpiece;
import Matrix.Pieces.Piece;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        
        // --- LOOP PRINCIPAL DO JOGO ---
        while (true) {
            // --- 1. CADASTRO (Perguntar o Nome) ---
            String playerName = JOptionPane.showInputDialog( // <-- CORRIGIDO
                    null, 
                    "Digite seu nome:", 
                    "Cadastro Tetris", 
                    JOptionPane.PLAIN_MESSAGE // <-- CORRIGIDO
            );

            if (playerName == null) {
                break; 
            }
            
            if (playerName.trim().isEmpty()) {
                playerName = "Player 1";
            }

            // --- 3. INICIAR O JOGO ---
            int height = 20, width = 10;
            Board B = new Board(height, width, playerName);
            
            // CORRIGIDO: Usa a nova classe "GameWindow"
            GameWindow Game = new GameWindow(B);
            
            System.out.println("Iniciando o jogo... Feche a janela para sair.");
            // CORRIGIDO: Passa "GameWindow" para o Engine.run
            Engine.run(B, Game);

            // --- 4. EXIBIR PONTUAÇÃO FINAL ---
            JOptionPane.showMessageDialog( // <-- CORRIGIDO
                    null, 
                    "FIM DE JOGO, " + B.getPlayerName() + "!\nSua pontuação final: " + B.getScore(),
                    "Game Over", 
                    JOptionPane.INFORMATION_MESSAGE // <-- CORRIGIDO
            );

            // --- 5. SALVAR NO RANKING ---
            Ranking.saveScore(B.getPlayerName(), B.getScore());

            // --- 6. LIMPAR A JANELA ANTIGA ---
            // CORRIGIDO: Acede ao JFrame 'Game_Window' dentro da classe 'Game'
            Game.Game_Window.dispose();
        }
        
        System.out.println("Jogo fechado. Obrigado por jogar!");
    }
    
    // Funções de demonstração (iguais a antes)
    public static void PieceMovements(Board B) {
        Piece p = new Jpiece();
        p.setX(5); p.setY(10);
        B.setFallingPiece(p);
        B.update(); System.out.println(B); B.clear();
        Engine.moveLeft(B, p);
        B.update(); System.out.println(B); B.clear();
        Engine.moveRight(B, p);
        B.update(); System.out.println(B); B.clear();
        Engine.rotate(B);
        B.update(); System.out.println(B); B.clear();
        Engine.dropDown(B, p);
        B.update(); System.out.println(B); B.updateLanded(); B.clearPiece();
    }
    
    public static void deletefullline(Board B){
        System.out.println(B);
        Piece I = new Ipiece();
        I.setX(0); I.setY(0); B.setFallingPiece(I);
        Engine.rotate(B); Engine.dropDown(B, I);
        B.updateLanded(); B.clearPiece();
        Piece J = new Jpiece();
        J.setX(4); J.setY(0); B.setFallingPiece(J);
        Engine.dropDown(B, J);
        B.updateLanded(); B.clearPiece();
        Piece M = new Ipiece();
        M.setX(6); M.setY(0); B.setFallingPiece(M);
        Engine.rotate(B); Engine.dropDown(B, M);
        B.update(); B.updateLanded(); B.clearPiece();
        System.out.println("ANTES DE APAGAR A LINHA:"); System.out.println(B);
        B.deleteLine(19);
        System.out.println("DEPOIS DE APAGAR A LINHA:"); System.out.println(B);
    }
}