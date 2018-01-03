package com.hangover.ashqures.hangover.view;

import com.hangover.ashqures.hangover.dto.ItemDTO;

import java.util.Collection;
import java.util.List;

/**
 * Created by ashqures on 8/7/16.
 */
public interface StoreView extends LoadDataView {


    void renderStore(List<ItemDTO> itemDTOCollection);

    void viewItem(ItemDTO itemDTO);
}
