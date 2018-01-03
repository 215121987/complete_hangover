package com.hangover.ashqures.hangover.component;

import com.hangover.ashqures.hangover.fragments.AuthFragment;
import com.hangover.ashqures.hangover.modules.ActivityModule;
import com.hangover.ashqures.hangover.modules.StoreModule;

import dagger.Component;

/**
 * Created by ashqures on 8/20/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, StoreModule.class})
public interface AuthComponent extends ActivityComponent{

    void inject(AuthFragment authFragment);
}
