package com.zqkj.bsm.client.dao;
import java.util.Map;

import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.util.CommonUtil;
/**
 * 客户端接口服务
 * @author 0242
 *
 */
public class ClientDao extends DbOperator
{
	private static volatile ClientDao instance = null;
	
	private ClientDao()
	{
		
	}
	public static ClientDao getInstance()
	{
		if(instance==null)
		{
			synchronized (ClientDao.class)
			{
			instance = new ClientDao();
			}
		}
		return instance;
	}
   
	public String getSeq(String seq)
	{
		return super.getSeq_Curr(seq);
	}
	
	public void upSeq(String seq)
	{
		super.upSeq(seq);
	}
	public String getNextSeq(String seq)
    {
        return super.getSeq_Next(seq);
    }
	
    /**
     * 获取网站注册用户的信息
     * @param loginName
     * @param loginPwd
     * @return
     */
//	public Map<String, Object> getAdminInfo(String loginName) 
//	{
//		Object[] object = new Object[1];
//		StringBuffer  param = new StringBuffer();
//		param.append("select a.*,b.qidType,b.startDate,b.endDate from web_user a left join (select c.* from render_user2plans c where c.status=0) b on a.id=b.userId where a.status=1 and a.type <> 4 and a.type <> 9 ");
//		if(loginName == null || loginName.trim().equals(""))
//		{
//           return null;
//		}
//		else
//		{
//			if (StringUtils.isNumeric(loginName) && loginName.length()==11) 
//			{
//				param.append(" and a.phone = ? ");
//			} else if (StringUtils.contains(loginName, "@")) 
//			{
//				param.append(" and a.email = ? ");
//			} else 
//			{
//				param.append(" and a.nickname = ? ");
//			}
//			
//			object[0] = loginName;
//		}
//		Map<String, Object> map = this.getHashMap_Pre(param.toString(), object);
//		return map;
//	}
	
	
	public Admin getAdminInfo(String name) 
	{
		String sql ="";
		if(hasFullChar(name))
		{
		   sql = "select * from admin a where a.truename=?";
		}
		else
		{
			sql = "select * from admin a where a.username=?";
		}
		
		Map<String, Object> map = this.getHashMap_Pre(sql,name);
		if(map!=null && map.size()>0)
		{
			Admin admin = new Admin();
			admin.setAdminId(Long.valueOf(CommonUtil.nullToDefault(map.get("ADMIN_ID"),"0")));
			admin.setPhoneNum(CommonUtil.nullToDefault(map.get("PHONE"),""));
			admin.setEmail(CommonUtil.nullToDefault(map.get("EMAI"),""));
			admin.setPassword(CommonUtil.nullToDefault(map.get("PASSWORD"),""));
			admin.setStatus(Integer.valueOf(CommonUtil.nullToDefault(map.get("STATUS"),"-1")));
			admin.setTruename(CommonUtil.nullToDefault(map.get("TRUENAME"),""));
			admin.setDepartId(CommonUtil.nullToDefault(map.get("ORG_ID"),"0"));
			admin.setOrg_infor(CommonUtil.nullToDefault(map.get("ORG_INFOR"),""));
			admin.setOrg_name(CommonUtil.nullToDefault(map.get("ORG_NAME"),""));
			admin.setOrg_id(Long.valueOf(CommonUtil.nullToDefault(map.get("ORG_ID"),"0")));
			admin.setName(CommonUtil.nullToDefault(map.get("USERNAME"),""));
            return admin;
		}
		else
		{
			return null;
		}
	}

	private boolean hasFullChar(String str) 
	{
        if (str.getBytes().length == str.length())
        {
            return false;
        } 
        return true;    
    }
	
}
