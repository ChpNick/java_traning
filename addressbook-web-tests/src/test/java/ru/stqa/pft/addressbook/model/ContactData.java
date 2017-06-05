package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("Contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private  String firstname;

    @Transient
    private  String middlename;

    @Expose
    @Column(name = "lastname")
    private  String lastname;

    @Transient
    private  String nickname;
    @Transient
    private  String title;
    @Transient
    private  String company;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private  String address;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private  String homePhone;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private  String mobilePhone;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private  String workPhone;

    @Transient
    private  String fax;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private  String email;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private  String email2;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private  String email3;

    @Transient
    private  String homepage;

    @XStreamOmitField
    @Transient
    private  int bday = 0;

    @XStreamOmitField
    @Transient
    private  int bmons = 0;

    @Transient
    private  String byear;

    @XStreamOmitField
    @Transient
    private  int aday = 0;

    @XStreamOmitField
    @Transient
    private  int amons = 0;

    @Transient
    private  String ayear;
    @Transient
    private  String group;
    @Transient
    private  String address2;
    @Transient
    private  String phone2;
    @Transient
    private  String notes;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;

    @Expose
    @Column(name = "photo")
    @Type(type = "text")
    private String photoRelativePath;

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getBday() {
        return bday;
    }

    public int getBmons() {
        return bmons;
    }

    public String getByear() {
        return byear;
    }

    public int getAday() {
        return aday;
    }

    public int getAmons() {
        return amons;
    }

    public String getAyear() {
        return ayear;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getNotes() {
        return notes;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getPhotoRelativePath() {
        return photoRelativePath;
    }

//    Сеттеры

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String home) {
        this.homePhone = home;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobilePhone = mobile;
        return this;
    }

    public ContactData withWorkPhone(String work) {
        this.workPhone = work;
        return this;
    }

    public ContactData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public ContactData withBday(int bday) {
        this.bday = bday;
        return this;
    }

    public ContactData withBmons(int bmons) {
        this.bmons = bmons;
        return this;
    }

    public ContactData withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public ContactData withAday(int aday) {
        this.aday = aday;
        return this;
    }

    public ContactData withAmons(int amons) {
        this.amons = amons;
        return this;
    }

    public ContactData withAyear(String ayear) {
        this.ayear = ayear;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public ContactData withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public ContactData withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withPhotoRelativePath(String photoRelativePath) {
        this.photoRelativePath = photoRelativePath;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? ! firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
