package lab4;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class StoreDTO {
	private String sellerId;
	private int storeId;
	private String sName;
	private String sPhone;
	private Time sTime;
	private String openDate; // 영업 요일 저장 ex)월,화, 수, 목... 이런 형식 
	private double sStarScore;
	private String sDescription;
	private int likeCount;  // 좋아요 수 필드 추가
	private String sImage_path; // 가게 이미지 추가
	
	public StoreDTO(String sellerId, int storeId, String sName, String sPhone, Time sTime, String openDate, double sStarScore,
			String sDescription, int likeCoun, String sImage_path) {
		super();
		this.sellerId = sellerId;
		this.storeId = storeId;
		this.sName = sName;
		this.sPhone = sPhone;
		this.sTime = sTime;
		this.openDate = openDate;
		this.sStarScore = sStarScore;
		this.sDescription = sDescription;
		this.likeCount = likeCount;  // 좋아요 수 초기화
		this.sImage = sImage_path;
	}

	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
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
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
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
		return likeCount;
	}
	public void setLikeCount(int LikeCount) {
		this.likeCount = likeCount;
	}
	
	// 가게 이미지 추가
	public String getsImage_path() {
		return sImage;
	}

	public void setsImage_path(String sImage_path) {
		this.sImage_path = sImage_path;
	}

	@Override
	public String toString() {
		return "Store [sellerId=" + sellerId + ", storeId=" + storeId + ", sName=" + sName + ", sPhone=" + sPhone
				+ ", sTime=" + sTime + ", openDate=" + openDate + ", sStarScore=" + sStarScore + ", sDescription="
				+ sDescription + ", likeCount=" + likeCount + "]";
	}
	
	
	
}
