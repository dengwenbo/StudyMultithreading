import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicEvenGenerator extends IntGenerator   {

    private AtomicInteger currentEvenValue = new AtomicInteger(0);

    public int next(){
        return currentEvenValue.addAndGet(2);
    }

    public static void main(String[] args){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("到达规定时间了，自动关闭jvm");
                System.exit(0);
            }
        },5000);
        EvenChecker.test( new AtomicEvenGenerator());
    }
}
