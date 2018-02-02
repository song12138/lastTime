package pro.service.impl;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.dao.LeaveDao;
import pro.entity.Leave;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by paul on 2017/12/15.
 */
@Service
public class LeaveWorkService {

    private static Logger logger = LoggerFactory.getLogger(LeaveWorkService.class);

    @Autowired
    private LeaveDao leaveDao;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IdentityService identityService;

//    /**
//     * 启动流程
//     *
//     * @param leave
//     * @param variables
//     * @return
//     */
//    public ProcessInstance startWorkFlow(Leave leave, Map<String, Object> variables) {
//        leaveDao.saveLeave(leave);
//        logger.debug("save leave:{ }", leave);
//        String businessKey = leave.getId().toString();
//        ProcessInstance processInstance = null;
//        try {
//            // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
//            identityService.setAuthenticatedUserId(leave.getUserId());
//            processInstance = runtimeService.startProcessInstanceById("leave", businessKey, variables);
//            String processInstanceId = processInstance.getProcessInstanceId();
//            leave.setProcesssId(processInstanceId);
//            leaveDao.update(leave);
//        } finally {
//            identityService.setAuthenticatedUserId(null);
//        }
//        return processInstance;
//    }

    /**
     * 启动流程
     */
    public void startWorkFlow(Leave leave,Map<String, Object> variables){
        String businessKey=leave.getId();
        //根据流程定义的key启动流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("leaveProcess", businessKey, variables);
    }


    /**
     * 查看任务
     */
    public List<Task> queryTasks(String assignee){
        List<Task> tasks = new ArrayList<>();
        tasks=taskService.createTaskQuery()
                .taskAssignee(assignee) //指定任务办理人
                .list();
        return tasks;
    }

//
//    /**
//     *  查询待办任务
//     * @param userId
//     * @return
//     */
//    public List<Leave> findTodoTasks(String userId) {
//        List<Leave> results = new ArrayList<>();
//        // 根据当前人的ID查询
//        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
//        List<Task> tasks = taskQuery.list();
//        // 根据流程的业务ID查询实体并关联
//        for (Task task : tasks) {
//            String processInstanceId = task.getProcessInstanceId();
//            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
//                    .processInstanceId(processInstanceId).active().singleResult();
//            String bussinessKey = processInstance.getBusinessKey();
//            if (bussinessKey == null) {
//                continue;
//            }
//            Leave leave = leaveDao.get(bussinessKey);
//            leave.setTask(task);
//            leave.setProcessInstance(processInstance);
//            leave.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
//            results.add(leave);
//        }
//        return results;
//    }
//
//    /**
//     * 读取运行中的流程
//     * @return
//     */
//    public List<Leave> findRunningProcessInstaces() {
//        List<Leave> results = new ArrayList<>();
//        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().processDefinitionKey("leave")
//                .active().orderByProcessDefinitionId().desc();
//        List<ProcessInstance> processInstances = query.list();
//        // 关联业务实体
//        for (ProcessInstance processInstance : processInstances) {
//            String buinessKey = processInstance.getBusinessKey();
//            if (buinessKey == null) {
//                continue;
//            }
//            Leave leave = leaveDao.get(buinessKey);
//            if (leave != null) {
//                leave.setProcessInstance(processInstance);
//                leave.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
//                List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId())
//                        .active().orderByTaskCreateTime().list();
//                leave.setTask(tasks.get(0));
//                results.add(leave);
//            }
//        }
//        return results;
//    }
//
//    /**
//     * 读取已结束中的流程
//     * @return
//     */
//    public List<Leave> findFinishedProcessInstaces(){
//        List<Leave> results = new ArrayList<>();
//
//        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery()
//                .processDefinitionKey("leave").finished().orderByProcessInstanceEndTime().desc();
//
//        List<HistoricProcessInstance> list = query.list();
//
//        for (HistoricProcessInstance historicProcessInstance : list) {
//            String buinessKey = historicProcessInstance.getBusinessKey();
//            Leave leave = leaveDao.get(buinessKey);
//            leave.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
//            leave.setHistoricProcessInstance(historicProcessInstance);
//            results.add(leave);
//        }
//        return results;
//    }
//
//
//    /**
//     * 查询流程定义对象
//     *
//     * @param processDefinitionId
//     *            流程定义ID
//     * @return
//     */
//    public ProcessDefinition getProcessDefinition(String processDefinitionId) {
//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
//                .processDefinitionId(processDefinitionId).singleResult();
//        return processDefinition;
//    }



}
