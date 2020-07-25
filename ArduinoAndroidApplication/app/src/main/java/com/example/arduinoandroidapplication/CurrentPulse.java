package com.example.arduinoandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CurrentPulse extends AppCompatActivity {
    private FirebaseAuth auth;
    private Firebase ref;
    private TextView currentPulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_pulse);

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null) {
            String userUId = auth.getCurrentUser().getUid();
            ref = new Firebase(String.format("%s/Users/%s/braceletId",
                    FirebaseDatabase.getInstance().getReference().toString(), userUId));
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    currentPulse = (TextView) findViewById(R.id.currentPulse);
                    currentPulse.setText(dataSnapshot.getValue().toString());
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) { }
            });
        }
    }
}
