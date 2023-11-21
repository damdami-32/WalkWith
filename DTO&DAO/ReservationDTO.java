package lab4;

import java.time.LocalDate;

public class ReservationDTO {
    private int reservationId;
    private LocalDate resDaTi;  // Date 대신 LocalDate 사용
    private String userId;
    private String uName;
    private int storeId;
    private String sName;
    private String comment;

    // 기본 생성자
    public ReservationDTO() {
    }

    // 모든 필드를 초기화하는 생성자
    public ReservationDTO(int reservationId, LocalDate resDaTi, String userId, int storeId) {
        this.reservationId = reservationId;
        this.resDaTi = resDaTi;
        this.userId = userId;
        this.storeId = storeId;
    }

    // 각 필드에 대한 getter 및 setter
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getResDaTi() {
        return resDaTi;
    }

    public void setResDaTi(LocalDate resDaTi) {
        this.resDaTi = resDaTi;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
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

    // 코멘트 부분 추가 여기에 무슨 내용을 넣을지는 자유
    public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
    

    @Override
    public String toString() {
        return "ReservationDTO [reservationId=" + reservationId + ", resDaTi=" + resDaTi + ", userId=" + userId
                + ", storeId=" + storeId + ",comment= "+ comment + "]";
    }
}
