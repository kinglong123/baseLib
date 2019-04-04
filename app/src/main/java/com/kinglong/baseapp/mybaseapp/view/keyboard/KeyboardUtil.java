package com.kinglong.baseapp.mybaseapp.view.keyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by lanjl on 2019/4/3.
 */
public class KeyboardUtil {

    Activity mContext;

    /**
     * 虚拟键盘高度
     */
    int virtualKeyboardHeight;

    /**
     * 屏幕高度
     */
    int screenHeight;

    /**
     * 屏幕6分之一的高度，作用是防止获取到虚拟键盘的高度
     */
    int screenHeight6;

    View rootView;

    public KeyboardUtil(Activity context) {
        this.mContext = context;
        /**
         * 获取屏幕的高度,该方式的获取不包含虚拟键盘
         */
        screenHeight = mContext.getResources().getDisplayMetrics().heightPixels;
        screenHeight6 = screenHeight / 6;
        rootView = mContext.getWindow().getDecorView();
    }

    /**
     * @param listener
     */
    public void setOnKeyboardChangeListener(final KeyboardChangeListener listener) {
        rootView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    //当键盘弹出隐藏的时候会 调用此方法。
                    @Override
                    public void onGlobalLayout() {
                        /**
                         * 回调该方法时rootView还未绘制，需要设置绘制完成监听
                         */
//                        rootView.post(new Runnable() {
//                            @Override
//                            public void run() {
                        Rect rect = new Rect();
                        /**
                         * 获取屏幕底部坐标
                         */
                        rootView.getWindowVisibleDisplayFrame(rect);
                        /**
                         * 获取键盘的高度
                         */
                        int heightDifference = screenHeight - rect.bottom;
                        if (heightDifference < screenHeight6) {
                            virtualKeyboardHeight = heightDifference;
                            if (listener != null) {
                                listener.onKeyboardHide();
                            }
                        } else {
                            if (listener != null) {
                                listener.onKeyboardShow(
                                        heightDifference - virtualKeyboardHeight);
                            }
                        }
//                            }
//                        });
                    }
                });
    }

    /**
     * 软键盘状态切换监听
     */
    public interface KeyboardChangeListener {

        /**
         * 键盘弹出
         *
         * @param keyboardHight 键盘高度
         */
        void onKeyboardShow(int keyboardHight);

        /**
         * 键盘隐藏
         */
        void onKeyboardHide();
    }

    public static void showKeyboard(Context context, EditText editText) {
        //其中editText为dialog中的输入框的 EditText
        if (editText != null) {
            //设置可获得焦点
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            //请求获得焦点
            editText.requestFocus();
            //调用系统输入法
            InputMethodManager inputManager = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editText, 0);
        }
    }

}
