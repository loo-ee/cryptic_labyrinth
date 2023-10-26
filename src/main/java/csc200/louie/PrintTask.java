package csc200.louie;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

class PrintTask extends TimerTask {
    private static AtomicBoolean isTimeUp;

    public PrintTask(AtomicBoolean isTimeUp) {
        PrintTask.isTimeUp = isTimeUp;
    }
    public void run() {
        System.out.println("Timer is up! Will not accept answer for this question. Press anything to continue...");
        PrintTask.isTimeUp.set(true);
    }
}