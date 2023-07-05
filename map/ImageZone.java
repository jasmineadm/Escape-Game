package map;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageZone {
    private final String imagePath;
    private BufferedImage image;
    
    public ImageZone(String imagePath) {
        this.imagePath = imagePath;
        loadImage();
    }
    
    private void loadImage() {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void displayImage() {
        JFrame frame = new JFrame();
        JLabel label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void displayImage(int width, int height) {
        // Redimensionnement de l'image
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // Mise à jour de l'icône du JLabel avec l'image redimensionnée
        JLabel label=new JLabel(new ImageIcon(scaledImage));
        // Création de la fenêtre pour afficher l'image
        JFrame frame = new JFrame();
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public  ImageZone[] imageParZone()
    {
    	ImageZone[] img= new ImageZone[10];
    	img[0]=new ImageZone("src/Cellule.jpg");
    	img[1]=new ImageZone("src/image.JPG");
    	img[2]=new ImageZone("src/image.JPG");
    	
		return img;
    	
    }
}
