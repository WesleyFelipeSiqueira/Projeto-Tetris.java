package Visuals;

import java.awt.Graphics;
import java.awt.Image;
// import java.io.File; // Não é mais necessário
import java.io.IOException;
import java.net.URL; // Importar URL

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * JPanelWithBackground Class
 * Custom JPanel with a background.
 */
public class JPanelWithBackground extends JPanel {

    private final Image backgroundImage;

    public JPanelWithBackground(String fileName) throws IOException {
        // Alterado de new File() para getClass().getResource()
        // O 'fileName' deve começar com '/' (ex: "/Visuals/Background.png")
        URL imgUrl = getClass().getResource(fileName);
        if (imgUrl == null) {
            throw new IOException("Recurso não encontrado: " + fileName);
        }
        backgroundImage = ImageIO.read(imgUrl);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }
}