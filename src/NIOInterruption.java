import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class NIOInterruption {

    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);

        InetSocketAddress isa = new InetSocketAddress("localhost",8080);
        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 =SocketChannel.open(isa);

        Future<?> f = executorService.submit(new NIOBlocked(sc1));
        executorService.execute(new NIOBlocked(sc2));
        executorService.shutdown();
        TimeUnit.SECONDS.sleep(1);

        f.cancel(true);
        TimeUnit.SECONDS.sleep(1);
        sc2.close();



    }
}
