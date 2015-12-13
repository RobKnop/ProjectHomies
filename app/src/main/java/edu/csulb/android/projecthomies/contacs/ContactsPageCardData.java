package edu.csulb.android.projecthomies.contacs;

// CONTACTS CARD DATA
public class ContactsPageCardData {

    private String name;
    private String company;
    private int imageID;

    public ContactsPageCardData(String name, String company) {
        this.name = name;
        this.company = company;

    }

    public String getName() {
        return name;
    }

    public String getCompany() { return company; }

    public int getImageID() { return imageID; }

    public void setImageID(int imageID) { this.imageID = imageID; }

}
