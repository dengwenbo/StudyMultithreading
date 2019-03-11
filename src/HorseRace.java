import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class HorseRace {
        static final int FINISH_LINE = 10;

        private List<Horse> horses = new ArrayList<>();

        private ExecutorService exec  = Executors.newCachedThreadPool();

        private  CyclicBarrier barrier;

        public HorseRace(int nHorses, final int pause){
            barrier = new CyclicBarrier(nHorses, new Runnable() {
                @Override
                public void run() {
                    StringBuffer s = new StringBuffer();
                    for (int i= 0;i<FINISH_LINE;i++){
                        s.append("=");

                    }
                    System.out.println(s);
                    for (Horse horse : horses){
                        System.out.println(horse.tracks());
                    }
                /*    for (Horse horse : horses){
                        if(horse.getStrides() >= FINISH_LINE){
                            System.out.println(horse+"   won");
                            exec.shutdown();
                            return;
                        }
                    }*/
                    try {
                        TimeUnit.MILLISECONDS.sleep(pause);
                    } catch (InterruptedException e) {
                        System.out.println("barrier -action sleep interrupted");
                    }
                }
            });
            for(int i=0;i<nHorses;i++){
                    Horse horse= new Horse(barrier);
                    horses.add(horse);
                    exec.execute(horse);
                }

        }

    public static void main(String[] args){
      int nHorse = 7,pause = 200;
      new HorseRace(nHorse,pause);

    }
}

class Horse implements Runnable{

    private static int counter = 0;

    public  final int id =counter++;

    private  int strides =0;

    private static Random rand = new Random(47);

    private static CyclicBarrier barrier;

    public Horse( CyclicBarrier barrier1){
         barrier = barrier1;
    }

    public int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try{
            while( !Thread.interrupted()){
                synchronized (this){
                    System.out.println(id);
                    strides+=rand.nextInt(3);
                }
                barrier.await();
            }
        }catch(BrokenBarrierException e){
            throw new RuntimeException(e);
        }catch (InterruptedException ex){

        }

    }


    @Override
    public String toString() {
        return "Horse   " + id+"    ";
    }

    public String tracks(){
        StringBuffer  s = new StringBuffer();
        for (int i =0 ;i<getStrides();i++){
            s.append("*");
        }
        s.append(id);
        return s.toString();
    }
}
