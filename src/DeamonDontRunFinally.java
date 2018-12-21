
import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;

import  static  net.mindview.util.Print.*;


public class DeamonDontRunFinally {

    public static void main(String[] args){
        Thread thread  = new Thread(new Adeamon());
        //thread.setDaemon(true);
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("先走完mian进程吗");
    }

}


class Adeamon implements  Runnable{
    @Override
    public void run() {
        print("Adeamon is start");
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("终于走到了finally中来了");
        }
    }
}