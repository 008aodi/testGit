import java.util.Date;
import java.util.List;

import com.baizhi.Application;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserMapperTest {

    /**
     *
     */
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {

        List<User> userList = userMapper.selectList(
                new EntityWrapper<User>().eq("name", "Tom"));
        System.out.println(userList);
    }

    @Test
    public void testSelectPage() {
        List<User> userList = userMapper.selectPage(
                new Page<User>(1, 10),
                new EntityWrapper<User>().eq("name", "Tom"));
        System.out.println(userList);
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("Diana");
        user.setAge(18);
        user.setBirth(new Date());
        int result = userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(1);
        user.setName("Jack");
        user.setAge(28);
        user.setBirth(new Date());
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(2);
        System.out.println(result);
    }
}