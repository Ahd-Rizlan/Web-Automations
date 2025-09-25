import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MouseMover {
    public static final int ONE_MINUTE = 60000;
    public static final int TWO_MINUTE = 120000;
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;

    public static void main(String... args) throws Exception {
        Robot robot = new Robot();
        Random random = new Random();
        while (true) {
            robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
            Thread.sleep(TWO_MINUTE);
        }
    }
}
