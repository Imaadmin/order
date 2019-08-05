package com.example.order.entities;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tbl_course")
public class Course {
    private Integer id;
    private String courseName;
    private String coursetypeId;
    private Integer praisePoints;
    private String schoolMonth;
    private int state;
    private String courseIcon;
    private String courseInfo;
    private String coursetypeName;
    private int totalPage;
    private Date createTime;
    private String userName;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCoursetypeName() {
        return coursetypeName;
    }

    public void setCoursetypeName(String coursetypeName) {
        this.coursetypeName = coursetypeName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoursetypeId() {
        return coursetypeId;
    }

    public void setCoursetypeId(String coursetypeId) {
        this.coursetypeId = coursetypeId;
    }

    public Integer getPraisePoints() {
        return praisePoints;
    }

    public void setPraisePoints(Integer praisePoints) {
        this.praisePoints = praisePoints;
    }

    public String getSchoolMonth() {
        return schoolMonth;
    }

    public void setSchoolMonth(String schoolMonth) {
        this.schoolMonth = schoolMonth;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCourseIcon() {
        return courseIcon;
    }

    public void setCourseIcon(String courseIcon) {
        this.courseIcon = courseIcon;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }
}
