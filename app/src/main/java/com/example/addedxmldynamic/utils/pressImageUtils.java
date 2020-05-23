package com.example.addedxmldynamic.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ChenYu
 * Author's github https://github.com/PrettyAnt
 * <p>
 * Created on 11:19 AM  2020/5/22
 * PackageName : com.example.addedxmldynamic.utils
 * describle :
 */
public class pressImageUtils {

    /**
     * 质量压缩方法-->> 需求：上传的图片小于5M
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
        Log.i("wangbei", "压缩结束-->>" + baos.toByteArray().length);
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
}
