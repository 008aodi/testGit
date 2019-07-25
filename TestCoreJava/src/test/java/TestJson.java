
import com.baizhi.AES.CipherUtil;
import com.baizhi.DoPost;
import com.baizhi.Student;
import com.baizhi.User;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static com.baizhi.AES.CipherUtil.AES;

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
    @Test
    public void testAES(){
        CipherUtil cipherAESE = CipherUtil.build(AES, "rh6n8jaUURIQ1BjQnLbdnaZtr54SCQ7R", "UTF-8");
        CipherUtil cipherAESD = CipherUtil.build(AES, "rh6n8jaUURIQ1BjQnLbdnaZtr54SCQ7R", "UTF-8");
        //加密前数据
        String content = "{\n" +
                "\t\"package\": {\n" +
                "\t\t\"body\": [{\n" +
                "\t\t\t\"endDay\": \"2019-04-03\",\n" +
                "\t\t\t\"hospitalId\": \"210004\",\n" +
                "\t\t\t\"idCardNo\": \"310229199111240021\",\n" +
                "\t\t\t\"patientName\": \"丁洁如\",\n" +
                "\t\t\t\"patientType\": \"001\",\n" +
                "\t\t\t\"startDay\": \"2018-06-03\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"endDay\": \"2019-02-20\",\n" +
                "\t\t\t\"hospitalId\": \"210004\",\n" +
                "\t\t\t\"idCardNo\": \"310229199111240021\",\n" +
                "\t\t\t\"patientName\": \"丁洁如\",\n" +
                "\t\t\t\"patientType\": \"001\",\n" +
                "\t\t\t\"startDay\": \"2018-06-03\"\n" +
                "\t\t}],\n" +
                "\t\t\"head\": {\n" +
                "\t\t\t\"busenissType\": \"2\",\n" +
                "\t\t\t\"sendTradeNum\": \"test201817281127092007\",\n" +
                "\t\t\t\"senderCode\": \"OUTER-US-RH\",\n" +
                "\t\t\t\"senderName\": \"OUTER-US-RH\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        //加密
        String encrypt = cipherAESE.encryptString(content);
        //解密前数据
        String content1 = "vwE_tbXRzo9M4Of9-RCRGIp81GLUD-4rLgMLFyzUbRCczqWMgTmHMf_yX3y_TXXvWGqJomdYSaFVFn_e1nMw_rSq0__8Zo_pm-ABZSBSTnL9Raan9v2r7ZTMpghvJpaxJadKLZCgsb-wqBm4TD1AHNzysBGNJ8H-Fo5GEhCJ4b6qYJzL_EMfXXSTy-iJqBbEYtyU5kUUUBTRoZcozCYxA33ItlruZaUXGEg8YVjxF4lJBxkYDXr1DGGsUqNz2VWhyUbgZwI0i0BB9LZFIbXbMu4r31qvSBRXeHjOkvUXd8qYZY6tcb83vfEsqz2CZEZW90ur4-9_33nHcIWmoMw_UcYE1yertEL_E4rnrt_xQXBe8-gkcpirT-hh3TsTw-U5fu0MhamuaC6QA7RxlE8Him7BWKZ7knmHCSdRLU-SDXnOjTk7QkdYhkdK0oPc69BFXJSmBf9u15fwUfDCRKQSZlxK5S6GVnHei7uyAMtC0YmVelt32jgUbOCCwoLC5atk1k-OJXF02_-bEsvEo5_Mug==";
        //解密
        String decrypt = cipherAESD.decryptString(content1);
        System.out.println(decrypt);
    }
    @Test
    public void testHttp(){
        ConcurrentMap<String, String> params= Maps.newConcurrentMap();
        //获取header部分参数
        params.put("Content-Type","application/json");
        //获取用户名密码
        String username ="OUTER-TKRS";
        String password ="JS7G36DBC9523KQSE";
        //按照username:password拼接参数
        String join = StringUtils.join(username, ":", password);
        //Base64格式转换
        String UsernameAndPassword = Base64.encodeBase64String(join.getBytes());
        //拼接认证类型Basic base64(username:password)
        System.out.println(UsernameAndPassword);
        String authorization = StringUtils.join("Basic", " ", UsernameAndPassword);
        params.put("Authorization",authorization);
        String url = "https://insurancetest.quyiyuan.com/xinjiang/oauth/token?grant_type=client_credentials";
        String s = DoPost.doPost(url, params, "application/json", authorization);
        System.out.println(s);
    }

}
