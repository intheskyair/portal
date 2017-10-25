package com.zqkj.bsm.cluster.pdu;

public class MoveJobsSeqInQueue {
	private long job_id_to;//目标位置任务id 
	private long[] job_ids_from;//数组	需要移动的任务id
	public long getJob_id_to() {
		return job_id_to;
	}
	public void setJob_id_to(long job_id_to) {
		this.job_id_to = job_id_to;
	}
	public long[] getJob_ids_from() {
		return job_ids_from;
	}
	public void setJob_ids_from(long[] job_ids_from) {
		this.job_ids_from = job_ids_from;
	}
	public boolean isInsert_after_position() {
		return insert_after_position;
	}
	public void setInsert_after_position(boolean insert_after_position) {
		this.insert_after_position = insert_after_position;
	}
	private boolean insert_after_position;//是否在目标位置后插入

}
