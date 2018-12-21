/*
public class Task implements Callable<String>{








        */
/*public String call() throws Exception {

        }
    public static void main(String[] args) {
        //1.创建task类
        //2.实现callable接口
        //3.使用Executors类的newCachedThreadPool()方法创建ThreadPoolExecutor对象
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        //4.创建Task对象
        Task task = new Task();
        //5.使用submit()方法提交任务给执行者
        System.out.println("Main Executing the Task\n");
        Future<String> result = executor.submit(task);
        //6.使主任务睡眠2秒
        try {
            //使用timeUnit类将参数单位设置为秒
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //7.使用通过submit()方法返回的Future对象result的cancel()方法，
        //取消任务的执行。传入true值作为cancel方法的参数。
        System.out.println("Main:Canceling the Task\n");
        result.cancel(true);
        //8.将isCancelled()方法和isDone()的调用结果写入控制台，验证任务已取消，已完成
        System.out.printf("Main:Canceled:%s\n",result.isCancelled());
        System.out.printf("Main:Done:%s\n",result.isDone());
        //9.使用shutdown()方法结束执行者，写入信息(到控制台)，表明程序结束
        executor.shutdown();
        System.out.println("Main:The executor has finished");

        *//*
 */

import java.util.concurrent.*;

/**
 * 那么cancel是如何工作的呢？
 *
 * 当你想要取消你已提交给执行者的任务，使用Future接口的cancel()方法。
 * 根据cancel()方法参数和任务的状态不同，这个方法的行为将不同：
 *      1、如果这个任务已经完成或之前的已经被取消或由于其他原因不能被取消，
 *          那么这个方法将会返回false并且这个任务不会被取消。
 *      2、如果这个任务正在等待执行者获取执行它的线程，那么这个任务将被取消而且不会开始他的执行。
 *          如果这个任务已经正在运行，则视方法的参数情况而定。
 *          cancel()方法接收一个Boolean值参数。
 *          如果参数为true并且任务正在运行，那么这个任务将被取消。
 *          如果参数为false并且任务正在运行，那么这个任务将不会被取消。
 *//*
 */
/*
    }*//*

    }
*/

public  class Task implements Callable<String>{
    @Override
    public String call() throws  Exception {
        while (true) {
            System.out.println("Task: Test\n");
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args){

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Task task = new Task();
        Future<String> result = executor.submit(task);
        try {
            //使用timeUnit类将参数单位设置为秒
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Main:Canceling the Task\n");
        result.cancel(true);
        //8.将isCancelled()方法和isDone()的调用结果写入控制台，验证任务已取消，已完成
        System.out.printf("Main:Canceled:%s\n",result.isCancelled());
        System.out.printf("Main:Done:%s\n",result.isDone());
    }

}

