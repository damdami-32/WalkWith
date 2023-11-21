package lab4;

import java.time.LocalDate;

public class StoreDTO {
    private String sName;
    private String sPhone;
    private LocalDate sTime;
    private Float sStarScore;
    private String sDetailDescription;
    private String sellerId;
    private LocalDate openDate;
    
    
    public StoreDTO(String sName, String sPhone, LocalDate sTime, Float sStarScore, String sDetailDescription,
            String sellerId, LocalDate openDate) {
        super();
        this.sName = sName;
        this.sPhone = sPhone;
        this.sTime = sTime;
        this.sStarScore = sStarScore;
        this.sDetailDescription = sDetailDescription;
        this.sellerId = sellerId;
        this.openDate = openDate;
    }


    public String getsName() {
        return sName;
    }


    public void setsName(String sName) {
        this.sName = sName;
    }


    public String getsPhone() {
        return sPhone;
    }


    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }


    public LocalDate getsTime() {
        return sTime;
    }


    public void setsTime(LocalDate sTime) {
        this.sTime = sTime;
    }


    public Float getsStarScore() {
        return sStarScore;
    }


    public void setsStarScore(Float sStarScore) {
        this.sStarScore = sStarScore;
    }


    public String getsDetailDescription() {
        return sDetailDescription;
    }


    public void setsDetailDescription(String sDetailDescription) {
        this.sDetailDescription = sDetailDescription;
    }


    public String getSellerId() {
        return sellerId;
    }


    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }


    public LocalDate getOpenDate() {
        return openDate;
    }


    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }


    @Override
    public String toString() {
        return "sName=" + sName + ", sPhone=" + sPhone + ", sTime=" + sTime + ", sStarScore=" + sStarScore
                + ", sDetailDescription=" + sDetailDescription + ", sellerId=" + sellerId + ", openDate=" + openDate;
    }

    
}
