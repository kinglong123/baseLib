package com.kinglong.baseapp.mybaseapp.view.service.remote;

import com.kinglong.baseapp.mybaseapp.view.service.Book;
import com.kinglong.baseapp.mybaseapp.view.service.BookController;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanjl on 2019/4/10.
 */
public class AIDLService  extends Service {
    private final String TAG = "Server";

    private List<Book> bookList;

    public AIDLService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        bookList = new ArrayList<>();
        initData();
    }
    private void initData() {
        Book book1 = new Book("活着");
        Book book2 = new Book("或者");
        Book book3 = new Book("叶应是叶");
        Book book4 = new Book("https://github.com/leavesC");
        Book book5 = new Book("http://www.jianshu.com/u/9df45b87cfdf");
        Book book6 = new Book("http://blog.csdn.net/new_one_object");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);
        bookList.add(book6);
    }

   private final BookController.Stub mStub = new BookController.Stub() {
       @Override
       public List<Book> getBookList() throws RemoteException {
           return bookList;
       }

       @Override
       public void addBookInOut(Book book) throws RemoteException {
           if (book != null) {
               book.setName("服务器改了新书的名字 InOut");
               bookList.add(book);
           } else {
               Log.e(TAG, "接收到了一个空对象 InOut");
           }
       }

       @Override
       public void addBookIn(Book book) throws RemoteException {
           if (book != null) {
               book.setName("服务器改了新书的名字 In");
               bookList.add(book);
           } else {
               Log.e(TAG, "接收到了一个空对象 In");
           }
       }

       //Out类型的表现形式是：数据只能由服务端传向客户端         （在于服务端不同进程的情况下才会是空的   android:process=":remote"）
       // ，即使客户端向方法接口传入了一个对象，该对象中的属性值也是为空的，
       // 即不包含任何数据，服务端获取到该对象后，对该对象的任何操作，就会同步到客户端这边
       //下面这个
       @Override
       public void addBookOut(Book book) throws RemoteException {
           if (book != null) {
               Log.e(TAG, "客户端传来的书的名字：" + book.getName());//为空
               book.setName("服务器改了新书的名字 Out");
               bookList.add(book);
           } else {
               Log.e(TAG, "接收到了一个空对象 Out");
           }
       }
   };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
}
