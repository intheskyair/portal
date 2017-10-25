package com.zqkj.bsm.dal.dboper;

import java.util.ArrayList;
import java.util.List;

import com.zqkj.bsm.dal.bean.Admin;

public class TestDao extends BaseDAO<Admin> 
{

    // 添加员工信息的操作
    public boolean addEmployees(final Admin employees) throws Exception {
        save(employees);
        return true;
    }

    // 将员工信息添加到表格中
//    public List<Admin> addEmployees(int id) throws Exception {
//        List<Admin> lstEmployees = new ArrayList<Admin>();
//        Admin employees = findById(id);
//        // 将当前封转好的数据装入对象中
//        lstEmployees.add(employees);
//        return lstEmployees;
//    }

    public void deleteEmp(final Admin entity) throws Exception {
        this.delete(entity);
    }

    public void updateEmp(final Admin entity) throws Exception {
        this.update(entity);
    }
}
