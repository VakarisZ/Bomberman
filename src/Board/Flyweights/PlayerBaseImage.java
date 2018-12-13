
package Board.Flyweights;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Linas
 */
public class PlayerBaseImage implements FlyweightImage {

    private ImageIcon imageIcon;

    public PlayerBaseImage() {
        this.imageIcon = new ImageIcon("images/bomber_fixed.png");
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
