package Visuals;

import java.awt.Dimension;
import java.net.URL; // Importar URL

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * ExitButton Class
 * Defines a JButton that exits the game.
 */
public class ExitButton extends JButton {

    public ExitButton(JFrame W) {
        super();
        
        // Alterado para getClass().getResource()
        URL iconUrl = getClass().getResource("/Visuals/Block_Icons/ExitButton.png");
        if (iconUrl != null) {
            this.setIcon(new ImageIcon(iconUrl));
        } else {
            this.setText("X"); // Texto de fallback se a imagem falhar
            System.err.println("Recurso não encontrado: /Visuals/Block_Icons/ExitButton.png");
        }
        
        this.setPreferredSize(new Dimension(40,40));
        this.addActionListener(e -> closeWindow(W));
    }

    /**
     * Closes the program.
     * @param W: Window of the game.
     */
    private void closeWindow(JFrame W) {
        W.dispose();
        System.exit(1);
    }
}