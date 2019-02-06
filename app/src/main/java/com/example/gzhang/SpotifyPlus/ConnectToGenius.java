//package com.example.gzhang.SpotifyPlus;
//
//import android.net.Uri;
//
//import com.github.scribejava.core.builder.ServiceBuilder;
//import com.github.scribejava.core.oauth.OAuth10aService;
//
//import java.net.URI;
//
//import retrofit2.Retrofit;
//
//public class ConnectToGenius {
//
//    private final String CLIENT_ACCESS_TOKEN = "yWMQzfOYWTVApOqJL_kj9pYTMAdfmvbgiFQ_KM9WW1SQuIkGIgclDCy1X43JlHP1";
//
//    private final String BASE_URL = "https://api.genius.com/search?q=";
//
//    GeniusApiService service = retrofit.create(GeniusApiService.class);
//
//    private String buildUri () {
//
//        String searchTerm = "";
//
//        //TODO: fetch the search term from the activity that calls this
//
//        String  searchTermAndBaseUrl = BASE_URL + searchTerm;
//
//        Uri searchTermQuery = Uri.parse(searchTermAndBaseUrl);
//
//
//
//    }
//
//    private void doApiCall(String uri) {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(uri)
//                .build();
//
//
//    }
//
//
//}
