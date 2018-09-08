package app.mvp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseCore extends SQLiteOpenHelper {
    // Banco
    private static final String DATABASE_NAME = "banco.db";

    // Versão
    private static final int DATABASE_VERSION = 1;

    // Tabela (Estabelecimentos)
    private static final String TABLE_ESTABLISHMENT = "establishment";

    // Campos
    private static final String COLUMN_ESTABLISHMENT_ID = "id";

    private static final String COLUMN_ESTABLISHMENT_UUID = "uuid";
    private static final String COLUMN_ESTABLISHMENT_NAME = "name";

    DatabaseCore(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_ESTABLISHMENT_TABLE = "CREATE TABLE " + TABLE_ESTABLISHMENT + "("
                + COLUMN_ESTABLISHMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ESTABLISHMENT_UUID + " TEXT,"
                + COLUMN_ESTABLISHMENT_NAME + " TEXT"
                + ")";
        database.execSQL(CREATE_ESTABLISHMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String DROP_ESTABLISHMENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_ESTABLISHMENT;
        database.execSQL(DROP_ESTABLISHMENT_TABLE);
        onCreate(database);
    }
}
