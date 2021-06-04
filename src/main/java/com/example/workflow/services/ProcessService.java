package com.example.workflow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    //ApplicationContext applicationContext;
    ResourceLoader resourceLoder;
    //@Deployment(resources = { "/JobCollectionResourceTest.testTimerProcess.bpmn20.xml" })
    public ProcessInstance startProcess(WorkflowInfo workflowInfo) throws FileNotFoundException {
		String fileName=workflowInfo.getName()+"_"+workflowInfo.getVersion();
    	log.debug(">>>>>>>>>>> ProcessService -> : startProcess(workflowInfo): start ...");
    	String localFilePath=fileService.copyXmlFile(workflowInfo.getFilePath(),workflowInfo.getDirPath());
		ZipInputStream inputStream = new ZipInputStream(new FileInputStream(localFilePath));
		RepositoryService repositoryService = processEngine.getRepositoryService();
		//InputStream inputstream = new FileInputStream(workflowInfo.getFilePath());
		//new ClassPathResource(workflowInfo.getFilePath());
		//log.info(">>>>>>>>>>>classpath :"+applicationContext.getResource(workflowInfo.getFilePath()));
		log.info(resourceLoder.getResource("file:"+System.getProperty("user.dir") + "\\src\\main\\resources\\"+workflowInfo.getFilePath()));
		DeploymentEntity deployment=(DeploymentEntity)repositoryService.createDeployment()
				  .addClasspathResource(workflowInfo.getFilePath())
				  //.addClasspathResource("Workflow\\Orange\\Project Details\\valid\\workflow_test_1622806291108.bpmn20.xml")
				  .deploy();
    	log.info(">>>>>>>>>>> ProcessService -> : startProcess(workflowInfo): DeploymentEntity deployment = "+deployment);
		return processRuntime.start(ProcessPayloadBuilder
				.start()
				.withProcessDefinitionKey(workflowInfo.getName())
                .build()
                );
    }
}
