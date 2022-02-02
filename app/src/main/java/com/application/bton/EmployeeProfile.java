package com.application.bton;


public class EmployeeProfile {
    private String firstName;
    private String lastName;
    private String bennEmail;
    private long phone;
    private String imageUrl;
    private String office;
    private String department;

    public EmployeeProfile(){
        firstName = null;
        lastName = null;
        bennEmail = null;
        phone = 0;
        imageUrl = null;
        office = null;
        department = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBennEmail() {
        return bennEmail;
    }

    public void setBennEmail(String bennEmail) {
        this.bennEmail = bennEmail;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
