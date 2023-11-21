package lab4;

public class ReviewDTO {
    private Integer reviewId;
    private Integer storeId;
    private Integer userId;
    private String reContent;
    private Integer starScore;
    
    public ReviewDTO(Integer reviewId, Integer storeId, Integer userId, String reContent, Integer starScore) {
        super();
        this.reviewId = reviewId;
        this.storeId = storeId;
        this.userId = userId;
        this.reContent = reContent;
        this.starScore = starScore;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReContent() {
        return reContent;
    }

    public void setReContent(String reContent) {
        this.reContent = reContent;
    }

    public Integer getStarScore() {
        return starScore;
    }

    public void setStarScore(Integer starScore) {
        this.starScore = starScore;
    }

    @Override
    public String toString() {
        return "reviewId=" + reviewId + ", storeId=" + storeId + ", userId=" + userId + ", reContent="
                + reContent + ", starScore=" + starScore;
    }
    
    
}
