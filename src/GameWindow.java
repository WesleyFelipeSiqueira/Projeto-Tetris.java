import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;

import Matrix.Board.Board;
import Visuals.Block;
import Visuals.ExitButton;
import Visuals.JPanelWithBackground;
import Visuals.NextPiecePanel;
import Visuals.RankingLabel;
import Visuals.Score;

public class GameWindow { 
    protected JFrame Game_Window ;
    protected JPanel Game_Board;
    protected JPanelWithBackground Info_Panel;
    
    protected Score Score;
    protected JLabel PlayerNameLabel; 
    protected ExitButton Exit;
    protected NextPiecePanel nextPiecePanel; 
    protected JLabel rankingTitle; 
    protected JLabel rank1, rank2, rank3; 
    
    protected List<Block> Blocks = new ArrayList<Block>();
    
    public GameWindow(Board B) throws IOException, FontFormatException {
        
        Game_Window = new JFrame("Tetris");
        Game_Window.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));
        Game_Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // --- TELA CHEIA (Como pediu) ---
        Game_Window.setExtendedState(JFrame.MAXIMIZED_BOTH); 

        Game_Board = new JPanel();
        final int BLOCK_SIZE = 40; 
        Game_Board.setPreferredSize(new Dimension(BLOCK_SIZE * B.getWidth(), BLOCK_SIZE * B.getHeight()));
        Game_Board.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));
        
        for (int i = 0; i < B.getHeight(); i++) {
            for (int j = 0; j < B.getWidth(); j++) {
                Block Block = new Block(B.getForm()[i][j]); 
                Blocks.add(Block);
                Game_Board.add(Block.getBlock());
            }
        }

        // --- FUNDO DO PAINEL LATERAL ---
        Info_Panel = new JPanelWithBackground("/Visuals/Background_Elements/IMG.jpg");
        
        Info_Panel.setPreferredSize(new Dimension(400, BLOCK_SIZE * B.getHeight()));
        SpringLayout springLayout = new SpringLayout();
        Info_Panel.setLayout(springLayout);
        
        nextPiecePanel = new NextPiecePanel();
        Info_Panel.add(nextPiecePanel);
        springLayout.putConstraint(SpringLayout.NORTH, nextPiecePanel, 100, SpringLayout.NORTH, Info_Panel);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nextPiecePanel, 0, SpringLayout.HORIZONTAL_CENTER, Info_Panel);
        
        PlayerNameLabel = new Score(); 
        PlayerNameLabel.setText(B.getPlayerName()); 
        Info_Panel.add(PlayerNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, PlayerNameLabel, 300, SpringLayout.NORTH, Info_Panel);
        springLayout.putConstraint(SpringLayout.WEST, PlayerNameLabel, 40, SpringLayout.WEST, Info_Panel);

        Score = new Score(); 
        Info_Panel.add(Score);
        springLayout.putConstraint(SpringLayout.NORTH, Score, 400, SpringLayout.NORTH, Info_Panel);
        springLayout.putConstraint(SpringLayout.WEST, Score, 40, SpringLayout.WEST, Info_Panel);
        
        rankingTitle = new RankingLabel(); 
        rankingTitle.setText("TOP 3 RANKING");
        Info_Panel.add(rankingTitle);
        springLayout.putConstraint(SpringLayout.NORTH, rankingTitle, 500, SpringLayout.NORTH, Info_Panel);
        springLayout.putConstraint(SpringLayout.WEST, rankingTitle, 40, SpringLayout.WEST, Info_Panel);
        
        // --- CORREÇÃO DO ERRO DO RANKING ---
        List<Ranking.ScoreEntry> topScores = Ranking.loadRanking();
        
        rank1 = new RankingLabel(); 
        rank1.setText("1. -----");
        if (topScores.size() > 0) rank1.setText("1. " + topScores.get(0).toString());
        Info_Panel.add(rank1);
        springLayout.putConstraint(SpringLayout.NORTH, rank1, 540, SpringLayout.NORTH, Info_Panel); 
        springLayout.putConstraint(SpringLayout.WEST, rank1, 40, SpringLayout.WEST, Info_Panel);

        rank2 = new RankingLabel(); 
        rank2.setText("2. -----");
        if (topScores.size() > 1) rank2.setText("2. " + topScores.get(1).toString());
        Info_Panel.add(rank2);
        springLayout.putConstraint(SpringLayout.NORTH, rank2, 570, SpringLayout.NORTH, Info_Panel); 
        springLayout.putConstraint(SpringLayout.WEST, rank2, 40, SpringLayout.WEST, Info_Panel);
        
        rank3 = new RankingLabel(); 
        rank3.setText("3. -----");
        if (topScores.size() > 2) rank3.setText("3. " + topScores.get(2).toString());
        Info_Panel.add(rank3);
        springLayout.putConstraint(SpringLayout.NORTH, rank3, 600, SpringLayout.NORTH, Info_Panel); 
        springLayout.putConstraint(SpringLayout.WEST, rank3, 40, SpringLayout.WEST, Info_Panel);

        Exit = new ExitButton(Game_Window);
        Info_Panel.add(Exit);
        springLayout.putConstraint(SpringLayout.NORTH, Exit, 40, SpringLayout.NORTH, Info_Panel);
        springLayout.putConstraint(SpringLayout.WEST, Exit, 320, SpringLayout.WEST, Info_Panel);

        Game_Window.getContentPane().add(Game_Board);
        Game_Window.getContentPane().add(Info_Panel);
        Game_Window.pack(); 
        Game_Window.setVisible(true); 

        InputMap inputMap = Game_Board.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = Game_Board.getActionMap();

        Action LEFT_MOVE = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Engine.moveLeft(B, B.getFallingPiece());
            }
        };
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "LEFT_MOVE");
        actionMap.put("LEFT_MOVE", LEFT_MOVE);
        
        Action RIGHT_MOVE = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Engine.moveRight(B, B.getFallingPiece());
            }
        };
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "RIGHT_MOVE");
        actionMap.put("RIGHT_MOVE", RIGHT_MOVE);
        
        Action DROP_DOWN = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Engine.dropDown(B, B.getFallingPiece());
            }
        };
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "DROP_DOWN");
        actionMap.put("DROP_DOWN", DROP_DOWN);
        
        // --- ALTERAÇÃO DA TECLA DE ROTAÇÃO ---
        Action ROTATE = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Engine.rotate(B);
            }
        };
        // inputMap.put(KeyStroke.getKeyStroke("SPACE"), "ROTATE"); // <-- REMOVIDO
        // inputMap.put(KeyStroke.getKeyStroke("BACK_SPACE"), "ROTATE"); // <-- REMOVIDO
        inputMap.put(KeyStroke.getKeyStroke("UP"), "ROTATE"); // <-- ADICIONADO: Seta para Cima
        
        actionMap.put("ROTATE", ROTATE);
    
        Game_Board.setFocusable(true);
        Game_Board.requestFocusInWindow();
    }

    public void update_Widow(Board B) throws IOException {
        for (int i = 0; i < B.getHeight(); i++) {
            for (int j = 0; j < B.getWidth(); j++) {
                Blocks.get(i*B.getWidth() + j).UpdateBlock(B.getForm()[i][j]);
            }
        }
        
        if (nextPiecePanel != null) {
            nextPiecePanel.updatePreview(B.getNextPiece());
        }
        
        Score.updateScore(B);
        GameLogger.gameLog(B.getLanded());
        GameLogger.gameScoreLog(B.getScore());
        
        Game_Board.revalidate();
        Game_Board.repaint();
    }
}