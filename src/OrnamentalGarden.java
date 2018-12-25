import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrnamentalGarden {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();


        for(int i=0;i<5;i++){
            executorService.execute(new Entrance(i));
        }

     try {
         TimeUnit.SECONDS.sleep(3);

         Entrance.cancle();

         executorService.shutdown();


         if (!executorService.awaitTermination(250, TimeUnit.MILLISECONDS)) {
             System.out.println("some task were not terminated");
         }

         System.out.println("Total : " + Entrance.getTotalCount());
         System.out.println("sum of Entrances:" + Entrance.sumEntrances());
     }catch(InterruptedException e){
         System.out.println("为什么被打断了");
     }
    }
}

class Count {
    private  int count =0;
   //相同种子数的Random对象，对应相同次数生成的随机数字是完全相同的
    private Random rand = new Random(47);

    public synchronized  int increment(){
        int temp =count;
        if(rand.nextBoolean()){
            Thread.yield();
        }
        return (count=++temp);
    }

    public synchronized  int value(){
        return count;
    }
}


class Entrance implements Runnable{

    public static  Count count = new Count();

    private static List<Entrance> entrances = new ArrayList<Entrance>();

    private int number =0;

    private final int id;

    private static volatile boolean canceled =false;


    public static void cancle(){
        canceled =true;
    }

    public Entrance(int id){
        this.id =id;

        entrances.add(this);
    }
    @Override
    public void run() {
       while(!canceled){
           synchronized (this){
               number++;
           }

           System.out.println(this+"total:"+count.increment());

          try{
              TimeUnit.MILLISECONDS.sleep(100);
          }catch(InterruptedException e){
              System.out.println("sleep interRupted");
          }

       }
        System.out.println("stopping"+this);
    }


    public synchronized  int getValue(){
        return number;
    }

    @Override
    public String toString() {
        return "Entrance "+id+": "+getValue();
    }

    public static int getTotalCount(){
        return count.value();
    }

    public static int sumEntrances(){
        int sum = 0;

        for (Entrance entrance : entrances) {
            sum += entrance.getValue();
        }
        return sum;
       }
}
