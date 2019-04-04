import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;

public class TestMongodb {
    public static void main(String[] args) {
        //创建monogodb实例，连接到副本集，可以不指定参数连接到当前系统正在运行的mongodb
        //也可以指定主机名或者主机名+端口号
        MongoClient mongoClient = new MongoClient("CentOS",27017);
        //通过副本集获取databases
        MongoDatabase database = mongoClient.getDatabase("test");
        //通过databases获取集合
        MongoCollection<Document> coll = database.getCollection("myTest");
        //通过databases创建集合并且设计集合的大小
       database.createCollection("008",
                new CreateCollectionOptions().capped(true).sizeInBytes(0x100000));

        ValidationOptions collOptions = new ValidationOptions().validator(
                Filters.or(Filters.exists("email"), Filters.exists("phone")));
        database.createCollection("contacts",
                new CreateCollectionOptions().validationOptions(collOptions));

        for (String name : database.listCollectionNames()) {
            System.out.println(name);
        }
    }
}
