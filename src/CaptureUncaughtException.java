import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.xml.stream.XMLOutputFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CaptureUncaughtException {

    public static void main(String[] args){
       // ExecutorService executorService = Executors.newCachedThreadPool(new HandlerThreadFactory());
        Thread t =new HandlerThreadFactory().newThread(new ExceptionThread2());
        //executorService.execute(new ExceptionThread2());
        t.start();
    }
}

class ExceptionThread2 implements  Runnable{
    @Override
    public void run() {
       Thread t =Thread.currentThread();
        System.out.println("run() by" + t);
        System.out.println("en = "+t.getUncaughtExceptionHandler());
       throw new RuntimeException();
    }
}

class MyUncaughtExceptionHandler implements  Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught"+e);
    }
}

class HandlerThreadFactory implements ThreadFactory{
    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this+"creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created "+t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("en = "+t.getUncaughtExceptionHandler());
        return t;
    }
}