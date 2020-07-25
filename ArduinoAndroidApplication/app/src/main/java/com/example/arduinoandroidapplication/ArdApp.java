package com.example.arduinoandroidapplication;

import android.app.Application;

import com.firebase.client.Firebase;

public class ArdApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
