package pro.controller;

import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.entity.Leave;
import pro.service.impl.LeaveWorkService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by paul on 2017/12/15.
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {

    private static Logger logger = LoggerFactory.getLogger(LeaveController.class);

    @Autowired
    private LeaveWorkService leaveWorkService;


    @RequestMapping("index")
    public String index(String assignee, Model model) {

        List<Task> list = leaveWorkService.queryTasks(assignee);

        model.addAttribute("tasks", list);


        return "leave";
    }

    @RequestMapping("/apply")
    public void apply(Leave leave) {

        logger.debug("leave{}:",leave);
        leave.setId(UUID.randomUUID().toString());
        leaveWorkService.startWorkFlow(leave, null);
    }


}
