package com.example.gzhang.SpotifyPlus;

public class CurrentUserSingleton {

    private static CurrentUserSingleton mCurrentUserSingleton = null;

    private CurrentUser mCurrentUser;

    private CurrentUserSingleton() {}

    public static CurrentUserSingleton getInstance() {

        if (mCurrentUserSingleton == null) {
            mCurrentUserSingleton = new CurrentUserSingleton();
        }

        return mCurrentUserSingleton;
    }

    public CurrentUser getCurrentUser() {

        return mCurrentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        mCurrentUser = currentUser;

    }
}
