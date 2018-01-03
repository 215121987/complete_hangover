package com.hangover.ashqures.hangover.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;
import com.hangover.ashqures.hangover.activity.R;
import com.hangover.ashqures.hangover.activity.StoreItemActivity;
import com.hangover.ashqures.hangover.adapter.ImageViewHolder;
import com.hangover.ashqures.hangover.component.StoreComponent;
import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.dto.ItemDetailDTO;
import com.hangover.ashqures.hangover.presenter.StoreItemPresenter;
import com.hangover.ashqures.hangover.task.DownloadImageTask;
import com.hangover.ashqures.hangover.util.CommonUtil;
import com.hangover.ashqures.hangover.view.CommonView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ashqures on 8/7/16.
 */
public class StoreItemFragment extends BaseFragment implements CommonView<ItemDTO> {

    /*private CommonTask<ItemDTO> mTask;*/

    private Long itemId;

    private ItemDTO item;

    private ItemDetailDTO selectedItemDetail;

    @Inject
    StoreItemPresenter presenter;

    @Bind(R.id.item_image)
    ImageView itemImageView;

    @Bind(R.id.item_name)
    TextView itemName;

    @Bind(R.id.item_description)
    TextView itemDescription;

    @Bind(R.id.item_size_layout)
    LinearLayout itemSizeLayout;

    @Bind(R.id.item_view_action)
    LinearLayout itemViewActionLayout;

    @Bind(R.id.item_number_bar)
    CrystalSeekbar itemNumberBar;

    @Bind(R.id.item_min_number)
    TextView itemNumber;

    @Bind(R.id.add_to_cart)
    Button addToCart;


    public StoreItemFragment() {
        setRetainInstance(true);
        //this.itemId = getArguments().getLong();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(StoreComponent.class).inject(this);
        //this.itemId = getActivity().getIntent().
        initializeFragment(savedInstanceState);
    }


    private void initializeFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.itemId = getActivity().getIntent().getLongExtra(StoreItemActivity.INTENT_EXTRA_PARAM_ITEM_ID, -1);
        } else {
            this.itemId = savedInstanceState.getLong(StoreItemActivity.INSTANCE_STATE_PARAM_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_store_item, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        if (savedInstanceState == null) {
            this.loadItemDetails();
        }
    }

    private void loadItemDetails() {
        this.presenter.initialize(this.itemId);
    }

    @Override
    public  void renderView(ItemDTO itemDTO) {
        loadView(itemDTO);
    }

    private void loadView(final ItemDTO itemDTO){
        this.item = itemDTO;
        this.itemName.setText(itemDTO.getName());
        this.itemDescription.setText(itemDTO.getDescription());
        if(null!=itemDTO.getImageURL() && itemDTO.getItemDetailDTOs().size()>0){
            new DownloadImageTask(context()).execute(new ImageViewHolder(itemImageView,itemDTO.getImageURL().get(0)));
        }
        for(final ItemDetailDTO itemDetailDTO : this.item.getItemDetailDTOs()){
            final Button sizeView = new Button(getActivity(), null, 0, R.style.ItemSizeButton);
            sizeView.setId(itemDetailDTO.getId().intValue());
            sizeView.setWidth(CommonUtil.dpToPx(73));
            sizeView.setHeight(CommonUtil.dpToPx(73));
            sizeView.setText(itemDetailDTO.getSize());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMarginEnd(CommonUtil.dpToPx(10));
            sizeView.setLayoutParams(params);
            sizeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button sizeButton = (Button)view;
                    boolean shouldAdd = true;
                    if(null != StoreItemFragment.this.selectedItemDetail){
                        shouldAdd = StoreItemFragment.this.selectedItemDetail.getId()!=sizeButton.getId();
                        Button selectedButton = (Button) getActivity().findViewById(StoreItemFragment.this.selectedItemDetail.getId().intValue());
                        selectedButton.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                        sizeButton.setTextSize(24);
                        StoreItemFragment.this.selectedItemDetail =null;
                        StoreItemFragment.this.addToCart.setEnabled(false);
                    }
                    if(shouldAdd){
                        Drawable top = getResources().getDrawable(R.drawable.ic_check_mark);
                        sizeButton.setCompoundDrawablesWithIntrinsicBounds(null,top,null,null);
                        sizeButton.setTextSize(16);
                        for(ItemDetailDTO itemDetail : StoreItemFragment.this.item.getItemDetailDTOs()){
                            if(itemDetail.getId()== sizeButton.getId()){
                                StoreItemFragment.this.selectedItemDetail = itemDetail;
                                break;
                            }
                        }
                        StoreItemFragment.this.addToCart.setEnabled(true);
                    }
                }
            });
            this.itemSizeLayout.addView(sizeView);
        }
        this.itemNumberBar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number value) {
                StoreItemFragment.this.itemNumber.setText(String.valueOf(value));
            }
        });

        this.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoreItemFragment.this.presenter.addToCart(itemDTO, selectedItemDetail, Integer.parseInt(StoreItemFragment.this.itemNumber.getText().toString()));
                showToastMessage(StoreItemFragment.this.selectedItemDetail.getSize());
            }
        });
    }
}
