package twitter.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PayloadProvider {

    public static Properties provideEnvProperty() {
        Properties properties = new Properties();
        try {

            FileInputStream fis = new FileInputStream("C:\\Development\\restassured\\src\\test\\java\\twitter\\utils\\env.properties");
            properties.load(fis);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return properties;
    }

    public String tweetData(){
        return new String("I am 3rd testing twitter API . . .");
    }

}
