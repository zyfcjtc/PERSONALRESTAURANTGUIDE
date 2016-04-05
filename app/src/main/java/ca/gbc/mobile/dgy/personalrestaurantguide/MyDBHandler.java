package ca.gbc.mobile.dgy.personalrestaurantguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/*********************************
 *    Students:                  *
 * David Olano - ID: 100847924   *
 * Yafan Zhang - ID: 100816652   *
 * Gary  Chan  - ID: 100882663   *
 *                               *
 ********************************/
public class MyDBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="restaurants.db";
    public static final int DATABASE_VERSION=1;
    //tables and columns
    //id,name,address,phone,tag,description,rate,website,pic,lat,lng
    public static final String TABLE_RESTAURANTS = "restaurants";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_ADDRESS="address";
    public static final String COLUMN_PHONE="phone";
    public static final String COLUMN_TAG="tag";
    public static final String COLUMN_DESCRIPTION="description";
    public static final String COLUMN_RATE="rate";
    public static final String COLUMN_WEBSITE="website";
    public static final String COLUMN_IMAGE="image";
    public static final String COLUMN_LATITUDE="latitude";
    public static final String COLUMN_LONGITUDE="longitude";

    public static final String[] Allcolumn={COLUMN_ID,COLUMN_NAME,COLUMN_ADDRESS,COLUMN_PHONE,COLUMN_TAG,COLUMN_DESCRIPTION,
                                      COLUMN_RATE,COLUMN_WEBSITE,COLUMN_IMAGE,COLUMN_LATITUDE,COLUMN_LONGITUDE};


    //create statement
    //id,name,address,phone,tag,description,rate,website,pic,lat,lng
    public static final String DATABASE_CREATE =
            "create table "
                    + TABLE_RESTAURANTS
                    + "("+COLUMN_ID     + " integer primary key autoincrement,"
                    +     COLUMN_NAME   + " text not null,"
                    +     COLUMN_ADDRESS+ " text not null,"
                    +     COLUMN_PHONE  + " text not null,"
                    +     COLUMN_TAG    + " text not null,"
                    +     COLUMN_DESCRIPTION+ " text not null,"
                    +     COLUMN_RATE   + " real not null,"
                    +     COLUMN_WEBSITE+ " text not null,"
                    +     COLUMN_IMAGE  + " integer not null,"
                    +     COLUMN_LATITUDE+ " real not null,"
                    +     COLUMN_LONGITUDE+ " real not null);";

    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w(MyDBHandler.class.getName(),
                "Create database " + DATABASE_NAME
                        + " version " + DATABASE_VERSION);
        db.execSQL(DATABASE_CREATE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersionNo,
                          int newVersionNo) {

        Log.w(MyDBHandler.class.getName(),
                "Upgrade database from version " + oldVersionNo +
                        " to version " + newVersionNo);
        //this should contain all changes that
        // you made in your database structure
        // starting from version 1 up

        db.execSQL("Drop table if exists " + TABLE_RESTAURANTS);
        onCreate(db);

    }
    public void createRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
      //id,name,address,phone,tag,description,rate,website,pic,lat,lng

        values.put(COLUMN_NAME, restaurant.getName());
        values.put(COLUMN_ADDRESS, restaurant.getAddress());
        values.put(COLUMN_PHONE, restaurant.getPhone());
        values.put(COLUMN_TAG, restaurant.getTag());
        values.put(COLUMN_DESCRIPTION, restaurant.getDescription());
        values.put(COLUMN_RATE, restaurant.getRate());
        values.put(COLUMN_WEBSITE, restaurant.getWebsite());
        values.put(COLUMN_IMAGE, restaurant.getImage());
        values.put(COLUMN_LATITUDE, restaurant.getLatitude());
        values.put(COLUMN_LONGITUDE, restaurant.getLongitude());

       long insertId =
                db.insert(
                        TABLE_RESTAURANTS,
                        null,
                        values);

        Cursor cursor = db.query(
                TABLE_RESTAURANTS,//table name
                Allcolumn,//list of columns (prjection)
                COLUMN_ID+" = "+insertId,//WHERE
                null,//parameters
                null,//groupBy
                null,//having
                null);//orderBy
        cursor.moveToFirst();
        Restaurant c = cursor2Restaurant(cursor);
        cursor.close();


    }

    public Restaurant cursor2Restaurant(Cursor cursor){
        //id,name,address,phone,tag,description,rate,website,pic,lat,lng
        Restaurant restaurant = new Restaurant();
        restaurant.setRest_id(cursor.getInt(0));
        restaurant.setName(cursor.getString(1));
        restaurant.setAddress(cursor.getString(2));
        restaurant.setPhone(cursor.getString(3));
        restaurant.setTag(cursor.getString(4));
        restaurant.setDescription(cursor.getString(5));
        restaurant.setRate(cursor.getFloat(6));
        restaurant.setWebsite(cursor.getString(7));
        restaurant.setImage(cursor.getInt(8));
        restaurant.setLatitude(cursor.getDouble(9));
        restaurant.setLongitude(cursor.getDouble(10));
        return restaurant;
    }
    public void UpdateRate(float rate,int id)
    {
            SQLiteDatabase db = getWritableDatabase();
            String strSQL = "UPDATE restaurants SET rate = " + rate + " WHERE _id = " + id;

            db.execSQL(strSQL);

    }

    public List<Restaurant> getTop10Restaurants() {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RESTAURANTS+" ORDER BY "+COLUMN_RATE+" DESC ", null);
       //id,name,address,phone,tag,description,rate,website,pic,lat,lng
        if (cursor.moveToFirst()) {
            do {
                restaurants.add(new Restaurant(
                        cursor.getInt(0),
                        cursor.getString(1)/*name*/,
                        cursor.getString(2)/*address*/
                        ,cursor.getString(3)/*phone*/,
                        cursor.getString(4)/*tag*/,
                        cursor.getString(5)/*description*/,
                        cursor.getFloat(6)/*rate*/,
                        cursor.getString(7)/*website*/,
                        cursor.getInt(8)/*image*/,
                        cursor.getDouble(9)/*lat*/,
                        cursor.getDouble(10)/*long*/));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return restaurants;
    }
    public boolean default_not_exist()
    {
        List<Restaurant> restaurantt = new ArrayList<Restaurant>();
        restaurantt=getTop10Restaurants();
        if(restaurantt.size()==0)
        {
            return true;
        }
        return false;
    }
    public List<Restaurant> searchRestaurants(String string) {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        Cursor cursor = null;
        SQLiteDatabase db = getWritableDatabase();
        try {

        cursor=db.rawQuery("SELECT * FROM restaurants where name like '%"+string+"%' order by rate DESC ", null);
        } catch (Exception e) {
            String msg = e.toString();
            Log.i("", msg);
        }
        if(cursor==null)
        {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_RESTAURANTS+" ORDER BY "+COLUMN_RATE+" DESC ", null);
        }
        if (cursor.moveToFirst()) {
            do {
                restaurants.add(new Restaurant(cursor.getInt(0)/*id*/,cursor.getString(1)/*name*/,cursor.getString(2)/*address*/
                        ,cursor.getString(3)/*phone*/,cursor.getString(4)/*tag*/,cursor.getString(5)/*description*/,cursor.getFloat(6)/*rate*/,
                        cursor.getString(7)/*website*/,cursor.getInt(8)/*image*/,cursor.getDouble(9)/*lat*/,cursor.getDouble(10)/*long*/));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return restaurants;
    }
    public Restaurant searchRestaurantsbyId(int id) {
        Restaurant restaurant=new Restaurant();
        Cursor cursor = null;
        SQLiteDatabase db = getWritableDatabase();
        try {

            cursor=db.rawQuery("SELECT * FROM restaurants where _id = "+id+" ", null);
        } catch (Exception e) {
            String msg = e.toString();
            Log.i("", msg);
        }
        if (cursor.moveToFirst()) {
            do {
                restaurant.setRest_id(cursor.getInt(0));
                restaurant.setName(cursor.getString(1));
                restaurant.setAddress(cursor.getString(2));
                restaurant.setPhone(cursor.getString(3));
                restaurant.setTag(cursor.getString(4));
                restaurant.setDescription(cursor.getString(5));
                restaurant.setRate(cursor.getFloat(6));
                restaurant.setWebsite(cursor.getString(7));
                restaurant.setImage(cursor.getInt(8));
                restaurant.setLatitude(cursor.getDouble(9));
                restaurant.setLongitude(cursor.getDouble(10));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return restaurant;
    }
    public void UpdateRestaurant(String column, String value,int id)
    {
        if(value==null)
        {
            value="not set";
        }
        SQLiteDatabase db = getWritableDatabase();
        String strSQL = "UPDATE restaurants SET "+column+" = '" + value + "' WHERE _id = " + id;

        db.execSQL(strSQL);

    }

}
