package com.example.mdp.data.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.mdp.data.model.Meal
//
//@Database(entities = [Meal::class], version = 1, exportSchema = false)
//abstract class MealDatabase : RoomDatabase() {
//
//    abstract fun mealDao(): MealDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: MealDatabase? = null
//
//        fun getDatabase(context: Context): MealDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    MealDatabase::class.java,
//                    "meal_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}