package com.hangover.ashqures.hangover.entity.mapper;

import android.content.ClipData;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hangover.ashqures.hangover.component.PerActivity;
import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.dto.StatusDTO;
import com.hangover.ashqures.hangover.entity.AddressEntity;
import com.hangover.ashqures.hangover.entity.BaseEntity;
import com.hangover.ashqures.hangover.entity.CartEntity;
import com.hangover.ashqures.hangover.entity.CartItemEntity;
import com.hangover.ashqures.hangover.entity.ItemDetailEntity;
import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.entity.ServiceChargeEntity;
import com.hangover.ashqures.hangover.entity.StatusEntity;
import com.hangover.ashqures.hangover.entity.UserEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ashqures on 8/7/16.
 */
public class JsonMapper {

    private final Gson gson;

    @Inject
    public JsonMapper() {
        this.gson = new Gson();
    }

    public StatusEntity transformStatus(JSONObject response)throws  JSONException {
        StatusEntity statusEntity = new StatusEntity();
        statusEntity.setCode(response.getInt("code"));
        statusEntity.setMessage(response.getString("message"));
        return statusEntity;
    }

    public ItemEntity transformItem(JSONObject response)throws  JSONException {
        JSONArray itemArray = response.getJSONArray("items");
        return fromJSONToItem(itemArray.getJSONObject(0));
    }

