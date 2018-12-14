
package Board.Flyweights;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Linas
 */
public class NullObjectImage implements FlyweightImage {

    private ImageIcon imageIcon;

    public NullObjectImage() {
        this.imageIcon = new ImageIcon("images/null_obj.png");
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
