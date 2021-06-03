package com.example.workflow.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class BroadcastMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6187975728843165071L;
	private WorkflowInfo workflowInfo;
	private String authorization;
	
}
