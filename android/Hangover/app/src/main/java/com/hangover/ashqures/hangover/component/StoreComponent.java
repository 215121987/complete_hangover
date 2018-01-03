package com.hangover.ashqures.hangover.component;

import com.hangover.ashqures.hangover.fragments.AuthFragment;
import com.hangover.ashqures.hangover.fragments.CartFragment;
import com.hangover.ashqures.hangover.fragments.HomeFragment;
import com.hangover.ashqures.hangover.fragments.StoreFragment;
import com.hangover.ashqures.hangover.fragments.StoreItemFragment;
import com.hangover.ashqures.hangover.modules.ActivityModule;
import com.hangover.ashqures.hangover.modules.StoreModule;

import dagger.Component;

/**
 * Created by ashqures on 8/11/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, StoreModule.class})
public interface StoreComponent extends ActivityComponent{

    void inject(StoreFragment storeFragment);

    void inject(StoreItemFragment itemDetailFragment);

    void inject(HomeFragment homeFragment);

    void inject(CartFragment cartFragment);

}
