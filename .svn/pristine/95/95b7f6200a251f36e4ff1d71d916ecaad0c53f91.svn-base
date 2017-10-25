/* Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cudatec.flow.framework.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Surrogate;
import org.snaker.engine.entity.Task;
import org.snaker.engine.helper.StringHelper;
import org.snaker.engine.model.StartModel;
import org.snaker.engine.model.TaskModel;
import org.snaker.engine.model.TaskModel.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cudatec.flow.framework.dao.TaskManageDao;
import com.cudatec.flow.framework.ext.ExtTaskModel;
import com.zqkj.bsm.dal.bean.Admin;

/**
 * @author yuqs
 * @since 0.1
 */
@Component
public class SnakerEngineFacets
{
    @Autowired
    private SnakerEngine engine;
    
    public void initFlows()
    {
        // engine.process().deploy(StreamHelper.getStreamFromClasspath("flows/leave.snaker"));
        // engine.process().deploy(StreamHelper.getStreamFromClasspath("flows/borrow.snaker"));
        // engine.process().deploy(StreamHelper.getStreamFromClasspath("flows/reimburce.snaker"));
    }
    
    public SnakerEngine getEngine()
    {
        return engine;
    }
    
    public List<String> getAllProcessNames()
    {
        List<Process> list = engine.process().getProcesss(new QueryFilter());
        List<String> names = new ArrayList<String>();
        for (Process entity : list)
        {
            if (names.contains(entity.getName()))
            {
                continue;
            }
            else
            {
                names.add(entity.getName());
            }
        }
        return names;
    }
    
    public Order startInstanceById(String processId, String operator, Map<String, Object> args)
    {
        return engine.startInstanceById(processId, operator, args);
    }
    
    public Order startInstanceByName(String name, Integer version, String operator, Map<String, Object> args)
    {
        return engine.startInstanceByName(name, version, operator, args);
    }
    
    public Order startAndExecute(String name, Integer version, String operator, Map<String, Object> args)
    {
        Order order = engine.startInstanceByName(name, version, operator, args);
        List<Task> tasks = engine.query().getActiveTasks(new QueryFilter().setOrderId(order.getId()));
        List<Task> newTasks = new ArrayList<Task>();
        if (tasks != null && tasks.size() > 0)
        {
            Task task = tasks.get(0);
            newTasks.addAll(engine.executeTask(task.getId(), operator, args));
        }
        return order;
    }
    
    public Map<String, String> startAndExecute(String processId, String operator, Map<String, Object> args)
    {
        Map<String, String> map = new HashMap<>();
        Order order = engine.startInstanceById(processId, operator, args);
        map.put("orderId", order.getId());
        List<Task> tasks = engine.query().getActiveTasks(new QueryFilter().setOrderId(order.getId()));
        List<Task> newTasks = new ArrayList<Task>();
        if (tasks != null && tasks.size() > 0)
        {
            Task task = tasks.get(0);
            map.put("taskId", task.getId());
            // 执行当前流程
            newTasks.addAll(engine.executeTask(task.getId(), operator, args));
            map.put("newTaskId", newTasks.get(0).getId());
            map.put("displayName", newTasks.get(0).getDisplayName());
        }
        return map;
    }
    
    public Map<String, String> startAndPerson(String processId, String operator, Map<String, Object> args)
    {
        Map<String, String> map = new HashMap<>();
        Order order = engine.startInstanceById(processId, operator, args);
        map.put("orderId", order.getId());
        List<Task> tasks = engine.query().getActiveTasks(new QueryFilter().setOrderId(order.getId()));
        List<Task> newTasks = new ArrayList<Task>();
        if (tasks != null && tasks.size() > 0)
        {
            Task task = tasks.get(0);
            map.put("taskId", task.getId());
            // 执行当前流程
            newTasks.addAll(engine.executeTask(task.getId(), operator, args));
            addTaskActor(newTasks.get(0).getId(), 0, args.get("nextOperator").toString().split(","));
            map.put("newTaskId", newTasks.get(0).getId());
            map.put("displayName", newTasks.get(0).getDisplayName());
        }
        return map;
    }
    
    public List<Task> execute(String taskId, String operator, Map<String, Object> args)
    {
        return engine.executeTask(taskId, operator, args);
    }
    
    public List<Task> executeAndJump(String taskId, String operator, Map<String, Object> args, String nodeName)
    {
        return engine.executeAndJumpTask(taskId, operator, args, nodeName);
    }
    
