package com.weiyi.mvpdemo.v.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.p.base.BasePresenter;
import com.weiyi.mvpdemo.v.CoordinatorActivity;
import com.weiyi.mvpdemo.v.MainActivity;
import com.weiyi.mvpdemo.v.TabLayoutActivity;
import com.weiyi.mvpdemo.v.ToolbarForActionBarActivity;
import com.weiyi.mvpdemo.v.base.BaseFragment;

import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

import static com.weiyi.mvpdemo.v.base.BaseActivity.themeIndex;

/**
 * Created by Lee on 2018/2/28 0028.
 */

public class TestFragment extends BaseFragment<MainActivity, BasePresenter> {

    @Bind(R.id.btn_tablayout)
    Button mBtnTablayout;
    @Bind(R.id.anim_view)
    ImageView mAnimView;
    @Bind(R.id.text_view)
    ImageView mTextView;
    @Bind(R.id.toolbar)
    public Toolbar mToolbar;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    public BasePresenter getPresenter() {
        return new BasePresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar.setTitle("主页");
        mToolbar.setNavigationIcon(R.mipmap.icon_me);
        mToolbar.inflateMenu(R.menu.menu_main);

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_search:
                        Random random = new Random();
                        themeIndex = random.nextInt(5);
                        Intent intent = mActivity.getIntent();
                        mActivity.finish();//结束当前的Activity
                        mActivity.overridePendingTransition(0,0);//不要动画
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "NavigationOnClick", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick({R.id.text_view, R.id.btn_tablayout, R.id.btn_toolBar, R.id.btn_coordinator})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.text_view:
                //                mAnimView.animate().scaleX(1f).scaleY(1f).setDuration(500).start();
                //开启一个调度作业
                //                JobInfo job = new JobInfo.Builder(0, new ComponentName(getApplicationContext(), TabLayoutActivity.class))
                //                        .setPeriodic(1000)
                //                        .setRequiresDeviceIdle(true)
                //                        .build();
                //
                //                JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
                //                if (scheduler.getAllPendingJobs().size() == 0)
                //                    Log.wtf("测试", scheduler.schedule(job) == JobScheduler.RESULT_SUCCESS
                //                            ? "LookForMediaJob scheduled successfully!" : "LookForMediaJob scheduled failed!");

                final ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1f, 0.8f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                ScaleAnimation scaleAnimation2 = new ScaleAnimation(1f, 0.8f, 1f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(150);
                scaleAnimation2.setDuration(150);
                scaleAnimation2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mAnimView.startAnimation(scaleAnimation);
                        mAnimView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mTextView.startAnimation(scaleAnimation2);
                mTextView.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_tablayout:
                startActivity(new Intent(mActivity, TabLayoutActivity.class));
                break;
            case R.id.btn_toolBar:
                startActivity(new Intent(mActivity, ToolbarForActionBarActivity.class));
                break;
            case R.id.btn_coordinator:
                startActivity(new Intent(mActivity, CoordinatorActivity.class));
                break;
        }

    }
}
