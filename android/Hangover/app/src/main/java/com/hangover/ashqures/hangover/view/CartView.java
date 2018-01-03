package com.hangover.ashqures.hangover.view;

import com.hangover.ashqures.hangover.dto.AddressDTO;
import com.hangover.ashqures.hangover.dto.CartDTO;

import java.util.List;

/**
 * Created by ashqures on 8/28/16.
 */
public interface CartView extends LoadDataView {


    void renderView(CartDTO cartDTO);

    void viewItem(Long itemId);

    void renderAddress(List<AddressDTO> addressDTOs);

    void addAddress(AddressDTO addressDTO);

    void removeAddress();


}
