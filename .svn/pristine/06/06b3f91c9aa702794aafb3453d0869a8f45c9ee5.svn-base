package com.zqkj.bsm.dal.client;

import java.util.ArrayList;
import java.util.Map;

import net.sf.json.JSONArray;

import com.zqkj.bsm.dal.bean.Menu;
import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.SysPara;
import com.zqkj.bsm.system.action.util.StringHelper;

public class MenuDao extends DbOperator
{
    private static MenuDao instance = null;
    
    private MenuDao()
    {
        
    }
    
    public static MenuDao getInstance()
    {
        if (instance == null)
        {
            instance = new MenuDao();
        }
        return instance;
    }
    
    private long getMaxMENUSEQ(long parentid)
    {
        long MaxMENUSEQ = 0;
        String sql = "select max(MENUSEQ) max from sys_menu where parentid=" + parentid;
        Map<String, Object> map = this.getHashMap_Pre(sql);
        if (map != null && map.size() > 0)
        {
            MaxMENUSEQ = Long.valueOf(StringHelper.noEmpty2(map.get("MAX")));
        }
        return MaxMENUSEQ;
    }
    
    /**
     * 新增菜单
     * 
     * @param menu
     * @param buttonname
     * @return
     */
    public boolean createMenu(Menu menu, JSONArray buttonname)
    {
        // 新增菜单
        long MENUSEQ = this.getMaxMENUSEQ(menu.getUpMenuId());
        Object[] object = new Object[6];
        object[0] = menu.getMenuName();
        object[1] = ++MENUSEQ;
        object[2] = menu.getIsLeaf();
        object[3] = menu.getUpMenuId();
        object[4] = menu.getMenuUrl();
        object[5] = menu.getIsIframe();
        ArrayList<String> list = new ArrayList<String>();
        if (SysPara.db_bz == 0)
        {
            String sql_menu =
                "insert into sys_menu(menuname,menuseq,is_leaf,parentid,menuurl,isIframe) values(?,?,?,?,?,?)";
            long user_id = this.executeInsert_backID(sql_menu, object);
            // 根据新增菜单的id，存入菜单按钮
            for (int i = 0; i < buttonname.size(); i++)
            {
                String sql =
                    "insert into sys_menu2button(menu_id,menu_name,button_name) values(" + "'" + user_id + "','"
                        + menu.getMenuName() + "','" + buttonname.getString(i) + "')";
                list.add(sql);
            }
        }
        else
        {
            String id = this.getSeq_oracle("SEQ_SYS_MENU");
            String sql_menu =
                "insert into sys_menu(id,menuname,menuseq,is_leaf,parentid,menuurl,isIframe) values(" + id
                    + ",?,?,?,?,?,?)";
            this.executeInsert(sql_menu, object);
            // 根据新增菜单的id，存入菜单按钮
            for (int i = 0; i < buttonname.size(); i++)
            {
                String sql =
                    "insert into sys_menu2button(id,menu_id,menu_name,button_name) values(SEQ_SYS_MENU2BUTTON.NextVal,"
                        + "'" + id + "','" + menu.getMenuName() + "','" + buttonname.getString(i) + "')";
                list.add(sql);
            }
        }
        return this.executeBatch(list);
    }
    
    /**
     * 更新菜单
     * 
     * @param menu
     * @param buttonname
     * @return
     */
    public boolean updateMenu(Menu menu, JSONArray buttonname)
    {
        // 更新菜单信息（除操作按钮外）
        ArrayList<Object[]> obj_list_update = new ArrayList<Object[]>();
        Object[] object_update = new Object[6];
        object_update[0] = menu.getMenuName();
        object_update[1] = menu.getIsLeaf();
        object_update[2] = menu.getUpMenuId();
        object_update[3] = menu.getMenuUrl();
        object_update[4] = menu.getIsIframe();
        object_update[5] = menu.getMenuId();
        obj_list_update.add(object_update);
        String sql_update = "update sys_menu set menuname=?,is_leaf=?,parentid=?,menuurl=?,isIframe=? where id=?";
        
        // 删除该菜单原先存入的操作按钮信息
        ArrayList<Object[]> obj_list_delete = new ArrayList<Object[]>();
        Object[] object_delete = new Object[1];
        object_delete[0] = menu.getMenuId();
        obj_list_delete.add(object_delete);
        String sql_delete = "delete from sys_menu2button where menu_id=?";
        
        // 完成以上两个按钮之后，将更新后的操作按钮信息存入数据库
        if (this.executeBatch_Pre2(sql_update, obj_list_update, sql_delete, obj_list_delete))
        {
            ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < buttonname.size(); i++)
            {
                String sql_insert =
                    "insert into sys_menu2button(menu_id,menu_name,button_name) values(" + "'" + menu.getMenuId()
                        + "','" + menu.getMenuName() + "','" + buttonname.getString(i) + "')";
                list.add(sql_insert);
            }
            return this.executeBatch(list);
        }
        return false;
    }
}
