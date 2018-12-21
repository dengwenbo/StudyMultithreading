import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javassist.bytecode.stackmap.BasicBlock;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {

    public static void main(String[] args)throws Exception {

        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();

        Thread putThread  = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("put Thread Start");
                try{

                    queue.put(20);
                    System.out.println("放入queue中把值");
                }catch(InterruptedException e){

                }
                System.out.println("put thread end");
            }
        });


        Thread takeThread = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("take thread start");
                try{
                    System.out.println( "take from putThrad :" + queue.take());
                }catch(InterruptedException e){

                }
                System.out.println( "take thread end");
            }
        });


        takeThread.start();
        Thread.sleep(10000);
        putThread.start();
    }


}
