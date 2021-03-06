/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hangover.ashqures.hangover.navigation;

import android.content.Context;
import android.content.Intent;

import com.hangover.ashqures.hangover.activity.CartActivity;
import com.hangover.ashqures.hangover.activity.HomeActivity;
import com.hangover.ashqures.hangover.activity.LoginAndSignUpActivity;
import com.hangover.ashqures.hangover.activity.StoreActivity;
import com.hangover.ashqures.hangover.activity.StoreItemActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
        //empty
    }

    public void navigateToHome(Context context) {
        if (null != context) {
            Intent intentToLaunch = HomeActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToLogin(Context context) {
        if (null != context) {
            Intent intentToLaunch = LoginAndSignUpActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToLogout(Context context) {
        if (null != context) {
            Intent intentToLaunch = LoginAndSignUpActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the store screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToStore(Context context) {
        if (null != context) {
            Intent intentToLaunch = StoreActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the item details screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToItemDetails(Context context, Long itemId) {
        if (null != context) {
            Intent intentToLaunch = StoreItemActivity.getCallingIntent(context, itemId);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToCart(Context context) {
        if (null != context) {
            Intent intentToLaunch = CartActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToCheckout(Context context) {
        if (null != context) {
            Intent intentToLaunch = LoginAndSignUpActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}
