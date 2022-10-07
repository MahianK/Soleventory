package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProduct extends AppCompatActivity {
    private EditText itemname, itemsku, itemprice, itemcolor, itemsize;
    private FirebaseAuth firebaseAuth;
    Button additemtodatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReferencecat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReferencecat = FirebaseDatabase.getInstance().getReference("Users");
        additemtodatabase = findViewById(R.id.additembuttontodatabase);
        itemname = findViewById(R.id.edititemname);
        itemsku = findViewById(R.id.editSKU);
        itemprice = findViewById(R.id.editprice);
        itemcolor = findViewById(R.id.editColor);
        itemsize = findViewById(R.id.editSize);



        additemtodatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additem();
            }
        });


    }


    // adding item to database
    public void additem() {
        String itemnameValue = itemname.getText().toString();
        String itemskuValue = itemsku.getText().toString();
        String itempriceValue = itemprice.getText().toString();
        String itemcolorValue = itemcolor.getText().toString();
        String itemsizeValue = itemsize.getText().toString();
        final FirebaseUser users = firebaseAuth.getCurrentUser();
        String finaluser = users.getEmail();
        String resultemail = finaluser.replace(".", "");

        if (!TextUtils.isEmpty(itemnameValue) && !TextUtils.isEmpty(itemskuValue) && !TextUtils.isEmpty(itempriceValue) && !TextUtils.isEmpty(itemcolorValue) && !TextUtils.isEmpty(itemsizeValue)){

            Items items = new Items(itemnameValue, itemskuValue, itempriceValue, itemcolorValue,itemsizeValue);
            databaseReference.child(resultemail).child("Items").setValue(items);
            databaseReferencecat.child(resultemail).child("ItemBySKU").child(itemskuValue).setValue(items);
            itemname.setText("");
            itemprice.setText("");
            itemsize.setText("");
            itemcolor.setText("");
            Toast.makeText(AddProduct.this, itemnameValue + " Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AddProduct.this, "Please Fill all the fields", Toast.LENGTH_SHORT).show();
        }

        }



}







