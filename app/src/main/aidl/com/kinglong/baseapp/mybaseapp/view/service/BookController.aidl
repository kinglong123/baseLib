// BookController.aidl
package com.kinglong.baseapp.mybaseapp.view.service;

// Declare any non-default types here with import statements
import com.kinglong.baseapp.mybaseapp.view.service.Book;
interface BookController {
    List<Book> getBookList();

    void addBookInOut(inout Book book);

    void addBookIn(in Book book);

    void addBookOut(out Book book);

}
