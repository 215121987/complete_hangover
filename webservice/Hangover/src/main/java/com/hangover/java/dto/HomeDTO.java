package com.hangover.java.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/9/16
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "home")
public class HomeDTO extends BaseDTO{


    private String bannerURL;
    private List<OfferDTO> generalOffer;
    private List<BrandDTO> brands;
    private OfferDTO specificOffer;
    private OfferDTO expressOffer;


    public String getBannerURL() {
        return bannerURL;
    }

    public void setBannerURL(String bannerURL) {
        this.bannerURL = bannerURL;
    }

    @XmlElementWrapper(name = "general_offer")
    @XmlElementRef
    public List<OfferDTO> getGeneralOffer() {
        return generalOffer;
    }

    public void setGeneralOffer(List<OfferDTO> generalOffer) {
        this.generalOffer = generalOffer;
    }

    @XmlElementWrapper(name = "brands")
    @XmlElementRef
    public List<BrandDTO> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandDTO> brands) {
        this.brands = brands;
    }

    @XmlElement(name = "specific_offer")
    public OfferDTO getSpecificOffer() {
        return specificOffer;
    }

    public void setSpecificOffer(OfferDTO specificOffer) {
        this.specificOffer = specificOffer;
    }

    @XmlElement(name = "express_offer")
    public OfferDTO getExpressOffer() {
        return expressOffer;
    }

    public void setExpressOffer(OfferDTO expressOffer) {
        this.expressOffer = expressOffer;
    }
}
