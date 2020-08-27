package com.example.zadanie_domowe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.zadanie_domowe.Kontakty.StockroomContent;


public class StockInfoFragment extends Fragment {

    public StockInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stock_info, container, false);
    }

    public void displayStock(StockroomContent.Stock stock){
        FragmentActivity activity = getActivity();

        TextView addressEditTxt = activity.findViewById(R.id.address);
        TextView manufacturerEditTxt = activity.findViewById(R.id.Manufacturer);
        TextView itemEditTxt = activity.findViewById(R.id.item);
        TextView numberEditTxt = activity.findViewById(R.id.number);

        String address = stock.address;
        String manufacturer = stock.Manufacturer;
        String item = stock.item;
        String number = stock.Number;
        addressEditTxt.setText("Address: "+address);
        manufacturerEditTxt.setText("Manufacturer: "+manufacturer);
        itemEditTxt.setText("item: "+item);
        numberEditTxt.setText("Quantity: "+number);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();
        if(intent != null){
           StockroomContent.Stock recivedStock = intent.getParcelableExtra(MainActivity.StockEdit);
            if(recivedStock != null) {
                displayStock(recivedStock);
            }
        }
    }

}
