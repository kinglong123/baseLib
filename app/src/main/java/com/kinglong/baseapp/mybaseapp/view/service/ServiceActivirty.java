package com.kinglong.baseapp.mybaseapp.view.service;

import com.kinglong.baseapp.mybaseapp.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

/**
 * Created by lanjl on 2019/4/10.
 */
public class ServiceActivirty extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void onClick(View view) {
        Intent intent = new Intent(this, MyService.class);
        switch (view.getId()) {
            case R.id.startBT:
                startService(intent);
                break;
            case R.id.stopBT:
                stopService(intent);
                break;
            case R.id.bindBT:
//				i++;
//				if(i<=3) {
                bindService(intent, mConntectin, BIND_AUTO_CREATE);
//				}else {
//					bindService(intent, mConntectin1, BIND_AUTO_CREATE);
//				}


                break;
            case R.id.unbindBT:
                try {
                    unbindService(mConntectin);
                }catch (Exception e){

                }

                break;
        }

    }
    private ServiceConnection mConntectin = new MyConn();
    private class MyConn implements ServiceConnection {

        // 在服务被成功绑定的时候 调用的方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("111111春哥把代理人返回回来了...");
            // 步骤3: 服务返回的ibinder对象 会被传递给 MyConn 的回调方法
            System.out.println("1111111"+service);

            MyService myService =((MyService.LocalBinder)service).getService();
            myService.todoSomeThingwaibu("1111111");

            ((MyService.LocalBinder)service).todoSomeThingneibu("1111111");

        }

        // 在服务失去绑定的时候 调用的方法 只有程序异常 终止,
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

    }


}
