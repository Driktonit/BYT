package MEMENTO;

import java.util.Random;
import java.util.Stack;

public class VideoPlayer {
    public double volume = 0; //0..1
    public double position = 0; //0..1
    public int length; //in seconds
    public boolean isPaused = true;

    public VideoPlayer(String url){
        //assume url is a url to video
        //length of the video will be calculated after importing the video
        //however in this case we set it to random

        this.length = new Random().nextInt(60000);

    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public Memento saveState(){
        return new Memento(this, volume,position,isPaused);
    }

    @Override
    public String toString() {
        return (isPaused?"[PAUSED] ":"[PLAYING] ")+"Videoplayer - "+ position*length + '/' + length + "s. (" + position*100+"%) with volume " + volume*100 + "%";
    }
}
class Memento implements MementoInterface {
    public final VideoPlayer vp;
    public final double volume; //0..1
    public final double position; //0..1
    public final boolean isPaused;

    public Memento(VideoPlayer vp, double volume, double position, boolean isPaused){
        this.vp = vp;
        this.volume = volume;
        this.position = position;
        this.isPaused = isPaused;
    }

    public void restoreState(){
        vp.position = this.position;
        vp.isPaused = this.isPaused;
        vp.volume = this.volume;
    }
}
class Caretaker{
    Stack<Memento> history;
    VideoPlayer vp;

    public Caretaker(VideoPlayer vp){
        this.vp = vp;
        this.history = new Stack<>();
    }
    public void undo(){
        if (!history.isEmpty()){
            history.pop().restoreState();
            System.out.println("UNDO!");
        }else{
            System.out.println("No more states to undo");
        }
    }
    public void save(){
        this.history.push(vp.saveState());
        System.out.println("SAVED!");
    }
}