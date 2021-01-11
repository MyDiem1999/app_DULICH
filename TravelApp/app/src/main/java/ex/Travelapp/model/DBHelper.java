package ex.Travelapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Random;

public class DBHelper {
    Context context;
    Utils utils;

    String dbName = "FurnitureDB.db";

    public DBHelper(Context context) {
        this.context = context;
        utils = new Utils(context);

        //Hàm xóa toàn bộ dữ liệu trên database
//        context.deleteDatabase(dbName);
        createTable();
        insertCategories();
        insertFurniture();
    }

    private SQLiteDatabase openDB()
    {
        return  context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }

    private  void  closeDB(SQLiteDatabase db)
    {
        db.close();
    }

    public  void createTable() {
        SQLiteDatabase db = openDB();
        String sqlFurniture = "CREATE TABLE IF NOT EXISTS tblFurniture (" +
                " ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                " Name TEXT, " +
                " Image TEXT, " +
                " Description TEXT, " +
                " CategoriesID INTEGER );";

        String sqlCategories = "CREATE TABLE IF NOT EXISTS tbtCategories (" +
                " CategoriesID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " Name TEXT," +
                " Image TEXT );";

        db.execSQL(sqlFurniture);
        db.execSQL(sqlCategories);
        closeDB(db);
    }

    public ArrayList<Furniture> getAllFurniture()
    {
        SQLiteDatabase db = openDB();
        ArrayList<Furniture> arr = new ArrayList<>();
        String sql = "select * from tblFurniture";
        Cursor csr = db.rawQuery(sql, null);
        if(csr != null){
            if(csr.moveToFirst()){
                do{
                    int id = csr.getInt(0);
                    String name = csr.getString(1);
                    String image = csr.getString(2);
                    String description = csr.getString(3);
                    int categoriesID = csr.getInt(4);
                    arr.add(new Furniture(name, description, image, findByCategoriesID(categoriesID), id));
                }while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public  ArrayList<Categories> getAllCategories()
    {
        SQLiteDatabase db = openDB();
        ArrayList<Categories> arr = new ArrayList<>();
        String sql = "select * from tbtCategories limit 4";
        Cursor csr = db.rawQuery(sql, null);
        if(csr != null)
        {
            if(csr.moveToFirst())
            {
                do {
                    int id = csr.getInt(0);
                    String name = csr.getString(1);
                    String image = csr.getString(2);
                    arr.add(new Categories(name, image, id));
                }
                while (csr.moveToNext());
            }
        }
        closeDB(db);
        return  arr;
    }

    public Categories findByCategoriesID(int id)
    {
        ArrayList<Categories> arr = getAllCategories();
        for (Categories ca : arr)
        {
            if(ca.getId() == id)
            {
                return  ca;
            }
        }
        return  null;
    }

    public Categories addFurnitureToCategories(int categoriesId)
    {
        Categories categories =  findByCategoriesID(categoriesId);
        ArrayList<Furniture> arrFurniture = getAllFurniture();

        for (Furniture furniture : arrFurniture)
        {
            if(furniture.getCategories().getId() == categoriesId)
            {
                categories.getArrayList().add(furniture);
            }
        }
        return  categories;
    }

    public  void  insertCategories()
    {
        ArrayList<Categories> arrCa = utils.getMockDataCategories();
        SQLiteDatabase db = openDB();

        for (Categories ca : arrCa)
        {
            ContentValues cv = new ContentValues();
            cv.put("Name", ca.getName());
            cv.put("Image", ca.getImage());
            db.insert("tbtCategories", null, cv);
        }
        closeDB(db);
    }

    public  void  insertFurniture()
    {
        ArrayList<Furniture> arrFurniture = utils.getMockData();
        SQLiteDatabase db = openDB();
        Random random = new Random();

        for (Furniture fu : arrFurniture)
        {
            ContentValues cv = new ContentValues();
            cv.put("Name", fu.getName());
            cv.put("Image", fu.getSimage());
            cv.put("Description", fu.getDescription());
            cv.put("CategoriesID", random.nextInt(4) + 1);
            db.insert("tblFurniture", null, cv);
        }
        closeDB(db);
    }

    public void clearDatabase(SQLiteDatabase db){
        clearDatabase(db);
    }
}
