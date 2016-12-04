package com.baseapp.uiframework.frame.base.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * ActivityManager
 *
 * @author lanjl
 * @version 2016/12/2
 */
public enum PageManager {

    INSTANCE;

    private static Stack<WeakReference<Activity>> activityStack;


    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<WeakReference<Activity>>();
        }
        activityStack.add(new WeakReference<Activity>(activity));
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        WeakReference<Activity> activity = activityStack.lastElement();
        return activity.get();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        WeakReference<Activity> wactivity = activityStack.lastElement();
        if(wactivity.get() != null) {
            finishActivity(wactivity.get());
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
           // activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (WeakReference<Activity> wactivity : activityStack) {
            Activity  activity  = wactivity.get();
            if (activity !=null  && activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            WeakReference<Activity> wactivity = activityStack.get(i);
            if (wactivity != null && wactivity.get() !=null) {
                wactivity.get().finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr =
                    (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }


    public void destroyExcept(Class<? extends Activity> clazz) {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            WeakReference<Activity> wactivity = activityStack.get(i);
            if (wactivity != null && wactivity.get() != null&&  !wactivity.get().getClass().equals(clazz)) {
              //  activityStack.remove()
                wactivity.get().finish();
            }
        }

    }

    public boolean isAppExit() {
        return activityStack == null || activityStack.isEmpty();
    }

}
