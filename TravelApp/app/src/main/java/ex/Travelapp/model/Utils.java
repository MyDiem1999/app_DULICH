package ex.Travelapp.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ex.Travelapp.R;

public class Utils {

    Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public Utils(Context context) {
        this.context = context;
    }

    public Utils() {
    }

    static String filename = "database";

    public static ArrayList<Furniture> furnitureHistory = new ArrayList<>();

    public static void addFurnitureHistory(Furniture furniture) {
        if (furnitureHistory.indexOf(furniture) > 0) {
            furnitureHistory.add(0, furniture);
        }
    }

    public static ArrayList<Furniture> getFurnitureHistory() {
        return furnitureHistory;
    }


    public void WriteToFileInternal() {
        try {
            ArrayList<Furniture> arrayList = getMockData();
            File file = new File(context.getFilesDir(), filename);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Furniture> LoadFileInternal() {
        ArrayList<Furniture> arrayList = new ArrayList<>();
        try {
            File file = new File(context.getFilesDir(), filename);
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            arrayList = (ArrayList<Furniture>) objectInputStream.readObject();
            Log.d("FurnitureApp", arrayList.size() + "");
            return arrayList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Bitmap convertStringToBitmapFromAccess(String filename) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Furniture> getMockData() {
        ArrayList<Furniture> tmp = new ArrayList<>();

        tmp.add(new Furniture(context.getString(R.string.name_product_one), context.getString(R.string.product_one), "phuquoc.jpg"));
        tmp.add(new Furniture(context.getString(R.string.name_product_two), context.getString(R.string.product_two), "vuontraicay.jpg"));
        tmp.add(new Furniture(context.getString(R.string.name_product_three), context.getString(R.string.product_three), "phanthiet.jpg"));
        tmp.add(new Furniture(context.getString(R.string.name_product_four), context.getString(R.string.product_four), "chonoicairang"));
        tmp.add(new Furniture(context.getString(R.string.name_product_five), context.getString(R.string.product_five), "ninhchudalat.jpg"));
        tmp.add(new Furniture(context.getString(R.string.name_product_six), context.getString(R.string.product_six), "danang.jpg"));
        tmp.add(new Furniture(context.getString(R.string.name_product_seven), context.getString(R.string.product_seven), "condao.jpg"));
        tmp.add(new Furniture(context.getString(R.string.name_product_eight), context.getString(R.string.product_eight), "mocchau.jpg"));

        return tmp;
    }

    //==================================== Dashboard ==================================================//

    public ArrayList<Categories> getMockDataCategories() {
        ArrayList<Categories> tmp = new ArrayList<>();
        tmp.add(new Categories("BedRoom","bed_room.png"));
        tmp.add(new Categories("LivingRoom","living_room.png"));
        tmp.add(new Categories("MeetingRoom","meeting_room.png"));
        tmp.add(new Categories("Accessories","accessories_room.png"));

        return tmp;
    }

    public ArrayList<Furniture> getFurnitureFromCategories(int pos) {
        ArrayList<Furniture> tmp = new ArrayList<>();
        switch (pos) {
            case 0:
                tmp.add(new Furniture(context.getString(R.string.name_product_one), context.getString(R.string.product_one), "phuquoc.jpg"));
                break;

            case 1:
                tmp.add(new Furniture(context.getString(R.string.name_product_two), context.getString(R.string.product_two), "vuontraicay.jpg"));
                break;

            case 2:
                tmp.add(new Furniture(context.getString(R.string.name_product_three), context.getString(R.string.product_three), "phanthiet.jpg"));
                break;

            case 3:
                tmp.add(new Furniture(context.getString(R.string.name_product_four), context.getString(R.string.product_four), "chonoicairang"));
                break;
            case 4:
                tmp.add(new Furniture(context.getString(R.string.name_product_five), context.getString(R.string.product_five), "ninhchudalat.jpg"));
                break;
            case 5:
                tmp.add(new Furniture(context.getString(R.string.name_product_six), context.getString(R.string.product_six), "danang.jpg"));
                break;
            case 6:
                tmp.add(new Furniture(context.getString(R.string.name_product_seven), context.getString(R.string.product_seven), "condao.jpg"));
                break;
            case 8:
                tmp.add(new Furniture(context.getString(R.string.name_product_eight), context.getString(R.string.product_eight), "mocchau.jpg"));
                break;
        }
        return tmp;
    }


}
