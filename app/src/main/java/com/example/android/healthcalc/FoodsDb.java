package com.example.android.healthcalc;

/**
 * Created by Fotev on 10/11/2016.
 */
public final class FoodsDb {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FoodsDb() {
    }

    /* Inner class that defines table FoodInfo contents */
    public static class FoodInfo {
        public static final String TABLE_NAME = "FoodInfo";
        public static final String ID_COLUMN = "Id";
        public static final String NAME_COLUMN = "Name";
        public static final String CALORIES_COLUMN = "Calories";
        public static final String PROTEIN_COLUMN = "Protein";
        public static final String CARBS_COLUMN = "Carbs";
        public static final String FATS_COLUMN = "Fats";
        public static final String VITA_COLUMN = "VitA";
        public static final String VITB6_COLUMN = "VitB6";
        public static final String VITC_COLUMN = "VitC";
        public static final String VITD_COLUMN = "VitD";
        public static final String ZINC_COLUMN = "Zinc";
        public static final String MAGNESIUM_COLUMN = "Magnesium";
        public static final String IRON_COLUMN = "Iron";
    }

    /* Inner class that defines table DailyNutrition contents */
    public static class DailyNutrition {
        public static final String TABLE_NAME = "DailyNutrition";
        public static final String FOODID_COLUMN = "FoodId";
        public static final String DATE_COLUMN = "Date";
        public static final String QUANTITY_COLUMN = "Quantity" ;
    }
}
