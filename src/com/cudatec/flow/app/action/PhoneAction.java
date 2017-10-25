package com.cudatec.flow.app.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.helper.StringHelper;
import org.snaker.engine.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.cudatec.flow.app.dao.CustomDao;
import com.cudatec.flow.framework.dao.TaskManageDao;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.carmanager.dao.CarManagerDao;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.system.action.dao.SysUserDao;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.ClientInfoUtils;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.zcgl.dao.AssetApplyDao;
import com.zqkj.bsm.zcgl.dao.AssetManagerDao;

import net.sf.json.JSONArray;

/** 
* @ClassName: PhoneAction 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author MJ002 
* @date 2016年12月8日 上午9:42:12 
*  
*/
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
@Action(value = "phone")
/**
 * 表单view的统一方法
 * 
 * @author MJ001
 *
 */
public class PhoneAction extends BaseAction {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SnakerEngine engine;

	public void initFlows() {
		// engine.process().deploy(StreamHelper.getStreamFromClasspath("flows/leave.snaker"));
		// engine.process().deploy(StreamHelper.getStreamFromClasspath("flows/borrow.snaker"));
		// engine.process().deploy(StreamHelper.getStreamFromClasspath("flows/reimburce.snaker"));
	}

	public SnakerEngine getEngine() {
		return engine;
	}