    public <T extends BaseEntity> T transform(JSONObject response)throws  JSONException {
        JSONObject userObject = response.getJSONObject("user");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userObject.getLong("id"));
        userEntity.setName(userObject.getString("name"));
        userEntity.setEmail(userObject.getString("email"));
        userEntity.setMobile(userObject.getString("mobile"));
        userEntity.setToken(response.getString("token"));
        return (T)userEntity;
    }

    public UserEntity transformUser(JSONObject response) throws JSONException {
        JSONObject userObject = response.getJSONObject("user");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userObject.getLong("id"));
        userEntity.setName(userObject.getString("name"));
        userEntity.setEmail(userObject.getString("email"));
        userEntity.setMobile(userObject.getString("mobile"));
        userEntity.setToken(response.getString("token"));
        userEntity.setMobileVerified(userObject.getBoolean("numberVerified"));
        return userEntity;
    }

    public  <T extends BaseEntity>  T transform(JSONObject response, Type type)throws  JSONException{
        JSONArray itemArray = response.getJSONArray("items");
        Gson gson = new Gson();
        type = new TypeToken<ItemEntity>() {}.getType();
       ///ClassType<T> classType = new ClassType<T>();
        DataMapper dataMapper = new DataMapper();
        return (T) transformItem(response);
        //return (T)dataMapper.transform(transformItem(response));
        //return (T) gson.fromJson(itemArray.getJSONObject(0).toString(), type);
    }

    class ClassType<T>{
        private Class<T> classType;
        public Class<T> getClassType(){
            if (classType == null) {
                this.classType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getClass();
            }
            return classType;
        }

    }

    public List<ItemEntity> transformItemCollection(JSONObject response) throws  JSONException {
        List<ItemEntity> itemEntities = new ArrayList<ItemEntity>();
        JSONArray itemArray = response.getJSONArray("items");
        for (int i = 0; i < itemArray.length(); i++) {
            itemEntities.add(fromJSONToItem(itemArray.getJSONObject(i)));
        }
        return itemEntities;
    }

    private ItemEntity fromJSONToItem(JSONObject jsonObject) throws JSONException {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(jsonObject.getLong("id"));
        itemEntity.setName(jsonObject.getString("name"));
        itemEntity.setDescription(jsonObject.getString("description"));
        if(jsonObject.has("itemDetailList")){
            JSONArray jsonArray = jsonObject.getJSONArray("itemDetailList");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject itemDetail = jsonArray.getJSONObject(i);
                ItemDetailEntity itemDetailEntity = new ItemDetailEntity();
                itemDetailEntity.setId(itemDetail.getLong("id"));
                itemDetailEntity.setSize(itemDetail.getString("itemSize"));
                itemDetailEntity.setQuantity(itemDetail.getString("quantity"));
                itemEntity.addItemDetail(itemDetailEntity);
            }
        }
        if(jsonObject.has("imageURL")){
            JSONArray imgUrls = jsonObject.getJSONArray("imageURL");
            for(int i=0; i<imgUrls.length();i++){
                itemEntity.addImageURL(imgUrls.getString(i));
            }
        }
        return itemEntity;
    }

    public CartEntity transformCart(JSONObject response) throws JSONException {
        String raw = "{\"cartItems\":[{\"id\":1,\"item\":{\"id\":1,\"name\":\"Vodka\",\"description\":\"http://10.0.2.2:8080/hangover/images/product/Antica-Formula-Carpano-min.png\",\"brand\":{\"id\":1,\"code\":\"kf22\",\"name\":\"Kingfisher\",\"displayName\":\"Kingfisher\",\"description\":\"Alcohol\",\"url\":null,\"contactPerson\":null,\"contactNumber\":null},\"count\":10,\"itemDetailList\":[{\"id\":1,\"itemSize\":\"300\",\"quantity\":\"20\",\"modelNumber\":\"AXYZ\",\"sellingPrice\":89}],\"imageURL\":[\"http://10.0.2.2:8080/hangover/images/product/Antica-Formula-Carpano-min.png\"]},\"itemDetail\":{\"id\":1,\"itemSize\":\"300\",\"quantity\":\"20\",\"modelNumber\":\"AXYZ\",\"sellingPrice\":89},\"quantity\":0,\"price\":null,\"taxable\":false},{\"id\":2,\"item\":{\"id\":2,\"name\":\"Beer\",\"description\":\"http://10.0.2.2:8080/hangover/images/product/Antica-Formula-Carpano-min.png\",\"brand\":{\"id\":1,\"code\":\"kf22\",\"name\":\"Kingfisher\",\"displayName\":\"Kingfisher\",\"description\":\"Alcohol\",\"url\":null,\"contactPerson\":null,\"contactNumber\":null},\"count\":10,\"itemDetailList\":[{\"id\":3,\"itemSize\":\"300\",\"quantity\":\"15\",\"modelNumber\":\"AXYZ\",\"sellingPrice\":79},{\"id\":4,\"itemSize\":\"600\",\"quantity\":\"10\",\"modelNumber\":\"AXYZ\",\"sellingPrice\":179}],\"imageURL\":[\"http://10.0.2.2:8080/hangover/images/product/Antica-Formula-Carpano-min.png\"]},\"itemDetail\":{\"id\":4,\"itemSize\":\"600\",\"quantity\":\"10\",\"modelNumber\":\"AXYZ\",\"sellingPrice\":179},\"quantity\":4,\"price\":null,\"taxable\":false},{\"id\":3,\"item\":{\"id\":3,\"name\":\"Wine\",\"description\":\"http://10.0.2.2:8080/hangover/images/product/Antica-Formula-Carpano-min.png\",\"brand\":{\"id\":1,\"code\":\"kf22\",\"name\":\"Kingfisher\",\"displayName\":\"Kingfisher\",\"description\":\"Alcohol\",\"url\":null,\"contactPerson\":null,\"contactNumber\":null},\"count\":10,\"itemDetailList\":[{\"id\":5,\"itemSize\":\"300\",\"quantity\":\"11\",\"modelNumber\":\"AXYZ\",\"sellingPrice\":99},{\"id\":6,\"itemSize\":\"600\",\"quantity\":\"21\",\"modelNumber\":\"AXYZ\",\"sellingPrice\":199}],\"imageURL\":[\"http://10.0.2.2:8080/hangover/images/product/Antica-Formula-Carpano-min.png\"]},\"itemDetail\":{\"id\":5,\"itemSize\":\"300\",\"quantity\":\"11\",\"modelNumber\":\"AXYZ\",\"sellingPrice\":99},\"quantity\":5,\"price\":null,\"taxable\":false}],\"serviceCharges\":[{\"id\":1,\"name\":\"VAT\",\"value\":14,\"percent\":true},{\"id\":2,\"name\":\"CST\",\"value\":5,\"percent\":true},{\"id\":3,\"name\":\"SBAT\",\"value\":0.5,\"percent\":true},{\"id\":4,\"name\":\"KKT\",\"value\":0.5,\"percent\":true},{\"id\":5,\"name\":\"Min Delivery Value\",\"value\":200,\"percent\":false},{\"id\":6,\"name\":\"delivery charge\",\"value\":100,\"percent\":false}],\"item_count\":3}";
        CartEntity cartEntity = new CartEntity();
        if(response.has("cartItems")){
            JSONArray cartItems = response.getJSONArray("cartItems");
            for(int i=0; i<cartItems.length();i++){
                cartEntity.addCartItems(transformCartItem(cartItems.getJSONObject(i)));
            }
        }
        if(response.has("serviceCharges")){
            JSONArray serviceCharges = response.getJSONArray("serviceCharges");
            for(int i=0; i<serviceCharges.length();i++){
                cartEntity.addServiceCharge(transformServiceCharge(serviceCharges.getJSONObject(i)));
            }
        }
        return cartEntity;
    }

    public CartItemEntity transformCartItem(JSONObject cartItem) throws JSONException {
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setId(cartItem.getLong("id"));
        cartItemEntity.setItemId(cartItem.getJSONObject("item").getLong("id"));
        cartItemEntity.setItemName(cartItem.getJSONObject("item").getString("name"));
        if(cartItem.getJSONObject("item").has("imageURL") && cartItem.getJSONObject("item").getJSONArray("imageURL").length()>0)
            cartItemEntity.setImageUrl(cartItem.getJSONObject("item").getJSONArray("imageURL").getString(0));
        cartItemEntity.setItemDetailId(cartItem.getJSONObject("itemDetail").getLong("id"));
        cartItemEntity.setItemSize(cartItem.getJSONObject("itemDetail").getString("itemSize"));
        if(cartItem.getJSONObject("itemDetail").has("sellingPrice") && !cartItem.getJSONObject("itemDetail").isNull("sellingPrice"))
            cartItemEntity.setSellingPrice(cartItem.getJSONObject("itemDetail").getDouble("sellingPrice"));
        if(cartItem.has("price") && !cartItem.isNull("price"))
            cartItemEntity.setItemPrice(cartItem.getDouble("price"));
        cartItemEntity.setTaxable(cartItem.getBoolean("taxable"));
        cartItemEntity.setItemQuantity(cartItem.getInt("quantity"));
        return cartItemEntity;
    }

    public ServiceChargeEntity transformServiceCharge(JSONObject serviceCharge) throws JSONException {
        ServiceChargeEntity serviceChargeEntity = new ServiceChargeEntity();
        serviceChargeEntity.setId(serviceCharge.getLong("id"));
        serviceChargeEntity.setName(serviceCharge.getString("name"));
        serviceChargeEntity.setValue(serviceCharge.getDouble("value"));
        serviceChargeEntity.setPercent(serviceCharge.getBoolean("percent"));
        return serviceChargeEntity;
    }

    public List<AddressEntity> transformAddressCollection(JSONObject response) throws JSONException {
        List<AddressEntity> addressEntities = new ArrayList<>();
        JSONArray addressArray = response.getJSONArray("items");
        for(int i=0; i<addressArray.length();i++){
            JSONObject address = addressArray.getJSONObject(i);
            addressEntities.add(address(address));
        }
        return addressEntities;
    }

    public AddressEntity transformAddress(JSONObject response) throws JSONException {
        JSONArray addressArray = response.getJSONArray("items");
        return address(addressArray.getJSONObject(0));
    }

    private AddressEntity address(JSONObject response) throws JSONException {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(response.getLong("id"));
        addressEntity.setAddress(response.getString("address"));
        addressEntity.setStreet(response.getString("street"));
        addressEntity.setCity(response.getString("city"));
        addressEntity.setState(response.getString("state"));
        addressEntity.setCountry(response.getString("country"));
        addressEntity.setZipCode(response.getString("zipCode"));
        addressEntity.setLatitude(response.getString("latitude"));
        addressEntity.setLongitude(response.getString("longitude"));
        return addressEntity;
    }
}
