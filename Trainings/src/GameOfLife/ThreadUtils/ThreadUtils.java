package GameOfLife.ThreadUtils;

public class ThreadUtils {
    public static void pauseThreadFor(int milliSeconds){
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
