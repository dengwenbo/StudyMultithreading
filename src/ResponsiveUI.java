import java.io.IOException;
import java.util.Scanner;

public class ResponsiveUI extends  Thread  {

    private static volatile  double d =1;

    public ResponsiveUI(){
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while(true) {

            //  d=  System.in.read();
            Scanner sc = new Scanner(System.in);
            d = sc.nextInt();
            d=d+(Math.PI+Math.E)/d;
            System.out.println(d);
        }

    }

    public static void main(String[] args) throws IOException {
       //
       new ResponsiveUI();
        new UnResponsiveUI();
        //System.in.read();
        //System.out.println(d);
        //int aa =System.in.read();
       // System.out.println(aa);
    }
}
class UnResponsiveUI{

    private  static volatile  double t=1;

    public  UnResponsiveUI() throws IOException {
        while(t>0){
           t=t+(Math.PI+Math.E)/t;

        }
    }
}