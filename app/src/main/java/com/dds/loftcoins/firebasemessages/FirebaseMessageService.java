package com.dds.loftcoins.firebasemessages;

import androidx.annotation.NonNull;

import com.dds.loftcoins.BaseComponent;
import com.dds.loftcoins.LoftCoins;
import com.dds.loftcoins.R;
import com.dds.loftcoins.utils.INotifier;
import com.dds.loftcoins.ux.main.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class FirebaseMessageService  extends FirebaseMessagingService {
    private final CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    INotifier notifier;

    @Override
    public void onCreate() {
        super.onCreate();
        final BaseComponent baseComponent = ((LoftCoins) getApplication()).getComponent();
        DaggerFirebaseMessageComponent.builder().baseComponent(baseComponent).build().inject(this);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        final RemoteMessage.Notification notification = remoteMessage.getNotification();
        if (notification != null) {
            disposable.add(notifier.sendMessage(
                    Objects.toString(notification.getTitle(), getString(R.string.app_name)),
                    Objects.toString(notification.getBody(), "Somethings wrong!"),
                    MainActivity.class
            ).subscribe());
        }
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
