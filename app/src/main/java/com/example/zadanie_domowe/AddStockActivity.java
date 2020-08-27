package com.example.zadanie_domowe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zadanie_domowe.Kontakty.StockroomContent.Stock;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddStockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);

        addCustomer();
    }

    @SuppressLint("WrongViewCast")
    private void addCustomer() {

        Button addButton;

        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText AddressEditTxt;
                EditText ManufacturerEditTxt;
                EditText ItemEditTxt;
                EditText NumberEditTxt;

                AddressEditTxt = findViewById(R.id.Address);
                ManufacturerEditTxt = findViewById(R.id.Manufacturer);
                ItemEditTxt = findViewById(R.id.item);
                NumberEditTxt = findViewById(R.id.Number);

                String address;
                String manufacturer;
                String item;
                String number;

                item = ItemEditTxt.getText().toString();
                number = NumberEditTxt.getText().toString();
                address = AddressEditTxt.getText().toString();
                manufacturer = ManufacturerEditTxt.getText().toString();


                Stock newStock = new Stock(address, manufacturer, item, number);


                // TODO firabase adding data
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("zadd").document(Integer.toString(newStock.ID)).set(newStock);


                ItemEditTxt.setText("");
                NumberEditTxt.setText("");
                AddressEditTxt.setText("");
                ManufacturerEditTxt.setText("");

                finish();
            }
        });
    }
}
