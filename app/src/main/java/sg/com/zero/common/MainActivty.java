package sg.com.zero.common;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import sg.com.base.BaseActivity;
import sg.com.base.model.EventMessage;
import sg.com.zero.R;
import sg.com.zero.databinding.ActivityMainBinding;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by sg on 2017/7/29.
 */

public class MainActivty extends BaseActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    public void initUI() {

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.rcvIndex.setLayoutManager(new LinearLayoutManager(this));

        activityMainBinding.rcvIndex.setAdapter(new IndexRecycleViewAdapter(context));

        Intent intent = new Intent(MainActivty.this, TestActivity.class);
        
        startActivity(intent);


    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {


    }

    @Override
    public void onEventMessage(EventMessage em) {

    }


}
