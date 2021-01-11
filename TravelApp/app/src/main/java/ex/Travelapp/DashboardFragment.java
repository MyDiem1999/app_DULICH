package ex.Travelapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import ex.Travelapp.adapter.FurnitureAdapterGrid;
import ex.Travelapp.model.Categories;
import ex.Travelapp.model.DBHelper;
import ex.Travelapp.model.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    GridView gridView;
    ArrayList<Categories> arrayList;
    FurnitureAdapterGrid furnitureAdapterGrid;
    Utils utils;

    DBHelper dbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        utils = new Utils(getContext());
//        utils.WriteToFileInternal();
        dbHelper = new DBHelper(getContext());

        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView = view.findViewById(R.id.gridView_product);

//        arrayList = getMockData();
//        arrayList = utils.getMockDataCategories();
        arrayList = dbHelper.getAllCategories();

        furnitureAdapterGrid = new FurnitureAdapterGrid(getContext(),arrayList);

        gridView.setAdapter(furnitureAdapterGrid);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, CategoriesFragment.newInstance(i));

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    private ArrayList<Categories> getMockData() {

        ArrayList<Categories> tmp = new ArrayList<>();
        tmp.add(new Categories("BedRoom", "bed_room.png"));
        tmp.add(new Categories("LivingRoom", "living_room.png"));
        tmp.add(new Categories("MeetingRoom",  "meeting_room.png"));
        tmp.add(new Categories("Accessories",  "accessories_room.png"));

        return tmp;

    }


}
