package Visuals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * NOVO Ficheiro: Define o estilo para o Ranking (Fundo Preto, Letra Branca, Tamanho 20)
 */
public class RankingLabel extends JLabel {
    private static InputStream myStream = null;
    private static Font BaseFont = null;
    private static Font Ranking_Font = null; // Fonte do Ranking

    public RankingLabel() throws IOException, FontFormatException {
        super("...", SwingConstants.LEFT); // Alinhado à esquerda
        
        // Carrega a mesma fonte (só carrega a fonte base uma vez)
        if (BaseFont == null) { 
            // Carrega a fonte do classpath
            myStream = getClass().getResourceAsStream("/Visuals/Background_Elements/Px437_IBM_VGA_8x16.ttf");
            if (myStream == null) {
                throw new IOException("Recurso de fonte não encontrado: /Visuals/Background_Elements/Px437_IBM_VGA_8x16.ttf");
            }
            BaseFont = Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(myStream));
        }
        
        // --- MUDANÇAS DE ESTILO ---
        Ranking_Font = BaseFont.deriveFont(Font.PLAIN, 20); // 1. Tamanho Menor (20)
        this.setFont(Ranking_Font);
        this.setBackground(java.awt.Color.BLACK); // 2. Fundo Preto
        this.setForeground(java.awt.Color.WHITE); // 3. Letra Branca
        this.setOpaque(true); // Necessário para o fundo aparecer
        
        this.setPreferredSize(new Dimension(300, 30)); // Tamanho ajustado
        this.setBorder(BorderFactory.createLineBorder(new Color(82,82,82), 2)); // Borda fina
    }
    
    // Sobrescreve setText para garantir alinhamento
    @Override
    public void setText(String text) {
        super.setText(" " + text); // Adiciona um espaço para padding
    }
}