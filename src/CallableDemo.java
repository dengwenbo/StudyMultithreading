

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {

    public static void main(String[] args){
        ExecutorService  executor = Executors.newCachedThreadPool();
        ArrayList<Future<String>> result = new ArrayList<>();

        for(int i=0;i<10;i++){
            result.add(executor.submit(new TaskWithResult(i)));
        }
        for(Future<String> res : result){

            try {
                if(res.isDone())
                System.out.println(res.get());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }

    }

}
