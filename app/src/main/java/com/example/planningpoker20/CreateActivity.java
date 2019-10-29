package com.example.planningpoker20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CreateActivity extends AppCompatActivity {
    private int lastSessionId;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();
    EditText editSessionId, editSessionName, editDescriptionText;
    Button creatSessionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // mDatabaseReference = FirebaseDatabase.getInstance().getReference("Session");
        setContentView(R.layout.activity_create);
        init();
        getLastSessionID();

        creatSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertFirebaseData();
               // Log.i("FBDB", "NewSession " + getLastSessionId());
              ///  Intent intent = new Intent(CreateActivity.this, Session.class);
             //   intent.putExtra("SessionName", editSessionName.getText().toString());
              //  intent.putExtra("SessionId", getLastSessionId());
             //   startActivity(intent);
            }
        });

    }
    private void insertFirebaseData(){
        getLastSessionId();

        mDatabaseReference.child(String.valueOf(lastSessionId)).child("SessionId").setValue(lastSessionId);
        mDatabaseReference.child(String.valueOf(lastSessionId)).child("SessionName").setValue(editSessionName.getText().toString());
        mDatabaseReference.child(String.valueOf(lastSessionId)).child("SessionDesc").setValue(editDescriptionText.getText().toString());

    }

    private void init() {

        creatSessionButton = (Button) findViewById(R.id.btnCreate);
        editSessionId=(EditText) findViewById(R.id.editSessID);
        editSessionName=(EditText) findViewById(R.id.editSessName);
        editDescriptionText=(EditText) findViewById(R.id.editDesc);
    }

    private void getLastSessionID()
    {
        Query query = mDatabaseReference.orderByChild("SessionID").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key;
                    key = child.getKey();
                    Log.i("FireBaseError","last sessionID: "+key);
                    try {
                        setLastSessionId(Integer.parseInt(key));
                    }catch (NumberFormatException e)
                    {
                        Log.i("FireBaseError",e.toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("FBDBERROR",databaseError.toString());
            }
        });
    }

    public int getLastSessionId() {
        return lastSessionId;
    }

    public void setLastSessionId(int lastSessionId) {
        Log.i("SessionId","Last sessionID: "+lastSessionId);
        this.lastSessionId = lastSessionId;
    }
}


