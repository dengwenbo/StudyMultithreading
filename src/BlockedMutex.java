import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedMutex {

    private Lock lock = new ReentrantLock();

    public BlockedMutex(){
       /* new Thread(){
            @Override
            public void run() {
              f();
            }
        }.start();*/
       lock.lock();
    }

    public   void f(){
        try {
            lock.lockInterruptibly();
            //Thread.sleep(50000);
            System.out.println("lock acquired in  f()");
        } catch (InterruptedException e) {
            System.out.println("Interrupted from lock acquisition in f()");
        }
    }
}

class Bolcked implements Runnable{

    private BlockedMutex blocked = new BlockedMutex();
    @Override
    public void run() {
        System.out.println("Waiting for f() in BlockedMutex");
        blocked.f();
        System.out.println("Broken out of blocked call");
    }
}
