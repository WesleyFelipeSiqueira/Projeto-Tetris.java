package Visuals; 

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Matrix.Color; 

public class Block extends JLabel {
    protected JLabel Block;

    // --- FUNDO PRETO (Corrigido) ---
    protected ImageIcon Game_Background = loadIcon("/Visuals/Block_Icons/Black_Background.png");

    // Ícones das peças
    protected ImageIcon Blue_Block = loadIcon("/Visuals/Block_Icons/Blue_Block.png");
    protected ImageIcon Yellow_Block = loadIcon("/Visuals/Block_Icons/Yellow_Block.png");
    protected ImageIcon Green_Block = loadIcon("/Visuals/Block_Icons/Green_Block.png");
    protected ImageIcon Red_Block = loadIcon("/Visuals/Block_Icons/Red_Block.png");
    protected ImageIcon Orange_Block = loadIcon("/Visuals/Block_Icons/Orange_Block.png");
    protected ImageIcon Pink_Block = loadIcon("/Visuals/Block_Icons/Pink_Block.png");
    protected ImageIcon Cyan_Block = loadIcon("/Visuals/Block_Icons/Cyan_Block.png");
    
    private ImageIcon loadIcon(String path) {
        URL imgUrl = getClass().getResource(path);
        if (imgUrl != null) {
            return new ImageIcon(imgUrl);
        } else {
            System.err.println("Erro ao carregar recurso: " + path);
            return null;
        }
    }
    
    // --- CONSTRUTOR CORRIGIDO ---
    public Block(Color c) {
        // Começa sempre com o fundo preto
        Block = new JLabel(Game_Background); 
        
        // Se não for um espaço vazio, desenha a cor da peça por cima
        if (c != Color.WHITE) {
            UpdateBlock(c);
        }
    }

    public JLabel getBlock() {
        return Block;
    }

    // --- UPDATEBLOCK CORRIGIDO ---
    public void UpdateBlock(Color c) {
        if (c == Color.BLUE)
            Block.setIcon(Blue_Block);
        else if (c == Color.YELLOW)
            Block.setIcon(Yellow_Block);
        else if (c == Color.GREEN)
            Block.setIcon(Green_Block);
        else if (c == Color.RED)
            Block.setIcon(Red_Block);
        else if (c == Color.ORANGE)
            Block.setIcon(Orange_Block);
        else if (c == Color.PINK)
            Block.setIcon(Pink_Block);
        else if (c == Color.CYAN)
            Block.setIcon(Cyan_Block);
        else // (c == Color.WHITE)
            Block.setIcon(Game_Background); // <-- USA O FUNDO PRETO
    }
}