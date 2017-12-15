package pruebas.hernan.com.tdd;

import java.util.Arrays;
import java.util.List;

class Frame implements FrameVisitable{
    List<Shot> shots;

    Frame(Shot... shots) {
        this.shots = Arrays.asList(shots);
    }

    @Override
    public void visit(FrameVisitor visitor) {
        visitor.accept(this);
    }

    public int getScore(){
        int score = 0;
        for( Shot shot : shots){
            score += shot.score;
        }
        return score;
    }
}
