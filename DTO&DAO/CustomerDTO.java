package lab3Sol;

import java.util.List;

public class CustomerDTO {
    
    private int userId;
    private String uName;
    private String uPassword;
    private String uPhone;
    private String uMail;
    private List<PetDTO> petList;
    private List<StoreDTO> likeList;

    public CustomerDTO(int userId, String uName, String uPassword, String uPhone, String uMail, List<PetDTO> petList, List<StoreDTO> likeList) {
        super();
        this.userId = userId;
        this.uName = uName;
        this.uPassword = uPassword;
        this.uPhone = uPhone;
        this.uMail = uMail;
        this.petList = petList;
        this.likeList = likeList;
    }

    public List<StoreDTO> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<StoreDTO> likeList) {
        this.likeList = likeList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuMail() {
        return uMail;
    }

    public void setuMail(String uMail) {
        this.uMail = uMail;
    }

    public List<PetDTO> getPetList() {
        return petList;
    }

    public void setPetList(List<PetDTO> petList) {
        this.petList = petList;
    }
}
