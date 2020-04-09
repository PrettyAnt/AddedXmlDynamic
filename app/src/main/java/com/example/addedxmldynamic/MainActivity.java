package com.example.addedxmldynamic;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addedxmldynamic.utils.AnimationUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
        requestPermissions(this);
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
                Toast.makeText(MainActivity.this, "开始压缩", Toast.LENGTH_SHORT).show();
                //                Toast.makeText(MainActivity.this, "ivt_a.....point", Toast.LENGTH_SHORT).show();
                //                photoZoomSave("a.png", dir);
                long id = Looper.getMainLooper().getThread().getId();
                Log.d("wangbei", "-----------111>>>>>>" + id);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long id = Thread.currentThread().getId();
                        Log.d("wangbei", "-----------222>>>>>>" + id);
                        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/spdb_cache";
                        compressImage(dir, "a.png");
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "压缩结束", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }){}.start();

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
     * 质量压缩方法
     *
     * @param
     * @return
     */
    public static Bitmap compressImage(String path, String name) {
        Bitmap image = BitmapFactory.decodeFile(path + "/" + name, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        Log.i("wangbei", "原图片大小-->>" + baos.toByteArray().length);

        while ((baos.toByteArray().length / 256) > 1024) { // 循环判断如果压缩后图片是否大于1M(1024kb),大于继续压缩
            baos.reset(); // 重置baos即清空baos
            options -= 10;// 每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            Log.i("wangbei", "压缩中......" + baos.toByteArray().length);
        }
        Log.i("wangbei", "压缩结束-->" + baos.toByteArray().length);
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        File outFile = new File(path, "压缩后.png");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 把数据写入文件
            fos.close();
            image.recycle();
            bitmap.recycle();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    public static boolean photoZoomSave(String name, String dir) {
        Bitmap bitmap;
        File outDir = new File(dir);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        try {
            bitmap = createThumbnail(dir + "/" + name);
            File outFile = new File(outDir, "压缩后.png");
            FileOutputStream fos = new FileOutputStream(outFile);

            boolean flag = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 把数据写入文件
            fos.close();
            return flag;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Bitmap createThumbnail(String filepath) {
        File file = new File(filepath);
        int i = 1;
        if (file.exists()) {
            long weight = file.length();
            Log.i("wangbei", "压缩前文件大小:" + weight);
            int maxSize = 1 * 1000 * 1000;
            if (weight < maxSize) {
                i = 1;
            } else {
                int times = (int) (weight / maxSize);
                i = 2;
                while (i <= times) {
                    i = i * 2;
                }
            }
            Log.i("wangbei", "maxSize: " + maxSize + "  压缩比例:" + i + "  压缩后大小:" + weight / i / 1000);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        return BitmapFactory.decodeFile(filepath, options);
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
