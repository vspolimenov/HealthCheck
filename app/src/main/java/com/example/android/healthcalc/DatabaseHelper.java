package com.example.android.healthcalc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;


/**
 * Created by Fotev on 10/11/2016.
 */
public class DatabaseHelper extends SQLiteAssetHelper {
    //Database description
    private static final String DATABASE_NAME = "foods.db";
    private static final int DATABASE_VERSION = 1;
    SQLiteDatabase db;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //make query to find an item in the food table with specific name
    public ArrayList<Food> searchInFoodTable(String itemName) {


        //If we have String send to the function
        //query to the database is send
        if (itemName == null)
            return null;

        ArrayList<Food> tempList = new ArrayList<Food>();
        db = this.getReadableDatabase();


        String selection = FoodsDb.FoodInfo.NAME_COLUMN + " LIKE ?";
        String[] selectionArgs = {itemName};

        Cursor c = db.query(
                FoodsDb.FoodInfo.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        //If the query has results we fill the Food class with info from the db
        if (c.moveToFirst() == true && c != null) {
            do {
                Food tempFood = new Food();
                tempFood.setmIntId(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.ID_COLUMN)));
                tempFood.setmStrName(c.getString(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.NAME_COLUMN)));
                tempFood.setmIntCalories(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.CALORIES_COLUMN)));
                tempFood.setmIntProtein(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.PROTEIN_COLUMN)));
                tempFood.setmIntCarbs(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.CARBS_COLUMN)));
                tempFood.setmIntFats(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.FATS_COLUMN)));
                tempFood.setmIntVitA(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.VITA_COLUMN)));
                tempFood.setmIntVitB6(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.VITB6_COLUMN)));
                tempFood.setmIntVitC(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.VITC_COLUMN)));
                tempFood.setmIntVitD(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.VITD_COLUMN)));
                tempFood.setmIntZinc(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.ZINC_COLUMN)));
                tempFood.setmIntMagnesium(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.MAGNESIUM_COLUMN)));
                tempFood.setmIntIron(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.IRON_COLUMN)));

                tempList.add(tempFood);

            } while (c.moveToNext() != false);
        }

        //Closing cursor and database
        c.close();
        db.close();

        //Retrieving the Arraylist filled with data based on the query
        return tempList;
    }

    //make query to find an item in the food table which starts with specific name
    public ArrayList<Food> searchInFoodTableWK(String itemName) {


        //If we have String send to the function
        //query to the database is send
        if (itemName == null)
            return null;

        ArrayList<Food> tempList = new ArrayList<Food>();

        if (itemName.length() < 2)
            return tempList;

        db = this.getReadableDatabase();


        String selection = FoodsDb.FoodInfo.NAME_COLUMN + " LIKE ?";
        itemName = itemName + "%";
        String[] selectionArgs = {itemName};

        Cursor c = db.query(
                FoodsDb.FoodInfo.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        //If the query has results we fill the Food class with info from the db
        if (c.moveToFirst() == true && c != null) {
            do {
                Food tempFood = new Food();
                tempFood.setmIntId(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.ID_COLUMN)));
                tempFood.setmStrName(c.getString(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.NAME_COLUMN)));
                tempFood.setmIntCalories(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.CALORIES_COLUMN)));
                tempFood.setmIntProtein(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.PROTEIN_COLUMN)));
                tempFood.setmIntCarbs(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.CARBS_COLUMN)));
                tempFood.setmIntFats(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.FATS_COLUMN)));
                tempFood.setmIntVitA(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.VITA_COLUMN)));
                tempFood.setmIntVitB6(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.VITB6_COLUMN)));
                tempFood.setmIntVitC(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.VITC_COLUMN)));
                tempFood.setmIntVitD(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.VITD_COLUMN)));
                tempFood.setmIntZinc(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.ZINC_COLUMN)));
                tempFood.setmIntMagnesium(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.MAGNESIUM_COLUMN)));
                tempFood.setmIntIron(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.IRON_COLUMN)));

                tempList.add(tempFood);

            } while (c.moveToNext() != false);
        }

        //Closing cursor and database
        c.close();
        db.close();

        //Retrieving the Arraylist filled with data based on the query
        return tempList;
    }

    public boolean addItemInFoodTable(String itemName, int itemCalories, int itemProtein, int itemCarbs,
                                      int itemFats, int itemVitA, int itemVitB6, int itemVitC,
                                      int itemVitD, int itemZinc, int itemMagnesium, int itemIron) {

        //Name is mandatory , if no name is send, no items are added to DB
        if (itemName == null) return false;

        //If name is added in the function call , the item is inserted in the DB
        db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FoodsDb.FoodInfo.NAME_COLUMN, itemName);
        contentValues.put(FoodsDb.FoodInfo.CALORIES_COLUMN, itemCalories);
        contentValues.put(FoodsDb.FoodInfo.PROTEIN_COLUMN, itemProtein);
        contentValues.put(FoodsDb.FoodInfo.CARBS_COLUMN, itemCarbs);
        contentValues.put(FoodsDb.FoodInfo.FATS_COLUMN, itemFats);
        contentValues.put(FoodsDb.FoodInfo.VITA_COLUMN, itemVitA);
        contentValues.put(FoodsDb.FoodInfo.VITB6_COLUMN, itemVitB6);
        contentValues.put(FoodsDb.FoodInfo.VITC_COLUMN, itemVitC);
        contentValues.put(FoodsDb.FoodInfo.VITD_COLUMN, itemVitD);
        contentValues.put(FoodsDb.FoodInfo.ZINC_COLUMN, itemZinc);
        contentValues.put(FoodsDb.FoodInfo.MAGNESIUM_COLUMN, itemMagnesium);
        contentValues.put(FoodsDb.FoodInfo.IRON_COLUMN, itemIron);

        //Check if the item is add
        long check = db.insert(FoodsDb.FoodInfo.TABLE_NAME, null, contentValues);
        if (check == -1) return false;

        db.close();
        return true;
    }

    //Inserting item/items in the database
    public boolean addItemInFoodTable(Food food) {
        //Same function for adding items in DB, but using class as input parameter
        if (food == null) return false;

        db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FoodsDb.FoodInfo.NAME_COLUMN, food.getmStrName());
        contentValues.put(FoodsDb.FoodInfo.CALORIES_COLUMN, food.getmIntCalories());
        contentValues.put(FoodsDb.FoodInfo.PROTEIN_COLUMN, food.getmIntProtein());
        contentValues.put(FoodsDb.FoodInfo.CARBS_COLUMN, food.getmIntCarbs());
        contentValues.put(FoodsDb.FoodInfo.FATS_COLUMN, food.getmIntFats());
        contentValues.put(FoodsDb.FoodInfo.VITA_COLUMN, food.getmIntVitA());
        contentValues.put(FoodsDb.FoodInfo.VITB6_COLUMN, food.getmIntVitB6());
        contentValues.put(FoodsDb.FoodInfo.VITC_COLUMN, food.getmIntVitC());
        contentValues.put(FoodsDb.FoodInfo.VITD_COLUMN, food.getmIntVitD());
        contentValues.put(FoodsDb.FoodInfo.ZINC_COLUMN, food.getmIntZinc());
        contentValues.put(FoodsDb.FoodInfo.MAGNESIUM_COLUMN, food.getmIntMagnesium());
        contentValues.put(FoodsDb.FoodInfo.IRON_COLUMN, food.getmIntIron());

        long check = db.insert(FoodsDb.FoodInfo.TABLE_NAME, null, contentValues);
        if (check == -1) return false;

        db.close();
        return true;
    }

    public boolean addItemInDailyNutrTable(int foodId, String date, int quantity) {
        db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FoodsDb.DailyNutrition.FOODID_COLUMN, foodId);
        contentValues.put(FoodsDb.DailyNutrition.DATE_COLUMN, date);
        contentValues.put(FoodsDb.DailyNutrition.QUANTITY_COLUMN, quantity);

        long check = db.insert(FoodsDb.DailyNutrition.TABLE_NAME, null, contentValues);
        if (check == -1) return false;

        db.close();
        return true;
    }

    public ArrayList<Food> searchForDiary(String itemDate) {


        //If we have String send to the function
        //query to the database is send
        if (itemDate == null)
            return null;

        ArrayList<Food> tempList = new ArrayList<Food>();
        db = this.getReadableDatabase();


        String selection = FoodsDb.DailyNutrition.DATE_COLUMN + " LIKE ?";
        String[] selectionArgs = {itemDate};

        Cursor c = db.query(
                FoodsDb.FoodInfo.TABLE_NAME + " INNER JOIN " + FoodsDb.DailyNutrition.TABLE_NAME + " ON " + FoodsDb.FoodInfo.ID_COLUMN +
                        "=" + FoodsDb.DailyNutrition.TABLE_NAME + "." + FoodsDb.DailyNutrition.FOODID_COLUMN,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        //If the query has results we fill the Food class with info from the db
        if (c.moveToFirst() == true && c != null) {
            do {
                Food tempFood = new Food();
                tempFood.setmIntId(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.ID_COLUMN)));
                tempFood.setmStrName(c.getString(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.NAME_COLUMN)));
                tempFood.setmIntCalories(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.CALORIES_COLUMN)));
                tempFood.setmIntProtein(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.PROTEIN_COLUMN)));
                tempFood.setmIntCarbs(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.CARBS_COLUMN)));
                tempFood.setmIntFats(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.FATS_COLUMN)));
                tempFood.setmIntVitA(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.VITA_COLUMN)));
                tempFood.setmIntVitB6(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.VITB6_COLUMN)));
                tempFood.setmIntVitC(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.VITC_COLUMN)));
                tempFood.setmIntVitD(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.VITD_COLUMN)));
                tempFood.setmIntZinc(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.ZINC_COLUMN)));
                tempFood.setmIntMagnesium(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.MAGNESIUM_COLUMN)));
                tempFood.setmIntIron(c.getInt(c.getColumnIndexOrThrow(FoodsDb.FoodInfo.IRON_COLUMN)));
                tempFood.setmIntQuantity(c.getInt(c.getColumnIndexOrThrow(FoodsDb.DailyNutrition.QUANTITY_COLUMN)));


                tempList.add(tempFood);

            } while (c.moveToNext() != false);
        }

        //Closing cursor and database
        c.close();
        db.close();

        //Retrieving the Arraylist filled with data based on the query
        return tempList;
    }
}
