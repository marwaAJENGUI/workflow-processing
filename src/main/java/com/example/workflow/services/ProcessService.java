package com.example.workflow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;

import com.example.workflow.config.SecurityUtil;
import com.example.workflow.model.WorkflowInfo;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class ProcessService {


    @Autowired
    private FileService fileService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private SecurityUtil securityUtil;
    public String startProcess(WorkflowInfo workflowInfo) throws FileNotFoundException, InterruptedException {
    	log.debug(">>>>>>>>>>> ProcessService -> : startProcess(workflowInfo): start ...");
    	String localFilePath=fileService.copyXmlFile(workflowInfo.getFilePath(),workflowInfo.getDirPath());
		log.info("Manual Debug 1 ==>"  + workflowInfo.getFilePath());
		log.info("Manual Debug 2 ==>");
		TimeUnit.SECONDS.sleep(15);
		securityUtil.logInAs("admin");
		DeploymentEntity deployment=(DeploymentEntity)repositoryService.createDeployment()
			  .addClasspathResource(workflowInfo.getFilePath())
			  .deploy();
    	log.info(">>>>>>>>>>> ProcessService -> : startProcess(workflowInfo): DeploymentEntity deployment = "+deployment);
    	ProcessInstance processInstance=processRuntime.start(ProcessPayloadBuilder
				.start()
				.withProcessDefinitionKey(workflowInfo.getName())
                .build()
                );
    	log.info(processInstance);
    	return processInstance.getProcessDefinitionId();
    }

	/*
	 * @Bean public TaskRuntimeEventListener<TaskAssignedEvent>
	 * taskAssignedListener() { return taskAssigned ->
	 * log.info(">>> Task Assigned: '" + taskAssigned.getEntity().getName() +
	 * "' We can send a notification to the assginee: " +
	 * taskAssigned.getEntity().getAssignee()); }
	 * 
	 * @Bean public TaskRuntimeEventListener<TaskCompletedEvent>
	 * taskCompletedListener() { return taskCompleted ->
	 * log.info(">>> Task Completed: '" + taskCompleted.getEntity().getName() +
	 * "' We can send a notification to the owner: " +
	 * taskCompleted.getEntity().getOwner()); }
	 */
    
	// fetch task assigned to user
	public List<Task> getUserProcessTasks(String assignee,String processDefinitionId) {
		securityUtil.logInAs(assignee);
		return taskService.createTaskQuery()
				.processDefinitionId(processDefinitionId)
				.active()
				//.taskAssignee(assignee)
				.list();
	}

	// complete the task
	public void completeTask(String taskId) {
		securityUtil.logInAs("admin");
		taskService.complete(taskId);
	}
}
