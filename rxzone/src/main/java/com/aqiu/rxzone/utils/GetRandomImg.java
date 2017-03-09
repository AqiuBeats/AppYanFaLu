package com.aqiu.rxzone.utils;


import com.aqiu.rxzone.R;

import java.util.Random;


/**
 * 获取随机图片
 * Created by aqiu on 2017/3/9.
 */

public class GetRandomImg {
    private Random r;
    private int[] img;

    public GetRandomImg() {
        r = new Random();
        img = new int[]{R.mipmap.img1, R.mipmap.img2, R.mipmap.img3, R.mipmap.img4, R.mipmap.img5,
                R.mipmap.img6, R.mipmap.img7, R.mipmap.img8, R.mipmap.img9, R.mipmap.img10,
                R.mipmap.img11, R.mipmap.img12, R.mipmap.img13, R.mipmap.img14, R.mipmap.img15,
                R.mipmap.img16, R.mipmap.img17, R.mipmap.img18, R.mipmap.img19, R.mipmap.img20,
                R.mipmap.img21, R.mipmap.img22, R.mipmap.img23, R.mipmap.img24, R.mipmap.img25,
                R.mipmap.img26, R.mipmap.img27, R.mipmap.img28, R.mipmap.img29, R.mipmap.img30
        };
    }

    public int getIdImg() {
        int index = r.nextInt(30);//[1,31);
        return img[index];
    }
}
