package com.example.zadanie_domowe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteDialog extends DialogFragment {

    public DeleteDialog() {
        // Required empty public constructor
    }

    static DeleteDialog newInstance(){
        return new DeleteDialog();
    }

    public interface OnDeleteDialogInteractionListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(getString(R.string.confirm_deleting));

        builder.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OnDeleteDialogInteractionListener mListener =  (OnDeleteDialogInteractionListener) getActivity();
                mListener.onDialogPositiveClick(DeleteDialog.this);
            }
        });

        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OnDeleteDialogInteractionListener mListener =  (OnDeleteDialogInteractionListener) getActivity();
                mListener.onDialogNegativeClick(DeleteDialog.this);
            }
        });
        return builder.create();
    }
}
