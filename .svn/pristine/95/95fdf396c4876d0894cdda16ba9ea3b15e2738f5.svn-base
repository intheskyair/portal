package com.zqkj.bsm.dal.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.zqkj.bsm.dal.bean.User;

public class UserOpera extends BaseOpera {
	private static UserOpera opera=null;
	
	private UserOpera(){}
	
	public static UserOpera getInstance(){
		if(opera==null){
			opera=new UserOpera();
		}
		return opera;
	}
	public boolean create(User user) {
		SqlSession session = getSession();
		int flag=0;
		try{
			flag=session.insert("userCreate",user);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flag==1?true:false;
	}

	public boolean setPayPass(User user){
		SqlSession session = getSession();
		int flag=0;
		try{
			flag=session.update("updatePayPass",user);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flag==1?true:false;
		
	}
	public boolean update(User user) {
		SqlSession session = getSession();
		int flag=0;
		try{
			flag=session.update("userUpdate",user);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flag==1?true:false;
	}

	public User getUserByID(long userId){
		SqlSession session = getSession();
		User user=null;
		try{
			user=session.selectOne("selectUserByID",userId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return user;
	}

	public User readByEmail(String email) {
		SqlSession session = getSession();
		User temp=null;
		try{
			temp=session.selectOne("readUserByEmail",email);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return temp;
	}
	public User readByRegPhone(User user) {
		SqlSession session = getSession();
		User temp=null;
		try{
			temp=session.selectOne("readByRegPhone",user);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return temp;
	}
	public boolean updateRegPass(User user) {
		SqlSession session = getSession();
		int rs=0;
		try{
			rs = session.update("updateRegPass", user);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return rs==1?true:false;
	}
	public boolean updatePayPass(User user) {
		SqlSession session = getSession();
		int rs=0;
		try{
			rs = session.update("updatePayPass", user);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return rs==1?true:false;
	}
	public boolean updateLastOperated(User user) {
		SqlSession session = getSession();
		int rs=0;
		try{
			rs = session.update("updateLastOperated", user);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return rs==1?true:false;
	}
	public boolean updateUserStatus(User user) {
		SqlSession session = getSession();
		int rs=0;
		try{
			rs = session.update("updateUserStatus", user);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return rs==1?true:false;
	}
	
	/**
	 * 更新渲云服务用户ID和状态
	 * @author haifei.huang
	 * @date 2013-4-18
	 * @param user
	 * @return
	 */
	public boolean updateSuserIdAndStatus(User user) {
        SqlSession session = getSession();
        int rs=0;
        try{
            rs = session.update("updateSuserIdAndStatus", user);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return rs==1?true:false;
    }
	
	/**
	 * 通过Email获取绑定的手机号码
	 * @author haifei.huang
	 * @date 2013-4-23
	 * @param user
	 * @return
	 */
	public String readRegPhoneByEmail(String email) {
        SqlSession session = getSession();
        String temp=null;
        try{
            temp=session.selectOne("readRegPhoneByEmail",email);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return temp;
    }
	
	/**
     * 通过Email获取绑定的手机号码
     * @author haifei.huang
     * @date 2013-4-23
     * @param user
     * @return
     */
    public String readPayPhoneByEmail(String email) {
        SqlSession session = getSession();
        String temp=null;
        try{
            temp=session.selectOne("readPayPhoneByEmail",email);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return temp;
    }
	
	/**
	 * 更新已绑定的手机号码
	 * @author haifei.huang
	 * @date 2013-4-24
	 * @param userId
	 * @return
	 */
	public boolean updateRegPhone(User user) {
        SqlSession session = getSession();
        int rs=0;
        try{
            rs=session.update("updateRegPhone",user);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return rs==1?true:false;
    }
	
	/**
	 * 根据支付手机号查询用户
	 * @author haifei.huang
	 * @date 2013-4-26
	 * @param user
	 * @return
	 */
	public User readByPayPhone(User user) {
        SqlSession session = getSession();
        User temp=null;
        try{
            temp=session.selectOne("readByPayPhone",user);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return temp;
    }

	/**
     * 更新支付手机号
     * @author haifei.huang
     * @date 2013-4-24
     * @param userId
     * @return
     */
    public boolean updatePayPhone(User user) {
        SqlSession session = getSession();
        int rs=0;
        try{
            rs=session.update("updatePayPhone",user);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return rs==1?true:false;
    }
    /**
     * 更新昵称
     * @author haifei.huang
     * @date 2013-4-24
     * @param userId
     * @return
     */
    public boolean updateNickName(User user) {
        SqlSession session = getSession();
        int rs=0;
        try{
            rs=session.update("updateNickName",user);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return rs==1?true:false;
    }
    /**
     * 更新所在城市
     * @author haifei.huang
     * @date 2013-4-24
     * @param userId
     * @return
     */
    public boolean updateCity(User user) {
        SqlSession session = getSession();
        int rs=0;
        try{
            rs=session.update("updateCity",user);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return rs==1?true:false;
    }
    /**
     * 更新发送激活邮件时间
     * @author haifei.huang
     * @date 2013-4-24
     * @param userId
     * @return
     */
    public boolean updateSendMailTime(User user) {
        SqlSession session = getSession();
        int rs=0;
        try{
            rs=session.update("updateSendMailTime",user);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return rs==1?true:false;
    }
    
    /**
     * 通过userID查询激活邮件发送时间
     * @author haifei.huang
     * @date 2013-4-23
     * @param user
     * @return
     */
    public long selectSendMailTime(long userId) {
        SqlSession session = getSession();
        long temp=0;
        try{
            temp= session.selectOne("selectSendMailTime",userId);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return temp;
    }
    
    /**
     * 更新用户账户余额
     * @author haifei.huang
     * @date 2013-5-13
     * @param userId
     * @return
     */
    public boolean updateBalance(User user) {
        SqlSession session = getSession();
        int rs=0;
        try{
            rs = session.update("updateBalance", user);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return rs==1?true:false;
    }
    
    /**
     * 更新用户信息标识
     * @author haifei.huang
     * @date 2013-5-13
     * @param userId
     * @return
     */
    public boolean updateInfoFlag(User user) {
        SqlSession session = getSession();
        int rs=0;
        try{
            rs = session.update("updateInfoFlag", user);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return rs==1?true:false;
    }
public static void main(String[] args) {
//	UserOpera opera = getInstance();
//	User user = opera.readByEmail("lee1059232202@163.com");
//	System.out.println(user.getNickName());
//	User user = new User();
//	user.setUserId(3);
//	user.setGrade("3");
//	opera.updateGrade(user);
	
//	List<String> emails = opera.readAllEmail();
//	for (String email : emails) {
//		System.out.println(email);
//	}
}
	public boolean updateGrade(User user) {
		 SqlSession session = getSession();
	        int rs=0;
	        try{
	            rs = session.update("updateGrade", user);
	            session.commit();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            session.close();
	        }
	        return rs==1?true:false;
	}

	public List<String> readAllEmail() {
		 SqlSession session = getSession();
	        List<User> users=null;
	        try{
	            users= session.selectList("readAllEmail");
	        }catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            session.close();
	        }
	        List<String> emails = new ArrayList<String>();
	        if(users != null && users.size()>0){
	        	for (User user : users) {
					emails.add(user.getEmail());
				}
	        }
	        return emails;
	}

	public List<User> readUserByTime(long startTime, long endTime, int pageNo,int pageSize) {
		SqlSession session = getSession();
	 	Map<String, Object> map = new HashMap<String, Object>();
	 	map.put("startTime", startTime);
	 	map.put("endTime", endTime);
	 	List<User> users=null;
        try{
        	users= session.selectList("readUserByTime",map,new RowBounds((pageNo-1)*pageSize,pageSize));
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return users;
	}
	public List<User> readUserByTime(long startTime, long endTime) {
		SqlSession session = getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<User> users=null;
		try{
			users= session.selectList("readUserByTime",map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return users;
	}

	public int readUserCountByTime(long startTime, long endTime) {
		SqlSession session = getSession();
	 	Map<String, Object> map = new HashMap<String, Object>();
	 	map.put("startTime", startTime);
	 	map.put("endTime", endTime);
	 	int number=0;
        try{
        	number= session.selectOne("readUserCountByTime",map);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return number;
	}
	
	/**
     * 查询用户已使用空间
     * @author haifei.huang
     * @date 2013-4-23
     * @param user
     * @return
     */
    public long selectSpace(User user) {
        SqlSession session = getSession();
        long temp=0;
        try{
            temp= session.selectOne("selectSpace",user);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return temp;
    }
    
	 /**
     * 更新用户已使用空间
     * @author haifei.huang
     * @date 2013-5-13
     * @param userId
     * @return
     */
    public boolean updateSpace(User user) {
        SqlSession session = getSession();
        int rs=0;
        try{
            rs = session.update("updateSpace", user);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return rs==1?true:false;
    }

	public List<String> readAllPhone() {
		 SqlSession session = getSession();
	        List<User> users=null;
	        try{
	            users= session.selectList("readAllPhone");
	        }catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            session.close();
	        }
	        List<String> phones = new ArrayList<String>();
	        if(users != null && users.size()>0){
	        	for (User user : users) {
	        		phones.add(user.getRegPhone());
				}
	        }
	        return phones;
	}
	
	/**
     * 查询用户级别
     * @author haifei.huang
     * @date 2013-4-23
     * @param user
     * @return
     */
    public String selectGrade(long suserId) {
        SqlSession session = getSession();
        String temp=null;
        try{
            temp= session.selectOne("selectGrade",suserId);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return temp;
    }
    
    /**
     * 更新登录令牌
     * @author haifei.huang
     * @date 2013-7-10
     * @param user
     * @return
     */
    public boolean updateToken(User user) {
        SqlSession session = getSession();
        int flag=0;
        try{
            flag=session.update("updateToken",user);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return flag==1?true:false;
    }
    
   /**
    * 查询用户登录令牌
    * @author haifei.huang
    * @date 2013-7-10
    * @param user
    * @return
    */
    public String selectToken(User user) {
        SqlSession session = getSession();
        String temp=null;
        try{
            temp= session.selectOne("selectToken",user);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return temp;
    }
    
    /**
     * 查询某个email的注册个数
     * @author haifei.huang
     * @date 2013-8-20
     * @param email
     * @return
     */
    public int readEmailCount(String email) {
        SqlSession session = getSession();
        int temp= 0;
        try{
            temp=session.selectOne("readEmailCount",email);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return temp;
    }
}
