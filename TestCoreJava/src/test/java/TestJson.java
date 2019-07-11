
import com.baizhi.Student;
import com.baizhi.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestJson {
    @Test
    public void testJson(){
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            students.add(new Student("S"+i));
            users.add(new User("U"+i,null,null));
            if (i%10==0){
                students.add(new Student("U"+i)) ;
            }
        }
        ArrayList<Object> objects = new ArrayList<>();
        long start = System.currentTimeMillis();
        List<String> collect = users.stream().map(User::getName).collect(Collectors.toList());
        ArrayList<Object> objects1 = new ArrayList<>();
        students.stream().forEach((m)->{
            if ( collect.contains(m.getName())) {
                objects1.add(m);
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(objects1.size());
    }
}
