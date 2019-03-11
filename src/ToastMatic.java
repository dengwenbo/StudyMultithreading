import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ToastMatic {
    public static void main(String[] args) throws Exception{

        ToastQueue dryQueue = new ToastQueue(),
                   butterQueue = new ToastQueue(),
                   finishedQueue = new ToastQueue();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Toaster(dryQueue));
        executorService.execute(new Butterer(dryQueue,butterQueue));
        executorService.execute(new Jammer(butterQueue,finishedQueue));
        executorService.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
         executorService.shutdownNow();
    }

}

class Toast{

    public enum Status{DRY,BUTTERED,JAMMED}

    private Status status =Status.DRY;

    public final int id;

    public  Toast(int idn){id=idn;}

    public void butter(){status=Status.BUTTERED;}

    public void jam(){status =Status.JAMMED;}

    public  Status getStatus(){
        return status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Toast "+id+" :" + status;
    }
}


class ToastQueue extends LinkedBlockingDeque<Toast>{};

class Toaster implements  Runnable{

    private ToastQueue toastQueue;

    private int count =0;

    private Random random = new Random(47);

    public Toaster( ToastQueue tq){
        toastQueue=tq;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(100+random.nextInt(500));
                Toast toast = new Toast(count++);
                System.out.println(toast);
                toastQueue.put(toast);
            }
        }catch (Exception e){
            System.out.println("Toaster Interrupted");
        }
        System.out.println("Toaster off");
    }
}

class Butterer implements  Runnable{

    public ToastQueue dryQueue,butterQueue;

    public Butterer(ToastQueue dry,ToastQueue butter){
        dryQueue=dry;
        butterQueue = butter;

    }

    @Override
    public void run() {

        try{
            while(!Thread.interrupted()){
              Toast t =dryQueue.take();
              t.butter();
              System.out.println(t);
              butterQueue.put(t);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Butter Interrupted");
        }
        System.out.println("Butter off");
    }
}

class Jammer implements  Runnable{

    private  ToastQueue butterQueue,finishQueue;

    public Jammer(ToastQueue butter ,ToastQueue jammerQueue){
         butterQueue = butter;
        finishQueue = jammerQueue;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
             Toast t = butterQueue.take();
             t.jam();
             System.out.println(t);
                finishQueue.put(t);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Jammer Interrupted");
        }
        System.out.println("Jammer off");
    }
}

class Eater implements  Runnable {

    private ToastQueue finishedqueue;

    private int counter = 0;

    public Eater( ToastQueue finished){
        finishedqueue = finished;
    }

    @Override
    public void run() {
        System.out.println(1);
        try{
            while(!Thread.interrupted()){
                Toast t =finishedqueue.take();
                if(t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED){
                    System.out.println(">>>>> Error" + t);
                    System.exit(1);
                }else{
                    System.out.println("Chomp!" +t);
                }
            }
        }catch (Exception e){
            System.out.println("Eater Interrupted");
        }
        System.out.println("Eater off");
    }
}