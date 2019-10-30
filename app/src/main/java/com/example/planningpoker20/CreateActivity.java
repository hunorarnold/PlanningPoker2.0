package com.example.planningpoker20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import static java.lang.Integer.parseInt;


public class CreateActivity extends AppCompatActivity {

    private int sessionID;
    DatabaseReference mDatabaseReference;



    EditText editSessionId, editSessionName, editDescriptionText;
    Button creatSessionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Sessions");

        setContentView(R.layout.activity_create);
        init();
        creatSession();

    }

    private void creatSession() {
        creatSessionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              insertFirebaseData();

            }
        });
    }

    private void insertFirebaseData(){

        String sessionid =editSessionId.getText().toString();
        if(isStringInt(sessionid)){
            setSessionID(parseInt(sessionid));
            mDatabaseReference.child(String.valueOf(sessionID)).setValue(sessionID);
            mDatabaseReference.child(String.valueOf(sessionID)).setValue(editSessionName.getText().toString());
            mDatabaseReference.child(String.valueOf(sessionID)).setValue(editDescriptionText.getText().toString());
        }

    }

    private void init() {

        creatSessionButton = (Button) findViewById(R.id.btnC);
        editSessionId=(EditText) findViewById(R.id.editSessIDC);
        editSessionName=(EditText) findViewById(R.id.editSessName);
        editDescriptionText=(EditText) findViewById(R.id.editDesc);
    }

  /*  private void getLastSessionID()
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
                        setLastSessionId(parseInt(key));
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
    }*/

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
}


