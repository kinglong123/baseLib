package com.kinglong.baseapp.mybaseapp.view.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lanjl on 2019/4/10.
 */
public class Book implements Parcelable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }
    public void readFromParcel(Parcel dest) {
        name = dest.readString();
    }

    @Override
    public String toString() {
        return "book name：" + name;
    }

    public Book() {
    }
    public Book(String name) {
        this.name = name;
    }
    protected Book(Parcel in) {
        this.name = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
