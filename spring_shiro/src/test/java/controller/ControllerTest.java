package controller;

import com.itclj.common.entity.ResponseData;
import com.itclj.controller.MainController;
import common.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ControllerTest extends BaseTest {
    @Autowired
    private MainController doMain;
    @Test
    public void testLogin(){
        ResponseData admin = doMain.login("admin", "123456");
        String msg = admin.getMsg();
        System.out.println(msg);
    }
    @Test
    public void  testAES(){

    }
}
