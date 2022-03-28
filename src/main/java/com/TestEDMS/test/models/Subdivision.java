package com.TestEDMS.test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subdivision {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String companyName, subdivisionName,contactDetails,subSupervisor;

    public Subdivision() {
    }

    public Subdivision(String companyName, String subdivisionName, String contactDetails, String subSupervisor) {
        this.companyName = companyName;
        this.subdivisionName = subdivisionName;
        this.contactDetails = contactDetails;
        this.subSupervisor = subSupervisor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSubdivisionName() {
        return subdivisionName;
    }

    public void setSubdivisionName(String subdivisionName) {
        this.subdivisionName = subdivisionName;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getSubSupervisor() {
        return subSupervisor;
    }

    public void setSubSupervisor(String subSupervisor) {
        this.subSupervisor = subSupervisor;
    }
}
