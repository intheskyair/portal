package com.zqkj.bsm.dal.client;

import org.apache.ibatis.session.SqlSession;

import com.zqkj.bsm.dal.bean.UserInfo;

/**
 * 操作用户信息表
 * @author haifei.huang
 * @date 2013-5-2
 */
public class UserInfoOpera  extends BaseOpera {
    private static UserInfoOpera userInfoOpera=null;
    
    private UserInfoOpera(){}
    
    public static UserInfoOpera getInstance(){
        if(userInfoOpera==null){
            userInfoOpera=new UserInfoOpera();
        }
        return userInfoOpera;
    }

    /**
     * 添加用户个人信息
     * @author haifei.huang
     * @date 2013-5-2
     * @param userInfo
     * @return
     */
    public boolean createUserInfo(UserInfo userInfo) {
        SqlSession session = getSession();
        int flag=0;
        try{
            flag=session.insert("createUserInfo",userInfo);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return flag==1?true:false;
    }
    
    /**
     * 更新用户个人信息
     * @author haifei.huang
     * @date 2013-5-2
     * @param userInfo
     * @return
     */
    public boolean updateUserInfo(UserInfo userInfo) {
        SqlSession session = getSession();
        int flag=0;
        try{
            flag=session.update("updateUserInfo",userInfo);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return flag==1?true:false;
    }
    
    /**
     * 查询用户个人信息
     * @author haifei.huang
     * @date 2013-5-2
     * @param userInfo
     * @return
     */
    public UserInfo selectUserInfoByUserId(long userId){
        SqlSession session = getSession();
        UserInfo userInfo=null;
        try{
            userInfo=session.selectOne("selectUserInfoByUserId",userId);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return userInfo;
    }
    
    /**
     * 删除用户个人信息
     * @author haifei.huang
     * @date 2013-5-2
     * @param userInfo
     * @return
     */
    public boolean deleteUserInfoByUserId(long userId){
        SqlSession session = getSession();
        int flag=0;
        try{
            flag=session.delete("deleteUserInfoByUserId",userId);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return flag==1?true:false;
    }
    
}
