package fi.tuni.prog3.sisu;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class StudentData implements Serializable {
    private String name;
    private String id;

    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<String> list;

    private int settingsData;

    public StudentData(String name, String id, LocalDate startDate, LocalDate endDate, int settingsData) {
        this.name = name;
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.settingsData=settingsData;
        list=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public int getSettingsData() {
        return settingsData;
    }

    public void setSettingsData(int settingsData) {
        this.settingsData = settingsData;
    }

    @Override
    public String toString() {
        return "StudentData{" +
                "studentName='" + name + '\'' +
                ", StudentId='" + id + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", Selected Study Programs=" + list +
                '}';
    }
}
