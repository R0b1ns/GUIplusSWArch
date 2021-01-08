package com.example.arztpraxis.helper;

import android.app.Application;


//just do anywhere:
// boolean loggedin=((MyApplication) this.getApplication()).isLoggedIn();
//or:
// ((MyApplication) this.getApplication()).setLoggedIn(true/false);
public class MyApplication extends Application {
    private boolean loggedIn;

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
