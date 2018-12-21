import com.sun.istack.internal.FinalArrayList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SerialNumberChecker {


    private static final int SIZE =10;


    private static CircularSet serials = new CircularSet(1000);

    private static ExecutorService executorService = Executors.newCachedThreadPool();
     private static Lock lock = new ReentrantLock();
    static class SerialChecker implements Runnable{

        @Override
        public void run() {
            synchronized (SerialChecker.class){
                while(true){
                    //synchronized (SerialNumberChecker.class) {

                    //try{
                      //  lock.lock();
                        int serial = SerialNumberGenerator.nextSerialNumber();
                        System.out.println(Thread.currentThread());
                        if (serials.contains(serial)) {

                            System.out.println("Duplicate:     " + serial);
                            System.exit(0);
                        }
                        serials.add(serial);
                  //  }finally {
                   //    //lock.lock();
                    //}


                  }
                }
            }

        }


    public static void main(String[] args) throws InterruptedException {

        for (int i=0;i<SIZE;i++){
            executorService.execute(new SerialChecker());
        }

        if(args.length>0){
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
            System.out.println("no Duplicate detected");
            System.exit(0);
        }
    }
}
class CircularSet{
    private int[] array;

    private int len;

    private int index=0;

    public CircularSet(int size){
        array = new int[size];

        len=size;

        //初始化数组
        for(int i=0;i<size;i++){
            array[i]=-1;
        }
    }

    public synchronized  void add(int i){
        array[index] = i;
        //包装索引并覆盖旧元素
        index =++index%len;
    }

    public synchronized  boolean contains(int val){
        for(int i=0;i<len;i++){
            if(array[i] == val  ) return true;

        }
        return false;
    }

}