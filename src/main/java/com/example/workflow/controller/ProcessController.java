package com.example.workflow.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.workflow.model.TasksRequest;
import com.example.workflow.services.ProcessService;

import lombok.extern.log4j.Log4j;

import org.activiti.engine.task.Task;
@Log4j
@RestController
@RequestMapping("/process")
@CrossOrigin
public class ProcessController {
	@Autowired
	ProcessService processService;
	@PostMapping("/tasks")
	@ResponseBody
	public List<Map<String, Object>> getUserProcessTasks(@RequestBody TasksRequest taskRequest)
	{
		List<Task> list= processService.getUserProcessTasks(taskRequest.getUsername(), taskRequest.getProcessDefinitionId());
		List<Map<String, Object>> customTaskList = new ArrayList<>();
	    for (Task task : list) {
	    	Map<String, Object> map = new LinkedHashMap<>();
	        map.put("taskId", task.getId());
	        map.put("taskDefinitionKey", task.getTaskDefinitionKey());
	        map.put("taskName", task.getName());
	        map.put("assignee", task.getAssignee());
	        map.put("taskOwner", task.getOwner());
	        map.put("taskDueDate",task.getDueDate());
	        map.put("taskProcessInstanceId",task.getProcessInstanceId());
	        customTaskList.add(map);
	    }
	    log.info("tasks number="+customTaskList.size());
		return customTaskList;
	}
	@PostMapping("/complete-task")
	@ResponseBody
	public void completeTask(@RequestBody String taskId)
	{
		 processService.completeTask(taskId);
	}
}
