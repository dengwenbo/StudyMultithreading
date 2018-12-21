
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.*;
public class THreadVariations {


    public static void main(String[] args){

        //new InnerThead1("InnerThead1");
       // new InnerThread2("InnerThread2");
       // new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        //                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  new ThreadMethod("ThreadMethod").runTask();
    }
}


class InnerThead1{

    private int countDown = 5;

    private Inner inner;


    private class Inner extends Thread{
       Inner(String name){
           super(name);
           start();
           }

        @Override
        public void run() {

           try{
               while(true){
                   print(this);
                   if(--countDown ==0) return;
                   sleep(10);
               }
           }catch(InterruptedException e){
               print("interrupt!");
           }

        }
        @Override
        public String toString() {
            return getName()+":"+countDown;
        }
    }



    public InnerThead1(String name){
        inner = new Inner(name);
    }
}

class InnerThread2 {

    private int countDown = 5;


    private Thread t;


    public InnerThread2(String name) {
        t = new Thread(name) {
            @Override
            public void run() {
                try {
                    while (true) {
                        print(this);
                        if (--countDown == 0) return;
                        sleep(10);
                    }
                } catch (InterruptedException e) {
                    print("this InnerThread2 Interrupted ");
                }
            }

            @Override
            public String toString() {
                return getName() +"  :  "+countDown;
            }
        };

        t.start();
    }
}

class InnerRunnable1 {
    private int countDown = 5;


    private Inner inner;


        private class Inner implements Runnable {
        Thread t;

        Inner(String name) {
            t = new Thread(this, name);
            t.start();

        }

        @Override
        public void run() {
            try {
                while (true) {
                    print(this);
                    if (--countDown == 0) return;
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println(" the InnerRunnable1 is interrupted" + +countDown);
            }
        }
        @Override
        public String toString() {
            return t.getName() + "  :  " + countDown;
        }

    }

    public InnerRunnable1(String name) {
        inner = new Inner(name);
    }

}


class InnerRunnable2{
    private  int countDown =5;

    private Thread t;

    public InnerRunnable2(String name){
        t = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    while(true){
                        print(this);
                        if(--countDown == 0) return;
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                }catch(InterruptedException e){
                    print(" the innerRunnable interrupted");
                }

            }

            @Override
            public String toString() {
                return t.getName()+" : "+countDown;
            }
        },name);
        t.start();
    }
}
class ThreadMethod{
    private int countDown =5;

    private Thread t;

    private String name;

    public ThreadMethod(String name ){
        this.name = name;
    }

    public  void runTask(){
        if(t==null){
            t =new Thread(name){
                @Override
                public void run() {

                        try { while(true){
                            print(this);
                            if(--countDown==0){
                                return;
                            }
                            TimeUnit.MILLISECONDS.sleep(10);
                        }
                        } catch (InterruptedException e) {
                            System.out.println("the ThrweadMethod interRupted");
                        }

                }

                @Override
                public String toString() {
                    return getName()+":"+countDown;
                }
            };
            t.start();
        }

    }


}
