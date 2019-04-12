import com.baizhi.test.ExeResult;
import org.junit.Test;
import org.junit.runner.RunWith;

public class TestExeResult {
@Test
public void testExeResult() throws InterruptedException {
    ExeResult result = ExeResult.start();
    Thread.sleep(5434);
    result.end();
    System.out.println(result.getUseTime());
}
}
