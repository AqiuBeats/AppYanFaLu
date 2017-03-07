package com.aqiu.rxzone.bean;

import java.util.List;

/**
 * 美女图片
 * Created by aqiu on 2017/3/7.
 */

public class Girls {

    /**
     * status : true
     * total : 1022
     * tngou : [{"count":13582,"fcount":0,"galleryclass":4,"id":1036,"img":"/ext/161223/7083a1fde72448a62e477c5aab0721c8.jpg","rcount":0,"size":11,"time":1482494705000,"title":"大胸美女性感爆乳丰满胸围性感图片"},{"count":60735,"fcount":0,"galleryclass":1,"id":1035,"img":"/ext/161223/395b860c06ccaf5b35cde317ff082c18.jpg","rcount":0,"size":9,"time":1482494660000,"title":"蕾丝透视装美女性感包臀裙极品私房照"},{"count":38096,"fcount":0,"galleryclass":3,"id":1034,"img":"/ext/161223/905b7784c0aeb91870fb40d34defae5d.jpg","rcount":0,"size":11,"time":1482494627000,"title":"风骚迷人性感美女凌凌美腿丝袜诱惑写真"},{"count":23409,"fcount":0,"galleryclass":6,"id":1033,"img":"/ext/161213/c5f1416b4feb857b8d711f83dc692885.jpg","rcount":0,"size":18,"time":1481628679000,"title":"亚洲美女菲儿火辣身材白皙肌肤性感人体艺术"},{"count":28778,"fcount":0,"galleryclass":1,"id":1032,"img":"/ext/161213/a94ead894d0d0e4e5b3b807626eeab4d.jpg","rcount":0,"size":10,"time":1481628573000,"title":"大胸美女御姐酥胸事业线美腿妖娆性感"},{"count":16675,"fcount":0,"galleryclass":5,"id":1031,"img":"/ext/161213/15570de94749040cd46edc90ff4d78fd.jpg","rcount":0,"size":9,"time":1481628520000,"title":"妖娆妩媚的少妇巨乳美腿诱人性感图片"},{"count":15575,"fcount":0,"galleryclass":1,"id":1030,"img":"/ext/161211/93a8c952a96f75389f2e9a0d6846ca6e.jpg","rcount":0,"size":9,"time":1481426601000,"title":"蕾丝美女大胆极品透视装性感私房写真图片"},{"count":12801,"fcount":0,"galleryclass":7,"id":1029,"img":"/ext/161211/46eb25e2af0bb96cf38b26e560574fcd.jpg","rcount":0,"size":9,"time":1481426436000,"title":"丰满大胸巨乳车模事业线诱人性感图片"},{"count":9813,"fcount":0,"galleryclass":4,"id":1028,"img":"/ext/161209/c4c20b7bb0d013512b59a2f4f40cdc97.jpg","rcount":0,"size":10,"time":1481282263000,"title":"长发性感女神菲儿白嫩美腿浴室"},{"count":11932,"fcount":0,"galleryclass":3,"id":1027,"img":"/ext/161209/c7cd24dcc868f2d61589cfafa833a462.jpg","rcount":0,"size":15,"time":1481282202000,"title":"高跟鞋黑丝美腿美女模特私房大胆妖娆性感"}]
     */

    private boolean status;
    private int total;
    /**
     * count : 13582
     * fcount : 0
     * galleryclass : 4
     * id : 1036
     * img : /ext/161223/7083a1fde72448a62e477c5aab0721c8.jpg
     * rcount : 0
     * size : 11
     * time : 1482494705000
     * title : 大胸美女性感爆乳丰满胸围性感图片
     */

    private List<TngouEntity> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TngouEntity> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouEntity> tngou) {
        this.tngou = tngou;
    }

    public static class TngouEntity {
        private int count;
        private int fcount;
        private int galleryclass;
        private int id;
        private String img;
        private int rcount;
        private int size;
        private long time;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getFcount() {
            return fcount;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public int getGalleryclass() {
            return galleryclass;
        }

        public void setGalleryclass(int galleryclass) {
            this.galleryclass = galleryclass;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getRcount() {
            return rcount;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
