package com.hangover.ashqures.hangover.view;

import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.dto.StatusDTO;
import com.hangover.ashqures.hangover.dto.UserDTO;

/**
 * Created by ashqures on 8/19/16.
 */
public interface AuthView extends LoadDataView {



    void handleSignIn(UserDTO userDTO);

    void handleSignUp(UserDTO userDTO);

    void handleIdentifyUser(StatusDTO statusDTO);

}
