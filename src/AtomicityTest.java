import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest  implements  Runnable {


    private volatile  int i=0;

    public synchronized int getI() {
        return i;
    }

    private synchronized  void evenIncrement(){
        i++;
        i++;
    }

    @Override
    public void run() {
        while(true){
            evenIncrement();
        }
    }


    public static void main(String[] args){

        ExecutorService service = Executors.newFixedThreadPool(5);
        AtomicityTest at = new AtomicityTest();
        service.execute(at);
        while(true){
            int value = at.getI();
            if(value%2 != 0){
                System.out.println(value);
                System.exit(0);
            }
        }
    }
}
