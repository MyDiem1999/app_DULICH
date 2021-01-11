package ex.Travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ex.Travelapp.model.Furniture;
import ex.Travelapp.R;

public class FurnitureAdapter extends ArrayAdapter<Furniture> {

    private ArrayList<Furniture> furnitureList;
    private LayoutInflater layoutInflater;
    private Context context;

    public FurnitureAdapter(@NonNull Context context, ArrayList<Furniture> furnitureList) {
        super(context, 0, (List<Furniture>) furnitureList);
        this.furnitureList = furnitureList;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.imgView = (ImageView) convertView.findViewById(R.id.imageView_product);
            holder.nameView = (TextView) convertView.findViewById(R.id.textView_Nameproduct);
            holder.descriptionView = (TextView) convertView.findViewById(R.id.textView_description);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Furniture furniture = this.furnitureList.get(position);
        holder.nameView.setText(furniture.getName());
        holder.descriptionView.setText(furniture.getDescription());
        holder.imgView.setImageBitmap(furniture.convertStringToBitmapFromAccess(getContext(), furniture.getSimage()));

    return convertView;
    }

    static class ViewHolder {
        ImageView imgView;
        TextView nameView;
        TextView descriptionView;
    }

}
