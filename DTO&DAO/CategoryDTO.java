package lab4;

public class CategoryDTO {
    private Integer categoryId;
    private String caName;
    private Integer storeCount;
    
    public CategoryDTO(Integer categoryId, String caName, Integer storeCount) {
        super();
        this.categoryId = categoryId;
        this.caName = caName;
        this.storeCount = storeCount;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCaName() {
        return caName;
    }

    public void setCaName(String caName) {
        this.caName = caName;
    }

    public Integer getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Integer storeCount) {
        this.storeCount = storeCount;
    }

    @Override
    public String toString() {
        return "categoryId=" + categoryId + ", caName=" + caName + ", storeCount=" + storeCount;
    }
    
    
}
