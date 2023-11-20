package lab4;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class Store {
	private int sellerId;
	private int storeId;
	private String sName;
	private String sPhone;
	private Time sTime;
	private Date openDate;
	private double sStarScore;
	private String sDescription;
	
	public Store(int sellerId, int storeId, String sName, String sPhone, Time sTime, Date openDate, double sStarScore,
			String sDescription) {
		super();
		this.sellerId = sellerId;
		this.storeId = storeId;
		this.sName = sName;
		this.sPhone = sPhone;
		this.sTime = sTime;
		this.openDate = openDate;
		this.sStarScore = sStarScore;
		this.sDescription = sDescription;
	}

	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
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
	public Time getsTime() {
		return sTime;
	}
	public void setsTime(Time sTime) {
		this.sTime = sTime;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public double getsStarScore() {
		return sStarScore;
	}
	public void setsStarScore(double sStarScore) {
		this.sStarScore = sStarScore;
	}
	public String getsDescription() {
		return sDescription;
	}
	public void setsDescription(String sDescription) {
		this.sDescription = sDescription;
	}

	@Override
	public String toString() {
		return "Store [sellerId=" + sellerId + ", storeId=" + storeId + ", sName=" + sName + ", sPhone=" + sPhone
				+ ", sTime=" + sTime + ", openDate=" + openDate + ", sStarScore=" + sStarScore + ", sDescription="
				+ sDescription + "]";
	}
	
	
	
}
