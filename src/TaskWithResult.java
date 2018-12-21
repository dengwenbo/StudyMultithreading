import java.util.concurrent.Callable;

public class TaskWithResult implements Callable<String> {

    private int id ;

    public TaskWithResult(int id){
        this.id = id;
    }

    @Override
    public String call() {
        return "the result of TaskWithResult "+ id;
    }
}
