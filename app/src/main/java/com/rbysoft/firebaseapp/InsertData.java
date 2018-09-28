package com.rbysoft.firebaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rbysoft.firebaseapp.model.User;

import java.util.UUID;

public class InsertData extends AppCompatActivity implements View.OnClickListener {
    private EditText product_name,product_place,product_price;
    private Button insert_btn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        product_name = findViewById(R.id.edt_1);
        product_place = findViewById(R.id.edt_2);
        product_price = findViewById(R.id.edt_3);

        insert_btn = findViewById(R.id.insert_btn);
        insert_btn.setOnClickListener(this);

        initFirebase();
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.insert_btn){
            createUser();
            startActivity(new Intent(InsertData.this,DashBoard.class));
        }

    }

    private void createUser() {
        User user1 = new User(UUID.randomUUID().toString(),product_name.getText().toString(),product_place.getText().toString(),product_price.getText().toString());
        databaseReference.child("user").child(user1.getUid()).setValue(user1);
        clearEditText();
    }

    private void clearEditText() {
        product_name.setText("");
        product_place.setText("");
        product_price.setText("");
    }
}
