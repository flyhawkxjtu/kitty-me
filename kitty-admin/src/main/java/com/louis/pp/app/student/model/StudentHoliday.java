package com.louis.pp.app.student.model;

import com.louis.platform.app.model.BaseModel;

import java.util.Date;

public class StudentHoliday extends BaseModel {
    private String holidayTitle;
    private  String studentName;
    private Date holidayStart;
    private Date holidayEnd;
    private String status;
    private Integer isDelete;

    public String getHolidayTitle() {
        return holidayTitle;
    }

    public void setHolidayTitle(String holidayTitle) {
        this.holidayTitle = holidayTitle;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getHolidayStart() {
        return holidayStart;
    }

    public void setHolidayStart(Date holidayStart) {
        this.holidayStart = holidayStart;
    }

    public Date getHolidayEnd() {
        return holidayEnd;
    }

    public void setHolidayEnd(Date holidayEnd) {
        this.holidayEnd = holidayEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
