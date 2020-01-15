package com.hacknife.atlas.http;


import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class Consumer<T> implements Observer<T> {
    private CompositeDisposable disposable;

    public Consumer(CompositeDisposable disposable) {
        this.disposable = disposable;
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable.add(d);
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        Log.i("dzq", "onError: "+e.toString());
    }

    @Override
    public void onComplete() {

    }
}
