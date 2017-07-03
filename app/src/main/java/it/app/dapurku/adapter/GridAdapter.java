package it.app.dapurku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.app.dapurku.R;
import it.app.dapurku.model.DiscoverResponse;

/**
 * Created by Islam on 08/05/2016.
 */
public class GridAdapter extends BaseAdapter {

    List<DiscoverResponse> resep;
    Context context;
    LayoutInflater inflater;
    public GridAdapter(Context context, List<DiscoverResponse>resep) {
        this.context = context;
        this.resep = resep;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return resep.size();
    }

    @Override
    public Object getItem(int position) {
        return resep.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ResepViewHolder cell;
        DiscoverResponse mDetail = (DiscoverResponse)getItem(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.content_main,parent,false);
            cell = new ResepViewHolder(convertView);
            convertView.setTag(cell);
        }else{
            cell = (ResepViewHolder)convertView.getTag();
        }

        cell.nama_resep.setText(mDetail.getResepNama());
        Picasso.with(context).load(mDetail.getResepGambar())
                .into(cell.image);
        return convertView;
    }

    class ResepViewHolder{
        ImageView image;
        TextView nama_resep;
        //ImageView favorite;

        public ResepViewHolder(View view) {
            image = (ImageView)view.findViewById(R.id.image);
            nama_resep = (TextView)view.findViewById(R.id.nama_resep);/*
            favorite = (ImageView) view.findViewById(R.id.favorite);*/
        }
    }
}
