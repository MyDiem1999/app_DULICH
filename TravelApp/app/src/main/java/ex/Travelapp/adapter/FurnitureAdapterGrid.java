package ex.Travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ex.Travelapp.R;
import ex.Travelapp.model.Categories;

public class FurnitureAdapterGrid extends BaseAdapter {
    private Context context;
    private List<Categories> categoriesList;
    private LayoutInflater layoutInflater;

    public FurnitureAdapterGrid(Context context, List<Categories> categoriesList) {
        this.context = context;
        this.categoriesList = categoriesList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return categoriesList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoriesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        convertView = layoutInflater.inflate(R.layout.grid_item_layout, parent,false);
        holder = new ViewHolder();

        holder.img = (ImageView) convertView.findViewById(R.id.imageGrid);
        holder.name = (TextView) convertView.findViewById(R.id.Titleproduct);


        Categories categories = this.categoriesList.get(position);
        holder.img.setImageBitmap(categories.convertStringToBitmapFromAccess(context,categories.getImage()));
        holder.name.setText(categories.getName());

        return convertView;
    }
    private  class ViewHolder{
        ImageView img;
        TextView name;
    }
}
