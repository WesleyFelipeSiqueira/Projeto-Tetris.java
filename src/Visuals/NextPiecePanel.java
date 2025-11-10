package Visuals;

// Importa 'java.awt.Color'
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Matrix.Pieces.Piece;

public class NextPiecePanel extends JPanel {
    
    private List<Block> previewBlocks = new ArrayList<>();
    private final int GRID_SIZE = 4; 

    public NextPiecePanel() {
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE, 0, 0));
        setPreferredSize(new Dimension(160, 160)); 
        
        // --- CORRIGIDO ---
        setBackground(java.awt.Color.BLACK); // Fundo preto

        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            Block b = new Block(Matrix.Color.WHITE); 
            previewBlocks.add(b);
            add(b.getBlock());
        }
    }

    public void updatePreview(Piece nextPiece) {
        for (Block b : previewBlocks) {
            b.UpdateBlock(Matrix.Color.WHITE);
        }

        if (nextPiece == null) return;

        Matrix.Color[][] form = nextPiece.getForm(); 
        int h = nextPiece.getHeight();
        int w = nextPiece.getWidth();

        int xOffset = (int) ((GRID_SIZE - w) / 2.0);
        int yOffset = (int) ((GRID_SIZE - h) / 2.0);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (form[i][j] != Matrix.Color.WHITE) {
                    int gridIndex = (i + yOffset) * GRID_SIZE + (j + xOffset);
                    if (gridIndex >= 0 && gridIndex < previewBlocks.size()) {
                        previewBlocks.get(gridIndex).UpdateBlock(form[i][j]);
                    }
                }
            }
        }
    }
}