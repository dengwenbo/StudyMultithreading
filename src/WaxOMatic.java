import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOMatic {

    public static void main(String[] args) throws InterruptedException {

        Car car = new Car();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaxOff(car));
        executorService.execute(new WaxOn(car));


        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}

    class Car{

        private Boolean waxOn =false;

        /**
         * ÅçÆá
         */
        public  synchronized void waxed(){
            waxOn=true;
            notify();
        }

        /**
         * ÕýÔÚÅ×¹â
         */
        public synchronized  void buffed(){
            waxOn =false;
            notifyAll();
        }


        public synchronized  void waitingForWaxing() throws InterruptedException {
            while(waxOn == false){
                wait();
            }
        }

        public synchronized  void waitForBuffing() throws InterruptedException {
            while(waxOn == true){
                wait();
            }
        }
    }

    class WaxOn implements Runnable{
        private Car car;

        public WaxOn(Car c){
           this.car=c;
        }

        public  void run(){
                try {
                    while(!Thread.interrupted()) {
                        System.out.println("Wax On!");
                        TimeUnit.MILLISECONDS.sleep(200);
                        car.waxed();
                        car.waitForBuffing();
                    }
                } catch (InterruptedException e) {
                    System.out.println("Exiting via interrupt");
                }
                System.out.println("Ending Wax On task");
            }

        }


    class WaxOff implements Runnable{

        private Car car;

        public WaxOff(Car c){
            this.car=c;
        }


        @Override
        public void run() {

                    try {
                        while(!Thread.interrupted()) {
                            car.waitingForWaxing();
                            System.out.println("Wax Off£¡");
                            TimeUnit.MILLISECONDS.sleep(200);
                            car.buffed();
                        }
                    } catch (InterruptedException e) {
                        System.out.println("Exiting via Interrupt");
                    }

                 System.out.println("Ending Wax offf task");
        }
    }

