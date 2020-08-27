package com.example.zadanie_domowe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.zadanie_domowe.Kontakty.StockroomContent;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class StockEditFragment extends Fragment {

    private int id;
    private String address;
    private String manufacturer;
    private String item;
    private String number;

    private EditText addressEditTxt;
    private EditText manufacturerEditTxt;
    private EditText itemEditTxt;
    private EditText numberEditTxt;
    private Button saveBtn;

    public StockEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stock_edit, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = getActivity();

        addressEditTxt = activity.findViewById(R.id.address);
        manufacturerEditTxt = activity.findViewById(R.id.manufacturer);
        itemEditTxt = activity.findViewById(R.id.item);
        numberEditTxt = activity.findViewById(R.id.number);
        saveBtn = activity.findViewById(R.id.saveButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        Intent intent = getActivity().getIntent();
        if(intent != null){
            StockroomContent.Stock recivedStock = intent.getParcelableExtra(MainActivity.StockEdit);
            if(recivedStock != null) {
                displayStock(recivedStock);
            }
        }

    }

    public void saveChanges(){


        address = addressEditTxt.getText().toString();
        manufacturer = manufacturerEditTxt.getText().toString();
        item = itemEditTxt.getText().toString();
        number = numberEditTxt.getText().toString();

        // TODO save changes to db
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("zadd").document(Integer.toString(id));

        docRef.update("Address", address,
                            "Manufacturer", manufacturer,
                            "item", item,
                            "Number", number);

        getActivity().finish();
    }

    public void displayStock(StockroomContent.Stock stock){

        id = stock.ID;
        address = stock.address;
        manufacturer = stock.Manufacturer;
        item = stock.item;
        number = stock.Number;

        addressEditTxt.setText(address);
        manufacturerEditTxt.setText(manufacturer);
        itemEditTxt.setText(item);
        numberEditTxt.setText(number);
    }
}
