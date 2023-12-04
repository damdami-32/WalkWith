package lab4;

public class LikeListDTO {
    private String userId;
    private int storeId;

    public LikeListDTO(String userId, int storeId) {
        this.userId = userId;
        this.storeId = storeId;
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
}

