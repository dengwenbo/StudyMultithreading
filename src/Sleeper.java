public class Sleeper extends Thread {

    private int sleepTime;

    public Sleeper( String name ,int sleepTime){
        super(name);
        this.sleepTime = sleepTime;
        start();

    }

    @Override
    public void run() {
        try{
            sleep(sleepTime);
        }catch(InterruptedException e){
            System.out.println(getName()+"was interRupted, is interrupted()"+ isInterrupted());
        }
        System.out.println(getName()+"has awakened");
    }
}
