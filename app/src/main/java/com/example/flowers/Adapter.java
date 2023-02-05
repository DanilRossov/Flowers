package com.example.flowers;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private final static String PHOTO_URL = "https://services.hanselandpetal.com/photos/";
    private List<Chveti> mFlowers;
    private Context mContext;

    Adapter(List<Chveti> flowers) {

        this.mFlowers = flowers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chveti flower = mFlowers.get(position);
        holder.nameTextView.setText(flower.getName());

        Picasso.with(mContext)
                .load(PHOTO_URL + flower.getPhoto())
                .resize(200, 150)
                .into(holder.flowerImageView);

    }

    @Override
    public int getItemCount() {
        if (mFlowers == null) {
            return 0;
        }
        return mFlowers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView flowerImageView;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.Name);
            flowerImageView = (ImageView) itemView.findViewById(R.id.Flow);
        }
    }
}
