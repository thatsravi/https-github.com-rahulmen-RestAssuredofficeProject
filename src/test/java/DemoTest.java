import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest {



    @Test
    public void m1(){
        System.out.print("Test Method");
    }

    @Test
    public void m2(){

        Assert.assertEquals("Test","Rahul");

    }
}
