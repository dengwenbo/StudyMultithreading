public class Joining {

    public static void main(String[] args){

    Sleeper sleeper = new Sleeper("Sleepy",1500),
            grumpy = new Sleeper("grumpy",1500);

    Joiner dopey = new Joiner("dopey",sleeper),
            doc = new Joiner("doc",grumpy);
        grumpy.interrupt();


    }
}
class Joiner extends Thread{


    private Sleeper sleeper;


    public Joiner(String name, Sleeper sleeper){
        super(name);
        this.sleeper=sleeper;
        start();
    }
    @Override
    public void run() {
        try{
            sleeper.join();
        }catch (InterruptedException e){
            System.out.println(getName()+" interrupted");
        }
        System.out.println(getName()+"join completed");
    }
}