package com.hacknife.atlas.http;


import android.util.Log;

import com.orhanobut.logger.Logger;

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
        Logger.i( "onError: "+e.toString());
    }

    @Override
    public void onComplete() {

    }
}
