package it.app.dapurku.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import it.app.dapurku.R;
import it.app.dapurku.model.Detail;
import it.app.dapurku.model.DiscoverResponse;


/**
 * Created by Islam on 25/02/2016.
 */
public class RescyclerViewAdapter extends RecyclerView.Adapter<RescyclerViewAdapter.ViewHolder> {

    List<Detail>mDetail;
    Context context;

    public RescyclerViewAdapter(Context context, List<Detail>mDetail) {
        this.context = context;
        this.mDetail = mDetail;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(mDetail.get(position).getResepBahanHalus()==null){
            holder.tambahan.setVisibility(View.GONE);
        }

        holder.bahan_text.setText(mDetail.get(position).getResepBahan());
        holder.bumbu_tambahan.setText(mDetail.get(position).getResepBahanHalus().toString());
        holder.caramasak_text.setText(mDetail.get(position).getResepCaraMasak());
    }

    @Override
    public int getItemCount() {
        return mDetail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView bumbu_tambahan,bahan_text,caramasak_text;
        CardView tambahan,bahan,caramasak;

        public ViewHolder(View itemView) {
            super(itemView);
            bumbu_tambahan = (TextView) itemView.findViewById(R.id.tips_text);
            bahan_text = (TextView) itemView.findViewById(R.id.bahan_text);
            caramasak_text = (TextView) itemView.findViewById(R.id.caramasa_text);
            tambahan = (CardView) itemView.findViewById(R.id.tips);
            bahan = (CardView) itemView.findViewById(R.id.bahan);
            caramasak = (CardView) itemView.findViewById(R.id.caramasak);
        }
    }
}
