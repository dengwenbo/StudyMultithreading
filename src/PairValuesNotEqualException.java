import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Pair {


    private int x,y;

    public Pair(int x,int y){
        this.x=x;
        this.y=y;
    }

    public  Pair(){
        this(0,0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementX(){
        x++;
    }

    public  void incrementsY(){
        y++;
    }

    @Override
    public String toString() {
        return "x = "+x+",y =  "+y;
    }

    public  class PairValuesNotEqualException extends RuntimeException {
        public PairValuesNotEqualException() {
            super("Pair values not equal: " + Pair.this);
        }
    }
     public void checkState() {
            if(x !=y){
                throw new PairValuesNotEqualException();
         }
     }

}

abstract  class PairManager{

    AtomicInteger checkCounter = new AtomicInteger(0);

    protected  Pair p  = new Pair();

    private List<Pair> storage  = Collections.synchronizedList(new ArrayList<Pair>());

   public synchronized  Pair getPair(){
       return new Pair(p.getX(),p.getY());
   }

   protected  void store(Pair p){
       storage.add(p);

       try{
           TimeUnit.MILLISECONDS.sleep(50);
       }catch (InterruptedException ignore){

       }
   }
  public  abstract  void increments();
}


class PairManager1 extends  PairManager{



    public synchronized void increments() {
        p.incrementX();;
        p.incrementsY();
        store(getPair());
    }
}


class PairManager2 extends  PairManager{

    public  void increments() {
         Pair temp;
         synchronized (this){
             p.incrementX();
             p.incrementsY();
             temp=getPair();
         }
         store(temp);
    }
}

class PairManipulator implements  Runnable{

     private PairManager pm;
    @Override
    public void run() {
           while(true){
             pm.increments();
           }
    }


    public  PairManipulator(PairManager pm){
            this.pm=pm;
    }

    @Override
    public String toString() {
        return "Pair: "+pm.getPair()+" checkCounter =  "+pm.checkCounter.get();
    }
}

class PairChecker implements  Runnable{
    private PairManager pm;

    public PairChecker(PairManager pm){
        this.pm=pm;
    }

    @Override
    public void run() {
        while(true){
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}

