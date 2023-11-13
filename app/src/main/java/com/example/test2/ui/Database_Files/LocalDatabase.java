package com.example.test2.ui.Database_Files;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class LocalDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ppmsDB.db";
    private static final int DATABASE_VERSION = 1;

    //consumer table
    private static final String TABLE_NAME_CONSUMER = "Consumer";
    private static final String COLUMN_CONSUMER_ID = "Consumer_ID";
    private static final String COLUMN_CONSUMER_USERNAME = "Username";
    private static final String COLUMN_CONSUMER_PASSWORD = "Password";
    private static final String COLUMN_CONSUMER_METER = "Meter_Number";
    private static final String COLUMN_CONSUMER_LOCATION = "Location";


    //admin table
    private static final String TABLE_NAME_ADMIN = "System_Admin";
    private static final String COLUMN_ADMIN_NAME = "Username";
    private static final String COLUMN_ADMIN_PASSWORD = "Password";

    //kplc table
    private static final String TABLE_NAME_KPLC = "KPLC_Admin";
    private static final String COLUMN_KPLC_NAME = "Username";
    private static final String COLUMN_KPLC_PASSWORD = "Password";


    //meter table
    private static final String TABLE_NAME_METER = "Meter";
    private static final String COLUMN_METER_NUM = "Meter_Number";
    private static final String COLUMN_METER_LOCATION = "Location";
    private static final String COLUMN_METER_CUSTOMER = "Customer_ID";
    private static final String COLUMN_METER_USAGE = "USAGE";
    private static final String COLUMN_METER_TOKEN = "Token_Quantity";

    //token table
    private static final String TABLE_NAME_TOKEN = "Token";
    private static final String COLUMN_TOKEN_CUSTOMER = "Customer_ID";
    private static final String COLUMN_TOKEN_QUANTITY = "Quantity";
    private static final String COLUMN_TOKEN_PRICE = "Price";

    //usage
    private static final String TABLE_NAME_USAGE = "Usage";
    private static final String COLUMN_USAGE_AMOUNT = "Customer_ID";
    private static final String COLUMN_USAGE_USAGE = "Usage";
    private static final String COLUMN_USAGE_MTR = "Meter_Number";


    public LocalDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query1 = "CREATE TABLE " + TABLE_NAME_CONSUMER +
                " (" + COLUMN_CONSUMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CONSUMER_USERNAME + " TEXT, " +
                COLUMN_CONSUMER_PASSWORD + " TEXT, " +
                COLUMN_CONSUMER_METER + " INTEGER REFERENCES " + TABLE_NAME_METER + "(" + COLUMN_METER_NUM + "), " +
                COLUMN_CONSUMER_LOCATION + " TEXT);";

        String query2 = "CREATE TABLE " + TABLE_NAME_ADMIN +
                " (" + COLUMN_ADMIN_NAME + " TEXT PRIMARY KEY, " +
                COLUMN_ADMIN_PASSWORD + " TEXT);";

        String query3 = "CREATE TABLE " + TABLE_NAME_KPLC + " (" + COLUMN_KPLC_NAME + " TEXT PRIMARY KEY, " +
                COLUMN_KPLC_PASSWORD + " TEXT);";

        String query4 = "CREATE TABLE " + TABLE_NAME_METER +
                " (" + COLUMN_METER_NUM + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_METER_LOCATION + " TEXT REFERENCES " + TABLE_NAME_CONSUMER + "(" + COLUMN_CONSUMER_LOCATION + "), " +
                COLUMN_METER_CUSTOMER + " INTEGER REFERENCES " + TABLE_NAME_CONSUMER + "(" + COLUMN_CONSUMER_ID + "), " +
                COLUMN_METER_USAGE + " INTEGER REFERENCES " + TABLE_NAME_USAGE + "(" + COLUMN_USAGE_USAGE + "), " +
                COLUMN_METER_TOKEN + " INTEGER REFERENCES " + TABLE_NAME_TOKEN + "(" + COLUMN_TOKEN_QUANTITY + ") );";


        String query5 = "CREATE TABLE " + TABLE_NAME_TOKEN +
                " (" + COLUMN_TOKEN_CUSTOMER + " INTEGER PRIMARY KEY REFERENCES " + TABLE_NAME_CONSUMER + "(" + COLUMN_CONSUMER_ID + "), " +
                COLUMN_TOKEN_QUANTITY + " INTEGER, " +
                COLUMN_TOKEN_PRICE + " INTEGER);";

        String query6 = "CREATE TABLE " + TABLE_NAME_USAGE +
                " (" + COLUMN_USAGE_AMOUNT + " INTEGER PRIMARY KEY, " +
                COLUMN_USAGE_USAGE + " INTEGER, " +
                COLUMN_USAGE_MTR + " INTEGER REFERENCES " + TABLE_NAME_METER + "(" + COLUMN_METER_NUM + "));";

        //create tables queries
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME_CONSUMER);
        db.execSQL("DROP TABLE " + TABLE_NAME_ADMIN);
        db.execSQL("DROP TABLE " + TABLE_NAME_KPLC);
        db.execSQL("DROP TABLE " + TABLE_NAME_METER);
        db.execSQL("DROP TABLE " + TABLE_NAME_TOKEN);
        db.execSQL("DROP TABLE " + TABLE_NAME_USAGE);
        onCreate(db);


    }

    public void addConsumer(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CONSUMER_USERNAME, username);
        cv.put(COLUMN_CONSUMER_PASSWORD, password);

        long result = db.insert(TABLE_NAME_CONSUMER, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Insertion Faile", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(context, "User Created", Toast.LENGTH_SHORT).show();
        }

    }
}