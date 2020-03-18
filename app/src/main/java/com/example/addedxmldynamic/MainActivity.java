package com.example.addedxmldynamic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addedxmldynamic.utils.AnimationUtils;

/**
 * 动态添加xml布局
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mAddView;
    private LinearLayout mMainNeed;
    private TextView mRemoveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
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
            case R.id.tv_add_view:
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View inflate = inflater.inflate(R.layout.view_yun, null);
                LinearLayout containView = (LinearLayout) inflate.findViewById(R.id.ll_yun_view);//view添加成功
                containView.setLayoutParams(params);
                mMainNeed.addView(containView);
                AnimationUtils.showAndHiddenAnimation(MainActivity.this, mMainNeed, AnimationUtils.AnimationState.STATE_SHOW, 500);
                initContainView(containView);

                break;
            case R.id.tv_remove_view:
                AnimationUtils.showAndHiddenAnimation(MainActivity.this, mMainNeed, AnimationUtils.AnimationState.STATE_HIDDEN, 1000);
                break;
            case R.id.itv_a:
                //添加成功后的控件id
                Toast.makeText(MainActivity.this, "ivt_a.....point", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itv_b:
                Toast.makeText(MainActivity.this, "ivt_b.....point", Toast.LENGTH_SHORT).show();
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
}
