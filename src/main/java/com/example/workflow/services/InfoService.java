package com.example.workflow.services;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.workflow.model.Expression;
import com.example.workflow.model.Info;
import com.example.workflow.model.JavaClass;

import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class InfoService {
	public List<Info> getJavaClasses(){
		List<Info> list=EnumSet.allOf(JavaClass.class).stream().map(e ->e.toInfo()).collect(Collectors.toList());
		log.info(list);
		return list;
	}
	public List<Info> getExpressions(){
		List<Info> list=EnumSet.allOf(Expression.class).stream().map(e ->e.toInfo()).collect(Collectors.toList());
		log.info(list);
		return list;
	}
}
