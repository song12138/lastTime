package common.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

/**
 * Created by paul on 2017/12/12.
 */
public class WorkFlow {

    private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 通过定义好的流程图文件部署，一次只能部署一个流程
     */
    public static void deploy(){
        RepositoryService repositoryService = processEngine.getRepositoryService();

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("activiti/leave.bpmn").deploy();


    }


    /**
     * 将多个流程文件打包部署，一次可以部署多个流程
     */
//    public void deployByZip() {
//        InputStream is = this.getClass().getClassLoader().getResourceAsStream("diagrams/bpm.zip");
//        ZipInputStream zip = new ZipInputStream(is);
//        Deployment deployment = processEngine
//                .getRepositoryService()
//                .createDeployment()
//                .addZipInputStream(zip)
//                .deploy();
//    }

}
