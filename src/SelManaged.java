public class SelManaged implements  Runnable {

    private  int countDown =5;

    private Thread t = new Thread(this);
    public SelManaged(){
        t.start();
    }
    @Override
    public void run() {
        while(true){
            System.out.println(this);
            if(--countDown==0){
                return;
            }
        }

    }

    @Override
    public String toString() {
        return Thread.currentThread().getName()+"("+countDown+")";
    }

    public static void main(String[] args){

        for(int i=0;i<5;i++){
            new SelManaged();
        }
    }
}
