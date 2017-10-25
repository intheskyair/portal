package com.zqkj.bsm.dal.dboper;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zqkj.bsm.dal.client.BaseOpera;

public class BaseDAO<T> extends BaseOpera
{

	protected Logger log = Logger.getLogger(this.getClass());
	
    @SuppressWarnings("unused")
    private Class<T> persistentClass;
    
    @SuppressWarnings("unchecked")
    public BaseDAO() 
    {
        //获得参数化类型        
        ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
        persistentClass = (Class<T>)type.getActualTypeArguments()[0];
    }
    
    
    /**
     * 保存
     */
    public boolean save(T entity) throws Exception
    {
        //SQL语句,insert into table name (
        String sql = "insert into " + entity.getClass().getSimpleName().toLowerCase() + "(";
        
        //获得带有字符串get的所有方法的对象
        List<Method> list = this.matchPojoMethods(entity,"get");
        
        Iterator<Method> iter = list.iterator();
        
        //拼接字段顺序 insert into table name(id,name,email,
        while(iter.hasNext()) {
            Method method = iter.next();
            sql += method.getName().substring(3).toLowerCase() + ",";
        }
        
        //去掉最后一个,符号insert insert into table name(id,name,email) values(
        sql = sql.substring(0, sql.lastIndexOf(",")) + ") values(";
        
        //拼装预编译SQL语句insert insert into table name(id,name,email) values(?,?,?,
        for(int j = 0; j < list.size(); j++) {
            sql += "?,";
        }

        //去掉SQL语句最后一个,符号insert insert into table name(id,name,email) values(?,?,?);
        sql = sql.substring(0, sql.lastIndexOf(",")) + ")";
        
        //到此SQL语句拼接完成,打印SQL语句
        System.out.println(sql);
		PreparedStatement statement = null;
		Connection conn = null;
		SqlSession session = getSession();
        try
        {
			conn = session.getConnection();
			statement = conn.prepareStatement(sql);
	        int i = 0;
	        //把指向迭代器最后一行的指针移到第一行.
	        iter = list.iterator();
	        while(iter.hasNext()) 
	        {
	            Method method = iter.next();
	            //此初判断返回值的类型,因为存入数据库时有的字段值格式需要改变,比如String,SQL语句是'"+abc+"'
	            if(method.getReturnType().getSimpleName().indexOf("String") != -1) 
	            {
	                statement.setString(++i, this.getString(method, entity));
	            } else if(method.getReturnType().getSimpleName().indexOf("Date") != -1)
	            {
	                statement.setDate(++i, this.getDate(method, entity));
	            } else if(method.getReturnType().getSimpleName().indexOf("InputStream") != -1) 
	            {
	                statement.setAsciiStream(++i, this.getBlob(method, entity),1440);
	            } 
	            else 
	            {
	                statement.setInt(++i, this.getInt(method, entity));
	            }
	        }
	        //执行
	        statement.executeUpdate();
			conn.commit();
			return true;
        }
        catch (Exception e) 
        {
			log.error("异常:" + e);
			log.error("异常sql==" + sql);
			return false;
		} finally 
		{
			this.clean(null,statement,null);
			session.close();
		}

    }
    
    
    /**
     * 修改
     */
    public boolean update(T entity) throws Exception{
        String sql = "update " + entity.getClass().getSimpleName().toLowerCase() + " set ";
        
        //获得该类所有get方法对象集合
        List<Method> list = this.matchPojoMethods(entity,"get");
        
        //临时Method对象,负责迭代时装method对象.
        Method tempMethod = null;
        
        //由于修改时不需要修改ID,所以按顺序加参数则应该把Id移到最后.
        Method idMethod = null;
        Iterator<Method> iter = list.iterator();
        while(iter.hasNext()) {
            tempMethod = iter.next();
            //如果方法名中带有ID字符串并且长度为2,则视为ID.
            if(tempMethod.getName().lastIndexOf("Id") != -1 && tempMethod.getName().substring(3).length() == 2) {
                //把ID字段的对象存放到一个变量中,然后在集合中删掉.
                idMethod = tempMethod;
                iter.remove();
            //如果方法名去掉set/get字符串以后与pojo + "id"想符合(大小写不敏感),则视为ID
            } else if((entity.getClass().getSimpleName() + "Id").equalsIgnoreCase(tempMethod.getName().substring(3))) {
                idMethod = tempMethod;
                iter.remove();                
            }
        }
        
        //把迭代指针移到第一位
        iter = list.iterator();
        while(iter.hasNext()) {
            tempMethod = iter.next();
            sql += tempMethod.getName().substring(3).toLowerCase() + "= ?,";
        }
        
        //去掉最后一个,符号
        sql = sql.substring(0,sql.lastIndexOf(","));
        
        //添加条件
        sql += " where " + idMethod.getName().substring(3).toLowerCase() + " = ?";
        
        //SQL拼接完成,打印SQL语句
        System.out.println(sql);
		PreparedStatement statement = null;
		Connection conn = null;
		SqlSession session = getSession();
        try
        {
			conn = session.getConnection();
			statement = conn.prepareStatement(sql);	        
	        int i = 0;
	        iter = list.iterator();
	        while(iter.hasNext()) {
	            Method method = iter.next();
	            //此初判断返回值的类型,因为存入数据库时有的字段值格式需要改变,比如String,SQL语句是'"+abc+"'
	            if(method.getReturnType().getSimpleName().indexOf("String") != -1) {
	                statement.setString(++i, this.getString(method, entity));
	            } else if(method.getReturnType().getSimpleName().indexOf("Date") != -1){
	                statement.setDate(++i, this.getDate(method, entity));
	            } else if(method.getReturnType().getSimpleName().indexOf("InputStream") != -1) {
	                statement.setAsciiStream(++i, this.getBlob(method, entity),1440);
	            } else {
	                statement.setInt(++i, this.getInt(method, entity));
	            }            
	        }
	        
	        //为Id字段添加值
	        if(idMethod.getReturnType().getSimpleName().indexOf("String") != -1) {
	            statement.setString(++i, this.getString(idMethod, entity));
	        } else {
	            statement.setInt(++i, this.getInt(idMethod, entity));
	        }
	        
	        //执行SQL语句
	        statement.executeUpdate();
			conn.commit();
			return true;
    }
    catch (Exception e) 
    {
		log.error("异常:" + e);
		log.error("异常sql==" + sql);
		return false;
	} finally 
	{
		this.clean(null,statement,null);
		session.close();
	}
}
    
    
    /**
     * 删除
     */
    public boolean delete(T entity) throws Exception
    {
        String sql = "delete from " + entity.getClass().getSimpleName().toLowerCase() + " where ";
        
        //存放字符串为"id"的字段对象
        Method idMethod = null;
        
        //取得字符串为"id"的字段对象
        List<Method> list = this.matchPojoMethods(entity, "get");
        Iterator<Method> iter = list.iterator();
        while(iter.hasNext()) {
            Method tempMethod = iter.next();
            //如果方法名中带有ID字符串并且长度为2,则视为ID.
            if(tempMethod.getName().lastIndexOf("Id") != -1 && tempMethod.getName().substring(3).length() == 2) {
                //把ID字段的对象存放到一个变量中,然后在集合中删掉.
                idMethod = tempMethod;
                iter.remove();
            //如果方法名去掉set/get字符串以后与pojo + "id"想符合(大小写不敏感),则视为ID
            } else if((entity.getClass().getSimpleName() + "Id").equalsIgnoreCase(tempMethod.getName().substring(3))) {
                idMethod = tempMethod;
                iter.remove();                
            }
        }
        
        sql += idMethod.getName().substring(3).toLowerCase() + " = ?";
		PreparedStatement statement = null;
		Connection conn = null;
		SqlSession session = getSession();
        try
        {
			conn = session.getConnection();
			statement = conn.prepareStatement(sql);
        
        //为Id字段添加值
        int i = 0;
        if(idMethod.getReturnType().getSimpleName().indexOf("String") != -1) {
            statement.setString(++i, this.getString(idMethod, entity));
        } else {
            statement.setInt(++i, this.getInt(idMethod, entity));
        }        
		conn.commit();
		return true;
    }
    catch (Exception e) 
    {
		log.error("异常:" + e);
		log.error("异常sql==" + sql);
		return false;
	} finally 
	{
		this.clean(null,statement,null);
		session.close();
	}
}    
    
