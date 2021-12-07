package CHAIN_OF_RESP;

public class Main {
    public static void main(String[] args) {
        Image m = new Image("someurl");
        AbstractHandler start = new AbstractHandler(EditType.START) {
            @Override
            public void handle(EditRequest r) {
                this.apply(r);
                this.next_in_chain.handle(r);
            }

            @Override
            public void apply(EditRequest r) {
                //create a copy of the image for modificaition
                r.im = r.im.copy();
            }
        };
        AbstractHandler finish = new AbstractHandler(EditType.FINISH) {
            @Override
            public void apply(EditRequest r) {
                System.out.println("Modifications finished");
            }
        };

        AbstractHandler resize = new AbstractHandler(EditType.RESIZE) {
            @Override
            public void apply(EditRequest r) {
                //resizing algorithm here
                r.im.width = 256;
                r.im.height = 256;
                r.im.pixels = new int[256][256];
                System.out.println("Picture is now in 256x256 size");
            }
        };

        AbstractHandler gray = new AbstractHandler(EditType.GRAYSCALE) {
            @Override
            public void apply(EditRequest r) {
                //resizing algorithm here
                for (int i = 0; i < r.im.height; i++) {
                    for (int j = 0; j < r.im.width; j++) {
                        //grayscaling image here
                    }
                }
                System.out.println("Picture is now in grayscale");
            }
        };

        AbstractHandler border = new AbstractHandler(EditType.ADD_BORDER) {
            @Override
            public void apply(EditRequest r) {
                //border algorithm here

                System.out.println("Picture now has a border");
            }
        };

        AbstractHandler rotate = new AbstractHandler(EditType.ROTATE) {
            @Override
            public void apply(EditRequest r) {
                //rotate by x degreess here

                System.out.println("Picture has been rotated by 25 degrees");
            }
        };

        start.set_next(resize);
        resize.set_next(gray);
        gray.set_next(border);
        border.set_next(rotate);
        rotate.set_next(finish);

        //single request to resize
        EditRequest r1 = new EditRequest(m, EditType.RESIZE);
        start.handle(r1);
        System.out.println(r1.im.width);

        //two modifications after another
        EditRequest r2 = new EditRequest(m, EditType.GRAYSCALE);
        start.handle(r2);

        EditRequest r3 = new EditRequest(m, EditType.ADD_BORDER);
        start.handle(r3);
    }
}
