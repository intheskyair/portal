package com.zqkj.bsm.dal.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.zqkj.bsm.util.Tools;


/**
 * 用户信息
 * @author haifei.huang
 * @date 2013-4-15
 */
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private long userId;    //用户ID
	private long suserId;   //服务器端用户ID
	private String email;//邮箱
	private String nickName;//昵称
	private String regPassword;  //用户密码
	private String regPhone;//注册手机号
	private String province;//省份
	private String city;//城市
	private String payPassword;//支付密码
	private String payPhone; //支付手机号   可以与注册手机号一样
	private long lastOperated;//上次操作时间
	private long sendMailTime; //发送激活邮件时间
	private String grade; //用户等级：1--普通用户,2--金牌用户,3--白金用户,4--钻石用户
	private String status;//激活状态    0-未激活用户,1-激活用户,2-锁定用户 3-已验证用户 4-正常用户 
	private BigDecimal balance; //账户金额
	private long createTime; //注册时间
	private String createTimeStr; //注册时间(便于显示)
	private long space; //已使用空间
	private String infoFlag; //信息标识  0-未知,1-公司注册的,2-个人注册的
	private String token; //登录令牌
	private String regSource; //注册来源
    public long getUserId()
    {
        return userId;
    }
    public void setUserId( long userId )
    {
        this.userId = userId;
    }
    public long getSuserId()
    {
        return suserId;
    }
    public void setSuserId( long suserId )
    {
        this.suserId = suserId;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail( String email )
    {
        this.email = email;
    }
    public String getNickName()
    {
        return nickName;
    }
    public void setNickName( String nickName )
    {
        this.nickName = nickName;
    }
    public String getRegPassword()
    {
        return regPassword;
    }
    public void setRegPassword( String regPassword )
    {
        this.regPassword = regPassword;
    }
    public String getRegPhone()
    {
        return regPhone;
    }
    public void setRegPhone( String regPhone )
    {
        this.regPhone = regPhone;
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
    public String getPayPassword()
    {
        return payPassword;
    }
    public void setPayPassword( String payPassword )
    {
        this.payPassword = payPassword;
    }
    public String getPayPhone()
    {
        return payPhone;
    }
    public void setPayPhone( String payPhone )
    {
        this.payPhone = payPhone;
    }
    public long getLastOperated()
    {
        return lastOperated;
    }
    public void setLastOperated( long lastOperated )
    {
        this.lastOperated = lastOperated;
    }
    public long getSendMailTime()
    {
        return sendMailTime;
    }
    public void setSendMailTime( long sendMailTime )
    {
        this.sendMailTime = sendMailTime;
    }
    public String getGrade()
    {
        return grade;
    }
    public void setGrade( String grade )
    {
        this.grade = grade;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus( String status )
    {
        this.status = status;
    }
    public BigDecimal getBalance()
    {
        return balance;
    }
    public void setBalance( BigDecimal balance )
    {
        this.balance = balance;
    }   
    public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
		this.createTimeStr = Tools.getInstance().longToString( createTime, "yyyy-MM-dd HH:mm:ss" );
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr( String createTimeStr )
    {
        this.createTimeStr = createTimeStr;
    }
	public long getSpace()
    {
        return space;
    }
    public void setSpace( long space )
    {
        this.space = space;
    }
	public String getInfoFlag()
    {
        return infoFlag;
    }
    public void setInfoFlag( String infoFlag )
    {
        this.infoFlag = infoFlag;
    }
    public String getToken()
    {
        return token;
    }
    public void setToken( String token )
    {
        this.token = token;
    }
    public String getRegSource()
    {
        return regSource;
    }
    public void setRegSource( String regSource )
    {
        this.regSource = regSource;
    }
   
}
