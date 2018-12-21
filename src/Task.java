/*
public class Task implements Callable<String>{








        */
/*public String call() throws Exception {

        }
    public static void main(String[] args) {
        //1.����task��
        //2.ʵ��callable�ӿ�
        //3.ʹ��Executors���newCachedThreadPool()��������ThreadPoolExecutor����
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        //4.����Task����
        Task task = new Task();
        //5.ʹ��submit()�����ύ�����ִ����
        System.out.println("Main Executing the Task\n");
        Future<String> result = executor.submit(task);
        //6.ʹ������˯��2��
        try {
            //ʹ��timeUnit�ཫ������λ����Ϊ��
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //7.ʹ��ͨ��submit()�������ص�Future����result��cancel()������
        //ȡ�������ִ�С�����trueֵ��Ϊcancel�����Ĳ�����
        System.out.println("Main:Canceling the Task\n");
        result.cancel(true);
        //8.��isCancelled()������isDone()�ĵ��ý��д�����̨����֤������ȡ���������
        System.out.printf("Main:Canceled:%s\n",result.isCancelled());
        System.out.printf("Main:Done:%s\n",result.isDone());
        //9.ʹ��shutdown()��������ִ���ߣ�д����Ϣ(������̨)�������������
        executor.shutdown();
        System.out.println("Main:The executor has finished");

        *//*
 */

import java.util.concurrent.*;

/**
 * ��ôcancel����ι������أ�
 *
 * ������Ҫȡ�������ύ��ִ���ߵ�����ʹ��Future�ӿڵ�cancel()������
 * ����cancel()���������������״̬��ͬ�������������Ϊ����ͬ��
 *      1�������������Ѿ���ɻ�֮ǰ���Ѿ���ȡ������������ԭ���ܱ�ȡ����
 *          ��ô����������᷵��false����������񲻻ᱻȡ����
 *      2���������������ڵȴ�ִ���߻�ȡִ�������̣߳���ô������񽫱�ȡ�����Ҳ��Ὺʼ����ִ�С�
 *          �����������Ѿ��������У����ӷ����Ĳ������������
 *          cancel()��������һ��Booleanֵ������
 *          �������Ϊtrue���������������У���ô������񽫱�ȡ����
 *          �������Ϊfalse���������������У���ô������񽫲��ᱻȡ����
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
            //ʹ��timeUnit�ཫ������λ����Ϊ��
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Main:Canceling the Task\n");
        result.cancel(true);
        //8.��isCancelled()������isDone()�ĵ��ý��д�����̨����֤������ȡ���������
        System.out.printf("Main:Canceled:%s\n",result.isCancelled());
        System.out.printf("Main:Done:%s\n",result.isDone());
    }

}

