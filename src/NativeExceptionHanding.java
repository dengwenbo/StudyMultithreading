import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NativeExceptionHanding {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();

        try{
            executorService.execute(new ExceptionThread());
        }catch(RuntimeException e){
            System.out.println("捕获住了异常                                                                                                                                                                                                                                  ");
            e.printStackTrace();
        }
    }
}
