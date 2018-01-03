package com.hangover.ashqures.hangover.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hangover.ashqures.hangover.activity.R;
import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.task.DownloadImageTask;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

/**
 * Created by ashqures on 7/12/16.
 */
public class StoreAdapter extends  ArrayAdapter<ItemDTO> {

    public interface OnItemClickListener {
        void onItemClicked(ItemDTO itemDTO);
    }


    private OnItemClickListener onItemClickListener;

    private Context context;

    private int layoutResourceId;

    /*@Inject*/
    public StoreAdapter(Context context, int layoutResourceId) {
        super(context,  layoutResourceId);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public long getItemId(int position) {
        ItemDTO itemDTO = getItem(position);
        return itemDTO.getId();
        //return position;
    }


    public void setItemCollection(List<ItemDTO> itemDTOs) {
        this.validateUsersCollection(itemDTOs);
        this.addAll(itemDTOs);
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item, null);
        }*/
       // TextView itemName = (TextView)convertView.findViewById(R.id.item_name);
        //itemName.setText(itemDTOs.get(position).getName());
        /*TextView itemDescription = (TextView)convertView.findViewById(R.id.item_de);
        itemName.setText(itemDTOs.get(position).getName());*//*
        return convertView;*/

        View row = convertView;
        RecordHolder holder = null;
        if (row == null) {
            //LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new RecordHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.item_name);
            holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }
        final ItemDTO itemDTO = getItem(position);
        holder.txtTitle.setText(itemDTO.getName());
        if(null != itemDTO.getImageURL() && itemDTO.getImageURL().size()>0){
            new DownloadImageTask(context).execute(new ImageViewHolder(holder.imageItem,itemDTO.getImageURL().get(0)));
        }
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClicked(itemDTO);
            }
        });
        return row;
    }

    private void validateUsersCollection(Collection<ItemDTO> itemDTOCollection) {
        if (itemDTOCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }


    static class RecordHolder{
        TextView txtTitle;
        ImageView imageItem;
    }

}
