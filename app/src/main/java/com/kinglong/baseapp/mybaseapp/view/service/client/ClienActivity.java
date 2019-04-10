package com.kinglong.baseapp.mybaseapp.view.service.client;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.view.service.Book;
import com.kinglong.baseapp.mybaseapp.view.service.BookController;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by lanjl on 2019/4/10.
 */
public class ClienActivity  extends Activity {

    private final String TAG = "Client";

    private BookController bookController;

    private boolean connected;

    private List<Book> bookList;
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_getBookList:
                    if (connected) {
                        try {
                            bookList = bookController.getBookList();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        log();
                    }
                    break;
                case R.id.btn_addBook_inOut:
                    if (connected) {
                        Book book = new Book("这是一本新书 InOut");
                        try {
                            bookController.addBookInOut(book);
                            Log.e(TAG, "向服务器以InOut方式添加了一本新书");
                            Log.e(TAG, "新书名：" + book.getName());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case R.id.btn_addBook_in:
                    if (connected) {
                        Book book = new Book("这是一本新书 In");
                        try {
                            bookController.addBookIn(book);
                            Log.e(TAG, "向服务器以In方式添加了一本新书");
                            Log.e(TAG, "新书名：" + book.getName());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case R.id.btn_addBook_out:
                    if (connected) {
                        Book book = new Book("这是一本新书 Out");
                        try {
                            bookController.addBookOut(book);
                            Log.e(TAG, "向服务器以Out方式添加了一本新书");
                            Log.e(TAG, "新书名：" + book.getName());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_main);
        findViewById(R.id.btn_getBookList).setOnClickListener(clickListener);
        findViewById(R.id.btn_addBook_inOut).setOnClickListener(clickListener);
        findViewById(R.id.btn_addBook_in).setOnClickListener(clickListener);
        findViewById(R.id.btn_addBook_out).setOnClickListener(clickListener);
        bindService();
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setPackage("com.kinglong.baseapp.mybaseapp");
        intent.setAction("com.kinglong.server.action");

//        绑定的操作与绑定本地服务的操作差不多，不过也就是多了个setAction与setPackage。
//        这个setAction所传进去的正是刚才在服务端说定义的action，而setPackage则是服务端的包名，
// 注意不是服务端的Service所在的包名，而是服务端的app包名。
//        这里多说两句的就是~在4.4之前只需要setAction就可以绑定远程服务了，
// 但是5.0之后就不能够这样绑定了，原因是什么不安全~为了兼容我们也写上setpackage

        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookController = BookController.Stub.asInterface(service);
            connected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connected) {
            unbindService(serviceConnection);
        }
    }

    private void log() {
        for (Book book : bookList) {
            Log.e(TAG, book.toString());
        }
    }

}
