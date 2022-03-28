package com.TestEDMS.test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String workersName,subjectOfDocument,authorOfDocument, executorsOfDocument,
    periodOfExecution, signOfControl, signOfPerformance, textOfDocument;

    public Documents() {
    }

    public Documents(String workersName, String subjectOfDocument, String authorOfDocument,
                     String executorsOfDocument, String periodOfExecution,
                     String signOfControl, String signOfPerformance, String textOfDocument) {
        this.workersName = workersName;
        this.subjectOfDocument = subjectOfDocument;
        this.authorOfDocument = authorOfDocument;
        this.executorsOfDocument = executorsOfDocument;
        this.periodOfExecution = periodOfExecution;
        this.signOfControl = signOfControl;
        this.signOfPerformance = signOfPerformance;
        this.textOfDocument = textOfDocument;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkersName() {
        return workersName;
    }

    public void setWorkersName(String workersName) {
        this.workersName = workersName;
    }

    public String getSubjectOfDocument() {
        return subjectOfDocument;
    }

    public void setSubjectOfDocument(String subjectOfDocument) {
        this.subjectOfDocument = subjectOfDocument;
    }

    public String getAuthorOfDocument() {
        return authorOfDocument;
    }

    public void setAuthorOfDocument(String authorOfDocument) {
        this.authorOfDocument = authorOfDocument;
    }

    public String getExecutorsOfDocument() {
        return executorsOfDocument;
    }

    public void setExecutorsOfDocument(String executorsOfDocument) {
        this.executorsOfDocument = executorsOfDocument;
    }

    public String getPeriodOfExecution() {
        return periodOfExecution;
    }

    public void setPeriodOfExecution(String periodOfExecution) {
        this.periodOfExecution = periodOfExecution;
    }

    public String getSignOfControl() {
        return signOfControl;
    }

    public void setSignOfControl(String signOfControl) {
        this.signOfControl = signOfControl;
    }

    public String getSignOfPerformance() {
        return signOfPerformance;
    }

    public void setSignOfPerformance(String signOfPerformance) {
        this.signOfPerformance = signOfPerformance;
    }

    public String getTextOfDocument() {
        return textOfDocument;
    }

    public void setTextOfDocument(String textOfDocument) {
        this.textOfDocument = textOfDocument;
    }
}
