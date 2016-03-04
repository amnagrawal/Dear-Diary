package com.aman.deardiary;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aman on 26/12/15.
 */
/*
public class ListEntriesAdapter extends RecyclerView.Adapter<ListEntriesAdapter.ListItemViewHolder> {

    private List<ListItemInfo> itemInfoList;

    public ListEntriesAdapter(List<ListItemInfo> itemInfoList){
        this.itemInfoList = itemInfoList;
    }


    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.previous_entries_list_item, parent, false);

        ListItemViewHolder holder = new ListItemViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        ListItemInfo listItemInfo = itemInfoList.get(position);
        holder.vDate.setText(listItemInfo.getDate());
        holder.vContent.setText(listItemInfo.getContent());
        try {
            holder.vImage.setImageResource(listItemInfo.getImageID());
        }catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return itemInfoList.size();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView vDate;
        protected TextView vContent;
        protected ImageView vImage;
        protected RelativeLayout relativeLayout;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            vDate = (TextView) itemView.findViewById(R.id.list_item_date);
            vContent = (TextView) itemView.findViewById(R.id.list_item_content);
            vImage = (ImageView) itemView.findViewById(R.id.list_item_image);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
            relativeLayout.setOnClickListener(this);
            itemView.setClickable(true);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getAdapterPosition();
            ListItemInfo listItemInfo = itemInfoList.get(itemPosition);
            Log.i("item content: ", listItemInfo.getContent());

            Intent intent = new Intent(v.getContext(), ViewEntry.class);
            intent.putExtra("Date", vDate.getText());
            intent.putExtra("Content", vContent.getText());
            v.getContext().startActivity(intent);
        }
    }
}
*/

public class ListEntriesAdapter extends RecyclerView.Adapter<ListEntriesAdapter.ListItemViewHolder> {

    private List<ListItemInfo> itemInfoList;

    public ListEntriesAdapter(List<ListItemInfo> itemInfoList){
        this.itemInfoList = itemInfoList;
    }


    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.previous_entries_list_item, parent, false);
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        ListItemInfo listItemInfo = itemInfoList.get(position);
        holder.vDate.setText(listItemInfo.getDate());
        holder.vContent.setText(listItemInfo.getContent());
        try {
            holder.vImage.setImageResource(listItemInfo.getImageID());
        }catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return itemInfoList.size();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder{

        protected TextView vDate;
        protected TextView vContent;
        protected ImageView vImage;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            vDate = (TextView) itemView.findViewById(R.id.list_item_date);
            vContent = (TextView) itemView.findViewById(R.id.list_item_content);
            vImage = (ImageView) itemView.findViewById(R.id.list_item_image);
        }
    }
}