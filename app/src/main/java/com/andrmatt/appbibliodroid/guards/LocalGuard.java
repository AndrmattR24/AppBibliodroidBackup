package com.andrmatt.appbibliodroid.guards;

import android.app.Application;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.andrmatt.appbibliodroid.views.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LocalGuard {
    private Application app;
    private FirebaseAuth auth;
    private FirebaseUser user;

    public LocalGuard(Application app) {
        this.app = app;
        this.auth = FirebaseAuth.getInstance();
        this.user = auth.getCurrentUser();
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void logout() {
        this.auth.signOut();
        this.verify();
    }

    public boolean isLogged() {
        return user != null;
    }

    public void verify() {
        if (this.isLogged()) return;
        app.startActivity(new Intent(app.getApplicationContext(), MainActivity.class));
    }

    public void showInfoTextView(TextView text) {
        if (!this.isLogged()) return;
        text.setText(this.user.getEmail());
    }
}
