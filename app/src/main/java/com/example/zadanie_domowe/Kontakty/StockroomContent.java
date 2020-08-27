package com.example.zadanie_domowe.Kontakty;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class StockroomContent {


    public static final List<Stock> STOCKS = new ArrayList<Stock>();

    public static int counter;
/*
    private static final int COUNT =7;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }
    */
    public static void addItem(Stock item)
    {
        STOCKS.add(item);
        //ITEM_MAP.put(item.id, item);
    }

    public static void removeItem(int position)
    {
        /*
        String itemId = ITEMS.get(position).id;
        ITEMS.remove(position);
        ITEM_MAP.remove(itemId);
        */

        Stock temp = STOCKS.get(position);
        int id = temp.ID;

        //TODO delete from database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("zadd").document(Integer.toString(id)).delete();

        STOCKS.remove(position);
    }

    public static class Stock implements Parcelable {
        public final int ID;
        public final String address;
        public final String Manufacturer;
        public final String item;
        public final String Number;

        public Stock(int id, String address, String Manufacturer, String item, String Number) {
            this.ID = id;
            this.address = address;
            this.Manufacturer = Manufacturer;
            this.item = item;
            this.Number = Number;
        }

        public Stock(String address, String Manufacturer, String item, String Number) {
            this.ID = counter;
            this.address = address;
            this.Manufacturer = Manufacturer;
            this.item = item;
            this.Number = Number;
        }

        protected Stock(Parcel in) {
            ID = in.readInt();
            address = in.readString();
            Manufacturer = in.readString();
            item = in.readString();
            Number = in.readString();
        }

        public static final Creator<Stock> CREATOR = new Creator<Stock>()
        {
            @Override
            public Stock createFromParcel(Parcel in) {
                return new Stock(in);
            }

            @Override
            public Stock[] newArray(int size) {
                return new Stock[size];
            }
        };


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(ID);
            dest.writeString(address);
            dest.writeString(Manufacturer);
            dest.writeString(item);
            dest.writeString(Number);
        }

    }

}
