package com.dds.loftcoins.firebasemessages;


import com.dds.loftcoins.BaseComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        FirebaseMessageModule.class
}, dependencies = {
        BaseComponent.class
})
abstract class FirebaseMessageComponent {

    abstract void inject(FirebaseMessageService service);

}

