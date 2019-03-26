package com.nd.hy.android.elearning.eleassist.component;

import org.junit.Test;

import android.bluetooth.BluetoothDevice;

import rx.Emitter;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Cancellable;
import rx.schedulers.Schedulers;

//import com.nd.smartcan.commons.util.security.Base64;

//import android.util.Base64;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {

//        System.out.print("212");
        Subscription subscription1 = Observable
                .fromEmitter(new Action1<Emitter<BluetoothDevice>>() {
                    boolean isC = false;

                    @Override
                    public void call(final Emitter<BluetoothDevice> bluetoothDeviceEmitter) {
//                final BluetoothAdapter.LeScanCallback scanCallback = new BluetoothAdapter.LeScanCallback() {
//                    @Override
//                    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {
//                        bluetoothDeviceEmitter.onNext(bluetoothDevice);
//                    }
//                };
//                // 开始扫描
//                mBluetoothAdapter.startLeScan(scanCallback) ;
                        // 当 unsubscribe 时执行该方法

                        bluetoothDeviceEmitter.setCancellation(new Cancellable() {
                            @Override
                            public void cancel() throws Exception {
                                isC = true;

                                System.out.println("111111111zz:cancel");
                            }
                        });
                        while (!isC) {
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                            System.out.println("11111122ss22:isC");
                        }


                    }
                }, Emitter.BackpressureMode.BUFFER)
                .subscribeOn(Schedulers.newThread())//起线程
                .subscribe(new Action1<BluetoothDevice>() {
                    @Override
                    public void call(BluetoothDevice bluetoothDevice) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

        System.out.println("1111111111isUnsubscribed" + subscription1.isUnsubscribed());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subscription1.unsubscribe();
        System.out.println("1111111111isUnsubscribed" + subscription1.isUnsubscribed());
//        testUnsubscribed testUnsubscribed = new testUnsubscribed();
//
//        Subscription subscription =  Observable.create(testUnsubscribed).doOnUnsubscribe(new Action0() {
//            @Override
//            public void call() {
//
//            }
//        })
//         .subscribe(new Action1<Object>() {
//            @Override
//            public void call(Object o) {
//                while (true){
//
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("111111hhh:");
//
//                }
//
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//
//            }
//        });
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        subscription.unsubscribe();
//
//    }

        class testUnsubscribed implements Observable.OnSubscribe<Object> {

            Subscriber mSubscriber;

            @Override
            public void call(Subscriber<? super Object> subscriber) {
                mSubscriber = subscriber;
                while (!isCancle()) {
                    System.out.println("111111222:" + isCancle());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1111113333:" + isCancle());

                }


            }

            public boolean isCancle() {

                return mSubscriber.isUnsubscribed();
            }
        }

    }


    @Test
    public void addition_isCorrect2() throws Exception {

        testUnsubscribed testUnsubscribed = new testUnsubscribed();

        Subscription subscription = Observable.create(testUnsubscribed)
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .subscribeOn(Schedulers.newThread())//起线程
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        while (true) {

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("111111hhh:");

                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        System.out.println("1111111111isUnsubscribed" + subscription.isUnsubscribed());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subscription.unsubscribe();
        System.out.println("1111111111isUnsubscribed" + subscription.isUnsubscribed());

    }

    class testUnsubscribed implements Observable.OnSubscribe<Object> {

        Subscriber mSubscriber;

        @Override
        public void call(Subscriber<? super Object> subscriber) {
            mSubscriber = subscriber;
            while (!isCancle()) {
                System.out.println("111111222:" + isCancle());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1111113333:" + isCancle());

            }


        }

        public boolean isCancle() {

            return mSubscriber.isUnsubscribed();
        }
    }


}