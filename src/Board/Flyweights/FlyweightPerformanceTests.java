package Board.Flyweights;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Linas
 */
public class FlyweightPerformanceTests extends JFrame implements ActionListener {

    JPanel drawingPanel;
    int windowWidth = 700;
    int windowHeight = 500;

    final int counter = 50000;

    String[] flyweightImages = {"images/bomber_fixed.png", "images/bomb1.png", "images/bomber_enemy.png"};
    private static final FlyweightImageKeys[] VALUES = FlyweightImageKeys.values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static FlyweightImageKeys getRandomFlyweightKey() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }

    public FlyweightPerformanceTests() {

        setSize(windowWidth, windowHeight);
        setTitle("Flyweight design pattern");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        drawingPanel = new JPanel();
        drawingPanel.setBackground(Color.WHITE);
        add(drawingPanel, BorderLayout.CENTER);

        JButton button = new JButton("Press on me");
        button.addActionListener(this);
        add(button, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        FlyweightPerformanceTests w = new FlyweightPerformanceTests();
        w.setVisible(true);
    }

    public int getRandomX() {
        return (int) (Math.random() * (windowWidth - 100));
    }

    public int getRandomY() {
        return (int) (Math.random() * (windowHeight - 100));
    }

    public String getRandomFlyweightImage() {
        Random random = new Random();
        int index = random.nextInt(flyweightImages.length);
        return flyweightImages[index];
    }

    public FlyweightImage getRandomFlyweightClass() {
        FlyweightImage f = null;
        FlyweightImageKeys fk = getRandomFlyweightKey();

        switch (fk) {
            case BigBomb:
                f = new BigBombImage();
                break;
            case SmallBomb:
                f = new BigBombImage();
                break;
            case Player:
                f = new PlayerBaseImage();
                break;
            case Enemy:
                f = new EnemyImage();
                break;
        }
        return f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Graphics g = drawingPanel.getGraphics();
        /*
        drawSingleSprite();
        
        g.clearRect(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());
        Runtime.getRuntime().gc();
         */
        drawManySprites();
        g.clearRect(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());
        Runtime.getRuntime().gc();

        drawManyClassSprites();
        Runtime.getRuntime().gc();
        g.clearRect(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());

        drawManyFactorySprites();
        Runtime.getRuntime().gc();
        g.clearRect(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());

    }

    public void drawSingleSprite() {
        Graphics g = drawingPanel.getGraphics();
        String fileName = getRandomFlyweightImage();
        Image image = new ImageIcon(fileName).getImage();
        g.drawImage(image, getRandomX(), getRandomY(), null);
    }

    public void drawManySprites() {
        long startTime = System.currentTimeMillis();
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        for (int i = 0; i < counter; i++) {
            drawSingleSprite();
        }

        long endTime = System.currentTimeMillis();
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long KB = (afterUsedMem - beforeUsedMem) >> 10;
        long MB = KB >> 10;
        System.out.println("Simple sprite draw took: " + (endTime - startTime) + "ms");
        System.out.println("Simple sprite memory utilized: " + (afterUsedMem - beforeUsedMem) + "bytes | " + MB + " MB");
    }

    public void drawManyClassSprites() {
        System.out.println("");
        long startTime = System.currentTimeMillis();
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        for (int i = 0; i < counter; i++) {
            FlyweightImage fw = getRandomFlyweightClass();
            moveSprite(fw, this.drawingPanel, getRandomX(), getRandomY());
        }
        long endTime = System.currentTimeMillis();
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long KB = (afterUsedMem - beforeUsedMem) >> 10;
        long MB = KB >> 10;
        System.out.println("Class sprites draw took: " + (endTime - startTime) + "ms");
        System.out.println("Class sprite memory utilized: " + (afterUsedMem - beforeUsedMem) + "bytes | " + MB + " MB");
    }

    public void drawManyFactorySprites() {
        System.out.println("");
        long startTime = System.currentTimeMillis();
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        Graphics g = drawingPanel.getGraphics();

        for (int i = 0; i < counter; i++) {
            FlyweightImage fw = FlyweightImageFactory.getFlyweightImage(getRandomFlyweightKey());
            moveSprite(fw, this.drawingPanel, getRandomX(), getRandomY());
        }

        long endTime = System.currentTimeMillis();
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long KB = (afterUsedMem - beforeUsedMem) >> 10;
        long MB = KB >> 10;
        System.out.println("Flyweight Factory sprites draw took: " + (endTime - startTime) + "ms");
        System.out.println("FlyWeight sprite memory utilized: " + (afterUsedMem - beforeUsedMem) + "bytes | " + MB + " MB");
        System.out.println("");
    }

    public void moveSprite(FlyweightImage fw, JPanel dp, int posX, int posY) {
        Graphics g = drawingPanel.getGraphics();
        //g.clearRect(0, 0, dp.getWidth(), dp.getHeight());
        g.drawImage(fw.getImage(), posX, posY, null);
    }

}
