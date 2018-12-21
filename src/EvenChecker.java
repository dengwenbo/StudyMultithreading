import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements  Runnable {

    private IntGenerator intGenerator;

    private final int id;


    public EvenChecker(IntGenerator g,int ident){
        this.intGenerator=g;
        id=ident;
    }

    @Override
    public void run() {
        while(!intGenerator.isCancled()){
            int val = intGenerator.next();
            if(val%2!=0) {System.out.println(val+"is not even" );
            intGenerator.cancle();}
        }
    }

    public static void test(IntGenerator intGenerator,int count){
        System.out.println("Press Control-C to eixt");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i =0;i<count;i++){
            executorService.execute(new EvenChecker(intGenerator,i));
        }

        executorService.shutdown();;
    }

    public static void test(IntGenerator intGenerator){
        test(intGenerator,10);
    }
}
