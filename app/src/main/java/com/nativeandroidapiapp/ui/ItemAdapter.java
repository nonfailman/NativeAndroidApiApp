package com.nativeandroidapiapp.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nativeandroidapiapp.R;
import com.nativeandroidapiapp.data.ReceivedItem;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    interface OnItemClickListener{
        void onItemClick(ReceivedItem receivedItem, int position);
    }

    private Context context;
    private List<ReceivedItem> receivedItems;

    private final OnItemClickListener onItemClickListener;

    ItemAdapter(Context context, List<ReceivedItem> receivedItems, OnItemClickListener onItemClickListener){
        this.context = context;
        this.receivedItems = receivedItems;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        ReceivedItem receivedItem = receivedItems.get(position);
        holder.titleView.setText(receivedItem.getTitle());
        holder.short_descriptionView.setText(receivedItem.getShort_description());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(receivedItem.getThumbnail())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.thumbnailView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(receivedItem, holder.getAdapterPosition());
            }
        });

    }


    @Override
    public int getItemCount() {
        return receivedItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        ImageView thumbnailView;
        TextView titleView, short_descriptionView;
        ViewHolder(View view){
            super(view);
            mView = view;
            thumbnailView = view.findViewById(R.id.thumbnail);
            titleView = view.findViewById(R.id.title);
            short_descriptionView = view.findViewById(R.id.short_description);
        }
    }
}
