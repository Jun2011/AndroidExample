package com.jun.androidexample.model;

import com.jun.androidexample.model.NewslistBean;

import java.util.List;

/**
 * Created by Jun on 2016/7/7.
 */
public class NewsBean {
    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-06-21 17:23","title":"阿富汗遭遇2起攻击 至少22人丧生数十人受伤","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20160621/Img455516313_ss.jpg","url":"http://news.sohu.com/20160621/n455526173.shtml"},{"ctime":"2016-06-21 17:30","title":"澳大利亚联邦大选 总理为原住民发声承认土地权益","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20160621/Img455518995_ss.jpeg","url":"http://news.sohu.com/20160621/n455530142.shtml"},{"ctime":"2016-06-21 17:54","title":"女议员遇害刹住英国\u201c脱欧\u201d支持率 议会悼念","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20160621/Img455532593_ss.jpeg","url":"http://news.sohu.com/20160621/n455532592.shtml"}]
     */

    private int code;
    private String msg;
    /**
     * ctime : 2016-06-21 17:23
     * title : 阿富汗遭遇2起攻击 至少22人丧生数十人受伤
     * description : 搜狐国际
     * picUrl : http://photocdn.sohu.com/20160621/Img455516313_ss.jpg
     * url : http://news.sohu.com/20160621/n455526173.shtml
     */

    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

}
