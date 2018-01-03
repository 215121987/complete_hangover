package com.hangover.ashqures.hangover.view;

import com.hangover.ashqures.hangover.dto.StatusDTO;

/**
 * Created by ashqures on 9/4/16.
 */
public interface DialogView extends LoadDataView {


    void positiveAction(StatusDTO status);

    void negativeAction(StatusDTO status);

    void onFailure(StatusDTO statusDTO);

}
