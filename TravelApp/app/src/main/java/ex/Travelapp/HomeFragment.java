package ex.Travelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ex.Travelapp.adapter.FurnitureAdapter;
import ex.Travelapp.model.DBHelper;
import ex.Travelapp.model.Furniture;
import ex.Travelapp.model.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ListView listView;
    ArrayList<Furniture> arrayList;
    FurnitureAdapter furnitureAdapter;
    Utils utils;

    DBHelper dbHelper;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        utils = new Utils(getContext());
//        utils.WriteToFileInternal();
        dbHelper = new DBHelper(getContext());

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);

//        arrayList = utils.LoadFileInternal();
        arrayList = dbHelper.getAllFurniture();

        furnitureAdapter = new FurnitureAdapter(getContext(), arrayList);

        listView.setAdapter(furnitureAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //Ngày Giờ
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

                String date = simpleDateFormat.format(calendar.getTime());
                //===
                arrayList.get(i).setDescription("(" + date + ") "+ arrayList.get(i).getDescription());

                Utils.furnitureHistory.add(arrayList.get(i));
                Toast.makeText(getContext(), i+"", Toast.LENGTH_SHORT).show();

                // chuyển vào xem chi tiết
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("image",arrayList.get(i).getSimage());
                intent.putExtra("name",arrayList.get(i).getName());
                intent.putExtra("description",arrayList.get(i).getDescription());
                startActivity(intent);

            }
        });
    }

    private ArrayList<Furniture> getMockData() {
        ArrayList<Furniture> tmp = new ArrayList<>();

        tmp.add(new Furniture(getString(R.string.name_product_one), getString(R.string.product_one), "phuquoc.jpg"));
        tmp.add(new Furniture(getString(R.string.name_product_two), getString(R.string.product_two), "vuontraicay.jpg"));
        tmp.add(new Furniture(getString(R.string.name_product_three), getString(R.string.product_three), "phanthiet.jpg"));
        tmp.add(new Furniture(getString(R.string.name_product_four), getString(R.string.product_four), "chonoicairang"));
        tmp.add(new Furniture(getString(R.string.name_product_five), getString(R.string.product_five), "ninhchudalat.jpg"));
        tmp.add(new Furniture(getString(R.string.name_product_six), getString(R.string.product_six), "danang.jpg"));
        tmp.add(new Furniture(getString(R.string.name_product_seven), getString(R.string.product_seven), "condao.jpg"));
        tmp.add(new Furniture(getString(R.string.name_product_eight), getString(R.string.product_eight), "mocchau.jpg"));
        return tmp;
    }

    @Override
    public void onPause() {
        super.onPause();
        utils.WriteToFileInternal();
    }
}

