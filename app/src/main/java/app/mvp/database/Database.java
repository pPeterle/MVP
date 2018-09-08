package app.mvp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.mvp.model.Establishment;

public class Database {
    private SQLiteDatabase database;
    private DatabaseCore db;

    public Database(Context context) {
        db = new DatabaseCore(context);
    }

    public void open() {
        database = db.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    /*public void addEstablishments(Establishment establishment) {
        ContentValues values = new ContentValues();
        values.put("uuid", establishment.getUUID());
        values.put("name", establishment.getName());

        database.insert("establishment", null, values);
    }

    public List<Establishment> getEstablishments() {
        String[] columns = {"uuid", "photo", "name", "city", "state"};

        String sortOrder = "name" + " ASC";
        List<Establishment> establishmentList = new ArrayList<>();

        Cursor cursor = database.query("establishment", columns, null, null, null, null, sortOrder);

        if (cursor.moveToFirst()) {
            do {
                Establishment establishment = new Establishment();

                establishment.setUUID(cursor.getString(cursor.getColumnIndex("uuid")));
                establishment.setName(cursor.getString(cursor.getColumnIndex("name")));
                establishmentList.add(establishment);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return establishmentList;
    }

    public void updateEstablishment(Establishment establishment) {
        ContentValues values = new ContentValues();
        values.put("name", establishment.getName());

        database.update("establishment", values, "uuid" + " = ?",
                new String[]{String.valueOf(establishment.getUUID())});
    }

    public void deleteEstablishment(Establishment establishment) {
        database.delete("establishment", "uuid" + " = ?",
                new String[]{String.valueOf(establishment.getUUID())});
    }

    public void deleteEstablishments() {
        database.delete("establishment", null, null);
    }*/
}
