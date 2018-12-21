import net.mindview.util.Generator;

public abstract class IntGenerator  {

    private   volatile  boolean cancled =false;

    public abstract  int next();

    public void cancle(){cancled =true;}

    public boolean isCancled() {
        return cancled;
    }
}
