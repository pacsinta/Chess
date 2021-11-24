package Chess;

public class Timer implements Runnable{
    int seconds = 0;
    @Override
    public void run() {
        try {
            wait(1000);
            seconds++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
