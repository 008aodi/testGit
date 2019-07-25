import org.junit.Test;
import util.sort.UtilMySort;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * 测试排序算法工具类
 */
public class TestSortUtil {
    @Test
    public void TestSort(){
        ArrayList<Integer> ints = new ArrayList<Integer>();
        for (int i = 0; i < 10000; i++) {
            int random = (int)(Math.random()*100);
            ints.add(random);
        }
        int[] arr = ints.stream().mapToInt(Integer::valueOf).toArray();
        // int[] ints = {5, 6, 3, 1, 89, 4, 50, 7, 44, 66, 33, 2, 0, 82, 19, 13, 63, 74, 8, 91, 11,101,999,201,301,501,601,701,109,99,77,22,33,44,55,43,79};
        long start = System.currentTimeMillis();
        UtilMySort.sortMaopao(arr);
       // UtilMySort.sortMaoPaoYouHua(objects);
        long end = System.currentTimeMillis();
        System.out.println(start);
        System.out.println(end);
        System.out.println(end-start);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
