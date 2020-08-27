package com.example.zadanie_domowe;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.zadanie_domowe.Kontakty.StockroomContent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static com.example.zadanie_domowe.Kontakty.StockroomContent.STOCKS;

public class MainActivity extends AppCompatActivity implements
        StockFragment.OnListFragmentInteractionListener,
        DeleteDialog.OnDeleteDialogInteractionListener,
        EditDialog.OnEditDialogInteractionListener {

    int selectedDeleteItem = -1;
    public static  String StockEdit = "StockEdit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddStockActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        getFirebaseData();

    }
    public void getFirebaseData(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("zadd")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            STOCKS.clear();

                            for (QueryDocumentSnapshot document : task.getResult()) {


                                Map<String, Object> stock = new HashMap<>();
                                stock = document.getData();

                                Object oId = stock.get("ID");
                                Object oAddress = stock.get("address");
                                Object oManufacturer = stock.get("Manufacturer");
                                Object oitem = stock.get("item");
                                Object oNumber = stock.get("Number");

                                int id = Integer.parseInt(oId.toString());
                                String address = oAddress.toString();
                                String manufacturer = oManufacturer.toString();
                                String item = oitem.toString();
                                String number = oNumber.toString();

                                StockroomContent.Stock stockFromFireBase = new StockroomContent.Stock(id, address, manufacturer, item, number);

                                StockroomContent.addItem(stockFromFireBase);
                                StockroomContent.counter = id+1;
                            }
                            ((StockFragment) getSupportFragmentManager().findFragmentById(R.id.StockFragment)).notifyDataChange();
                        }
                    }
                });

    }


    public void startStockInfoActivity(StockroomContent.Stock stock){
        Intent intent = new Intent(this, StockInfoActivity.class);
        intent.putExtra(StockEdit, stock);
        startActivity(intent);
    }

    public void startEditStockActivity(StockroomContent.Stock stock){
        Intent intent = new Intent(this, EditStockActivity.class);
        intent.putExtra(StockEdit, stock);
        startActivity(intent);
    }

    @Override
    public void onListFragmentClickInteraction(StockroomContent.Stock item, View view) {

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            displayStockInFragment(item);
        } else {
            startStockInfoActivity(item);
        }
    }

    @Override
    public void onListFragmentLongClickInteraction(StockroomContent.Stock item) {
        EditDialog.newInstance(item).show(getSupportFragmentManager(), getString(R.string.call));
    }

    @Override
    public void onDeleteButtonClick(int position) {
        selectedDeleteItem = position;
        DeleteDialog.newInstance().show(getSupportFragmentManager(), getString(R.string.delete_dialog_tag));
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if (selectedDeleteItem >= 0 && selectedDeleteItem < STOCKS.size()) {
            StockroomContent.removeItem(selectedDeleteItem);

            ((StockFragment) getSupportFragmentManager().findFragmentById(R.id.StockFragment)).notifyDataChange();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onEditDialogPositiveClick(DialogFragment dialog, StockroomContent.Stock stock) {

        startEditStockActivity(stock);

    }

    @Override
    public void onEditDialogNegativeClick(DialogFragment dialog) {

    }

    public void displayStockInFragment(StockroomContent.Stock stock){
        StockInfoFragment stockInfoFragment = ( (StockInfoFragment) getSupportFragmentManager().findFragmentById(R.id.StockInfoFragment));
        if(stockInfoFragment != null){
            stockInfoFragment.displayStock(stock);
        }
    }




}
