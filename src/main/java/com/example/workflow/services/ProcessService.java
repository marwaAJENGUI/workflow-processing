package com.example.workflow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipInputStream;

import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.test.Deployment;
import com.example.workflow.model.WorkflowInfo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
//import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class ProcessService {

    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private FileService fileService;
    @Autowired
    ProcessEngine processEngine;
    @Autowired
    ResourceLoader resourceLoder;
    
    //ApplicationContext applicationContext;
    //@Deployment(resources = { "/JobCollectionResourceTest.testTimerProcess.bpmn20.xml" })
    public ProcessInstance startProcess(WorkflowInfo workflowInfo) throws FileNotFoundException, InterruptedException {
		String fileName=workflowInfo.getName()+"_"+workflowInfo.getVersion();
		String fullFileName=workflowInfo.getName()+"_"+workflowInfo.getVersion()+workflowInfo.getExtension();
    	log.debug(">>>>>>>>>>> ProcessService -> : startProcess(workflowInfo): start ...");
    	String localFilePath=fileService.copyXmlFile(workflowInfo.getFilePath(),workflowInfo.getDirPath());
		//ZipInputStream inputStream = new ZipInputStream(new FileInputStream(localFilePath));
		//InputStream inputstream = new FileInputStream(workflowInfo.getFilePath());
		//new ClassPathResource(workflowInfo.getFilePath());
		//log.info(">>>>>>>>>>>classpath :"+applicationContext.getResource(workflowInfo.getFilePath()));
		//log.info(resourceLoder.getResource("file:"+System.getProperty("user.dir") + "\\src\\main\\resources\\"+workflowInfo.getFilePath()));
		log.info("Manual Debug 1 ==>"  + workflowInfo.getFilePath());
		log.info("Manual Debug 2 ==>");
		//TimeUnit.SECONDS.sleep(15);
		/*RepositoryService repositoryService = processEngine.getRepositoryService();
		DeploymentEntity deployment=(DeploymentEntity)repositoryService.createDeployment()
			  .addClasspathResource(workflowInfo.getFilePath())
			  .tenantId("aaaaaaaa")
			  .deploy();
    	log.info(">>>>>>>>>>> ProcessService -> : startProcess(workflowInfo): DeploymentEntity deployment = "+deployment);
		return processRuntime.start(ProcessPayloadBuilder
				.start()
				.withProcessDefinitionKey(workflowInfo.getName())
                .build()
                );
		*/
		processEngine = ProcessEngines.getDefaultProcessEngine();
		// Create repository service and deploy with process xml.
		RepositoryService repositoryService = processEngine.getRepositoryService();
		log.debug("repositoryService :"+ repositoryService);
		try {
		DeploymentEntity dep=(DeploymentEntity)repositoryService.createDeployment()
		.addInputStream(fullFileName,
		new FileInputStream(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\"+workflowInfo.getFilePath())))
		.deploy();
		log.info("dep :"+ dep);
		} catch (FileNotFoundException e) {
		System.out.println("Error");
		e.printStackTrace();
		}
		TimeUnit.SECONDS.sleep(15);
		// Everything related to the runtime state of processes can be found in
		// the RuntimeService.
		RuntimeService runtimeService = processEngine.getRuntimeService();


		org.activiti.engine.runtime.ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(workflowInfo.getName());
		return null;
    }
}
