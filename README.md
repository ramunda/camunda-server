# camunda-server
Generic Camunda server for Ramunda usage

### Walk-through 

1. Clone git repository https://github.com/ramunda/camunda-server-examples.git.
2. Import Maven project to your IDE.
3. By default this server uses POSTGRES, so you need to download it and install it.
4. Create a Database with the name camunda in POSTGRES. If you decide that you want another name you must change the file application.properties, found in resources, to the match the new name.
5. Fill the fields with username and password refering to the created database in application.properties.
6. Create the necessary tables in POSTGRES. You can achive this by running the sript in the file “TableCreation.sql” from the package "repo".
7. If you desire to change the database from POSTGRES to another database you have to add a dependency to the new database. Camunda provides a list of supported databases (https://docs.camunda.org/manual/7.5/introduction/supported-environments/#databases). If you decide to have a different database you must also change the file application.properties as well as the Java classes from the package "repo". This classes must follow the same logic and names, so you should only change sintaxe related issues, like the query of the interface "MilestoneRepository".
8. Proceed to model a process using a modelling tool, example the Camunda Modeler (https://camunda.com/download/modeler/).
9. During the design of the process is required to be carefull to attribute listeners to the user tasks with the path to the java class, this is going to be triggered by the event that we can define.

<p align="center">
  <img src="https://svgshare.com/i/Eno.svg"><br/>
</p>

This classe must be added to the package "listeners".

```
package org.camunda.demo.listeners;

public class ReceiveDataListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		ArrayList<ParameterDTO> inputParameters = new ArrayList<>();
		inputParameters.add(new ParameterDTO("n1",null));
		inputParameters.add(new ParameterDTO("n2",null));
		inputParameters.add(new ParameterDTO("toSum",null));
		delegateTask.setVariableLocal("inputParameters",inputParameters);
	}
}
```

10. You must also define the Java classes for the service tasks. As follow:

<p align="center">
  <img src="https://svgshare.com/i/Emb.svg"><br/>
</p>

```
package org.camunda.demo.serviceTaskImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FinancialMovement implements JavaDelegate{
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		// Código que a service task executará

	}
}
```

This classes must be added to the package "serviceTaskImpl".

11. Once concluded the modelation of the process, this must be saved in the directory "resources/processes" so that the deploy of the process can be done as the server is excuted.

12. Add to the map of the class "ProcessFormatter" the respective entries for the processes created. The key is the process definition key that can be acquired from the XML generated from modelling the process in Camunda modeler. 

```
<bpmn:process id="Process_08rb4ct" isExecutable="true">
```
The value of the map is an instance of the class "ProcessDefinition" with the input and output parameteres, respectively.

13. Do what is told in step 6. The following code represents an insert to the database from a process with 2 milestones, in which each milestone represents a task. The task id can also be obtain by the XML from the process modelation, see step 9.

```
INSERT INTO process_milestones
	VALUES('Process Definition Key','{"Milestone ID","Milestone ID"}');
	
INSERT INTO milestones
	VALUES('Milestone ID','Milestone Name','{"Task ID"}'),('Milestone ID','Milestone Name','{"Task ID"}');
```

14. Run the Application by executing the class "CamundaApplication".

