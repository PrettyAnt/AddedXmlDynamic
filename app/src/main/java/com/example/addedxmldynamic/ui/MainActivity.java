package com.example.addedxmldynamic.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addedxmldynamic.R;
import com.example.addedxmldynamic.utils.AnimationUtils;
import com.example.addedxmldynamic.utils.pressImageUtils;

import java.io.File;

/**
 * 动态添加xml布局
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mAddView;
    private LinearLayout mMainNeed;
    private TextView mRemoveView;
    private String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/spdb_cache";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        requestPermissions(this);//申请权限
    }

    private void initView() {
        mAddView = (TextView) findViewById(R.id.tv_add_view);
        mRemoveView = (TextView) findViewById(R.id.tv_remove_view);
        mMainNeed = (LinearLayout) findViewById(R.id.ll_main_need_view);
        mAddView.setOnClickListener(this);
        mRemoveView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_view://添加布局
                Toast.makeText(MainActivity.this, "添加布局", Toast.LENGTH_SHORT).show();
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View inflate = inflater.inflate(R.layout.view_yun, null);
                LinearLayout containView = (LinearLayout) inflate.findViewById(R.id.ll_yun_view);//view添加成功
                containView.setLayoutParams(params);
                mMainNeed.addView(containView);
                AnimationUtils.showAndHiddenAnimation(MainActivity.this, mMainNeed, AnimationUtils.AnimationState.STATE_SHOW, 500);
                initContainView(containView);

                break;
            case R.id.tv_remove_view://移除布局
                Toast.makeText(MainActivity.this, "移除布局", Toast.LENGTH_SHORT).show();
                AnimationUtils.showAndHiddenAnimation(MainActivity.this, mMainNeed, AnimationUtils.AnimationState.STATE_HIDDEN, 1000);
                break;
            case R.id.itv_a://图片压缩
                //添加成功后的控件id
                Toast.makeText(MainActivity.this, "开始压缩", Toast.LENGTH_SHORT).show();
                File file = new File(dir + "/a.png");
                if (!file.exists()) {
                    Toast.makeText(MainActivity.this, "图片不存在-->> " + dir + "/a.png", Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        pressImageUtils.compressImage(dir, "a.png");
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "压缩结束", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }) {
                }.start();

                break;
            case R.id.itv_b:
                Toast.makeText(MainActivity.this, "频道管理页面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, CustomChannelActivity.class));//频道管理页面
                break;
            case R.id.itv_c:
                Toast.makeText(MainActivity.this, "ivt_c.....point", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itv_d:
                Toast.makeText(MainActivity.this, "ivt_d.....point", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itv_e:
                Toast.makeText(MainActivity.this, "ivt_e.....point", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itv_f:
                Toast.makeText(MainActivity.this, "ivt_f.....point", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itv_g:
                Toast.makeText(MainActivity.this, "ivt_g.....point", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itv_h:
                Toast.makeText(MainActivity.this, "ivt_h.....point", Toast.LENGTH_SHORT).show();
                break;
            default:
        }

    }

    private Handler mHandler = new Handler();

    private void initContainView(LinearLayout linearLayout) {
        View itvA = linearLayout.findViewById(R.id.itv_a);
        View itvB = linearLayout.findViewById(R.id.itv_b);
        View itvC = linearLayout.findViewById(R.id.itv_c);
        View itvD = linearLayout.findViewById(R.id.itv_d);
        View itvE = linearLayout.findViewById(R.id.itv_e);
        View itvF = linearLayout.findViewById(R.id.itv_f);
        View itvG = linearLayout.findViewById(R.id.itv_g);
        View itvH = linearLayout.findViewById(R.id.itv_h);
        itvA.setOnClickListener(MainActivity.this);
        itvB.setOnClickListener(MainActivity.this);
        itvC.setOnClickListener(MainActivity.this);
        itvD.setOnClickListener(MainActivity.this);
        itvE.setOnClickListener(MainActivity.this);
        itvF.setOnClickListener(MainActivity.this);
        itvG.setOnClickListener(MainActivity.this);
        itvH.setOnClickListener(MainActivity.this);
    }

    /**
     * 申请权限
     *
     * @param activity
     */
    public void requestPermissions(Activity activity) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0x0010);
                }

                if (permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0x0010);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
