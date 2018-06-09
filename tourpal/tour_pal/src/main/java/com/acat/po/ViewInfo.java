package com.acat.po;

import java.io.Serializable;
import java.util.Date;

public class ViewInfo implements Serializable {
    private Integer viewId;

    private String userNumber;

    private String viewName;

    private Date viewTime;

    private String viewPhoto;

    private String viewDesc;

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Date getViewTime() {
        return viewTime;
    }

    public void setViewTime(Date viewTime) {
        this.viewTime = viewTime;
    }

    public String getViewPhoto() {
        return viewPhoto;
    }

    public void setViewPhoto(String viewPhoto) {
        this.viewPhoto = viewPhoto;
    }

    @Override
	public String toString() {
		return "ViewInfo [viewId=" + viewId + ", userNumber=" + userNumber
				+ ", viewName=" + viewName + ", viewTime=" + viewTime
				+ ", viewPhoto=" + viewPhoto + ", viewDesc=" + viewDesc + "]";
	}

	public String getViewDesc() {
        return viewDesc;
    }

    public void setViewDesc(String viewDesc) {
        this.viewDesc = viewDesc;
    }
}