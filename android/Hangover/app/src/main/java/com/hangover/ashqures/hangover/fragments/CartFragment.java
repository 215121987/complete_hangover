package com.hangover.ashqures.hangover.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.hangover.ashqures.hangover.activity.R;
import com.hangover.ashqures.hangover.adapter.CartAdapter;
import com.hangover.ashqures.hangover.auth.SessionManager;
import com.hangover.ashqures.hangover.component.StoreComponent;
import com.hangover.ashqures.hangover.dto.AddressDTO;
import com.hangover.ashqures.hangover.dto.CartDTO;
import com.hangover.ashqures.hangover.dto.CartItemDTO;
import com.hangover.ashqures.hangover.presenter.CartPresenter;
import com.hangover.ashqures.hangover.service.RestApi;
import com.hangover.ashqures.hangover.util.CommonUtil;
import com.hangover.ashqures.hangover.util.FlipAnimationUtil;
import com.hangover.ashqures.hangover.util.Handler;
import com.hangover.ashqures.hangover.util.OnSwipeTouchListener;
import com.hangover.ashqures.hangover.util.RestConstants;
import com.hangover.ashqures.hangover.util.SwipeDetectorUtil;
import com.hangover.ashqures.hangover.view.CartView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ashqures on 8/27/16.
 */
public class CartFragment extends BaseFragment implements CartView {


    public interface CartListener {

        void onItemClick(Long itemId);

        void checkout();
    }

    private CartListener listener;

    private CartDTO cart;

    private Long selectedAddressId;

    @Inject
    CartPresenter presenter;

    private CartAdapter adapter;

    private CartAdapter adapterForReview;

    @Bind(R.id.flipper)
    ViewFlipper flipper;

    @Bind(R.id.cart_item_view)
    ListView listView;

    @Bind(R.id.cart_total_amount)
    TextView totalAmount;

    @Bind(R.id.cart_total)
    TextView cartTotal;

    @Bind(R.id.cart_price)
    TextView cartPrice;

    @Bind(R.id.cart_handling_charge)
    TextView cartHandlingCharges;

    @Bind(R.id.cart_taxes)
    TextView cartTaxes;

    @Bind(R.id.checkout_button)
    Button checkoutButton;

    /*Checkout view*/

    @Bind(R.id.deliver_to)
    TextView deliverTo;

    @Bind(R.id.user_address_title_layout)
    ViewGroup addressTittleLayout;

    @Bind(R.id.user_addresses_layout)
    ViewGroup addressLayout;

    @Bind(R.id.existing_address)
    RadioGroup existingAddressGroup;

    @Bind(R.id.address)
    EditText addressView;

    @Bind(R.id.street)
    EditText streetView;

    @Bind(R.id.city)
    EditText cityView;

    @Bind(R.id.state)
    EditText stateView;

    @Bind(R.id.country)
    EditText countryView;

    @Bind(R.id.zipCode)
    EditText zipCodeView;

    @Bind(R.id.save_address)
    Button saveAddressBtn;

    /*Review Cart*/

    @Bind(R.id.review_cart_layout_title)
    ViewGroup reviewCartTitleLayout;

    @Bind(R.id.review_cart_item_layout)
    ViewGroup reviewCartItemLayout;

    @Bind(R.id.review_cart_item_view)
    ListView reviewCartView;

    @Bind(R.id.edit_cart)
    Button editCartBtn;

    @Bind(R.id.proceed_to_pay_button)
    Button proceedToPayBtn;


