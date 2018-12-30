import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Interrupting {
private static ExecutorService executorService = Executors.newCachedThreadPool();


static  void  test(Runnable runnable) throws InterruptedException{
    Future<?> f = executorService.submit(runnable);

    TimeUnit.MILLISECONDS.sleep(100);
    System.out.println("Interrupting  "+runnable.getClass().getName());
    f.cancel(true);
    System.out.println(" interrupt  sent to "+runnable.getClass().getName());
}

public static void main(String[] args) throws InterruptedException{
    test(new SleepBlocked());
    test(new IOBlocked(System.in));
    test(new SynchronizedBlocked());

    TimeUnit.SECONDS.sleep(300);

    System.out.println("Aborting with System.exit(0)");
    System.exit(0);

}

}

class SleepBlocked implements Runnable{


    @Override
    public void run() {

        try {
            TimeUnit.SECONDS.sleep(100);
            System.out.println("111111111111111111111111111 ");
        } catch (InterruptedException e) {
            System.out.println(" InterroptingExcwption");
        }
        System.out.println("exiting sleepBlocked.run()");

    }
}


class IOBlocked implements Runnable{

    private InputStream in;

    public IOBlocked(InputStream is){
        in=is;
    }
    @Override
    public void run() {
        System.out.println("waiting for read()");
        try {
            in.read();
        } catch (IOException e) {
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interruptrd from blocked I/O");
            }else{
                throw  new RuntimeException();
            }
        }
        System.out.println("exting I/OBlocked.run()");
    }


}


class SynchronizedBlocked implements  Runnable{
    public  synchronized  void f(){
        while(true){
            Thread.yield();
        }
    }
    public SynchronizedBlocked(){
        new Thread(){
            @Override
            public void run() {
               f();
            }
        }.start();
    }
    @Override
    public void run() {
        System.out.println("Trying to call f()");
        f();
        System.out.println("Exting Synchronied.run()");
    }
}
