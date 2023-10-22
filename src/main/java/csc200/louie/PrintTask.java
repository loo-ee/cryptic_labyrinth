package csc200.louie;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

class PrintTask extends TimerTask {
    private AtomicBoolean isTimeUp;

    public PrintTask(AtomicBoolean isTimeUp) {
        this.isTimeUp = isTimeUp;
    }
    public void run() {
        System.out.println("Timer is up! Will not accept answer for this question. Press anything to continue...");
        this.isTimeUp.set(true);
    }
}