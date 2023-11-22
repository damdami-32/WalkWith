package lab4;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class Store {
	private Stirng sellerId;
	private int storeId;
	private String sName;
	private String sPhone;
	private Time sTime;
	private Date openDate;
	private double sStarScore;
	private String sDescription;
	private int LikeCount;  // 좋아요 수 필드 추가
	
	public Store(Stirng sellerId, int storeId, String sName, String sPhone, Time sTime, Date openDate, double sStarScore,
			String sDescription, int LikeCount) {
		super();
		this.sellerId = sellerId;
		this.storeId = storeId;
		this.sName = sName;
		this.sPhone = sPhone;
		this.sTime = sTime;
		this.openDate = openDate;
		this.sStarScore = sStarScore;
		this.sDescription = sDescription;
		this.sLikeCount = LikeCount;  // 좋아요 수 초기화
	}

	public Stirng getSellerId() {
		return sellerId;
	}
	public void setSellerId(Stirng sellerId) {
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

	// 좋아요 수 getter, setter 추가
	public int getLikeCount() {
		return LikeCount;
	}
	public void setLikeCount(int LikeCount) {
		this.LikeCount = sLikeCount;
	}

	@Override
	public String toString() {
		return "Store [sellerId=" + sellerId + ", storeId=" + storeId + ", sName=" + sName + ", sPhone=" + sPhone
				+ ", sTime=" + sTime + ", openDate=" + openDate + ", sStarScore=" + sStarScore + ", sDescription="
				+ sDescription + ", LikeCount=" + LikeCount + "]";
	}
	
	
	
}
