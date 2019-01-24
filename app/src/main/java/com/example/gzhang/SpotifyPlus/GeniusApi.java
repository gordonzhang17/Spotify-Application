package com.example.gzhang.SpotifyPlus;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1RequestToken;

public class GeniusApi extends DefaultApi10a {

    private static final String AUTHORIZE_URL = "https://api.genius.com/oauth/authorize?token=%s";

    protected GeniusApi() {
    }

    private static class InstanceHolder {
        private static final GeniusApi INSTANCE = new GeniusApi();
    }

    public static GeniusApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://api.genius.com/oauth/authorize/access_token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return null;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return "https://api.genius.com/oauth/authorize/request_token";
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
        return String.format(AUTHORIZE_URL, requestToken.getToken());
    }

}
