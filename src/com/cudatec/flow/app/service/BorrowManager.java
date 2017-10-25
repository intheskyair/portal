package com.cudatec.flow.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cudatec.flow.app.dao.BorrowDao;
import com.cudatec.flow.app.entity.Borrow;
import com.cudatec.flow.framework.service.SnakerEngineFacets;
import com.cudatec.flow.framework.util.DateUtils;
@Component
public class BorrowManager {
    @Autowired
    private SnakerEngineFacets facets;
    @Autowired
    private BorrowDao dao;

    public void save(String processId, String orderId, String taskId, Borrow model, String userName) {
        /** 流程数据构造开始 */
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("apply.operator", userName);
        params.put("approval.operator", userName);
        /** 流程数据构造结束 */

        /**
         * 启动流程并且执行申请任务
         */
        if (StringUtils.isEmpty(orderId) && StringUtils.isEmpty(taskId)) {
            Map<String, String> map = facets.startAndExecute(processId, userName, params);
            /** 业务数据处理开始*/
            model.setOrderId(map.get("orderId"));
            model.setOperateTime(DateUtils.getCurrentDay());
            model.setOperator(userName);
            //save(model);
        } else {
            facets.execute(taskId, userName, params);
            /** 业务数据处理开始*/
            model.setOperator(userName);
            //save(model);
        }
    }

    /**
     * 保存实体
     * @param entity
     */
    public void save(Borrow entity) {
        dao.save(entity);
    }

    /**
     * 根据主键ID删除对应的
     * @param id
     */
    public void delete(Long id) {
        dao.delete(id);
    }

    /**
     * 根据主键ID获取实体
     * @param id
     * @return
     */
    public Borrow get(Long id) {
        return dao.get(id);
    }

    /**
     * 获取所有记录
     * @return
     */
    public List<Borrow> getAll() {
        return dao.getAll();
    }

    public Borrow findByOrderId(String orderId) {
        List<Borrow> results = dao.findBy("orderId", orderId);
        if(results != null && results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }
}
