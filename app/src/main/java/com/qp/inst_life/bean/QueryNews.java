package com.qp.inst_life.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/26 0026.
 */

public class QueryNews {




    private String reason;
    private int error_code;
    private List<ResultBean> result;

    @Override
    public String toString() {
        return "QueryNews{" +
                "reason='" + reason + '\'' +
                ", error_code=" + error_code +
                ", result=" + result +
                '}';
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {

        private String title;
        private String content;
        private String img_width;
        private String full_title;
        private String pdate;
        private String src;
        private String img_length;
        private String img;
        private String url;
        private String pdate_src;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", img_width='" + img_width + '\'' +
                    ", full_title='" + full_title + '\'' +
                    ", pdate='" + pdate + '\'' +
                    ", src='" + src + '\'' +
                    ", img_length='" + img_length + '\'' +
                    ", img='" + img + '\'' +
                    ", url='" + url + '\'' +
                    ", pdate_src='" + pdate_src + '\'' +
                    '}';
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg_width() {
            return img_width;
        }

        public void setImg_width(String img_width) {
            this.img_width = img_width;
        }

        public String getFull_title() {
            return full_title;
        }

        public void setFull_title(String full_title) {
            this.full_title = full_title;
        }

        public String getPdate() {
            return pdate;
        }

        public void setPdate(String pdate) {
            this.pdate = pdate;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getImg_length() {
            return img_length;
        }

        public void setImg_length(String img_length) {
            this.img_length = img_length;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPdate_src() {
            return pdate_src;
        }

        public void setPdate_src(String pdate_src) {
            this.pdate_src = pdate_src;
        }
    }
}
