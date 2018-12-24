public class SyncObject {

    public static void main(String[] args){
    final  DualSynch ds = new DualSynch();

    new Thread(){
        @Override
        public void run() {
            try{
                ds.f();
            }catch(InterruptedException e){

            }

        }
    }.start();
    try{
        ds.g();
    }catch (InterruptedException e){

    }



    }
}


class DualSynch{
    private Object  syncObject = new Object();


    public synchronized  void f() throws InterruptedException{
        for (int i=0;i<5;i++){
            System.out.println("f()");
            Thread.sleep(10);
        }
    }


    public void g() throws InterruptedException{
        synchronized (syncObject){
            for(int i=0;i<5;i++){
                System.out.println("g()");
                Thread.sleep(5000);
            }
        }

    }
}