

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NotifyVsNotifyAll {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task2());
        }
        executorService.execute(new Task2());

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            boolean prod = true;

            @Override
            public void run() {
                if (prod) {
                    System.out.println("notify()");
                    Task1.blocker.prod();
                    prod = false;
                } else {
                    System.out.println("notifyall()");
                    Task1.blocker.prodAll();
                    prod = true;
                }
            }
        }, 400, 400);

         /*;
            @Override
            public void run(){

            }*/

        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("timer cancled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("Task2.block2,prodAll()");
        Task2.blocker.prodAll();
        System.out.println("Shutting dowm");
        executorService.shutdownNow();

    }
}
class Blocker{


    synchronized  void WaitingCall(){
        try{
            while(!Thread.interrupted()){
                wait();
                System.out.println(Thread.currentThread()+"");
            }
        }catch(InterruptedException e){
            System.out.println("第一个线程出现异常");
        }
    }

    synchronized void prod(){
        notify();
    }

    synchronized void prodAll(){
        notifyAll();
    }
}

class  Task2 implements  Runnable{
    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.WaitingCall();
    }
}

class  Task1 implements  Runnable{
    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.WaitingCall();
    }
}

