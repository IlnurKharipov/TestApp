package com.TestEDMS.test.models;

import javax.persistence.*;

@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subdivisionName, workersName, workerSurname,workerMiddleName, workerPosition;

    public Worker() {
    }

    public Worker(String subdivisionName, String workersName, String workerSurname,
                  String workerMiddleName, String workerPosition) {
        this.subdivisionName = subdivisionName;
        this.workersName = workersName;
        this.workerSurname = workerSurname;
        this.workerMiddleName = workerMiddleName;
        this.workerPosition = workerPosition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubdivisionName() {
        return subdivisionName;
    }

    public void setSubdivisionName(String subdivisionName) {
        this.subdivisionName = subdivisionName;
    }

    public String getWorkersName() {
        return workersName;
    }

    public void setWorkersName(String workersName) {
        this.workersName = workersName;
    }

    public String getWorkerSurname() {
        return workerSurname;
    }

    public void setWorkerSurname(String workerSurname) {
        this.workerSurname = workerSurname;
    }

    public String getWorkerMiddleName() {
        return workerMiddleName;
    }

    public void setWorkerMiddleName(String workerMiddleName) {
        this.workerMiddleName = workerMiddleName;
    }

    public String getWorkerPosition() {
        return workerPosition;
    }

    public void setWorkerPosition(String workerPosition) {
        this.workerPosition = workerPosition;
    }
}
