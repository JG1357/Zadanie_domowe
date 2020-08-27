package com.example.zadanie_domowe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.zadanie_domowe.Kontakty.StockroomContent;

public class EditDialog extends DialogFragment {

    private String address;
    private StockroomContent.Stock mStock;

    public EditDialog(StockroomContent.Stock stock) {
        this.address = stock.address;
        this.mStock = stock;
    }

    static EditDialog newInstance(StockroomContent.Stock stock){
        return new EditDialog(stock);
    }

    public interface OnEditDialogInteractionListener {
        void onEditDialogPositiveClick(DialogFragment dialog, StockroomContent.Stock stock);
        void onEditDialogNegativeClick(DialogFragment dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Edit item on stock ?");

        builder.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OnEditDialogInteractionListener mListener =  (OnEditDialogInteractionListener) getActivity();
                mListener.onEditDialogPositiveClick(EditDialog.this, mStock);
            }
        });

        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OnEditDialogInteractionListener mListener =  (OnEditDialogInteractionListener) getActivity();
                mListener.onEditDialogNegativeClick(EditDialog.this);
            }
        });
        return builder.create();
    }
}
