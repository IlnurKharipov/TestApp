package com.TestEDMS.test.models;

import javax.persistence.*;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String companyName, physicalAdress, legalAdress,comSupervisor;
    private int views;

    public Company() {
    }

    public Company(String companyName, String physicalAdress, String legalAdress, String comSupervisor) {
        this.companyName = companyName;
        this.physicalAdress = physicalAdress;
        this.legalAdress = legalAdress;
        this.comSupervisor = comSupervisor;
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

    public String getPhysicalAdress() {
        return physicalAdress;
    }

    public void setPhysicalAdress(String physicalAdress) {
        this.physicalAdress = physicalAdress;
    }

    public String getLegalAdress() {
        return legalAdress;
    }

    public void setLegalAdress(String legalAdress) {
        this.legalAdress = legalAdress;
    }

    public String getComSupervisor() {
        return comSupervisor;
    }

    public void setComSupervisor(String comSupervisor) {
        this.comSupervisor = comSupervisor;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
