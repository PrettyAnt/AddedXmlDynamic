package com.example.addedxmldynamic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TicketRunnable implements Runnable {
    private final String name;
    private final String path;

    public TicketRunnable(String path, String name) {
        this.path = path;
        this.name = name;
    }

    // 为了保持票数的一致，票数要静态
    private static int tick = 20;

    // 重写run方法，实现买票操作
    @Override
    public void run() {
        Bitmap image = BitmapFactory.decodeFile(path + "/" + name, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        Log.i("wangbei", "原图片大小-->>" + baos.toByteArray().length);
        while (baos.toByteArray().length / 1024 > 256) {
            synchronized (TicketRunnable.class) {// 这个很重要，必须使用一个锁，
                // 进去的人会把钥匙拿在手上，出来后才把钥匙拿让出来
                //                if (tick > 0) {
                //                    System.out.println(Thread.currentThread().getName() + "卖出了第" + tick + "张票");
                //                    tick--;
                //                } else {
                //                    System.out.println("票卖完了");
                //                }


                if (baos.toByteArray().length / 1024 > 256) { // 循环判断如果压缩后图片是否大于1M(1024kb),大于继续压缩
                    baos.reset(); // 重置baos即清空baos
                    options -= 2;// 每次都减少10
                    image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                    Log.i("wangbei", "压缩中......" + baos.toByteArray().length);
                } else {
                    Log.i("wangbei", "压缩结束-->>" + baos.toByteArray().length);
                    ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
                    Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
                    File outFile = new File(path, "压缩后.png");
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(outFile);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 把数据写入文件
                        fos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public static Bitmap compressImage(String path, String name) {
        Bitmap image = BitmapFactory.decodeFile(path + "/" + name, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        Log.i("wangbei", "原图片大小-->>" + baos.toByteArray().length);

        while (baos.toByteArray().length / 1024 > 256) { // 循环判断如果压缩后图片是否大于1M(1024kb),大于继续压缩
            baos.reset(); // 重置baos即清空baos
            options -= 2;// 每次都减少10
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}