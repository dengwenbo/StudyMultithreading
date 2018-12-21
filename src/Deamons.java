import java.util.concurrent.TimeUnit;

public class Deamons {


    public static void main(String[] args){

        Thread t = new Thread(new deamon());
             t.setDaemon(true);
             t.start();
        System.out.println("t isdeamon()的值为"+t.isDaemon());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("The Thread is interrupted");
            e.printStackTrace();
        }
    }

}
class deamon implements  Runnable{

    private Thread[]  threadArray = new Thread[10];

    @Override
    public void run() {

        for (int i=0;i<threadArray.length;i++){
            threadArray[i] = new Thread(new deamonSpawn());
            threadArray[i].start();
            System.out.println("the deamonSpawn "+i+"is start");
        }
        for (int j=0;j<threadArray.length;j++){
            System.out.println("threadArray["+j+"].isDeamon()的值为"+threadArray[j].isDaemon());
            Thread.yield();;
        }
    }
}

class deamonSpawn implements  Runnable{
    @Override
    public void run() {
        while (true){
            Thread.yield();
        }
    }
}