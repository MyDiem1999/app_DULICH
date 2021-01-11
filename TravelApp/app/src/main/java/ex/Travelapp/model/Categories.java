package ex.Travelapp.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Categories {
    String name;
    ArrayList<Furniture> arrayList;
    String image;
    int id;

    public Categories(String name, ArrayList<Furniture> arrayList, String image) {
        this.name = name;
        this.arrayList = arrayList;
        this.image = image;
    }

    public Categories(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Categories(String name, String image, int id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Furniture> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Furniture> arrayList) {
        this.arrayList = arrayList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static Bitmap convertStringToBitmapFromAccess(Context context, String filename){
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return  bitmap;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
