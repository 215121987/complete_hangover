package com.hangover.ashqures.hangover.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hangover.ashqures.hangover.activity.R;
import com.hangover.ashqures.hangover.dto.CartItemDTO;
import com.hangover.ashqures.hangover.task.DownloadImageTask;
import com.hangover.ashqures.hangover.util.OnSwipeTouchListener;
import com.hangover.ashqures.hangover.util.SwipeDetectorUtil;

import java.util.Collection;
import java.util.List;

/**
 * Created by ashqures on 8/28/16.
 */
public class CartAdapter extends ArrayAdapter<CartItemDTO> {

    public interface OnItemClickListener {
        void onItemClicked(Long itemId);
    }

    public interface OnItemTouchListener {
        boolean onTouch(MotionEvent event, CartItemDTO cartItem);
    }

    private OnItemClickListener onItemClickListener;

    private OnItemTouchListener onItemTouchListener;

    private Context context;

    private int layoutResourceId;

    public CartAdapter(Context context, int layoutResourceId) {
        super(context, layoutResourceId);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
    }



    @Override
    public long getItemId(int position) {
        CartItemDTO cartItem = getItem(position);
        return cartItem.getId();
    }

    public void setItemCollection(List<CartItemDTO> cartItems) {
        this.validateUsersCollection(cartItems);
        this.addAll(cartItems);
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.onItemTouchListener = onItemTouchListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new RecordHolder();
            holder.itemName = (TextView) row.findViewById(R.id.item_name);
            holder.itemImage = (ImageView) row.findViewById(R.id.item_image);
            holder.itemDetail = (TextView) row.findViewById(R.id.item_detail);
            holder.itemPrice = (TextView) row.findViewById(R.id.item_price);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }
        final CartItemDTO cartItem = getItem(position);
        holder.itemName.setText(cartItem.getItemName());
        String itemDetail = holder.itemDetail.getText().toString().replace("_SIZE", cartItem.getItemSize());
        itemDetail = itemDetail.replace("_QUANTITY", cartItem.getItemQuantity() + "");
        itemDetail = itemDetail.replace("_PRICE", cartItem.getItemPrice() + "");
        holder.itemDetail.setText(itemDetail);
        if (null != cartItem.getItemPrice()) {
            holder.itemPrice.setText(cartItem.getItemPrice() * cartItem.getItemQuantity() + "");
        }
        if (null != cartItem.getImageUrl() && !"".equals(cartItem.getImageUrl())) {
            new DownloadImageTask(context).execute(new ImageViewHolder(holder.itemImage, cartItem.getImageUrl()));
        }
        if (null != this.onItemClickListener)
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CartAdapter.this.onItemClickListener.onItemClicked(cartItem.getItemId());
                }
            });
        if (null != this.onItemTouchListener){
            row.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return CartAdapter.this.onItemTouchListener.onTouch(event, cartItem);
                }
            });
        }
        return row;
    }

    private void validateUsersCollection(Collection<CartItemDTO> cartItems) {
        if (cartItems == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    static class RecordHolder {
        TextView itemName;
        ImageView itemImage;
        TextView itemDetail;
        TextView itemPrice;
    }

}
