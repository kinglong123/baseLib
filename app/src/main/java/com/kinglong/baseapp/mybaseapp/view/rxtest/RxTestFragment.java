package com.kinglong.baseapp.mybaseapp.view.rxtest;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.view.base.BaseFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by lanjl on 2017/4/14.
 */

public class RxTestFragment extends BaseFragment {

    Button mButton;
    Button mButtonmergeDelayError;
    Button mButtonmergezip;


    public static  RxTestFragment newInstance(){
        Bundle args = new Bundle();

        RxTestFragment fragment = new RxTestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rxtest;
    }

    @Override
    protected void afterCreate(Bundle state) {
        mButton = findViewCall(R.id.btmerge);
        mButtonmergeDelayError = findViewCall(R.id.mergeDelayError);
        mButtonmergezip = findViewCall(R.id.mergezip);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                merge();
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                merge();
            }
        });

        mButtonmergeDelayError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mergeDelayError();
            }
        });


        mButtonmergezip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mergezipError();
            }
        });
    }


    public void merge() {
        Integer[]itemsOne = {1,2,3,4,5,5,5,5,5,1,};
        Integer[]itemsTwo = {6,7,8,9,100000};

        //这样执行，上面再子线程执行
        //onNext.......XINACEHNG.......... 在主线程执行

        Observable<Integer> one = Observable.from(itemsOne).doOnNext(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("onNext....one...zhixinggetId()....1111......"+Thread.currentThread().getId());
                System.out.println("onNext....one....zhixing.......1111.."+integer);
            }
        });
        Observable<Integer> two =  Observable.from(itemsTwo).doOnNext(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("onNext...two.......zhixinggetId()...1111......."+Thread.currentThread().getId());
                System.out.println("onNext...two........zhixing.......1111.."+integer);
            }
        });;
        //----------------------------------------------------------------
        Observable<Integer> myObservable = Observable.merge(one.subscribeOn(Schedulers.io()),
                two.subscribeOn(Schedulers.io())) .observeOn(AndroidSchedulers.mainThread());//

        Subscriber<Integer> mySubscriber =new Subscriber<Integer>() {
            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext.......XINACEHNG.........."+Thread.currentThread().getId());
                System.out.println("onNext................."+integer);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted.................");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError.....................");
            }
        };

        myObservable.subscribe(mySubscriber);

    }

    public void mergeDelayError() {
        Integer[]itemsTwo = {1,2,3,4,5};
        Observable<Integer> one =  Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    if(i==3){
                        subscriber.onError(new Throwable("error"));
                    }else {
                        subscriber.onNext(i);
                    }
                    try {
                        Thread.sleep(100*i);
                    } catch (Exception e) {

                    }
                }
                subscriber.onCompleted();
            }
        });

        Observable<Integer> two =  Observable.from(itemsTwo);

        Observable<Integer> myObservable = Observable.mergeDelayError(two,one);

        Subscriber<Integer> mySubscriber =new Subscriber<Integer>() {
            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext................."+integer);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted.................");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError.....................");
            }
        };
        myObservable.subscribe(mySubscriber);
    }


    public void mergezipError() {
        Integer[]itemsOne = {1,2,3,4};//它只发射与发射数据项最少的那个Observable一样多的数据。
        Integer[]itemsTwo = {6,7,8,9,10};

        Observable<Integer> one = Observable.from(itemsOne);
        Observable<Integer> two = Observable.from(itemsTwo);

        Observable myObservable = Observable.zip(one, two, new Func2<Integer, Integer, Integer>() {

            @Override
            public Integer call(Integer integer1, Integer integer2) {
                return integer1 +  integer2;
            }
        });

        Subscriber<Integer> mySubscriber =new Subscriber<Integer>() {
            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext................."+integer);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted.................");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError.....................");
            }
        };

        myObservable.subscribe(mySubscriber);
    }
}
