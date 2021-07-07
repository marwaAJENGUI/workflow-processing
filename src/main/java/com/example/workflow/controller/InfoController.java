package com.example.workflow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.workflow.model.Info;
import com.example.workflow.services.InfoService;

@RestController
@RequestMapping("/infoes")
@CrossOrigin
public class InfoController {
	@Autowired
	InfoService infoService;
	
	@GetMapping("/java-classes")
	@ResponseBody
	public List<Info> getJavaClasses(){
		return infoService.getJavaClasses();
	}
	@GetMapping("/expressions")
	@ResponseBody
	public List<Info> getExpressions(){
		return infoService.getExpressions();
	}
}
