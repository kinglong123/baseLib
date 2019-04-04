package com.kinglong.baseapp.mybaseapp.view.keyboard;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.view.base.BaseFragment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by lanjl on 2019/4/4.
 */
public class KeyBoardFragment extends BaseFragment {

    public static KeyBoardFragment newInstance() {

        Bundle args = new Bundle();

        KeyBoardFragment fragment = new KeyBoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.keyboard;
    }
    ListView mListView;
    KeyboardUtil  mKeyboardUtil;
    @Override
    protected void afterCreate(Bundle state) {
        mListView = findViewCall(R.id.lv);
        mKeyboardUtil = new KeyboardUtil(getActivity());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_expandable_list_item_1,
                getData());
        mListView.setAdapter(adapter);

        mKeyboardUtil.setOnKeyboardChangeListener(new KeyboardUtil.KeyboardChangeListener() {
            @Override
            public void onKeyboardShow(int keyboardHight) {


                System.out.println("keyboardHight:"+keyboardHight);
                mListView.setSelection(mListView.getBottom());
            }

            @Override
            public void onKeyboardHide() {

            }
        });
    }
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> getData()
    {
        list.add("180平米的房子");
        list.add("一个勤劳漂亮的老婆");
        list.add("一辆宝马");
        list.add("一个强壮且永不生病的身体");
        list.add("一个喜欢的事业");
        list.add("一个喜欢的事业");
        list.add("一个喜欢的事业");  list.add("一个喜欢的事业");
        list.add("一个喜欢的事业");
        list.add("一个喜欢的事业");
        list.add("180平米的房子");
        list.add("一个喜欢的事业");
        list.add("一个喜欢的事业");
        list.add("180平米的房子");
        list.add("一个喜欢的事业");
        list.add("一个喜欢的事业");
        list.add("180平米的房子");
        list.add("一个喜欢的事业");
        list.add("一个喜欢的事业");
        list.add("180平米的房子");
        list.add("一个喜欢的事业");
        list.add("一个喜欢的事业");
        list.add("180平米的房子");
        list.add("一个喜欢的事业");
        list.add("一个喜欢的事业");
        list.add("180平米的房子");
        list.add("一个喜欢的事业");
        list.add("一个喜欢的事业");
        list.add("180平米的房子");
        list.add("一个喜欢的事业");
        list.add("一个喜欢的事业");
        list.add("180平米的房子");
        list.add("一个喜欢的事业");
        list.add("一个喜欢的事业");
        list.add("180平米的房子");

        return list;
    }
}