    public List<Task> transferMajor(String taskId, String operator, String... actors)
    {
        List<Task> tasks = engine.task().createNewTask(taskId, TaskType.Major.ordinal(), actors);
        engine.task().complete(taskId, operator);
        return tasks;
    }
    
    public List<Task> transferAidant(String taskId, String operator, String... actors)
    {
        List<Task> tasks = engine.task().createNewTask(taskId, TaskType.Aidant.ordinal(), actors);
        engine.task().complete(taskId, operator);
        return tasks;
    }
    
    public void addTaskActor(String taskId, Integer performType, Object[] authName)
    {
        
        String[] strs = new String[authName.length];
        for (int i = 0; i < authName.length; i++)
        {
            strs[i] = authName[i].toString();
        }
        engine.task().addTaskActor(taskId, performType, strs);
    }
    
    public Map<String, Object> flowData(String orderId, String taskName)
    {
        Map<String, Object> data = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(orderId) && StringUtils.isNotEmpty(taskName))
        {
            List<HistoryTask> histTasks =
                engine.query().getHistoryTasks(new QueryFilter().setOrderId(orderId).setName(taskName));
            List<Map<String, Object>> vars = new ArrayList<Map<String, Object>>();
            for (HistoryTask hist : histTasks)
            {
                vars.add(hist.getVariableMap());
            }
            data.put("vars", vars);
            data.put("histTasks", histTasks);
        }
        return data;
    }
    
    public void addSurrogate(Surrogate entity)
    {
        if (entity.getState() == null)
        {
            entity.setState(1);
        }
        engine.manager().saveOrUpdate(entity);
    }
    
    public void deleteSurrogate(String id)
    {
        engine.manager().deleteSurrogate(id);
    }
    
    public Surrogate getSurrogate(String id)
    {
        return engine.manager().getSurrogate(id);
    }
    
    public List<Surrogate> searchSurrogate(Page<Surrogate> page, QueryFilter filter)
    {
        return engine.manager().getSurrogate(page, filter);
    }
    
    /**
     * 
     * 
     * @Title: chooseStep
     * @Description: TODO(根据orderid选择返回步骤名称)
     * @param @param processId
     * @param @param orderId
     * @param @param taskId
     * @param @return 设定文件
     * @return Map<String,Object> 返回类型
     * @throws
     */
    public Map<String, Object> chooseStep1(String processId, String orderId, String taskId, Admin admin)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        // List<TaskModel> list = engine.process().getProcessById(processId).getModel().getModels(TaskModel.class);
        List<ExtTaskModel> list = engine.process().getProcessById(processId).getModel().getModels(ExtTaskModel.class);
        if (StringHelper.isNotEmpty(orderId))
        {
            map = TaskManageDao.getInstance().getForm_List(orderId);
            List<Task> listTask =
                engine.query().getActiveTasks(new QueryFilter().setTaskId(taskId).setOrderId(orderId));
            map.put("taskName", listTask.get(0).getTaskName()); // 所有页面必传参数
            String to = null;
            for (TaskModel model : list)
            {
                if (model.getName().equals(map.get("taskName").toString()))
                {
                    to = model.getOutputs().get(0).getTo();
                    break;
                }
            }
            boolean flag = false;
            for (TaskModel model : list)
            {
                if (to.equals(model.getName()))
                {
                    flag = true;
                    map.put("assignee", model.getAssignee());
                    map.put("displayName", model.getDisplayName());
                }
            }
            if (!flag)
            {
                for (TaskModel model : list)
                {
                    if (model.getName().equals("sb"))
                    {
                        map.put("assignee", model.getAssignee());
                        map.put("displayName", model.getDisplayName());
                        break;
                    }
                }
            }
        }
        else
        {
            // 第一步
            map.put("taskName", list.get(0).getName());
            // 下一步操作人权限
            map.put("assignee", list.get(1).getAssignee());
            // 流程名称
            map.put("displayName", list.get(1).getDisplayName());
        }
        map.put("processId", processId);
        map.put("orderId", orderId);
        map.put("taskId", taskId);
        return map;
    }
    
    /**
     * 
     * 
     * @Title: chooseStep
     * @Description: TODO(根据orderid选择返回步骤名称)
     * @param @param processId
     * @param @param orderId
     * @param @param taskId
     * @param @return 设定文件
     * @return Map<String,Object> 返回类型
     * @throws
     */
    public Map<String, Object> chooseStep(String processId, String orderId, String taskId, Admin admin)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        int x = 0;
        List<ExtTaskModel> list = engine.process().getProcessById(processId).getModel().getModels(ExtTaskModel.class);
        if (StringHelper.isNotEmpty(orderId))
        {
            map = TaskManageDao.getInstance().getForm_List(orderId);
            List<Task> listTask =
                engine.query().getActiveTasks(new QueryFilter().setTaskId(taskId).setOrderId(orderId));
            map.put("taskName", listTask.get(0).getTaskName()); // 所有页面必传参数
            for (int i = 0; i < list.size(); i++)
            {
                if (list.get(i).getName().equals(map.get("taskName").toString()))
                {
                    x = i;
                    break;
                }
            }
        }
        
        if (x == 0)
        { // 第一步
          // 当前步骤名称
            map.put("taskName", list.get(0).getName());
            // 下一步操作人权限
            map.put("assignee", list.get(1).getAssignee());
            // 判断下一步流程，是否需要进行组织筛选条件
            map.put("autoOrg", list.get(1).getAutoOrg());
            // 流程名称
            map.put("displayName", list.get(1).getDisplayName());
            // 第一步组织ID
            map.put("ord", admin.getOrg_id());
        }
        else if (x == list.size() - 1)
        { // 最后一步
            map.put("assignee", "-1");
            map.put("displayName", list.get(x).getDisplayName());
        }
        else
        { // 其他步骤
            map.put("assignee", list.get(x + 1).getAssignee());
            map.put("autoOrg", list.get(x + 1).getAutoOrg());
            map.put("displayName", list.get(x).getDisplayName());
        }
        // 如果下一步权限为空并且不是最后一步，则获取上一层权限
        if (map.get("assignee") == null || map.get("assignee").toString().equals(""))
        {
            map.put("assignee", list.get(x - 1).getAssignee());
            map.put("autoOrg", list.get(x - 1).getAutoOrg());
        }
        // 流程步骤序号
        map.put("step", String.valueOf(x + 1));
        map.put("processId", processId);
        map.put("orderId", orderId);
        map.put("taskId", taskId);
        return map;
    }
    
    public Map<String, Object> getCs(String processId, String orderId, String taskId, Admin admin)
    {
        Map<String, Object> map = TaskManageDao.getInstance().getForm_List(orderId);
        map.put("processId", processId);
        map.put("orderId", orderId);
        map.put("taskId", taskId);
        ExtTaskModel nextModel = null;
        // 獲取start
        StartModel startModel = engine.process().getProcessById(processId).getModel().getStart();
        
        // 獲取第一步task
        ExtTaskModel FirstModel = (ExtTaskModel)startModel.getOutputs().get(0).getTarget();
        
        if (StringUtils.isEmpty(orderId))
        {
            // 獲取下一步權限
            nextModel = (ExtTaskModel)FirstModel.getOutputs().get(0).getTarget();
            // 第一步
            map.put("taskName", FirstModel.getName());
            // 流程名称
            map.put("displayName", FirstModel.getDisplayName());
            // 下一步操作人权限
            map.put("assignee", nextModel.getAssignee());
            map.put("autoOrg", nextModel.getAutoOrg());
        }
        else
        {
            List<Task> listTask =
                engine.query().getActiveTasks(new QueryFilter().setTaskId(taskId).setOrderId(orderId));
            nextModel = getNextTask(listTask.get(0).getTaskName(), processId);
            // 當前步驟
            map.put("taskName", listTask.get(0).getTaskName());
            if (nextModel != null)
            {
                // 下一步操作人权限
                map.put("assignee", nextModel.getAssignee());
                map.put("autoOrg", nextModel.getAutoOrg());
            }
            else
            {
                map.put("assignee", "-1");
            }
            // 流程名称
            map.put("displayName", listTask.get(0).getDisplayName());
        }
        
        if (nextModel != null && nextModel.getAutoOrg().equals("Y"))
        {
            map.put("ord", admin.getOrg_id());
        }
        // 判斷是否為第一步
        if (FirstModel.getName().equals(map.get("taskName")))
        {
            map.put("flag", "true");
        }
        return map;
    }
    
    // 根據taskName獲取下一步model
    public ExtTaskModel getNextTask(String name, String processId)
    {
        List<ExtTaskModel> list = engine.process().getProcessById(processId).getModel().getModels(ExtTaskModel.class);
        for (ExtTaskModel extTaskModel : list)
        {
            if (extTaskModel.getName().equals(name))
            {
                if (!extTaskModel.getOutputs().get(0).getTo().equals("end"))
                {
                    return (ExtTaskModel)extTaskModel.getOutputs().get(0).getTarget();
                }
            }
        }
        return null;
    }
}