    /**
     * 过滤当前Pojo类所有带传入字符串的Method对象,返回List集合.
     */
    private List<Method> matchPojoMethods(T entity,String methodName) {
        //获得当前Pojo所有方法对象
        Method[] methods = entity.getClass().getDeclaredMethods();
        
        //List容器存放所有带get字符串的Method对象
        List<Method> list = new ArrayList<Method>();
        
        //过滤当前Pojo类所有带get字符串的Method对象,存入List容器
        for(int index = 0; index < methods.length; index++) {
            if(methods[index].getName().indexOf(methodName) != -1) {
                list.add(methods[index]);
            }
        }        
        return list;
    }
    /**
     * 方法返回类型为int或Integer类型时,返回的SQL语句值.对应get
     */
    public Integer getInt(Method method, T entity) throws Exception{
        return (Integer)method.invoke(entity, new Object[]{});
    }
    
    /**
     * 方法返回类型为String时,返回的SQL语句拼装值.比如'abc',对应get
     */
    public String getString(Method method, T entity) throws Exception{
        return (String)method.invoke(entity, new Object[]{});
    }
    
    /**
     * 方法返回类型为Blob时,返回的SQL语句拼装值.对应get
     */
    public InputStream getBlob(Method method, T entity) throws Exception{
        return (InputStream)method.invoke(entity, new Object[]{});
    }
    
    
    /**
     * 方法返回类型为Date时,返回的SQL语句拼装值,对应get
     */
    public Date getDate(Method method, T entity) throws Exception{
        return (Date)method.invoke(entity, new Object[]{});
    }
    
    
    /**
     * 参数类型为Integer或int时,为entity字段设置参数,对应set
     */
    public Integer setInt(Method method, T entity, Integer arg) throws Exception{
        return (Integer)method.invoke(entity, new Object[]{arg});
    }
    
