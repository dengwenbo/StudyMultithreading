import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NativeExceptionHanding {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();

        try{
            executorService.execute(new ExceptionThread());
        }catch(RuntimeException e){
            System.out.println("����ס���쳣                                                                                                                                                                                                                                  ");
            e.printStackTrace();
        }
    }
}