    public CartFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CartListener) {
            this.listener = (CartListener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(StoreComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_cart_flipper, container, false);
        ButterKnife.bind(this, fragmentView);
        setUpView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        if (savedInstanceState == null) {
            this.loadItemList();
        }
    }

    private void setUpView() {
        this.adapter = new CartAdapter(context(), R.layout.list_cart_item);
        this.adapterForReview = new CartAdapter(context(), R.layout.list_cart_item);
        this.listView.setAdapter(this.adapter);
        this.reviewCartView.setAdapter(this.adapterForReview);
        this.adapter.setOnItemClickListener(this.onItemClickListener);
        this.adapter.setOnItemTouchListener(this.onItemTouchListener);
        this.checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartFragment.this.showCheckout();
            }
        });
        this.addressTittleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != selectedAddressId){
                    toggleTabView(reviewCartTitleLayout, reviewCartItemLayout, !reviewCartItemLayout.isShown());
                    toggleTabView(addressTittleLayout, addressLayout, !addressLayout.isShown());
                }
            }
        });

        this.saveAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartFragment.this.attemptSaveAddress();
            }
        });
        this.editCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartFragment.this.showCart();
            }
        });

        this.proceedToPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Add payment process
            }
        });
        this.existingAddressGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onSelectAddress((long)checkedId);
            }
        });
    }

    private void loadItemList() {
        this.presenter.initialize();
    }

    private void showCart() {
        this.flipper.setInAnimation(FlipAnimationUtil.inFromLeftAnimation());
        this.flipper.setOutAnimation(FlipAnimationUtil.outToRightAnimation());
        this.flipper.setDisplayedChild(flipper.indexOfChild(getActivity().findViewById(R.id.cart)));
    }

    private void showCheckout() {
        this.flipper.setInAnimation(FlipAnimationUtil.inFromRightAnimation());
        this.flipper.setOutAnimation(FlipAnimationUtil.outToLeftAnimation());
        this.flipper.setDisplayedChild(flipper.indexOfChild(getActivity().findViewById(R.id.checkout)));
        this.initializeCheckout();
    }

    private void initializeCheckout() {
        toggleTabView(reviewCartTitleLayout, reviewCartItemLayout, false);
        Map<String,String> param= new HashMap<>();
        param.put(RestConstants.ZIP_CODE, sessionManager.getStringValue(SessionManager.KEY_ZIP_CODE));
        this.presenter.loadUserDeliveryAddress(param);
        toggleTabView(addressTittleLayout, addressLayout, true);
        this.existingAddressGroup.removeAllViews();
    }

    private void onSelectAddress(Long addressId) {
        this.selectedAddressId = addressId;
        toggleTabView(addressTittleLayout, addressLayout, false);
        toggleTabView(reviewCartTitleLayout, reviewCartItemLayout, true);
        View  addressView = getActivity().findViewById(addressId.intValue());
        if(null != addressView){
            String deliverTo = ((RadioButton)getActivity().findViewById(addressId.intValue())).getText().toString();
            updateDeliverTo(deliverTo);
        }
    }

    private void attemptSaveAddress() {
        if(isValidateAddressForm()){
            Map<String,String> formAttr = new HashMap<>();
            formAttr.put(RestApi.ATTR_ADDRESS, this.addressView.getText().toString());
            formAttr.put(RestApi.ATTR_STREET, this.streetView.getText().toString());
            formAttr.put(RestApi.ATTR_CITY,this.cityView.getText().toString() );
            formAttr.put(RestApi.ATTR_STATE, this.stateView.getText().toString());
            formAttr.put(RestApi.ATTR_COUNTRY, this.countryView.getText().toString());
            formAttr.put(RestApi.ATTR_ZIPCODE, this.zipCodeView.getText().toString());
            this.presenter.attemptToSaveAddress(formAttr);
        }
    }

    @Override
    public void renderView(CartDTO cart) {
        this.cart = cart;
        if (null != cart.getCartItems()) {
            this.adapter.setItemCollection(cart.getCartItems());
            this.adapterForReview.setItemCollection(cart.getCartItems());
        }
        Handler.handleCartListView(listView);
        Handler.handleCartListView(reviewCartView);
        //Cart Summary
        this.cartTotal.setText(Double.toString(cart.getNetAmount()));
        this.cartTaxes.setText(Double.toString(cart.getTax()));
        this.cartPrice.setText(Double.toString(cart.getGrossAmount()));
        if (cart.getDeliveryCharge() > 0.00)
            this.cartHandlingCharges.setText(Double.toString(cart.getDeliveryCharge()));
        else
            this.cartHandlingCharges.setText(R.string.label_handling_charge);

        this.totalAmount.setText(Double.toString(cart.getNetAmount()));
        this.proceedToPayBtn.setText(this.proceedToPayBtn.getText().toString()+ " "+ Double.toString(cart.getNetAmount()));
    }

    @Override
    public void viewItem(Long itemId) {
        this.listener.onItemClick(itemId);
    }

    @Override
    public void renderAddress(List<AddressDTO> addressDTOs) {
        this.loadAddress(addressDTOs);
    }

    @Override
    public void addAddress(AddressDTO addressDTO) {
        resetAddressForm();
        addAddressInVew(addressDTO,0);
    }

    @Override
    public void removeAddress() {
        //TODO remove address from UI;
    }

    private void loadAddress(List<AddressDTO> addresses) {
        if(null!= addresses && addresses.size()>0){
            prefilledAddressForm(addresses.get(0));
            for (AddressDTO address : addresses) {
                addAddressInVew(address);
            }
        }else{
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setZipCode(sessionManager.getStringValue(SessionManager.KEY_ZIP_CODE));
            addAddressInVew(addressDTO);
        }
    }

    private void prefilledAddressForm(AddressDTO addressDTO){
        this.zipCodeView.setText(addressDTO.getZipCode());
        this.countryView.setText(addressDTO.getCountry());
        this.stateView.setText(addressDTO.getState());
        this.cityView.setText(addressDTO.getCity());
    }

    private void updateDeliverTo(String deliverTo){
        this.deliverTo.setText(deliverTo);
    }

    private void addAddressInVew(AddressDTO addressDTO, int index){
        RadioButton radioButton = getAddressRadioButton(addressDTO);
        radioButton.setChecked(true);
        this.existingAddressGroup.addView(radioButton, index);
    }

    private void addAddressInVew(AddressDTO addressDTO){
        this.existingAddressGroup.addView(getAddressRadioButton(addressDTO));
    }

    private RadioButton getAddressRadioButton(AddressDTO address) {
        RadioButton radioButton = new RadioButton(getActivity(), null, 0, R.style.AddressRadioButton);
        radioButton.setId(address.getId().intValue());
        radioButton.setText(address.getCompleteAddress());
        if (null != this.selectedAddressId && address.getId().equals(selectedAddressId)) {
            radioButton.setChecked(true);
        }
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(0,CommonUtil.dpToPx(10),0, CommonUtil.dpToPx(20));
        radioButton.setLayoutParams(params);
        return radioButton;
    }

    private void resetAddressForm(){
        this.addressView.setText("");
        this.streetView.setText("");
    }

    private CartAdapter.OnItemClickListener onItemClickListener =
            new CartAdapter.OnItemClickListener() {
                @Override
                public void onItemClicked(Long itemId) {
                    if (null != CartFragment.this.presenter && null != itemId) {
                        CartFragment.this.presenter.onItemClicked(itemId);
                    }
                }
            };

    private CartAdapter.OnItemTouchListener onItemTouchListener =
            new CartAdapter.OnItemTouchListener() {
                @Override
                public boolean onTouch(MotionEvent event, CartItemDTO cartItemDTO) {
                    SwipeDetectorUtil swipeTouchListener = new SwipeDetectorUtil(event);
                    if (swipeTouchListener.swipeDetected() && swipeTouchListener.getAction().equals(SwipeDetectorUtil.Action.LR)) {
                        //CartFragment.this.adapter.remove(cartItemDTO);
                        CartFragment.this.presenter.removeItemFromCart(cartItemDTO);
                    }
                    return true;
                }
            };


    private boolean isValidateAddressForm() {
        boolean isValid = true;
        String address = this.addressView.getText().toString();
        String street = this.streetView.getText().toString();
        String city = this.cityView.getText().toString();
        String state = this.stateView.getText().toString();
        String country = this.countryView.getText().toString();
        String zipCode = this.zipCodeView.getText().toString();
        if (TextUtils.isEmpty(address)) {
            this.addressView.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if (TextUtils.isEmpty(street)) {
            this.streetView.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if (TextUtils.isEmpty(city)) {
            this.cityView.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if (TextUtils.isEmpty(state)) {
            this.stateView.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if (TextUtils.isEmpty(country)) {
            this.countryView.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if (TextUtils.isEmpty(zipCode)) {
            this.zipCodeView.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        return isValid;
    }
}
