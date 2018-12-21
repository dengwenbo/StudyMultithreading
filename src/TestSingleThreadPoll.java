import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleThreadPoll {

    public static void main(String[] args){
      ExecutorService exec  = Executors.newFixedThreadPool(3);
        for (int i =0; i<5; i++) {
            exec.execute(new LiftOff());
        }
             exec.shutdown();

    }


}
