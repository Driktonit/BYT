package MEMENTO;

public class Main {
    public static void main(String[] args) {
        VideoPlayer vp = new VideoPlayer("urlurlurl");
        Caretaker ct = new Caretaker(vp);
        System.out.println(vp);
        ct.save();

        vp.setPosition(0.67);
        vp.setVolume(0.8);
        System.out.println(vp);
        ct.save();

        vp.setPosition(0.9);
        vp.setVolume(0.1);
        vp.setPaused(false);

        System.out.println(vp);

        ct.undo();
        System.out.println(vp);
        ct.undo();
        System.out.println(vp);
        ct.undo();
    }
}
