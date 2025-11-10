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

import Matrix.Board.Board;

/**
 * Define o Score (usado para Nome e Pontuação)
 */
public class Score extends JLabel {
    private static InputStream myStream = null;
    private static Font BaseFont = null;
    private static Font Game_Font = null;

    public Score() throws IOException, FontFormatException {
        super("0", SwingConstants.CENTER); // <-- JÁ ESTÁ CENTRALIZADO
        
        if (BaseFont == null) {
            myStream = getClass().getResourceAsStream("/Visuals/Background_Elements/Px437_IBM_VGA_8x16.ttf");
            if (myStream == null) {
                throw new IOException("Recurso de fonte não encontrado: /Visuals/Background_Elements/Px437_IBM_VGA_8x16.ttf");
            }
            BaseFont = Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(myStream));
        }
        
        Game_Font = BaseFont.deriveFont(Font.PLAIN, 40); // Fonte grande
        this.setFont(Game_Font);
        
        // --- MUDANÇAS DE ESTILO (Como pediu) ---
        this.setBackground(java.awt.Color.BLACK); // 1. Fundo Preto
        this.setForeground(java.awt.Color.WHITE); // 2. Letra Branca
        this.setOpaque(true); // Necessário para o fundo aparecer
        
        this.setPreferredSize(new Dimension(300, 80)); // Aumentado para caber nomes
        this.setBorder(BorderFactory.createLineBorder(new Color(82,82,82), 5));
    }

    public void updateScore(Board B) {
        this.setText(Integer.toString(B.getScore()));
    }
}