package ex.Travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import ex.Travelapp.adapter.FurnitureAdapter;
import ex.Travelapp.model.DBHelper;
import ex.Travelapp.model.Furniture;
import ex.Travelapp.model.Utils;
import me.gujun.android.taggroup.TagGroup;

public class SearchActivity extends AppCompatActivity {

    SearchView searchView;
    ArrayList<Furniture> arrayList;
    Utils utils;
    ListView listView;
    FurnitureAdapter furnitureAdapter;
    TagGroup mTagGroup;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        utils = new Utils(SearchActivity.this);
        dbHelper = new DBHelper(SearchActivity.this);
        arrayList = new ArrayList<>();

        listView = findViewById(R.id.listView);
        furnitureAdapter = new FurnitureAdapter(SearchActivity.this, arrayList);
        listView.setAdapter(furnitureAdapter);

//        Log.d("FurnitureApp", utils.LoadFileInternal().size() + "");

        searchView = findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchFurniture(newText);
                return false;
            }
        });

        mTagGroup = findViewById(R.id.tag_group);
        mTagGroup.setTags(new String[]{"Bed", "Living", "Accessories", "Sealy", "Christopher"});
        mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                searchView.setQuery(tag,false);
                hideSoftKeyboard(searchView);
            }
        });
    }

    private void searchFurniture(String newText) {
        final ArrayList<Furniture> tmp = new ArrayList<>();

//        for (Furniture furniture : utils.LoadFileInternal()) {
        for (Furniture furniture : dbHelper.getAllFurniture())
        {
            if (furniture.getName().toLowerCase().contains((newText.toLowerCase()))) {
                tmp.add(furniture);
            }
        }

        Toast.makeText(this, tmp.size() + "", Toast.LENGTH_SHORT).show();

        arrayList = new ArrayList<>();
        if (tmp.size() > 0) {
            for (int i = 0; i < tmp.size(); i++) {
                arrayList.add(new Furniture(tmp.get(i).getName(), tmp.get(i).getDescription(),
                        tmp.get(i).getSimage()));

            }

            furnitureAdapter.clear();
            furnitureAdapter.addAll(arrayList);
            furnitureAdapter.notifyDataSetChanged();
            listView.setVisibility(View.VISIBLE);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                    ArrayList<Furniture> lst = Utils.furnitureHistory;
                    Furniture furniture = tmp.get(i);

                    Toast.makeText(SearchActivity.this, "name: " + tmp.get(i).getSimage().toString(),
                            Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("name", tmp.get(i).getName());
                    intent.putExtra("description", tmp.get(i).getDescription());
                    intent.putExtra("image", tmp.get(i).getSimage());
                    startActivity(intent);
                }
            });
        }
        else
        {
            Toast.makeText(this,"khong co du lieu!", Toast.LENGTH_SHORT).show();
        }
        if (newText.isEmpty())
        {
            listView.setVisibility(View.GONE);
        }
    }

    public void hideSoftKeyboard(SearchView searchView) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchView.getWindowToken(),0);
    }
}