package com.associate.sbmfa.Rest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static okhttp3.logging.HttpLoggingInterceptor.Level;
public class RestHandler {

    private static ApiService API_SERVICE;
    //http://my.samraddhbestwin.com/api/
    //http://uat.samraddhbestwin.com/api/
//    public static String BASE_URL1 = "http://uat.samraddhbestwin.com/api/";
//    public static String BASE_URL1 = "http://my.samraddhbestwin.com/api/"; // Testing Server
//    public static String BASE_URL1 = "http://my2.samraddhbestwin.com/api/"; // Testing Server

    public static String BASE_URL1 = "http://my.samraddhbestwin.com/api/"; // Live Server
//    public static String BASE_URL1 = "http://uatsamraddh.host4india.in/api/"; // Testing Server

    public static ApiService getApiService()  {
        if (API_SERVICE == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            httpClient.connectTimeout(7000, TimeUnit.SECONDS);
            httpClient.readTimeout(7000, TimeUnit.SECONDS);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
                Retrofit adapter = new Retrofit.Builder()
                        .baseUrl(BASE_URL1)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(httpClient.build())
                        .build();
                API_SERVICE = adapter.create(ApiService.class);
        }
        return API_SERVICE;
    }
}
