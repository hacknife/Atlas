package com.hacknife.atlas.http;


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

    }

    @Override
    public void onComplete() {

    }
}
