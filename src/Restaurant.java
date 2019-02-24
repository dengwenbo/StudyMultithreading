import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class  Restaurant {

    Meal meal;
     WaitPerson waitPerson = new WaitPerson(this);
    Chef chef =new Chef(this);
    ExecutorService executorService = Executors.newCachedThreadPool();
    public Restaurant(){
        executorService.execute(waitPerson);
        executorService.execute(chef);
    }
    public static void main(String[] args) throws  Exception{
          new Restaurant();
    }


}
class Meal {
    private int orderNum;
    public Meal(int orderNum){
        this.orderNum=orderNum;
    }

    @Override
    public String toString() {
        return "第"+orderNum+"份餐";
    }
}

class WaitPerson implements  Runnable{
    private Restaurant restaurant;
    public WaitPerson(Restaurant restaurant){
        this.restaurant=restaurant;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    while(restaurant.meal==null){
                        wait();
                    }
                }
                System.out.println("waitPerson go"+restaurant.meal);

                synchronized (restaurant.chef){
                    if(!restaurant.meal.equals("waitPerson go第10份餐") ){
                        restaurant.meal=null;
                        restaurant.chef.notifyAll();
                    }else{
                        System.out.println(           );
                    }

                }
            }
        }catch(InterruptedException e){
            System.out.println("出现异常，interrupt（）");
        }

    }
}

class Chef implements Runnable{

    private Restaurant restaurant;


    private  int count=0;

    public Chef(Restaurant restaurant){
        this.restaurant=restaurant;

    }
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    while(restaurant.meal!=null){
                        wait();
                    }
                }
                if(++count==10){
                    System.out.println("out of food closing");
                    restaurant.executorService.shutdownNow();
                }
                System.out.println("order up");
                synchronized (restaurant.waitPerson){
                   // TimeUnit.MILLISECONDS.sleep(500);
                    restaurant.meal = new Meal(count);
                   restaurant.waitPerson.notifyAll();
                }
            }
        }catch(InterruptedException e){
            System.out.println("chef 异常 interrupted");
        }
    }
}
