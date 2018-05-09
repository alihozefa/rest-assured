package twitter.utils;

public class ResourceProvider {

    public String provideHost(){
        return PayloadProvider.provideEnvProperty().getProperty("HOST");
    }

    public String provideAPIKey(){
        return PayloadProvider.provideEnvProperty().getProperty("API_Key");
    }

    public String provideAPISecret(){
        return PayloadProvider.provideEnvProperty().getProperty("API_SECRET");
    }

    public String provideAccessToken(){
        return PayloadProvider.provideEnvProperty().getProperty("ACCESS_TOKEN");
    }

    public String provideAccessTokenSecret(){
        return PayloadProvider.provideEnvProperty().getProperty("ACCESS_TOKEN_SCRECT");
    }

    public String getTweetsResource(){
        return "/statuses/home_timeline.json";
    }

    public String postTweetResource(){
        return "/statuses/update.json";
    }

    public String deleteTweetResource(){
        return "/statuses/destroy/<id>.json";
    }

}
