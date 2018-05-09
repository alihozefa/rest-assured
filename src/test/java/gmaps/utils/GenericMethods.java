package gmaps.utils;


import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

public class GenericMethods {

    public Properties provideHostValue() {
        Properties properties = new Properties();
        try {

            FileInputStream fis = new FileInputStream("C:\\Users\\Hozefaa\\IdeaProjects\\restassured\\src\\test\\java\\twitter\\utils\\env.properties");
            properties.load(fis);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return properties;
    }

    public static String getHostValue() {
        return new GenericMethods().provideHostValue().getProperty("HOST");
    }

    public static String getKeyValue(){ return  new GenericMethods().provideHostValue().getProperty("KEY");}

    public static String getStringFromXML(String fileName)
    {
        String st = null;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            FileInputStream is = new FileInputStream(fileName);
            Document document = docBuilderFactory.newDocumentBuilder().parse(is);
            StringWriter sw = new StringWriter();
            Transformer serializer = TransformerFactory.newInstance().newTransformer();
            serializer.transform(new DOMSource(document), new StreamResult(sw));
            st = sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

    public static XmlPath convertRawToXML(Response response){
        String responseStr = response.asString();
        XmlPath xml = new XmlPath(responseStr);
        return xml;
    }

    public static JsonPath convertRawToJson(Response response){
        String responseStr = response.asString();
        JsonPath json = new JsonPath(responseStr);
        return json;
    }

}
