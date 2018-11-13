package com.test.loblaw.newsfeed.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.test.loblaw.newsfeed.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

public class DetailsFragmentViewModel extends ViewModel {
    public final ObservableField<String> content = new ObservableField<>("");
    public final ObservableField<String> password = new ObservableField<>();

    public final ObservableField<String> rxField = new ObservableField<>();
    private BehaviorSubject<Void> testClickedSubject = BehaviorSubject.create();

    public void initSubscriptions() {
        Observable<String> obs = RxUtils.toObservable(content);
        Disposable ds = obs.subscribe(rxField::set);

        Observable<Void> newObservable = Observable.combineLatest(
                RxUtils.toObservable(content),
                RxUtils.toObservable(password),
                testClickedSubject,
                (x, y, z) -> null);
    }

    public void onTestClicked() {
        testClickedSubject.onNext(null);
    }
}
