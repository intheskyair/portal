package com.zqkj.bsm.dal.client;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.zqkj.bsm.dal.bean.Menu;

public class MenuOpera extends BaseOpera{
	private static MenuOpera menuOpera=null;
	private MenuOpera(){}
	public static MenuOpera getInstance(){
		synchronized (MenuOpera.class) {
			if (menuOpera==null) {
				menuOpera=new MenuOpera();
			}
		}
		return menuOpera;
	}
	/**
	 * 新建菜单
	 * @author 刘劲
	 * @date Aug 12, 2013
	 * @param menu
	 * @return
	 */
	public boolean createMenu(Menu menu){
		SqlSession session=getSession();
		int flag = session.insert("createMenu", menu);
		session.commit();
		session.close();
		return flag==1?true:false;
	}
	
	
	
	public boolean updateMenu(Menu menu){
		SqlSession session=getSession();
		int flag = session.update("updateMenu", menu);
		session.commit();
		session.close();
		return flag==1?true:false;
	}
	
	/**
	 * 根据id读取菜单
	 * @author 刘劲
	 * @date Aug 12, 2013
	 * @param menuId
	 * @return
	 */
	public Menu readMenuById(long menuId) {
		SqlSession session=getSession();
		Menu rs=session.selectOne("readMenuById", menuId);
		session.close();
		return rs;
	}
	/**
	 * 读取所有菜单
	 * @author 刘劲
	 * @date Aug 12, 2013
	 * @return
	 */
	public List<Menu> readMenuAll(Map<String, Object> param) {
		SqlSession session = getSession();
		List<Menu> rs = session.selectList("readMenuAll", param);
		session.close();
		return rs;
	}
	/**
	 * 读取所有一级菜单
	 * @author 刘劲
	 * @date Aug 12, 2013
	 * @return
	 */
	public List<Menu> readOneGradeMenu() {
		SqlSession session = getSession();
		List<Menu> rs = session.selectList("readOneGradeMenu");
		session.close();
		return rs;
	}

	/**
	 * 获取所有菜单个数
	 * @author 0188
	 * @return
	 */
	public int getAllCount(Map<String, Object> param) {
		SqlSession session = getSession();
		int rs = session.selectOne("getCount", param);
		session.close();
		return rs;
	}
}