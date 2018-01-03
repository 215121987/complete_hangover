package com.hangover.ashqures.hangover.view;

import com.hangover.ashqures.hangover.dto.ItemDTO;

/**
 * Created by ashqures on 8/7/16.
 */
public interface ItemView extends LoadDataView {


    void renderItem(ItemDTO itemDTO);

}
