package Chess;

import javax.swing.*;
import java.sql.Time;

public class Timer implements Runnable {
    int seconds = 0;
    Boolean isRunning = true;
    JLabel timeMonitor;

    public Timer(JLabel timeMonitor) {
        this.timeMonitor = timeMonitor;
    }

    public int getSeconds() {
        return seconds;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                while (isRunning) {
                    timeMonitor.setText("Time: " + seconds);
                    this.wait(1000);
                    seconds++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
