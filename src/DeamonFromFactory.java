import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeamonFromFactory implements Runnable {

    @Override
    public void run() {
        try {
                while(true) {
                        TimeUnit.MILLISECONDS.sleep(100);
                        System.out.println(Thread.currentThread() + " " + this);
                     }
            } catch (InterruptedException e) {
             System.out.println("the thread intrrupted");
            }

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<3;i++){
            DeamonThreadPoolExecutor exec  = new DeamonThreadPoolExecutor();
            exec.execute(new DeamonFromFactory());
        }
        System.out.println("all deamons thread already start");
        Thread.sleep(500);
    }



}
