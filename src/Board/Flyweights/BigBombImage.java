
package Board.Flyweights;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Linas
 */
public class BigBombImage implements FlyweightImage {

    private ImageIcon imageIcon;

    public BigBombImage() {
        this.imageIcon = new ImageIcon("images/bomb1.png");
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
