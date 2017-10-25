package com.zqkj.bsm.dal.bean;

import java.io.Serializable;

public class UserInfo implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private long userInfoId;  //用户信息ID
    private long userId;   //用户ID   
    private String realName;   //真实姓名
    private String gender;   //性别
    private String profession;  //职业，从事行业
    private String qq;  //个人QQ
    private String province;//省份
    private String city;//城市
    private String address; //个人联系地址
    public long getUserInfoId()
    {
        return userInfoId;
    }
    public void setUserInfoId( long userInfoId )
    {
        this.userInfoId = userInfoId;
    }
    public long getUserId()
    {
        return userId;
    }
    public void setUserId( long userId )
    {
        this.userId = userId;
    }
    public String getRealName()
    {
        return realName;
    }
    public void setRealName( String realName )
    {
        this.realName = realName;
    }
    public String getGender()
    {
        return gender;
    }
    public void setGender( String gender )
    {
        this.gender = gender;
    }
    public String getProfession()
    {
        return profession;
    }
    public void setProfession( String profession )
    {
        this.profession = profession;
    }
    public String getQq()
    {
        return qq;
    }
    public void setQq( String qq )
    {
        this.qq = qq;
    }
    public String getProvince()
    {
        return province;
    }
    public void setProvince( String province )
    {
        this.province = province;
    }
    public String getCity()
    {
        return city;
    }
    public void setCity( String city )
    {
        this.city = city;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress( String address )
    {
        this.address = address;
    }
    
}
