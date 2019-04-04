import com.baizhi.User;
import com.baizhi.test.RSA;
import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;

public class TestRefence {
    @Test
    public void Test(){
     //  User user = new User("zhang","1234","12");
        try {
            User user1 = User.class.newInstance();
            Class<User> u= (Class<User>) Class.forName("com.baizhi.User");
            Object o = u.newInstance();
            Field[] declaredFields = u.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                Field declaredField = declaredFields[i];
                declaredField.setAccessible(true);
                declaredField.set(o,i+declaredField.getName());
                System.out.println(declaredField.getType());
            }
            Constructor<?>[] declaredConstructors = u.getDeclaredConstructors();
            for (int i = 0; i < declaredConstructors.length; i++) {
                Constructor<?> declaredConstructor = declaredConstructors[i];
                System.out.println(declaredConstructor);
            }
        } catch (Exception e) {
        }
    }
}
