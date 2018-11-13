package com.test.loblaw.newsfeed;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import io.reactivex.Observable;

public class RxUtils {
    public static <T> Observable<T> toObservable(@NonNull final ObservableField<T> observableField) {
        return Observable.create(emitter -> {
            final android.databinding.Observable.OnPropertyChangedCallback callback = new android.databinding.Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(android.databinding.Observable dataBindingObservable, int propertyId) {
                    if (dataBindingObservable == observableField) {
                        emitter.onNext(observableField.get());
                    }
                }
            };

            observableField.addOnPropertyChangedCallback(callback);

            //emitter.setCancellation(() -> observableField.removeOnPropertyChangedCallback(callback));
        });
    }

}
