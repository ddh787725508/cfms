package com.acat.po;

import java.io.Serializable;

public class UserInfo  implements Serializable {
    private String userNumber;

    private String userName;

    private String userPw;

    private String userType;

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

	@Override
	public String toString() {
		return "UserInfo [userNumber=" + userNumber + ", userName=" + userName
				+ ", userPw=" + userPw + ", userType=" + userType + "]";
	}
    
    
}