import java.util.concurrent.TimeUnit;

public class Interrupting2 {

    public static void main(String[] args){

        Thread t = new Thread(new Bolcked());

        t.start();

        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Issuing t.intertupt()");
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
