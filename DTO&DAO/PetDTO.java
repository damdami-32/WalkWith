package lab3Sol;

public class PetDTO {
    private int petId;
    private String pImage_path;
    private String pName;
    private int pAge;
    private String pCategory;
    private String pDetailCa;
    private String pNeureting;
    
    public PetDTO(int petId, String pImage_path, String pName, int pAge, String pCategory, String pDetailCa,
            String pNeureting) {
        super();
        this.petId = petId;
        this.pImage_path = pImage_path;
        this.pName = pName;
        this.pAge = pAge;
        this.pCategory = pCategory;
        this.pDetailCa = pDetailCa;
        this.pNeureting = pNeureting;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getpImage_path() {
        return pImage_path;
    }

    public void setpImage_path(String pImage_path) {
        this.pImage_path = pImage_path;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getpAge() {
        return pAge;
    }

    public void setpAge(int pAge) {
        this.pAge = pAge;
    }

    public String getpCategory() {
        return pCategory;
    }

    public void setpCategory(String pCategory) {
        this.pCategory = pCategory;
    }

    public String getpDetailCa() {
        return pDetailCa;
    }

    public void setpDetailCa(String pDetailCa) {
        this.pDetailCa = pDetailCa;
    }

    public String getpNeureting() {
        return pNeureting;
    }

    public void setpNeureting(String pNeureting) {
        this.pNeureting = pNeureting;
    }
}
