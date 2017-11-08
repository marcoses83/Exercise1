package com.spotahome.model;

public class PageInfo {
    private String url;
    private String domain;
    private String title;

    public PageInfo(String url, String domain, String title) {
        this.url = url;
        this.domain = domain;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
