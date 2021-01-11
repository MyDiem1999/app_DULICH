package ex.Travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ex.Travelapp.model.Furniture;
import ex.Travelapp.model.Utils;

public class DetailActivity extends AppCompatActivity {
    TextView textName, textDes;
    ImageView imageView;
    ArrayList<Furniture> arrayList;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        utils = new Utils(this);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        textDes = findViewById(R.id.discription);
        imageView = findViewById(R.id.image);


        Intent intent = getIntent();
        Bundle furniter = intent.getExtras();

        if(furniter!=null)
        {
            String img = furniter.getString("image");
            String name = furniter.getString("name");
            textDes.setText(furniter.getString("description"));
            getSupportActionBar().setTitle(name);
            imageView.setImageBitmap(Furniture.convertStringToBitmapFromAccess(this, img));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}