package com.example.workflow.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
public class User implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = -6236517548335858347L;
		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
		@NotNull
		@Column(unique=true)
	    @Size(max=50)
		private String username;
		@NotNull
	    @Size(max=50)
		private String password;
		private String role;
		private String firstName;
	    private String lastName;
	    @Email
	    private String email;
}