package com.zqkj.bsm.util;

public class ColumnMap {
	public static String getColName(String key){
		String value = "";
		if("id".equals(key)){
			value = "序号";
		}else if("plugin".equals(key)){
			value = "渲染类型";
		}else if("order_no".equals(key)){
			value = "任务单号";
		}else if("name".equals(key)){
			value = "任务名称";
//		}else if("queue_status".equals(key)){
//			value = "队列状态";
		}else if("render_status_text".equals(key)){
			value = "任务状态";
		}else if("cancel_reason".equals(key)){
			value = "取消原因";
		}else if("submit_time".equals(key)){
			value = "提交时间";
//		}else if("start_time".equals(key)){
//			value = "开始时间";
		}else if("total_frame".equals(key)){
			value = "总帧数";
		}else if("concurrency_number".equals(key)){
			value = "并行数";
		}else if("success_frame".equals(key)){
			value = "成功帧数";
		}else if("frames".equals(key)){
			value = "序列帧";
		}else if("failure_frame".equals(key)){
			value = "失败帧数";
		}else if("cancel_frame".equals(key)){
			value = "取消帧数";
		}else if("progress".equals(key)){
			value = "进度";
		}else if("seconds".equals(key)){
			value = "核小时";
		}else if("mul_flag".equals(key)){
			value = "计费类型";
		}else if("yu_money".equals(key)){
			value = "应扣金额(元)";
		}else if("money".equals(key)){
			value = "应扣金额(元)";
		}else if("nature_money".equals(key)){
			value = "实扣金额(元)";
		}else if("remainTime".equals(key)){
			value = "剩余时间";
		}else if("user_name".equals(key)){
			value = "创建人";
		}else if("yw_status".equals(key)){
			value = "订单状态";
		}
		else if("nickname".equals(key))
		{
			value = "用户昵称";
		}
		else if("phone".equals(key)){
			value = "手机号码";
		}
		else if("nature_time".equals(key))
		{
			value = "扣费时间";
		}
		else if("total_frame".equals(key))
		{
			value = "渲染总图片数";
		}
		else if("unit_price".equals(key))
		{
			value = "单价(元)";
		}
		else if("trans_id".equals(key)){
			value = "渲染单号";
		}
		else if("software".equals(key)){
			value = "任务类型";
		} else if ("ordernumber".equals(key)) {
			value = "充值单号";
		} else if ("recharge_status".equals(key)) {
			value = "充值状态";
		} else if ("createtime".equals(key)) {
			value = "充值时间";
		} else if ("orderamount".equals(key)) {
			value = "充值金额(元)";
		} else if ("settleamount".equals(key)) {
			value = "到账金额(元)";
		} else if ("type".equals(key)) {
			value = "支付方式";
		}  else if ("point".equals(key)) {
			value = "赠送积分";
		}else if ("money_hetime".equals(key)) {
			value = "每核小时的扣费金额 (元/核小时)";
		}else if ("time_he".equals(key)) {
			value = "渲染核小时 ";
		}else if ("finish_time".equals(key)) {
			value = "完成时间 ";
		}else if("project_seq_id".equals(key)){
			value = "项目单号";
		}else if("project_name".equals(key)){
			value = "项目名称";
		}else if("fee_type".equals(key)){
			value = "计费模式";
		}
		else if("fee_typ".equals(key)){
			value = "计费模式";
		}else if("ys_money".equals(key)){
			value = "应收金额(元)";
		}else if("back_money".equals(key)){
			value = "返回金额(元)";
		}else if("cw_applytime".equals(key)){
			value = "提现时间";
		}else if("cw_totalmoney".equals(key)){
			value = "申请总额";
		}else if("cw_tax".equals(key)){
			value = "扣税总额";
		}else if("cw_money".equals(key)){
			value = "打款总额";
		}else if("cw_sucessnum".equals(key)){
			value = "成功次数";
		}else if("render_status_new".equals(key)){
			value = "订单状态";
		}else if("render_status_line".equals(key)){
			value = "订单状态";
		}else if("farm_name".equals(key)){
			value = "所属农场";
		}else if("speed".equals(key)){
			value = "用户平均网速(k/s)";
		}else if("create_time".equals(key)){
			value = "提交时间";
		}else if("third_party_billing_result".equals(key)){
			value = "退费状态";
		}else if("seconds_frame".equals(key)){
			value = "单帧核小时";
		}
		return value;
	}
}
