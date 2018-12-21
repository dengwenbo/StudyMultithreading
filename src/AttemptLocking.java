import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {


    private Lock lock = new ReentrantLock();

    public void unTimed(){
        boolean captured = lock.tryLock();

      try{
          System.out.println("tryLock() result is "+captured);
      }finally {
          if(captured){
              lock.unlock();
          }
      }
    }

    public void Timed(){
        boolean captured =false;
       try {
           captured = lock.tryLock(2, TimeUnit.SECONDS);
       }catch(InterruptedException e){
           throw new RuntimeException(e);
       }
        try{
            System.out.println("lock.tryLock(2, TimeUnit.SECONDS)" + captured);
        }finally {
           if(captured){
               lock.unlock();
           }
        }

    }

    public static void main(String[] args){

      final AttemptLocking al = new AttemptLocking();

      al.unTimed();
      al.Timed();
      new Thread(){
          {setDaemon(true);}

          @Override
          public void run() {
             al.lock.lock();
              System.out.println("acquired");
          }
      }.start();
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        al.unTimed();
      al.Timed();
    }
}
