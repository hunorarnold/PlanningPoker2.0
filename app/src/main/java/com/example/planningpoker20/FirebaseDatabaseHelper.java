package com.example.planningpoker20;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FirebaseDatabaseHelper {

    private DatabaseReference database;
    private Query query;
    private Session session;


    public FirebaseDatabaseHelper(String sessionId) {
        database = FirebaseDatabase.getInstance().getReference("SESSION");
        session = new Session();
        getDatabaseSessionData(sessionId);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void getDatabaseSessionData(String sessionID) {
        query = database.child(sessionID);

    }
}