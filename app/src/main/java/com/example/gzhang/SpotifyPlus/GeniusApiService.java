package com.example.gzhang.SpotifyPlus;

public interface GeniusApiService {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

}
