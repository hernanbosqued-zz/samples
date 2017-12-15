package pruebas.hernan.com.tdd;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {

    @Before
    public void before(){

    }

    @Test
    public void mustGet60() throws Exception {
        Game game = new Game();

        game.addFrame(new Frame(new Shot(1),new Shot(2)));
        game.addFrame(new Frame(new Shot(3),new Shot(4)));
        game.addFrame(new Frame(new Shot(5),new Shot(1)));
        game.addFrame(new Frame(new Shot(2),new Shot(3)));
        game.addFrame(new Frame(new Shot(4),new Shot(5)));
        game.addFrame(new Frame(new Shot(1),new Shot(2)));
        game.addFrame(new Frame(new Shot(3),new Shot(4)));
        game.addFrame(new Frame(new Shot(5),new Shot(1)));
        game.addFrame(new Frame(new Shot(2),new Shot(3)));
        game.addFrame(new Frame(new Shot(4),new Shot(5)));

        assertEquals(60, game.getTotal());
    }

    @Test
    public void mustGet90() throws Exception {
        Game game = new Game();

        game.addFrame(new Frame(new Shot(9),new Shot(0)));
        game.addFrame(new Frame(new Shot(9),new Shot(0)));
        game.addFrame(new Frame(new Shot(9),new Shot(0)));
        game.addFrame(new Frame(new Shot(9),new Shot(0)));
        game.addFrame(new Frame(new Shot(9),new Shot(0)));
        game.addFrame(new Frame(new Shot(9),new Shot(0)));
        game.addFrame(new Frame(new Shot(9),new Shot(0)));
        game.addFrame(new Frame(new Shot(9),new Shot(0)));
        game.addFrame(new Frame(new Shot(9),new Shot(0)));
        game.addFrame(new Frame(new Shot(9),new Shot(0)));

        assertEquals(90, game.getTotal());
    }

    @Test
    public void mustGet190() throws Exception {
        Game game = new Game();

        game.addFrame(new Spare(new Shot(9),new Shot(1)));
        game.addFrame(new Spare(new Shot(9),new Shot(1)));
        game.addFrame(new Spare(new Shot(9),new Shot(1)));
        game.addFrame(new Spare(new Shot(9),new Shot(1)));
        game.addFrame(new Spare(new Shot(9),new Shot(1)));
        game.addFrame(new Spare(new Shot(9),new Shot(1)));
        game.addFrame(new Spare(new Shot(9),new Shot(1)));
        game.addFrame(new Spare(new Shot(9),new Shot(1)));
        game.addFrame(new Spare(new Shot(9),new Shot(1)));
        game.addFrame(new Spare(new Shot(9),new Shot(1)));

        //EXTRA SHOT
        game.addShot(new Shot(9));

        assertEquals(190, game.getTotal());
    }

    @Test
    public void mustGet300() throws Exception {
        Game game = new Game();

        game.addFrame(new Strike());
        game.addFrame(new Strike());
        game.addFrame(new Strike());
        game.addFrame(new Strike());
        game.addFrame(new Strike());
        game.addFrame(new Strike());
        game.addFrame(new Strike());
        game.addFrame(new Strike());
        game.addFrame(new Strike());
        game.addFrame(new Strike());

        //EXTRA SHOTS
        game.addShot(new Shot(10));
        game.addShot(new Shot(10));

        assertEquals(300, game.getTotal());
    }

    @After
    public void after(){

    }
}