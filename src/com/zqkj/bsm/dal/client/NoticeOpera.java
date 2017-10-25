package com.zqkj.bsm.dal.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.zqkj.bsm.dal.bean.Notice;

public class NoticeOpera extends BaseOpera
{

    private static NoticeOpera noticeOpera=null;
    
    private NoticeOpera(){}
    
    public static NoticeOpera getInstance(){
        if(noticeOpera==null){
            noticeOpera=new NoticeOpera();
        }
        return noticeOpera;
    }
    
    /**
     * 查询公告 带分页
     * @author haifei.huang
     * @date 2013-8-16
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Notice> selectNotice(Map<String, Object> param){
        SqlSession session = getSession();
        List<Notice> list= null;
        try{
            list=session.selectList("selectNotice", param);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return list;    
    }
    
    /**
     * 查询公告数量
     * @author haifei.huang
     * @date 2013-8-16
     * @param notice
     * @return
     */
    public int selectNoticeCount(Map<String, Object> param){
        SqlSession session = getSession();
        int rs= 0;
        try{
            rs=session.selectOne("selectNoticeCount",param);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return rs;    
    }
    
    /**
     * 创建公告
     * @author haifei.huang
     * @date 2013-8-16
     * @param notice
     * @return
     */
    public boolean createNotice(Notice notice) {
        SqlSession session = getSession();
        int flag=0;
        try{
            flag=session.insert("createNotice",notice);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return flag==1?true:false;
    }
    
    /**
     * 根据Id查询公告
     * @author haifei.huang
     * @date 2013-8-19
     * @param noticeId
     * @return
     */
    public Notice selectNoticeById(long noticeId){
        SqlSession session = getSession();
        Notice notice = null;
        try{
            notice=session.selectOne("selectNoticeById",noticeId);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return notice;
    }

    /**
     * 更新公告
     * @author haifei.huang
     * @date 2013-8-16
     * @param notice
     * @return
     */
    public boolean updateNotice(Notice notice) {
        SqlSession session = getSession();
        int flag=0;
        try{
            flag=session.insert("updateNotice",notice);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        return flag==1?true:false;
    }
    
    /**
     * 查询发布中的公告
     * @author haifei.huang
     * @date 2013-8-20
     * @param nowTime
     * @return
     */
    public List<Notice> selectReleaseNotice(long nowTime){
    	 SqlSession session = getSession();
         List<Notice> list = null;
         try{
             Map<String, Object> map = new HashMap<String, Object>();
             //System.out.println(nowTime);
             map.put("nowTime", nowTime);
             list=session.selectList("selectReleaseNotice",map);
         }catch (Exception e) {
             e.printStackTrace();
         }finally{
             session.close();
         }
         return list;
    }
}
