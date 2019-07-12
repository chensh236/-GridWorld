import imagereader.IImagePorcessor;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSouce;
import java.awt.image.RGBImageFilter;
//0 red
//1 green
//2 blue
public class ImplementImageProcessor implements IImagePorcessor{
    public Image showChanelR(Image src){
        ColorFilter redFilter = new ColorFilter(0);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.createImage(new FilteredImageSource(src.getSource(), redFilter));
        return img;
    }

    public Image showChanelG(Image src){
        ColorFilter redFilter = new ColorFilter(1);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.createImage(new FilteredImageSource(src.getSource(), redFilter));
        return img;
    }

    public Image showChanelB(Image src){
        ColorFilter redFilter = new ColorFilter(2);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.createImage(new FilteredImageSource(src.getSource(), redFilter));
        return img;
    }
}

class colorFilter extends RGBImageFilter{
    private int attri;

    public void colorFilter(int src){
        this.attri = src;
        canFilterIndexColorModel = true;
    }

    public int filterRGB(int x, int y, int rgb){
        switch(attri){
            case 0: return (rgb & 0xffff0000);
            case 1: return (rgb & 0xff00ff00);
            case 2: return (rgb & 0xff0000ff);
            default:
                int l = (int)((rgb & 0x00ff0000)/*red*/ >> 16) * 0.299 +
                ((rgb & 0x0000ff00)/*green*/ >> 8) * 0.587 +
                ((rgb & 0x000000ff)/*blue*/) * 0.114;
                int tmp = (l << 16 | l << 8 | l);
                return (tmp | 0xff000000); 
        }
    }


}