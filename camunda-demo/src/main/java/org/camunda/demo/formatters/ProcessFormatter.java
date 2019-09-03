package org.camunda.demo.formatters;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.camunda.demo.ProcessDefinition;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptyList;

/*
 * Should define here the input and output variables of a process.
 * Key: Process Definition Key, Value: ProcessDefinition instance with input and output variables respectively 
 */
@Component
public class ProcessFormatter {
	
	public HashMap<String,ProcessDefinition> formatter;
	
	@PostConstruct
	public void init() {
		formatter = new HashMap<>();

		// fill formatter map here
	}
}
