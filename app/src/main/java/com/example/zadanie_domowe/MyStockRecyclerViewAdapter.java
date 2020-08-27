package com.example.zadanie_domowe;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.zadanie_domowe.Kontakty.StockroomContent.Stock;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link /*KontatktItem} and makes a call to the
 * specified {@link /*OnListFragmentInteractionListener*//*}.
 * TODO: Replace the implementation with code for your data type.
 */

public class MyStockRecyclerViewAdapter extends RecyclerView.Adapter<MyStockRecyclerViewAdapter.ViewHolder> {

    private final List<Stock> mValues;
    public final StockFragment.OnListFragmentInteractionListener mListener;

    public MyStockRecyclerViewAdapter(List<Stock> items, StockFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_stock, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Stock mStock = mValues.get(position);
        holder.mItem = mStock;
        holder.mManufacturerView.setText(mStock.Manufacturer);
        //holder.mIdView.setText(mValues.get(position).id);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentClickInteraction(holder.mItem, v);

                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mListener.onListFragmentLongClickInteraction(holder.mItem);
                return false;
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDeleteButtonClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView mManufacturerView;
        public Stock mItem;
        public ImageButton deleteButton;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mManufacturerView = view.findViewById(R.id.manufacturer);
            deleteButton = view.findViewById(R.id.delete);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mManufacturerView.getText() + "'";
        }
    }
}
