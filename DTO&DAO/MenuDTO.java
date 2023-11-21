package lab4;

public class MenuDTO {
    private Integer menuId;
    private Integer storeId;
    private String menuName;
    private String menuDescrip;
    private Integer mePrice;
    
    public MenuDTO(Integer menuId, Integer storeId, String menuName, String menuDescrip, Integer mePrice) {
       super();
        this.menuId = menuId;
        this.storeId = storeId;
        this.menuName = menuName;
        this.menuDescrip = menuDescrip;
        this.mePrice = mePrice;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescrip() {
        return menuDescrip;
    }

    public void setMenuDescrip(String menuDescrip) {
        this.menuDescrip = menuDescrip;
    }

    public Integer getMePrice() {
        return mePrice;
    }

    public void setMePrice(Integer mePrice) {
        this.mePrice = mePrice;
    }

    @Override
    public String toString() {
        return "menuId=" + menuId + ", storeId=" + storeId + ", menuName=" + menuName + ", menuDescrip="
                + menuDescrip + ", mePrice=" + mePrice;
    }
    
    
}
