import java.util.concurrent.TimeUnit;
import static net.mindview.util.Print.*;
public class SimpleDeamons implements Runnable{


    @Override
    public void run() {
        try {
                  while(true) {
                      TimeUnit.MILLISECONDS.sleep(100);
                      print(Thread.currentThread() + "" + this);
                  }
              } catch (InterruptedException e) {
                  e.printStackTrace();
                  System.out.println("sleep() interrupted");

              }

          }

    public static void main(String[] args) throws InterruptedException {

        for(int i=0;i<5 ;i++){
            SimpleDeamons simpleDeamons = new SimpleDeamons();
            Thread thread = new Thread(simpleDeamons);
            thread.setName("²âÊÔºó¶Ë½ø³Ì"+i);
            thread.setDaemon(true);
            thread.start();
        }
        System.out.println("all deamons start");
        TimeUnit.MILLISECONDS.sleep(175);
        
    }

}
