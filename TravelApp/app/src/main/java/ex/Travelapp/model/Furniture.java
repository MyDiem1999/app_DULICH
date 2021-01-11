package ex.Travelapp.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class Furniture implements Serializable {
    String name;
    String description;
//    Bitmap image;
    String Simage;
    int id;
    Categories categories;

    public Furniture(String name, String description, String image, Categories byCategoriesID, int id) {
        this.name = name;
        this.description = description;
        this.Simage = image;
        this.categories = byCategoriesID;
        this.id = id;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Bitmap getImage() {
//        return image;
//    }
//
//    public void setImage(Bitmap image) {
//        this.image = image;
//    }

    public String getSimage() {
        return Simage;
    }

    public void setSimage(String simage) {
        Simage = simage;
    }

//    public Furniture(String name, String description, Bitmap image) {
//        this.name = name;
//        this.description = description;
//        this.image = image;
//    }

    public Furniture(String name, String description, String simage) {
        this.name = name;
        this.description = description;
        this.Simage = simage;
    }

    public static Bitmap convertStringToBitmapFromAccess(Context context, String filename)
    {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return  bitmap;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return  null;
    }
}
