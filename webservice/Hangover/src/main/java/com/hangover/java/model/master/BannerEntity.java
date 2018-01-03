package com.hangover.java.model.master;

import com.hangover.java.model.type.BannerType;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/9/16
 * Time: 1:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "banner")
public class BannerEntity {

    private Long id;
    private String sImageURL;
    private String mImageURL;
    private String LImageURL;
    private BannerType bannerType;


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "small_image_url")
    public String getsImageURL() {
        return sImageURL;
    }

    public void setsImageURL(String sImageURL) {
        this.sImageURL = sImageURL;
    }

    @Column(name = "medium_image_url")
    public String getmImageURL() {
        return mImageURL;
    }

    public void setmImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
    }

    @Column(name = "large_image_url")
    public String getLImageURL() {
        return LImageURL;
    }

    public void setLImageURL(String LImageURL) {
        this.LImageURL = LImageURL;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "banner_type")
    public BannerType getBannerType() {
        return bannerType;
    }

    public void setBannerType(BannerType bannerType) {
        this.bannerType = bannerType;
    }
}
