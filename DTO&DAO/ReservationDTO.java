package lab4;

import java.util.Date;

public class ReservationDTO {
    private int reservationId;
    private Date resDaTi;
    private String userId;
    private int storeId;

    // 기본 생성자
    public ReservationDTO() {
    }

    // 모든 필드를 초기화하는 생성자
    public ReservationDTO(int reservationId, Date resDaTi, String userId, int storeId) {
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

    public Date getResDaTi() {
        return resDaTi;
    }

    public void setResDaTi(Date resDaTi) {
        this.resDaTi = resDaTi;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "ReservationDTO [reservationId=" + reservationId + ", resDaTi=" + resDaTi + ", userId=" + userId
                + ", storeId=" + storeId + "]";
    }
    

}