    /**
     * 参数类型为String时,为entity字段设置参数,对应set
     */
    public String setString(Method method, T entity, String arg) throws Exception{
        return (String)method.invoke(entity, new Object[]{arg});
    }
    
    /**
     * 参数类型为InputStream时,为entity字段设置参数,对应set
     */
    public InputStream setBlob(Method method, T entity, InputStream arg) throws Exception{
        return (InputStream)method.invoke(entity, new Object[]{arg});
    }
    
    
    /**
     * 参数类型为Date时,为entity字段设置参数,对应set
     */
    public Date setDate(Method method, T entity, Date arg) throws Exception{
        return (Date)method.invoke(entity, new Object[]{arg});
    }
    
    
	protected void clean(ResultSet rs, PreparedStatement ps, Statement st)
	{
		if (rs != null) 
		{
			try {
				Statement st2 = rs.getStatement();
				if (st2 != null) 
				{
					st2.close();
					st2 = null;
				}
				rs.close();
				rs = null;
			} catch (Exception e) {
				log.error("异常:" + e);
				e.printStackTrace();
			}
		}
		if (ps != null) 
		{
			try {
				ps.close();
				ps = null;
			} catch (Exception e) {
				log.error("异常:" + e);
				e.printStackTrace();
			}
		}
		if (st != null) 
		{
			try {
				st.close();
				st = null;
			} catch (Exception e) {
				log.error("异常:" + e);
				e.printStackTrace();
			}
		}
	}
}
