package ex.Travelapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ex.Travelapp.adapter.FurnitureAdapter;
import ex.Travelapp.model.Furniture;
import ex.Travelapp.model.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    ListView listView;
    ArrayList<Furniture> arrayList;
    FurnitureAdapter furnitureAdapter;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);

        Utils utils = new Utils(getContext());

        arrayList = utils.getFurnitureHistory();
//        ArrayList<Furniture> arr = new ArrayList<>();
//        for (int i =0;i<arrayList.size();i++)
//        {
//            arr.add(new Furniture(arrayList.get(i).getName(),arrayList.get(i).getDescription(),
//                    arrayList.get(i).getSimage()));
//        }

        furnitureAdapter = new FurnitureAdapter(getContext(), arrayList);

        listView.setAdapter(furnitureAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Utils.furnitureHistory.add(arrayList.get(i));
            }
        });


    }
}
