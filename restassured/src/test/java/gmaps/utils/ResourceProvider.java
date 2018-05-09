package gmaps.utils;

public class ResourceProvider {

    public static String provideAddPlaceResource(){
        String res = "maps/api/place/add/json";
        return res;
    }

    public static String provideDeletePlaceResource(){
        String res = "maps/api/place/delete/json";
        return res;
    }

    public static String provideAddPlaceResourceXML(){
        String res = "maps/api/place/add/xml";
        return res;
    }

    public static String provideDeletePlaceResourceXML(){
        String res = "maps/api/place/delete/xml";
        return res;
    }

}
