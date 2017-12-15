package pruebas.hernan.com.tdd;

import java.util.ArrayList;
import java.util.List;

class Game implements FrameVisitor {
    private int total = 0;

    private List<Shot> shots;
    private List<Frame> frames;

    Game() {
        this.shots = new ArrayList<>();
        this.frames = new ArrayList<>();
    }

    void addShot(Shot shot) {
        shots.add(shot);
    }

    void addFrame(Frame frame) {
        shots.addAll(frame.shots);
        frames.add(frame);
    }

    int getTotal() {
        for (Frame frame : frames) {
            frame.visit(this);
        }
        return total;
    }

    @Override
    public void accept(Spare spare) {
        int index = shots.indexOf(spare.shots.get(spare.shots.size() - 1));
        total += spare.getScore() + shots.get(index + 1).score;
    }

    @Override
    public void accept(Strike strike) {
        int index = shots.indexOf(strike.shots.get(0));
        total += strike.getScore() + shots.get(index + 1).score + shots.get(index + 2).score;
    }

    @Override
    public void accept(Frame frame) {
        total += frame.getScore();
    }
}
