
package Board.Flyweights;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Linas
 */
public class EnemyImage implements FlyweightImage {

    private ImageIcon imageIcon;

    public EnemyImage() {
        this.imageIcon = new ImageIcon("images/bomber_enemy.png");
    }

    @Override
    public Image getImage() {
        return imageIcon.getImage();
    }

    @Override
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

}