	/**
	 * @Title: getUserTask @Description: TODO(获取当前用户所有任务信息) @param 设定文件 @return
	 * void 返回类型 @throws
	 */
	public void getUserTask() {
		String userName = CommonUtil.nullToDefault(request.getParameter("userName"), "");
		int pageNo = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pageNo"), "1")) - 1;
		int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pageSize"), "10"));
		pageNo = pageNo < 0 ? 0 : pageNo;
		pageSize = pageSize < 0 ? 10 : pageSize;
		List<Map<String, Object>> listTotal = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int size = CustomDao.getInstance().JudgeUser(userName);
		if (size == 0) {
			listTotal = CustomDao.getInstance().getUserTask(userName, 0, 1000);
			list = CustomDao.getInstance().getUserTask(userName, pageNo, pageSize);
			for (Map<String, Object> map : list) {
				map.put("ACTION", "phone!recdocPhone.action");
			}

		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("totalSize", listTotal.size());
		result.put("rows", list);
		writeGson(response, result);
	}

	/**
	 * @Title: recdocPhone @Description: TODO(流程任务跳转) @param 设定文件 @return void
	 * 返回类型 @throws
	 */
	public void recdocPhone() {
		String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
		String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
		String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
		String username = CommonUtil.nullToDefault(request.getParameter("userName"), "");
		Admin admin = getAdmin(username);
		// 流程发起
		// 整合需要发送的信息
		Map<String, Object> importentMap = new HashMap<String, Object>();
		List<Map<String, Object>> listUser = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapUser = null;
		List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = facets.chooseStep(processId, orderId, taskId, admin);
		if (map.get("assignee") != null && !map.get("assignee").toString().equals("")) {
			userList = SysUserDao.getInstance().getUserList(null, map.get("assignee").toString(), null);
		} else {
			if (processId.equals("0cfc092d8e44454382fd68d5538656bc")) {
				map.put("assignee", "-2");
			}
		}
		for (Map<String, Object> map2 : userList) {
			mapUser = new HashMap<String, Object>();
			mapUser.put("TRUENAME", map2.get("TRUENAME"));
			mapUser.put("USERNAME", map2.get("USERNAME"));
			mapUser.put("ORG_INFOR", map2.get("ORG_INFOR"));
			listUser.add(mapUser);
		}
		importentMap.put("USERLIST", listUser); // 用户列表
		// 收文
		if (processId.equals("739ba060ba08406abab4aa1fa0ae6346")) {
			importentMap.put("ACTION", "phone!process"); // action
			importentMap.put("CALL", map.get("call")); // 来文文号
			importentMap.put("NUM", map.get("num")); // 标号
			importentMap.put("TITLE", map.get("title")); // 来文标题
			importentMap.put("ORG_NAME", map.get("org_name")); // 来文单位
			importentMap.put("REC_TIME", map.get("rec_time")); // 来文时间
			importentMap.put("PROMOTIONSHOW", map.get("promotionShow")); // 用户列表
			// 发文
		} else if (processId.equals("8289579875e346da8ddc563bacf23081")) {
			importentMap.put("ACTION", "phone!process"); // action
			importentMap.put("DRAFTER", map.get("first_ng")); // 拟稿人
			importentMap.put("ZBCS", map.get("zbcs")); // 主办处室
			importentMap.put("DOCUMENT_TYPE", map.get("document_type")); // 发文类型
			importentMap.put("DOCUMENT_WH", map.get("document_wh")); // 发文文号
			importentMap.put("SFGK", map.get("sfgk")); // 是否公开
			importentMap.put("TITLE", map.get("title")); // 发文标题
			importentMap.put("ZSJG", map.get("first_zsjg")); // 主送机关
			// 水政执法
		} else if (processId.equals("78259d9e98944bbca8cce4315cfe1c85")) {
			importentMap.put("ACTION", "phone!szzfProcess"); // action
			importentMap.put("TRANSID", map.get("transid")); // 编号
			String lxfs = map.get("lxfs").toString(); // 来信方式
			switch (lxfs) {
			case "ldjb":
				importentMap.put("LXFS", "来电举报");
				break;
			case "lxjb":
				importentMap.put("LXFS", "来信举报");
				break;
			case "lfjb":
				importentMap.put("LXFS", "来访举报");
				break;
			default:
				importentMap.put("LXFS", "");
				break;
			}
			String dbfs = map.get("dbfs").toString(); // 督办方式
			switch (dbfs) {
			case "jjgdb":
				importentMap.put("DBFS", "局机关督办");
				break;
			case "sjgdb":
				importentMap.put("DBFS", "市机关督办");
				break;
			case "xcfx":
				importentMap.put("DBFS", "巡查发现");
				break;
			default:
				importentMap.put("DBFS", "");
				break;
			}
			importentMap.put("TIME", map.get("time")); // 受理时间
			importentMap.put("QT", map.get("qt")); // 其他
			importentMap.put("WZNAME", map.get("wzname")); // 违章人
			importentMap.put("WZADDRESS", map.get("wzaddress")); // 违章地址
			importentMap.put("JYAQ", map.get("aqjy")); // 简要案情
			importentMap.put("DCQK", map.get("dcqk") != null ? map.get("dcqk") : ""); // 调查情况
			importentMap.put("CLQK", map.get("clqk") != null ? map.get("clqk") : ""); // 处理情况
			// 车辆申请
		} else if (processId.equals("a24f18f112a2414aae76accbe62a4cc4")) {
			importentMap.put("ACTION", "phone!carProcess"); // action
			importentMap.put("TITLE", map.get("title")); // 申请标题
			importentMap.put("SQCS", map.get("section")); // 申请处室
			importentMap.put("SQR", map.get("name")); // 申请人
			importentMap.put("CAR_NO", map.get("car_no")); // 申请车号
			importentMap.put("JSY", map.get("jsy")); // 驾驶员
			importentMap.put("BTIME", map.get("stime")); // 开始时间
			importentMap.put("ETIME", map.get("etime")); // 结束时间
			importentMap.put("MDD", map.get("mdd")); // 目的地
			importentMap.put("YCSY", map.get("ycsy")); // 用车事由
			importentMap.put("SXRY", map.get("sxry")); // 随行人员
			// 请假申请
		} else if (processId.equals("5ba62e2b649b493d8462b96c5a3e76ce")) {
			importentMap.put("ACTION", "phone!leaveProcess"); // action
			importentMap.put("NAME", map.get("name")); // 申请人
			importentMap.put("TITLE", map.get("title")); // 请假标题
			importentMap.put("QTYPE", map.get("qtype")); // 请假类型
			importentMap.put("BTIME", map.get("stime")); // 开始时间
			importentMap.put("ETIME", map.get("etime")); // 结束时间
			importentMap.put("ALLLEAVE", map.get("allleave")); // 请假天数
			importentMap.put("REMARK", map.get("bz")); // 备注
			// 工作计划
		} else if (processId.equals("0cfc092d8e44454382fd68d5538656bc")) {
			importentMap.put("ACTION", "phone!szzfProcess"); // action
			importentMap.put("YEAR", map.get("yd")); // 年度
			importentMap.put("QUARTER", map.get("jd")); // 季度
			importentMap.put("NAME", map.get("name")); // 姓名
			importentMap.put("WORK", map.get("work")); // 职务
			importentMap.put("SECTION", map.get("section")); // 常州市水利局,处室(办公室)
			importentMap.put("TIME", map.get("gzjhtime")); // 填写时间
			importentMap.put("PLAN", map.get("gzjh")); // 工作计划内容
			importentMap.put("SUMMARY", map.get("gzzj")); // 工作总结内容
			// 部门工作月报表
		} else if (processId.equals("bf79e27c4f4047f0b67ad6e6402c093f")) {
			importentMap.put("ACTION", "phone!szzfProcess"); // action
			importentMap.put("UNIT", map.get("dw")); // 填报单位
			importentMap.put("TIME",
					map.get("year").toString() + "-" + map.get("month").toString() + "-" + map.get("day").toString()); // 填报时间
			importentMap.put("FIRST", map.get("first")); // 姓名
			importentMap.put("SECOND", map.get("second")); // 姓名
			importentMap.put("THIRD", map.get("third")); // 姓名
			importentMap.put("CONTENT", map.get("jsonV")); // 姓名
			// 资产入录管理
		} else if (processId.equals("af57a3325ede4424a4d8f9868cc8c1e3")) {
			importentMap.put("ACTION", "phone!enterProcess"); // action
			String asset_type = map.get("asset_type") != null ? map.get("asset_type").toString() : "";
			switch (asset_type) {
			case "1":
				importentMap.put("CLASSIFY", "房屋及构筑物"); // 资产分类
				break;
			case "2":
				importentMap.put("CLASSIFY", "专用设备");
				break;
			case "3":
				importentMap.put("CLASSIFY", "通用设备");
				break;
			case "4":
				importentMap.put("CLASSIFY", "文物和陈列品");
				break;
			case "5":
				importentMap.put("CLASSIFY", "图书、档案");
				break;
			case "6":
				importentMap.put("CLASSIFY", "家具、用具、装具、动植物");
				break;
			default:
				importentMap.put("CLASSIFY", "");
				break;
			}
			String asset_code = map.get("asset_code") != null ? map.get("asset_code").toString() : "0";
			Map<String, Object> mapCode = CustomDao.getInstance().getZcName(asset_code);
			String asset_name = mapCode.get("ASSETNAME") != null ? mapCode.get("ASSETNAME").toString() : "";
			importentMap.put("ASSETNAME", asset_name); // 资产名称
			importentMap.put("NUMBER", asset_code); // 编号
			importentMap.put("BRAND", map.get("asset_brand")); // 编号
			importentMap.put("PRICE", map.get("asset_price")); // 单价
			importentMap.put("COUNT", map.get("asset_total")); // 数量
			importentMap.put("PURCHASER", map.get("asset_purchaser")); // 采购人
			importentMap.put("TIME", map.get("asset_purchasetime")); // 采购时间
			importentMap.put("RMARK", map.get("asset_remark")); // 备注

			// 资产领用管理
		} else if (processId.equals("830c84eb0c4f456d93f8e072f4695f8d")) {
			importentMap.put("ACTION", "phone!useProcess"); // action
			importentMap.put("NUMBER", map.get("asset_id")); // 资产具体编号
			importentMap.put("CODE", map.get("asset_code")); // 资产编号
			importentMap.put("NAME", map.get("asset_name")); // 资产名称
			importentMap.put("TYPE", map.get("asset_type")); // 资产分类
			importentMap.put("BRAND", map.get("asset_type")); // 品牌
			importentMap.put("PROPOSER", map.get("name")); // 申请人
			importentMap.put("TIME", map.get("asset_applytime")); // 申请时间
			importentMap.put("REMARK", map.get("asset_remark")); // 备注
		}
		importentMap.put("ASSIGNEE", map.get("assignee")); // 下一步权限ID
		importentMap.put("TASKNAME", map.get("taskName")); // 下一步权限名称

		writeGson(response, importentMap);
	}

	/**
	 * @Title: getAdmin @Description: TODO(获取admin) @param @param
	 * username @param @return 设定文件 @return Admin 返回类型 @throws
	 */
	private Admin getAdmin(String username) {
		Admin admin = new Admin();
		Map<String, Object> map = CustomDao.getInstance().getAdminMsg(username);
		admin.setAdminId(Integer.parseInt(map.get("ADMIN_ID").toString()));
		admin.setTruename(map.get("TRUENAME").toString());
		admin.setName(username);
		admin.setOrg_id(Long.parseLong(map.get("ORG_ID").toString()));
		admin.setOrg_name(map.get("ORG_NAME").toString());
		admin.setOrg_infor(map.get("ORG_INFOR").toString());
		return admin;
	}

	/**
	 * @Title: process @Description: TODO(收文处理) @param @throws IOException
	 * 设定文件 @return void 返回类型 @throws
	 */
	public void process() throws IOException {
		ArrayList<Object[]> listObj = new ArrayList<Object[]>();
		String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
		String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
		String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
		String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");
		String method = CommonUtil.nullToDefault(request.getParameter("method"), "0");
		String username = CommonUtil.nullToDefault(request.getParameter("userName"), "0");
		Admin admin = getAdmin(username);
		Enumeration<String> paraNames = request.getParameterNames();
		// 流程中保存的数据要少，只要保存标题和步骤就行
		Map<String, Object> params_title = this.dealParams(paraNames, "title");
		params_title.put("ip", ClientInfoUtils.getIP(request));
		Enumeration<String> paraNames_ = request.getParameterNames();
		// 表单中保存的数据要多，所有数据都要保存
		Map<String, Object> params_all = this.dealParams(paraNames_, null);
		// 对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
		Map<String, Object> newParams = changeParam(params_all, taskName);
		newParams.put(taskName + "Deal", admin.getTruename());
		// 下步流程的taskId
		String newTaskId = null;
		int tasktype = 0;
		String displayName = "";
		String status = "0";
		String sql = null;
		Map<String, Object> startMap = null;
		if (!StringHelper.isEmpty(orderId) && !StringHelper.isEmpty(taskId) && !StringHelper.isEmpty(processId)) {
			// 获取当前taskID的状态
			String taskStatus = TaskStatus(orderId, taskId, admin);
			if (taskStatus.equals("repeat")) {
				response.getWriter().write(taskStatus);
				return;
			} else if (taskStatus.equals("over")) {
				response.getWriter().write(taskStatus);
				return;
			} else {
				List<Task> task = null;
				List<HistoryTask> list = null;
				Process process = facets.getEngine().process().getProcessById(processId);
				List<TaskModel> models = process.getModel().getModels(TaskModel.class);
				for (TaskModel model : models) {
					if (model.getName().equals(taskName)) {
						if (model.getPerformType().equals("ALL")) {
							tasktype = 1;
						}
						  //此处步骤使用会签的数据保存方式，仅此而已
            	        if(model.getOutputs().get(0).getTo().equals("end")){
            	        	tasktype = 2;
            	        }
            	        break;
					}
				}

				if (tasktype == 1 || tasktype == 2) {
					// 会签模式
					Map<String, Object> taskParam = new HashMap<String, Object>();
					List<Map<String, Object>> listForm = TaskManageDao.getInstance().getCounterJson(taskName, orderId);
					List<Map<String, Object>> listSuggest = new ArrayList<Map<String, Object>>();
					Map<String, Object> mapV = new HashMap<>();
					mapV.put("uid", admin.getAdminId());
					mapV.put("truename", admin.getTruename());
					mapV.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					mapV.put("method", method);
					mapV.put("suggest",
							newParams.get(taskName + "suggest") == null ? "" : newParams.get(taskName + "suggest"));
					JSONArray jsonArray = null;
					if (listForm.size() == 0) {
						sql = BATCH_SQL;
						listSuggest.add(mapV);
						jsonArray = JSONArray.fromObject(listSuggest);
						taskParam.put(taskName, jsonArray.toString());
						listObj = TaskManageDao.getInstance().save(taskParam, orderId, taskId);
					} else {
						boolean flag = true;
						sql = UPDATE_SQL;
						String json = listForm.get(0).get("HTML_VALUE").toString();
						jsonArray = JSONArray.fromObject(json);
						listSuggest = (List<Map<String, Object>>) JSONArray.toCollection(jsonArray, HashMap.class);
						for (Map<String, Object> mapSuggest : listSuggest) {
							if (mapSuggest.get("uid").toString().equals(String.valueOf(admin.getAdminId()))) {
								mapSuggest.put("method", method);
								mapSuggest.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
								mapSuggest.put("suggest", newParams.get(taskName + "suggest") == null ? ""
										: newParams.get(taskName + "suggest"));
								flag = false;
							}
						}
						if (flag) {
							listSuggest.add(mapV);
						}
						jsonArray = JSONArray.fromObject(listSuggest);
						taskParam.put(taskName, jsonArray.toString());
						listObj = TaskManageDao.getInstance().update(taskParam, orderId);
					}

				} else {
					// 串行模式
					list = facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
					for (HistoryTask historyTask : list) {
						// 判断当前流程是否执行过
						if (historyTask.getTaskName().equals(taskName)) {
							status = "1";
						}
					}

				}
				// 第一步用户不用保存意见
				startMap = getFirstStep(orderId);
				if (!admin.getName().equals(startMap.get("operator"))) {
					params_title.put("method", method);
				}
				// 領導同意，或下一步流程
				if (method.equals("0")) {
					task = facets.execute(taskId, admin.getName(), params_title);
				} else {
					// 領導不同意
					String parentId = null;
					startMap = getFirstStep(orderId);
					task = facets.executeAndJump(taskId, admin.getName(), params_title,
							startMap.get("taskName").toString());
					facets.addTaskActor(task.get(0).getId(), 0, new String[] { startMap.get("operator").toString() });
					List<HistoryTask> lihst = facets.getEngine().query()
							.getHistoryTasks(new QueryFilter().setOrderId(orderId).setTaskId(taskId));
					// 这里还是很奇怪，你麻痹，精确查找还是只能所有记录，这个snaker有问题啊，艹
					for (HistoryTask historyTask : lihst) {
						if (historyTask.getId().equals(taskId)) {
							parentId = historyTask.getParentTaskId();
						}
					}
					// 删除会签任务
					TaskManageDao.getInstance().del(parentId);

				}

				// 当上一步流程用户为空时，调用
				if (params_all.get("assignee") != null && params_all.get("assignee").toString().equals("-2")) {
					list = facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
					for (HistoryTask historyTask : list) {
						if (historyTask.getParentTaskId().equals("start")) {
							params_all.put("userList", historyTask.getOperator() + ",");
						}
					}
				}

				// 审批完成后，去掉提醒
				manager.updateMessage(admin.getName(), taskId, orderId);
				newTaskId = (task.size() != 0) ? task.get(0).getId() : "-1";
				displayName = (task.size() != 0) ? task.get(0).getDisplayName() : "";
			}

		}
		// 非会签模式保存数据方式,会签方式不适用
		if (tasktype != 1 && tasktype != 2) {
			if (status.equals("0")) {
				sql = BATCH_SQL;
				listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
			} else {
				sql = UPDATE_SQL;
				listObj = TaskManageDao.getInstance().update(newParams, orderId);
			}
		}

		manager.executeBatch_Pre(sql, listObj);
		// 获取不同的保存地址
		String actionPath = null;
		if (processId.equals("739ba060ba08406abab4aa1fa0ae6346")) { // 收文
			actionPath = "recdoc!alldocumet.action";
		} else { // 发文
			actionPath = "recdoc!alldocumet.action";
		}

		if (!newTaskId.equals("-1")) {
			if (method.equals("0")) {
				if (tasktype == 1) {
					// 清除会签之前不同意的意见(第一次会签参与并不同意，第二次没参与的领导)
				}
				// 去除下一步流程的权限ID记录
				TaskManageDao.getInstance().DeActorId(newTaskId);
				// 用户流程添加
				facets.getEngine().task().addTaskActor(newTaskId, 0, newParams.get("userList").toString().split(","));
				// 普通步骤进行流程提醒
				addMsg(newParams.get("userList").toString(), orderId, newTaskId, processId, actionPath, displayName);
			} else {
				addMsg(startMap.get("operator").toString(), orderId, newTaskId, processId, actionPath, displayName);
			}
		} else {
			if(tasktype == 2){
	        		Map<String, Object> list_form = TaskManageDao.getInstance().getForm_List(orderId);
	        		List<Object> list = new ArrayList<Object>();
	        		list.add(orderId); //orderId
	        		list.add(list_form.get("rec_time")); //来文日期
	        		list.add(list_form.get("num")); //文号
	        		list.add(list_form.get("title"));//标题	
	        		list.add(list_form.get("path_plann_file"));//文件保存地址
	        		list.add(0); //类型：收文
	        		list.add(list_form.get("bcqx"));  //保存期限
	        		list.add(list_form.get("promotionShow"));  //文件名称
	        		list.add(list_form.get("call"));  //收文类型
	        		list.add(0);
	        		TaskManageDao.getInstance().addFlowGWGD(list);
			}
		}
		response.getWriter().write("success");
	}

	public void szzfProcess() throws IOException {
		ArrayList<Object[]> listObj = new ArrayList<Object[]>();
		String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
		String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
		String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
		String title = CommonUtil.nullToDefault(request.getParameter("title"), "");
		String method = CommonUtil.nullToDefault(request.getParameter("method"), "0");
		String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");
		String username = CommonUtil.nullToDefault(request.getParameter("userName"), "");
		Admin admin = getAdmin(username);
		Map<String, String> map = null;
		Enumeration<String> paraNames = request.getParameterNames();
		Map<String, Object> params = this.dealParams(paraNames, null);
		Map<String, Object> params_title = new HashMap<String, Object>();
		params_title.put("ip", ClientInfoUtils.getIP(request));
		// 下步流程的taskId
		String newTaskId = null;
		String displayName = "";
		boolean flag = true; // true ，新增，false，编辑
		String sql = BATCH_SQL;
		if (StringHelper.isEmpty(orderId)) {
			// 第一步流程发起
			params_title.put("title", admin.getTruename() + "_" + title);
			params_title.put("path", "szzfView");
			map = facets.startAndExecute(processId, admin.getName(), params_title);
			orderId = map.get("orderId");
			taskId = map.get("taskId");
			newTaskId = map.get("newTaskId");
			// listObj = TaskManageDao.getInstance().save(params_all, orderId,
			// taskId);
		} else {
			List<HistoryTask> listHIs = facets.getEngine().query()
					.getHistoryTasks(new QueryFilter().setOrderId(orderId));
			// 这里很奇怪，参数给的很详细还是不能查出指定数据，看来只能遍历，艹
			for (HistoryTask historyTask : listHIs) {
				if (historyTask.getId().equals(taskId)) {
					// 审批完成后，去掉提醒
					manager.updateMessage(admin.getName(), taskId, orderId);
					response.getWriter().write("repeat");
					return;
				}
			}

			if (method.equals("-1")) {
				// 获取流程发起人名称，和第一步流程名称
				Map<String, Object> startMap = getFirstStep(orderId);
				params.put("taskBack", startMap.get("taskName"));
			}

			// 执行下一步流程
			map = nextFlowHandle(orderId, taskId, taskName, params, method, admin, processId);
			flag = map.get("status").equals("0") ? true : false;
		}

		// 最新流程Id
		newTaskId = map.get("newTaskId");
		// 当前流程名称
		displayName = map.get("displayName");

		// 对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
		Map<String, Object> newParams = changeParam(params, taskName);

		if (flag) {
			sql = BATCH_SQL;
			listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
		} else {
			sql = UPDATE_SQL;
			listObj = TaskManageDao.getInstance().update(newParams, orderId);
		}
		// 表单数据保存至flow_form
		manager.executeBatch_Pre(sql, listObj);
		// 流程结束时，newTaskId为-1
		String actionPath = null;
		if (processId.equals("78259d9e98944bbca8cce4315cfe1c85 ")) {// 水政执法
			actionPath = "szzf!allSzzf.action";
		} else if (processId.equals("0cfc092d8e44454382fd68d5538656bc ")) {// 工作计划
			actionPath = "plan!allPlan.action";
		} else {
			actionPath = "bmgz!allBmgz.action";
		}

		if (!newTaskId.equals("-1")) {
			if (method.equals("0")) {
				// 去除下一步流程的权限ID记录
				facets.getEngine().task().removeTaskActor(newTaskId,
						new String[] { newParams.get("assignee").toString() });
				// 用户流程添加
				facets.getEngine().task().addTaskActor(newTaskId, 0, newParams.get("userList").toString().split(","));
				// 普通步骤进行流程提醒
				addMsg(newParams.get("userList").toString(), orderId, newTaskId, processId, actionPath, displayName);
			} else {
				addMsg(map.get("startUser"), orderId, newTaskId, processId, actionPath, displayName);
			}
		}
		response.getWriter().write("success");
	}

	/**
	 * 工作流流转的主要处理方法
	 * 
	 * @throws IOException
	 */
	public void carProcess() throws IOException {
		ArrayList<Object[]> listObj = new ArrayList<Object[]>();
		String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
		String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
		String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
		String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");
		String method = CommonUtil.nullToDefault(request.getParameter("method"), "0");
		String step = CommonUtil.nullToDefault(request.getParameter("step"), "0");
		String username = CommonUtil.nullToDefault(request.getParameter("userName"), "0");
		Admin admin = getAdmin(username);
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> paraNames = request.getParameterNames();
		Map<String, Object> params = this.dealParams(paraNames, null);
		// 下步流程的taskId
		String newTaskId = null;
		String displayName = null;
		String sql = null;
		boolean flag = true;
		boolean car_status = true;
		List<Map<String, Object>> list = CarManagerDao.getInstance().queryCarInfoByCarStatus();
		if (StringHelper.isEmpty(orderId)) {
			// 判断车辆的状态是否空闲,如果被占用了，直接返回失败
			if (list == null || list.size() == 0) {
				car_status = false;
			}
			if (car_status) {
				// 执行第一步流程
				Map<String, Object> mapTitle = new HashMap<String, Object>();
				mapTitle.put("title", params.get("title"));
				mapTitle.put("path", "carView");
				map = facets.startAndExecute(processId, admin.getName(), mapTitle);
				orderId = map.get("orderId");
				taskId = map.get("taskId");
				params.put("name", admin.getTruename()); // 用户
				params.put("section", admin.getOrg_name()); // 部门
				params.put("work", admin.getOrg_infor());// 职务
				CarManagerDao.getInstance().updateCarInfo(CommonUtil.nullToDefault(params.get("car_no"), ""), "2");
			}
		} else {// 获取当前taskID的状态
			String taskStatus = TaskStatus(orderId, taskId, admin);
			if (taskStatus.equals("repeat")) {
				response.getWriter().write(taskStatus);
				return;
			} else if (taskStatus.equals("over")) {
				response.getWriter().write(taskStatus);
				return;
			} else {
				// 执行下一步流程
				map = nextFlowHandle(orderId, taskId, taskName, params, method, admin, processId);
				// 说明流程被回退过
				if (step.equals("1")) {
					CarManagerDao.getInstance().updateCarInfo(CommonUtil.nullToDefault(params.get("car_no"), ""), "2");
				}
				flag = map.get("status").equals("0") ? true : false;
				if (!method.equals("0")) {
					Map<String, Object> map_ = TaskManageDao.getInstance().getForm_List(orderId);
					CarManagerDao.getInstance().updateCarInfo(CommonUtil.nullToDefault(map_.get("car_no"), ""), "1");
				}
			}
		}
		if (car_status) {
			// 最新流程Id
			newTaskId = map.get("newTaskId");
			// 当前流程名称
			displayName = map.get("displayName");

			// 对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
			Map<String, Object> newParams = changeParam(params, taskName);

			if (flag) {
				sql = BATCH_SQL;
				listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
			} else {
				sql = UPDATE_SQL;
				listObj = TaskManageDao.getInstance().update(newParams, orderId);
			}
			// 保存表单信息到flow_form
			manager.executeBatch_Pre(sql, listObj);
			// 流程结束时，newTaskId为-1
			if (!newTaskId.equals("-1")) {
				if (method.equals("0")) {
					// 去除下一步流程的权限ID记录
					facets.getEngine().task().removeTaskActor(newTaskId,
							new String[] { newParams.get("assignee").toString() });
					// 用户流程添加
					facets.getEngine().task().addTaskActor(newTaskId, 0,
							newParams.get("userList").toString().split(","));
					// 普通步骤进行流程提醒
					addMsg(newParams.get("userList").toString(), orderId, newTaskId, processId,
							"carManager!allCar.action", displayName);
				} else {
					addMsg(map.get("startUser"), orderId, newTaskId, processId, "carManager!allCar.action",
							displayName);
				}
			}

			response.getWriter().write("success");
		} else {
			response.getWriter().write("fail");
		}
	}

	/**
	 * @Title: leaveProcess @Description: TODO(请假提交) @param @throws IOException
	 * 设定文件 @return void 返回类型 @throws
	 */
	public void leaveProcess() throws IOException {
		ArrayList<Object[]> listObj = new ArrayList<Object[]>();
		String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
		String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
		String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
		String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");
		String method = CommonUtil.nullToDefault(request.getParameter("method"), "0");
		String username = CommonUtil.nullToDefault(request.getParameter("userName"), "0");
		Admin admin = getAdmin(username);
		String ipaddr = ClientInfoUtils.getIP(request);
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> paraNames = request.getParameterNames();
		Map<String, Object> params = this.dealParams(paraNames, null);
		Map<String, Object> mapTitle = new HashMap<String, Object>();
		mapTitle.put("ip", ipaddr);
		// 下步流程的taskId
		String newTaskId = null;
		// 下步流程名称
		String displayName = null;
		String sql = null;
		// 状态 0 ，流程第一次发起，1，流程第N次发起
		String status = "0";
		// 第一步流程信息
		Map<String, Object> startMap = null;
		if (StringHelper.isEmpty(orderId)) {
			// 执行第一步流程
			mapTitle.put("title", params.get("title"));
			mapTitle.put("path", "leaveView");
			mapTitle.put("result", params.get("result"));
			map = facets.startAndExecute(processId, admin.getName(), mapTitle);
			orderId = map.get("orderId");
			taskId = map.get("taskId");
			newTaskId = map.get("newTaskId");
			displayName = map.get("displayName");
			params.put("name", admin.getTruename()); // 用户
			params.put("section", admin.getOrg_name());// 部门
			params.put("work", admin.getOrg_infor());// 职务
			params.put("uid", admin.getAdminId());// 用户id
		} else {
			List<HistoryTask> listHIs = facets.getEngine().query()
					.getHistoryTasks(new QueryFilter().setOrderId(orderId));
			// 这里很奇怪，参数给的很详细还是不能查出指定数据，看来只能遍历，艹
			for (HistoryTask historyTask : listHIs) {
				if (historyTask.getId().equals(taskId)) {
					// 审批完成后，去掉提醒
					manager.updateMessage(admin.getName(), taskId, orderId);
					response.getWriter().write("repeat");
					return;
				}
			}

			startMap = getFirstStep(orderId);
			if (!admin.getName().equals(startMap.get("operator"))) {
				mapTitle.put("method", method);
			}
			List<Task> task = null;
			List<HistoryTask> list = facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
			for (HistoryTask historyTask : list) {
				// 判断当前流程是否执行过
				if (historyTask.getTaskName().equals(taskName)) {
					status = "1";
				}
			}

			// 領導同意，或下一步流程
			if (method.equals("0")) {
				task = facets.execute(taskId, admin.getName(), mapTitle);
			} else {
				// 領導不同意
				task = facets.executeAndJump(taskId, admin.getName(), mapTitle, startMap.get("taskName").toString());
				facets.addTaskActor(task.get(0).getId(), 0, new String[] { startMap.get("operator").toString() });
			}
			// 审批完成后，去掉提醒
			manager.updateMessage(admin.getName(), taskId, orderId);
			newTaskId = (task.size() != 0) ? task.get(0).getId() : "-1";
			displayName = (task.size() != 0) ? task.get(0).getDisplayName() : "";
		}

		// 对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
		Map<String, Object> newParams = changeParam(params, taskName);

		if (status.equals("0")) {
			sql = BATCH_SQL;
			listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
		} else {
			sql = UPDATE_SQL;
			listObj = TaskManageDao.getInstance().update(newParams, orderId);
		}
		// 保存表单信息
		manager.executeBatch_Pre(sql, listObj);
		// 流程结束时，newTaskId为-1
		if (!newTaskId.equals("-1")) {
			if (method.equals("0")) {
				// 去除下一步流程的权限ID记录
				TaskManageDao.getInstance().DeActorId(newTaskId);
				// 用户流程添加
				facets.getEngine().task().addTaskActor(newTaskId, 0, newParams.get("userList").toString().split(","));
				// 普通步骤进行流程提醒
				addMsg(newParams.get("userList").toString(), orderId, newTaskId, processId, "leave!allLeave.action",
						displayName);
			} else {
				addMsg(startMap.get("operator").toString(), orderId, newTaskId, processId, "leave!allLeave.action",
						displayName);
			}
		} else {
			// 流程结束保存数据
			Map<String, Object> AllResult = TaskManageDao.getInstance().getForm_List(orderId);

			TaskManageDao.getInstance().updateUserleave(AllResult);
		}
		response.getWriter().write("success");
	}

	/**
	 * 工作流流转的主要处理方法
	 * 
	 * @throws IOException
	 */
	public void enterProcess() throws IOException {
		ArrayList<Object[]> listObj = new ArrayList<Object[]>();
		String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");// 获取流程ID
		String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");// 获取流程实例ID
		String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");// 获取步骤ID
		String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");// 获取当前步骤名称
		String method = CommonUtil.nullToDefault(request.getParameter("method"), "0");// 同意0/不同意-1
																						// 默认0同意
		String username = CommonUtil.nullToDefault(request.getParameter("userName"), "");
		Admin admin = getAdmin(username);
		String ipaddr = ClientInfoUtils.getIP(request);// 获取用户IP地址
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> paraNames = request.getParameterNames();// 获取页面参数并序列化
		Map<String, Object> params = this.dealParams(paraNames, null);// 把页面获取的参数转存MAP类型
		Map<String, Object> mapTitle = new HashMap<String, Object>();// 流程详情MAP
		mapTitle.put("ip", ipaddr);// 存入用户IP

		String newTaskId = null; // 下步流程的Id

		String displayName = null; // 下步流程名称
		String sql = null;

		String status = "0"; // 状态 0 ，流程第一次发起，1，流程第N次发起

		Map<String, Object> startMap = null;// 第一步流程信息
		if (StringHelper.isEmpty(orderId)) {
			// 执行第一步流程
			mapTitle.put("title", params.get("title"));// 存入标题
			mapTitle.put("path", "assetinputinfo");// 存入流程详情表单页面路径
			map = facets.startAndExecute(processId, admin.getName(), mapTitle);// 获取当前流程步骤的信息
			orderId = map.get("orderId");
			taskId = map.get("taskId");
			newTaskId = map.get("newTaskId");
			displayName = map.get("displayName");
			params.put("name", admin.getTruename()); // 用户
			params.put("section", admin.getOrg_name()); // 部门
			params.put("work", admin.getOrg_infor());// 职务
			params.put("uid", admin.getAdminId());// 用户id
		} else { // 获取历史纪录
			List<HistoryTask> listHIs = facets.getEngine().query()
					.getHistoryTasks(new QueryFilter().setOrderId(orderId));
			// 这里很奇怪，参数给的很详细还是不能查出指定数据，看来只能遍历，艹
			for (HistoryTask historyTask : listHIs) {
				// 循环历史纪录，如果其他领导审批了，就返回repeat(其他领导不用再审批了，在需要多个领导审批时使用)
				if (historyTask.getId().equals(taskId)) {
					response.getWriter().write("repeat");
					return;
				}
			}
			// 获取流程发起人名称，和第一步流程名称
			startMap = getFirstStep(orderId);
			if (!admin.getName().equals(startMap.get("operator"))) {
				mapTitle.put("method", method);
			}
			List<Task> task = null;
			// 从his_task获取历史步骤纪录
			List<HistoryTask> list = facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
			// 遍历历史纪录
			for (HistoryTask historyTask : list) {
				// 判断当前步骤是否有历史纪录，有则将状态改为1
				if (historyTask.getTaskName().equals(taskName)) {
					status = "1";
				}
			}
			// 如果领导同意，执行下一步流程
			if (method.equals("0")) {
				task = facets.execute(taskId, admin.getName(), mapTitle);
			} else {
				// 領導不同意，流程跳转（打回）到发起人
				task = facets.executeAndJump(taskId, admin.getName(), mapTitle, startMap.get("taskName").toString());
				facets.addTaskActor(task.get(0).getId(), 0, new String[] { startMap.get("operator").toString() });
			}
			// 审批完成后，去掉提醒
			manager.updateMessage(admin.getName(), taskId, orderId);
			// 如果流程的当前步骤不为最后一步，取当前步骤的ID，如果为最后一步，下一步骤ID取—1（表示流程结束）
			newTaskId = (task.size() != 0) ? task.get(0).getId() : "-1";
			displayName = (task.size() != 0) ? task.get(0).getDisplayName() : "";
		}

		// 对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
		Map<String, Object> newParams = changeParam(params, taskName);
		// 如果状态值为0插入数据到表flow_form,否则更新数据
		if (status.equals("0")) {
			sql = BATCH_SQL;
			listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
		} else {
			sql = UPDATE_SQL;
			listObj = TaskManageDao.getInstance().update(newParams, orderId);
		}
		// 保存表单信息到flow_form
		manager.executeBatch_Pre(sql, listObj);

		AssetManagerDao.getInstance().deleteAssetByStatus(orderId);

		Map<String, Object> dataMap = TaskManageDao.getInstance().getForm_List(orderId);
		int sum = Integer.parseInt(dataMap.get("asset_total").toString().trim());
		for (int i = 0; i < sum; i++) {
			// 从AllResult获取需要的录入的数据，放到新的map
			Map<String, Object> datamap = new HashMap<String, Object>();
			datamap.put("asset_code", dataMap.get("asset_code"));
			datamap.put("asset_type", dataMap.get("asset_type"));
			datamap.put("asset_price", dataMap.get("asset_price"));
			datamap.put("asset_purchasetime", dataMap.get("asset_purchasetime"));
			datamap.put("asset_purchaser", dataMap.get("asset_purchaser"));
			datamap.put("asset_brand", dataMap.get("asset_brand"));
			datamap.put("asset_remark", dataMap.get("asset_remark"));
			datamap.put("status", "0");
			datamap.put("orderId", orderId);
			// 根据录入的数量插入N条数据数据
			AssetManagerDao.getInstance().addAssetDetailInfo(datamap);
		}

		// 如果下一步ID不为—1
		if (!newTaskId.equals("-1")) {
			// 如果领导同意
			if (method.equals("0")) {
				// 去除下一步流程的权限ID记录
				TaskManageDao.getInstance().DeActorId(newTaskId);
				// 用户流程添加
				facets.getEngine().task().addTaskActor(newTaskId, 0, newParams.get("userList").toString().split(","));
				// 普通步骤进行流程提醒
				addMsg(newParams.get("userList").toString(), orderId, newTaskId, processId,
						"assetmanager!startFlow.action", displayName);
			} else {
				// 如果领导不同意
				addMsg(startMap.get("operator").toString(), orderId, newTaskId, processId,
						"assetmanager!startFlow.action", displayName);

			}
		} else {
			// 流程结束，取出所有表单数据
			Map<String, Object> AllResult = TaskManageDao.getInstance().getForm_List(orderId);
			// TaskManageDao.getInstance().updateUserleave(AllResult);

			// uid=1, work=处长, asset_price=1, asset_name=11, asset_brand=1,
			// rect3method=0, asset_total=1, section=常州市水利局,处室(办公室),
			// asset_purchasetime=2016-05-25,
			// time=2016/05/25 13:28:23, title=资产录入, asset_code=11, name=系统管理员,
			// asset_type=1, asset_remark=11, rect3suggest=aaa,
			// asset_purchaser=1
			AssetManagerDao.getInstance().updateStatus(orderId, "1");

		}
		// 返回前台success
		response.getWriter().write("success");
	}

	/**
	 * @Title: useProcess @Description: TODO(资产领用) @param @throws IOException
	 * 设定文件 @return void 返回类型 @throws
	 */
	public void useProcess() throws IOException {
		ArrayList<Object[]> listObj = new ArrayList<Object[]>();
		String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");// 获取流程ID
		String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");// 获取流程实例ID
		String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");// 获取步骤ID
		String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");// 获取当前步骤名称
		String method = CommonUtil.nullToDefault(request.getParameter("method"), "0");// 同意0/不同意-1
																						// 默认0同意
		String username = CommonUtil.nullToDefault(request.getParameter("userName"), "");// 同意0/不同意-1
																							// 默认0同意
		Admin admin = getAdmin(username);
		String ipaddr = ClientInfoUtils.getIP(request);// 获取用户IP地址
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> paraNames = request.getParameterNames();// 获取页面参数并序列化
		Map<String, Object> params = this.dealParams(paraNames, null);// 把页面获取的参数转存MAP类型
		Map<String, Object> mapTitle = new HashMap<String, Object>();// 流程详情MAP
		mapTitle.put("ip", ipaddr);// 存入用户IP

		String newTaskId = null; // 下步流程的Id

		String displayName = null; // 下步流程名称
		String sql = null;

		String status = "0"; // 状态 0 ，流程第一次发起，1，流程第N次发起

		Map<String, Object> startMap = null;// 第一步流程信息
		if (StringHelper.isEmpty(orderId)) {
			// 执行第一步流程
			mapTitle.put("title", params.get("title"));// 存入标题
			mapTitle.put("path", "assetapplyinfo");// 存入流程详情表单页面路径
			map = facets.startAndExecute(processId, admin.getName(), mapTitle);// 执行第一步流程产生OrderId
			orderId = map.get("orderId");
			taskId = map.get("taskId");
			newTaskId = map.get("newTaskId");
			displayName = map.get("displayName");
			params.put("name", admin.getTruename()); // 用户
			params.put("section", admin.getOrg_name()); // 部门
			params.put("work", admin.getOrg_infor());// 职务
			params.put("uid", admin.getAdminId());// 用户id
		} else { // 获取历史纪录
			List<HistoryTask> listHIs = facets.getEngine().query()
					.getHistoryTasks(new QueryFilter().setOrderId(orderId));
			// 这里很奇怪，参数给的很详细还是不能查出指定数据，看来只能遍历，艹
			for (HistoryTask historyTask : listHIs) {
				// 循环历史纪录，如果其他领导审批了，就返回repeat(其他领导不用再审批了，在需要多个领导审批时使用)
				if (historyTask.getId().equals(taskId)) {
					response.getWriter().write("repeat");
					return;
				}
			}
			// 获取流程发起人名称，和第一步流程名称
			startMap = getFirstStep(orderId);
			if (!admin.getName().equals(startMap.get("operator"))) {
				mapTitle.put("method", method);
			}
			List<Task> task = null;
			// 从his_task获取历史步骤纪录
			List<HistoryTask> list = facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
			// 遍历历史纪录
			for (HistoryTask historyTask : list) {
				// 判断当前步骤是否有历史纪录，有则将状态改为1
				if (historyTask.getTaskName().equals(taskName)) {
					status = "1";
				}
			}
			// 如果领导同意，执行下一步流程
			if (method.equals("0")) {

				task = facets.execute(taskId, admin.getName(), mapTitle);
			} else {
				// 領導不同意，流程跳转（打回）到发起人
				task = facets.executeAndJump(taskId, admin.getName(), mapTitle, startMap.get("taskName").toString());
				facets.addTaskActor(task.get(0).getId(), 0, new String[] { startMap.get("operator").toString() });
			}
			// 审批完成后，去掉提醒
			manager.updateMessage(admin.getName(), taskId, orderId);
			// 如果流程的当前步骤不为最后一步，取当前步骤的ID，如果为最后一步，下一步骤ID取—1（表示流程结束）
			newTaskId = (task.size() != 0) ? task.get(0).getId() : "-1";
			displayName = (task.size() != 0) ? task.get(0).getDisplayName() : "";
		}

		// 对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
		Map<String, Object> newParams = changeParam(params, taskName);
		// 如果状态值为0插入数据到表flow_form,否则更新数据
		if (status.equals("0")) {
			sql = BATCH_SQL;
			listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
		} else {
			sql = UPDATE_SQL;
			listObj = TaskManageDao.getInstance().update(newParams, orderId);
		}
		// 保存表单信息到flow_form
		manager.executeBatch_Pre(sql, listObj);
		Map<String, Object> getData = TaskManageDao.getInstance().getForm_List(orderId);
		String uid = getData.get("asset_id").toString().trim();
		AssetApplyDao.getInstance().updateUserAndStatusById(null, "2", uid);
		// 如果下一步ID不为—1
		if (!newTaskId.equals("-1")) {
			// 如果领导同意
			if (method.equals("0")) {
				// 去除下一步流程的权限ID记录
				TaskManageDao.getInstance().DeActorId(newTaskId);
				// 用户流程添加
				facets.getEngine().task().addTaskActor(newTaskId, 0, newParams.get("userList").toString().split(","));
				// 普通步骤进行流程提醒
				addMsg(newParams.get("userList").toString(), orderId, newTaskId, processId,
						"assetapply!startFlow.action", displayName);
			} else {
				// 如果领导不同意
				addMsg(startMap.get("operator").toString(), orderId, newTaskId, processId,
						"assetapply!startFlow.action", displayName);
				AssetApplyDao.getInstance().updateUserAndStatusById(null, "1", uid);
			}
		} else {
			// 流程结束，取出所有表单数据
			Map<String, Object> AllResult = TaskManageDao.getInstance().getForm_List(orderId);
			// TaskManageDao.getInstance().updateUserleave(AllResult);

			// uid=1, work=处长, asset_price=1, asset_name=11, asset_brand=1,
			// rect3method=0, asset_total=1, section=常州市水利局,处室(办公室),
			// asset_purchasetime=2016-05-25,
			// time=2016/05/25 13:28:23, title=资产录入, asset_code=11, name=系统管理员,
			// asset_type=1, asset_remark=11, rect3suggest=aaa,
			// asset_purchaser=1
			String id = AllResult.get("asset_id").toString().trim();
			String user = AllResult.get("asset_user").toString().trim();

			AssetApplyDao.getInstance().updateUserAndStatusById(user, "3", id);
		}
		// 返回前台success
		response.getWriter().write("success");
	}

	public void download() throws IOException {
		String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");// 获取流程实例ID
		Map<String, Object> obj = CustomDao.getInstance().getDownloadFlow_form(orderId);
		if (obj != null) {
			String path = obj.get("HTML_VALUE").toString();
			String newPath = path.replace("@","/"); 
			File packageZip = new File(newPath);
			log.info("开始下载订单的源文件path=" + newPath);
			// 渲染的源文件名
			String fileName = "";
			long file_length = 0;
			if (packageZip.exists()) {
				// 文件的大小
				file_length = packageZip.length();
				fileName = packageZip.getName();
				// 将获取的数据当成文件下载
				getSrcStrem(packageZip, fileName, file_length);
			}
		}

	}

	private void getSrcStrem(File path, String fileName, long file_length) throws IOException {
		// 清空response
		response.reset();

		// 设置response的头部
		response.addHeader("Content-Disposition",
				"attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));  
		response.addHeader("Content-Length", "" + file_length);
		response.setContentType("appication/octet-stream");
		BufferedOutputStream output = null;
		BufferedInputStream bfs = null;
		try {
			bfs = new BufferedInputStream(new FileInputStream(path));
			// 新建文件输入输出流对象
			output = new BufferedOutputStream(response.getOutputStream());
			// 设置每次写入缓存大小
			byte[] b = new byte[2048];
			int bytesindex;
			while (-1 != (bytesindex = bfs.read(b, 0, b.length))) {
				output.write(b, 0, bytesindex);
			}
			output.flush(); 
		} catch (IOException e) {
			// e.printStackTrace();
			log.info("下载发生异常" + e.getMessage());
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				output = null;
			}
			if (bfs != null) {
				try {
					bfs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				bfs = null;
			}

		}
		response.flushBuffer();// 强行将响应缓存中的内容发送到目的地
	}
	
	
	
	public void szzf(){
		 String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
		 Map<String, Object> map = new HashMap<String,Object>();
		 List<Map<String, Object>> userList = new ArrayList<Map<String,Object>>();
		 List<TaskModel> list = engine.process().getProcessById(processId).getModel().getModels(TaskModel.class);
		 String assignee = null;
		 for (TaskModel taskModel : list) {
			if(taskModel.getName().equals("zdbyj")){
				assignee = taskModel.getAssignee();
				break;
			}
		 }
		 if (assignee!= null && !assignee.equals("")) {
			 	List<Map<String, Object>> user = SysUserDao.getInstance().getUserList(null,assignee, null);
				Map<String, Object> mapUser = null;
				for (Map<String, Object> map2 : user) {
					mapUser = new HashMap<String, Object>();
					mapUser.put("TRUENAME", map2.get("TRUENAME"));
					mapUser.put("USERNAME", map2.get("USERNAME"));
					mapUser.put("ORG_INFOR", map2.get("ORG_INFOR"));
					userList.add(mapUser);
				}
		 } 
		 map.put("USERLIST", userList);
	     map.put("TRANSID", BHUtil.getTransID());	//编号
	 	writeGson(response, map);
	}
	
	public void szzfStartProcess() throws IOException {
	 		ArrayList<Object[]> listObj = new ArrayList<Object[]>();
		 	String processId = "78259d9e98944bbca8cce4315cfe1c85";
		 	String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
		 	String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
		 	String title = CommonUtil.nullToDefault(request.getParameter("title"), "");
		 	String method = CommonUtil.nullToDefault(request.getParameter("method"), "0");
		 	String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");
		 	String userName = CommonUtil.nullToDefault(request.getParameter("userName"), "");
		 	Admin admin = getAdmin(userName);
	        Map<String, String> map = null;
	        Enumeration<String> paraNames = request.getParameterNames();
	        Map<String, Object> params = this.dealParams(paraNames,null);       
	        Map<String, Object> params_title = new HashMap<String, Object>();
	        params_title.put("ip", ClientInfoUtils.getIP(request));
	        //下步流程的taskId
	        String newTaskId = null;
	        String displayName= "";
	        boolean flag = true; //true ，新增，false，编辑
	        String sql = BATCH_SQL;
	        if (StringHelper.isEmpty(orderId)) 
	        {	
	        	//第一步流程发起
	        	params_title.put("title", admin.getTruename()+"_"+title);
	        	params_title.put("path", "szzfView");
	        	map = facets.startAndExecute(processId, admin.getName(), params_title);
	        	BHUtil.updateTransID("MovieSeq");
	        	orderId = map.get("orderId");
	        	taskId = map.get("taskId");
	        	newTaskId = map.get("newTaskId");
	        	//listObj = TaskManageDao.getInstance().save(params_all, orderId, taskId);
	        }
	     
	        //最新流程Id
	        newTaskId = map.get("newTaskId");
	        //当前流程名称
	    	displayName   = map.get("displayName");
	    	
	        //对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
	        Map<String, Object> newParams = changeParam(params, taskName);
	        newParams.put("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	        if(newParams.get("qt") != null){
	        	newParams.put("qt",URLDecoder.decode(newParams.get("qt").toString(),"utf8"));
	        }
	        if(newParams.get("wzname") != null){
	        	newParams.put("wzname",URLDecoder.decode(newParams.get("wzname").toString(),"utf8"));
	        }
	        if(newParams.get("aqjy") != null){
	        	newParams.put("aqjy",URLDecoder.decode(newParams.get("aqjy").toString(),"utf8"));
	        }
	        newParams.put("wzaddress",URLDecoder.decode(newParams.get("wzaddress").toString(),"utf8"));
	        if(flag){
	        	sql = BATCH_SQL;
	        	listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
	        }else{
	        	sql = UPDATE_SQL;
	        	listObj = TaskManageDao.getInstance().update(newParams, orderId);
	        }
	        //表单数据保存至flow_form
	    	manager.executeBatch_Pre(sql, listObj);
	    	//流程结束时，newTaskId为-1
	    	if(!newTaskId.equals("-1")){
	    		if(method.equals("0")){
	    			//去除下一步流程的权限ID记录
	        		//facets.getEngine().task().removeTaskActor(newTaskId, new String[]{newParams.get("assignee").toString()});
	        		//用户流程添加
	        		facets.getEngine().task().addTaskActor(newTaskId, 0, newParams.get("userList").toString().split(","));
	        		//普通步骤进行流程提醒
	        		addMsg(newParams.get("userList").toString(), orderId, newTaskId, processId, "szzf!allSzzf.action", displayName);
	    		}else{
	    			addMsg(map.get("startUser"), orderId, newTaskId, processId, "szzf!allSzzf.action", displayName);
	    		}
	    	}
	        response.getWriter().write("success");
	 }
	
	
	
	/** 
	* @Title: leave 
	* @Description: TODO(手机端请假申请) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void leave(){
		 String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
		 String username = CommonUtil.nullToDefault(request.getParameter("userName"), "");
		 Admin admin = getAdmin(username);
		 //判断第一步用户是否在该流程中存在操作权限
		 List<Map<String, Object>> listUser = TaskManageDao.getInstance().getUserAuth(admin.getAdminId());
		 Map<String, Object> map = new HashMap<String,Object>();
		 List<Map<String, Object>> userList = new ArrayList<Map<String,Object>>();
		 List<TaskModel> list = engine.process().getProcessById(processId).getModel().getModels(TaskModel.class);
		 String result = "";
		 String assignee = null;
		 //判断请假人员是否属于领导
		 boolean flag = true;		 
		 for (TaskModel taskModel : list) {
			 for (Map<String, Object> map1 : listUser) {
				if(taskModel.getAssignee() != null && taskModel.getAssignee().equals(map1.get("AUTHID").toString())){
					flag = false;
					result = taskModel.getOutputs().get(0).getTo();
					break;
				}
			}
		 }
		 //请假人员不属于审批权限汇总
		 if(flag){
			 for (TaskModel taskModel : list) {
				 if(taskModel.getName().equals("bmyj")){
					 assignee = taskModel.getAssignee();
					 result = "bmyj";
					 break;
				 }
			 }
		 }else{
			 for (TaskModel taskModel : list) {
				 if(taskModel.getName().equals(result)){
					 assignee = taskModel.getAssignee();
					 break;
				 }
			 }
		 }
		 
		 if (assignee!= null && !assignee.equals("")) {
			 List<Map<String, Object>> user  = null;
			 if(flag){
				 user = SysUserDao.getInstance().getUserList(null,assignee, String.valueOf(admin.getOrg_id()));
			 }else{
				 user = SysUserDao.getInstance().getUserList(null,assignee, "");
			 }
			 
				Map<String, Object> mapUser = null;
				for (Map<String, Object> map2 : user) {
					mapUser = new HashMap<String, Object>();
					mapUser.put("TRUENAME", map2.get("TRUENAME"));
					mapUser.put("USERNAME", map2.get("USERNAME"));
					mapUser.put("ORG_INFOR", map2.get("ORG_INFOR"));
					userList.add(mapUser);
				}
		 } 
		 
		 List<Map<String, Object>> leaveList = TaskManageDao.getInstance().getUserYear(admin.getAdminId());
 		if(leaveList.size() == 0){
 			map.put("YEAR", 0);
 			map.put("AL", 0);
 		}else{
 			Double year = Double.parseDouble(leaveList.get(0).get("YEAR").toString());
 			Double used = Double.parseDouble(leaveList.get(0).get("USED").toString());
 			map.put("YEAR", BHUtil.jsj(year, used));
 			map.put("ALLDAY", leaveList.get(0).get("ALL_TIME"));
 		}
		 
		
		 map.put("RESULT", result);
		 map.put("USERLIST", userList);
		 writeGson(response, map);
		
	}
	
	
	
	public void leaveStartProcess() throws IOException {
 		ArrayList<Object[]> listObj = new ArrayList<Object[]>();
	 	String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
	 	String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
	 	String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
	 	String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");
	 	String method = CommonUtil.nullToDefault(request.getParameter("method"), "0");
	 	String username = CommonUtil.nullToDefault(request.getParameter("userName"), "");
    	Admin admin = getAdmin(username);
    	String ipaddr =ClientInfoUtils.getIP(request);
        Map<String, String> map = new HashMap<String,String>();
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params = this.dealParams(paraNames,null);
        Map<String, Object> mapTitle = new HashMap<String, Object>();
        mapTitle.put("ip", ipaddr);
        //下步流程的taskId
        String newTaskId = null;
        //下步流程名称
        String displayName= null;
        String sql = null;
        //状态  0 ，流程第一次发起，1，流程第N次发起
    	String status = "0";
    	//第一步流程信息
    	Map<String, Object> startMap = null;
        if (StringHelper.isEmpty(orderId)) 
        {	
        	//执行第一步流程
        	mapTitle.put("title", params.get("title"));
        	mapTitle.put("path", "leaveView");
        	mapTitle.put("result", params.get("result"));
        	map = facets.startAndExecute(processId, admin.getName(),mapTitle);
        	orderId = map.get("orderId");
        	taskId = map.get("taskId");
        	newTaskId = map.get("newTaskId");
        	displayName = map.get("displayName");
        	params.put("name", admin.getTruename()); //用户
        	params.put("section",admin.getOrg_name());//部门	
        	params.put("work", admin.getOrg_infor());//职务
        	params.put("uid", admin.getAdminId());//用户id
        }
      //对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
        Map<String, Object> newParams = changeParam(params, taskName);
        if(newParams.get("qtype") != null){
        	newParams.put("qtype",URLDecoder.decode(newParams.get("qtype").toString(),"utf8"));
        }
        if(newParams.get("title") != null){
        	newParams.put("title",URLDecoder.decode(newParams.get("title").toString(),"utf8"));
        }
        newParams.put("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(status.equals("0")){
        	sql = BATCH_SQL;
        	listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
        }else{
        	sql = UPDATE_SQL;
        	listObj = TaskManageDao.getInstance().update(newParams, orderId);
        }
        //保存表单信息
        manager.executeBatch_Pre(sql, listObj);
        //流程结束时，newTaskId为-1
    	if(!newTaskId.equals("-1")){
    		if(method.equals("0")){
    			//去除下一步流程的权限ID记录
        		TaskManageDao.getInstance().DeActorId(newTaskId);
        		//用户流程添加
        		facets.getEngine().task().addTaskActor(newTaskId, 0, newParams.get("userList").toString().split(","));
        		//普通步骤进行流程提醒
        		addMsg(newParams.get("userList").toString(), orderId, newTaskId, processId, "leave!allLeave.action", displayName);
    		}else{
    			addMsg(startMap.get("operator").toString(), orderId, newTaskId, processId, "leave!allLeave.action", displayName);
    		}
    	}else{
    		//流程结束保存数据
    		Map<String, Object> AllResult =  TaskManageDao.getInstance().getForm_List(orderId);
    		
    		TaskManageDao.getInstance().updateUserleave(AllResult);
    	}
        response.getWriter().write("success");
        
	}
	
	
	
	public void carApply()
	{
		 String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
		 Map<String, Object> map = new HashMap<String,Object>();
		 List<Map<String, Object>> userList = new ArrayList<Map<String,Object>>();
		 List<Map<String, Object>> carList1 = new ArrayList<Map<String,Object>>();
		 List<TaskModel> list = engine.process().getProcessById(processId).getModel().getModels(TaskModel.class);
		 String assignee = null;
		 for (TaskModel taskModel : list) {
			if(taskModel.getName().equals("rect3")){
				assignee = taskModel.getAssignee();
				break;
			}
		 }
		 if (assignee!= null && !assignee.equals("")) {
			 	List<Map<String, Object>> user = SysUserDao.getInstance().getUserList(null,assignee, null);
				Map<String, Object> mapUser = null;
				for (Map<String, Object> map2 : user) {
					mapUser = new HashMap<String, Object>();
					mapUser.put("TRUENAME", map2.get("TRUENAME"));
					mapUser.put("USERNAME", map2.get("USERNAME"));
					mapUser.put("ORG_INFOR", map2.get("ORG_INFOR"));
					userList.add(mapUser);
				}
		 } 
		 List<Map<String, Object>> carInfo = CarManagerDao.getInstance().queryCarInfoByCarStatus();
		 for (Map<String, Object> map2 : carInfo) {
			Map<String, Object> map5 = new HashMap<String,Object>();
			map5.put("CAR_NO", map2.get("CAR_NO"));
			carList1.add(map5);
		}
		 map.put("CARLIST", carList1);
		 map.put("USERLIST", userList);
	 	writeGson(response, map);
	}
	
	
	
	/**
	 * 工作流流转的主要处理方法
	 * @throws IOException
	 */
	public void carStartProcess() throws IOException {
 		ArrayList<Object[]> listObj = new ArrayList<Object[]>();
	 	String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
	 	String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
	 	String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
	 	String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");
	 	String method = CommonUtil.nullToDefault(request.getParameter("method"), "0");
	 	String step = CommonUtil.nullToDefault(request.getParameter("step"), "0");
	 	String username = CommonUtil.nullToDefault(request.getParameter("userName"), "0");
    	Admin admin = getAdmin(username);
        Map<String, String> map = new HashMap<String,String>();
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params = this.dealParams(paraNames,null);
        //下步流程的taskId
        String newTaskId = null;
        String displayName= null;
        String sql = null;
        boolean flag = true;
        boolean car_status= true;
        List<Map<String, Object>> list = CarManagerDao.getInstance().queryCarInfoByCarStatus();
        if (StringHelper.isEmpty(orderId)) 
        {
        	//判断车辆的状态是否空闲,如果被占用了，直接返回失败
        	if(list == null || list.size() == 0)
          	{   
           		
           		car_status = false;
           			}
        	if(car_status)
        	{
	        	//执行第一步流程
	        	Map<String, Object> mapTitle = new HashMap<String, Object>();
	        	mapTitle.put("title", params.get("title"));
	        	mapTitle.put("path", "carView");
	        	map = facets.startAndExecute(processId, admin.getName(),mapTitle);
	        	orderId = map.get("orderId");
	        	taskId = map.get("taskId");
	        	params.put("name", admin.getTruename()); //用户
	        	params.put("section",admin.getOrg_name()); //部门	
	        	params.put("work", admin.getOrg_infor());//职务
	        	CarManagerDao.getInstance().updateCarInfo(CommonUtil.nullToDefault(params.get("car_no"),""),"2");
        	}
        }else
        {
        	//执行下一步流程
        	map = nextFlowHandle(orderId, taskId, taskName, params, method, admin,processId);
        	//说明流程被回退过
    	    if(step.equals("1"))
    	    {
    	    	CarManagerDao.getInstance().updateCarInfo(CommonUtil.nullToDefault(params.get("car_no"),""),"2");
    	    }
        	flag = map.get("status").equals("0") ? true : false;
        	if(!method.equals("0"))
        	{
        		Map<String, Object> map_= TaskManageDao.getInstance().getForm_List(orderId);
        		CarManagerDao.getInstance().updateCarInfo(CommonUtil.nullToDefault(map_.get("car_no"),""),"1");
        	}
        }
        if(car_status)
        {
	        //最新流程Id
	        newTaskId = map.get("newTaskId");
	        //当前流程名称
	    	displayName   = map.get("displayName");
	    	 if(params.get("sxry") != null){
	    		 params.put("sxry",URLDecoder.decode(params.get("sxry").toString(),"utf8"));
	         }
	    	 if(params.get("mdd") != null){
	    		 params.put("mdd",URLDecoder.decode(params.get("mdd").toString(),"utf8"));
	         }
	    	 if(params.get("ycsy") != null){
	    		 params.put("ycsy",URLDecoder.decode(params.get("ycsy").toString(),"utf8"));
	         }
	    	 if(params.get("title") != null){
	    		 params.put("title",URLDecoder.decode(params.get("title").toString(),"utf8"));
	         }
	    	 if(params.get("jsy") != null){
	    		 params.put("jsy",URLDecoder.decode(params.get("jsy").toString(),"utf8"));
	         }
	        //对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
	        Map<String, Object> newParams = changeParam(params, taskName);
	        
	        if(flag){
	        	sql = BATCH_SQL;
	        	listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
	        }else{
	        	sql = UPDATE_SQL;
	        	listObj = TaskManageDao.getInstance().update(newParams, orderId);
	        }
	        //保存表单信息到flow_form
	        manager.executeBatch_Pre(sql, listObj);
	        //流程结束时，newTaskId为-1
	    	if(!newTaskId.equals("-1")){
	    		if(method.equals("0")){
	    			//去除下一步流程的权限ID记录
	        	//	facets.getEngine().task().removeTaskActor(newTaskId, new String[]{newParams.get("assignee").toString()});
	        		//用户流程添加
	        		facets.getEngine().task().addTaskActor(newTaskId, 0, newParams.get("userList").toString().split(","));
	        		//普通步骤进行流程提醒
	        		addMsg(newParams.get("userList").toString(), orderId, newTaskId, processId, "carManager!allCar.action", displayName);
	    		}else{
	    			addMsg(map.get("startUser"), orderId, newTaskId, processId, "carManager!allCar.action", displayName);
	    		}
	    	}
	    	
	        response.getWriter().write("success");
        }
        else
        {
        	response.getWriter().write("fail");
        }
	}
}
