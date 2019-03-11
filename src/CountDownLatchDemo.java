import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    static  final int SIZE = 100;
    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();

        CountDownLatch latch  = new CountDownLatch(SIZE);

        for(int i = 0; i<10 ;i++){
            service.execute(new WaitingTask(latch));
        }

        for (int i = 0 ; i<SIZE ;i++){
            service.execute(new TaskPortion(latch));
        }

        System.out.println("Launched all tasks");

        service.shutdown();

    }
}

class TaskPortion implements  Runnable{


    private static  int counter = 0;

    private final int id = counter++;

    private static Random rand = new Random(47);


    private final CountDownLatch latch;

    TaskPortion(CountDownLatch latch){
        this.latch=latch;
    }
    @Override
    public void run() {
        try{
            doWork();
            latch.countDown();
        }catch( InterruptedException e){

        }
    }

    public void doWork() throws InterruptedException{
        TimeUnit.SECONDS.sleep(1);
        System.out.println(this + "compelted");
    }

    public  String toString(){
        return  String.format("%1$-3d",id);
    }
}


class WaitingTask implements  Runnable{

    private static  int counter = 0;

    private static  int id =counter++;

    private CountDownLatch latch;

    WaitingTask(CountDownLatch latch ){
        this.latch = latch;
    }


    @Override
    public void run() {
        try {
            System.out.println("任务初始化");
            latch.await();
            System.out.println("任务执行结束");
        }catch (InterruptedException e){
            System.out.println( this+ "  interrupted");
        }
    }


    public  String toString(){
        return String.format("WatingTask %1$ -3d", id);
    }
}