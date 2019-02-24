import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

public class TestBlockingQueues {
    static void getkey(){
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }catch( IOException e){
            throw new RuntimeException();
        }
    }

    static  void getkey(String message){
        System.out.println(message);
        getkey();
    }

    static void test(String msg, BlockingQueue<LiftOff> queue ){
        System.out.println(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);

        Thread t = new Thread(runner);
        t.start();
        for(int i=0; i<5;i++){
            runner.add  (new LiftOff(5));
        }

        getkey("Press 'enter ("+msg+")'");
        t.interrupt();
        System.out.println("Finished "+msg+"Test");
    }
    public static void main(String[] args){

        test("LinkedBlockingQueue" ,new LinkedBlockingDeque<LiftOff>());
        test("ArrayBlockedQueue",new ArrayBlockingQueue<LiftOff>(3));
        test("SynchronousQueue",new SynchronousQueue<LiftOff>());
    }
}
