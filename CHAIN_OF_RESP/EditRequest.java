package CHAIN_OF_RESP;

import java.util.Random;

public class EditRequest {
    public final EditType type;
    public Image im;
    public EditRequest(Image im, EditType type){
        this.type = type;
        this.im = im;
    }
}
class Image{
    public String url;
    public int width;
    public int height;
    public int[][] pixels;
    public double rotationAngle;
    public Image(String url){
        //assume its loaded from the url
        // but here we assign values randomly
        this.url = url;
        this.width = new Random().nextInt(1920);
        this.height = new Random().nextInt(1920);
        this.pixels = new int[this.height][this.width];
        this.rotationAngle = 2*new Random().nextDouble()*Math.PI;
    }

    public Image copy(){
        Image m = new Image(this.url);
        m.width = this.width;
        m.height = this.height;
        m.rotationAngle = this.rotationAngle;
        m.pixels = this.pixels.clone();

        return m;
    }
}
enum EditType{
    START,
    RESIZE,
    GRAYSCALE,
    ROTATE,
    ADD_BORDER,
    FINISH
}