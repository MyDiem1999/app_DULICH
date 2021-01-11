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

import java.util.ArrayList;

import ex.Travelapp.adapter.FurnitureAdapter;
import ex.Travelapp.model.Furniture;
import ex.Travelapp.model.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends Fragment {

    ListView listView;
    ArrayList<Furniture> arrayList;
    FurnitureAdapter furnitureAdapter;
    Utils utils;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    public static CategoriesFragment newInstance(int pos) {
        Bundle bundle = new Bundle();
        bundle.putInt("category", pos);
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        categoriesFragment.setArguments(bundle);

        return categoriesFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        utils = new Utils(getContext());
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();

        listView = view.findViewById(R.id.listView);

        arrayList = utils.getFurnitureFromCategories(bundle.getInt("category"));

        furnitureAdapter = new FurnitureAdapter(getContext(), arrayList);

        listView.setAdapter(furnitureAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Utils.furnitureHistory.add(arrayList.get(i));
                Toast.makeText(getContext(), i + "", Toast.LENGTH_SHORT).show();;

                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("furniture", arrayList.get(i));
                startActivity(intent);

            }
        });
    }
}