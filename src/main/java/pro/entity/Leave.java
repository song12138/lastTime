package pro.entity;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.Date;

/** 请假
 * Created by paul on 2017/12/15.
 */
public class Leave {
    private String id;
    private String userName;
    private String userOffice;
    private String startTime;		// 请假时间始
    private String days;		//请假天数
    private String leaveType;  //请假类型：公休（1）、请假（2）、调休（3）
//    	private Date endTime;		// 请假时间终
    private String leaveReason;		// 请假事由
    private String  applyTime;		// 申请日期
//
//    private String processsId; // 流程id
//
//    private Task task;
//
//    // 运行中的流程实例
//    private ProcessInstance processInstance;
//    // 历史的流程实例
//    private HistoricProcessInstance historicProcessInstance;
//    // 流程定义
//    private ProcessDefinition processDefinition;
//


    /**/
    private String startDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserOffice() {
        return userOffice;
    }

    public void setUserOffice(String userOffice) {
        this.userOffice = userOffice;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }
}
