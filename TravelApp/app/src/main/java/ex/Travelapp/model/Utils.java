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

//        tmp.add(new Furniture(context.getString(R.string.name_product_one), context.getString(R.string.product_one),
//                Furniture.convertStringToBitmapFromAccess(getContext(),"hinh_1.png")));
//        tmp.add(new Furniture(context.getString(R.string.name_product_two), context.getString(R.string.product_two),
//                Furniture.convertStringToBitmapFromAccess(getContext(),"hinh_2.png")));
//        tmp.add(new Furniture(context.getString(R.string.name_product_three), context.getString(R.string.product_three),
//                Furniture.convertStringToBitmapFromAccess(getContext(),"hinh_3.png")));
//        tmp.add(new Furniture(context.getString(R.string.name_product_four), context.getString(R.string.product_four),
//                Furniture.convertStringToBitmapFromAccess(getContext(),"hinh_4.png")));
//        tmp.add(new Furniture(context.getString(R.string.name_product_five), context.getString(R.string.product_five),
//                Furniture.convertStringToBitmapFromAccess(getContext(),"hinh_5.png")));

        tmp.add(new Furniture(context.getString(R.string.name_product_one), context.getString(R.string.product_one), "hinh_1.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_two), context.getString(R.string.product_two), "hinh_2.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_three), context.getString(R.string.product_three), "hinh_3.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_four), context.getString(R.string.product_four), "hinh_4.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_five), context.getString(R.string.product_five), "hinh_5.png"));

        return tmp;
    }

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
//                tmp.add(new Furniture(context.getString(R.string.name_product_one),
//                        context.getString(R.string.product_one), Furniture.convertStringToBitmapFromAccess(getContext(),"hinh_1.png")));
//                tmp.add(new Furniture(context.getString(R.string.name_product_two),
//                        context.getString(R.string.product_two),  Furniture.convertStringToBitmapFromAccess(getContext(),"hinh_2.png")));
//                tmp.add(new Furniture(context.getString(R.string.name_product_three),
//                        context.getString(R.string.product_three),  Furniture.convertStringToBitmapFromAccess(getContext(),"hinh_3.png")));

                tmp.add(new Furniture(context.getString(R.string.name_product_four),
                        context.getString(R.string.product_four), "hinh_4.png"));
                break;

            case 1:
                tmp.add(new Furniture(context.getString(R.string.name_product_three),
                        context.getString(R.string.product_three),"hinh_3.png"));
                tmp.add(new Furniture(context.getString(R.string.name_product_four),
                        context.getString(R.string.product_four), "hinh_5.png"));

//                tmp.add(new Furniture(context.getString(R.string.name_product_five),
//                        context.getString(R.string.product_five),  "hinh_2.png"));
                break;

            case 2:
                tmp.add(new Furniture(context.getString(R.string.name_product_two),
                        context.getString(R.string.product_two), "hinh_2.png"));

//                tmp.add(new Furniture(context.getString(R.string.name_product_three),
//                        context.getString(R.string.product_three), "hinh_3.png"));
//                tmp.add(new Furniture(context.getString(R.string.name_product_four),
//                        context.getString(R.string.product_four),  "hinh_4.png"));
//                tmp.add(new Furniture(context.getString(R.string.name_product_five),
//                        context.getString(R.string.product_five),  "hinh_5.png"));
                break;

            case 3:
                tmp.add(new Furniture(context.getString(R.string.name_product_three),
                        context.getString(R.string.product_three), "hinh_1.png"));

//                tmp.add(new Furniture(context.getString(R.string.name_product_four),
//                        context.getString(R.string.product_four),  "hinh_4.png"));
//                tmp.add(new Furniture(context.getString(R.string.name_product_five),
//                        context.getString(R.string.product_five),  "hinh_5.png"));
//                tmp.add(new Furniture(context.getString(R.string.name_product_one),
//                        context.getString(R.string.product_one),   "hinh_1.png"));
                break;
        }
        return tmp;
    }


}
