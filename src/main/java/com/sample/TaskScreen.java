package com.sample;

import java.util.Objects;

import com.sample.dependsFile.Task;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;


public class TaskScreen extends Div{
	private  Task task;
	private TextField name =new TextField("Name");
	private ComboBox<String> assignedTo = new ComboBox();
	private	DateTimePicker dateTimePicker = new DateTimePicker();
	private VerticalLayout vlayout=new VerticalLayout();
	public TaskScreen(Task task) {
	this.task=task;
	createlayout();
		
		}
	public void createlayout() {
FlexLayout base = new FlexLayout();
		
		Label label=new Label("TASK");
		
		Button save=new Button("save");
		assignedTo.setItems("kavi","Guru");
		assignedTo.setPlaceholder("Assigned To");
		dateTimePicker.setLabel("date and time");
		
		vlayout.add(label,name,assignedTo,dateTimePicker,save);
      save.addClickListener(event->{
		validation();
		this.clear();
		
		});
    
		base.add(vlayout);
		add(base);
	}
	public void updateMethod() {
		task=new Task();
		task.setName(name.getValue());
		task.setAssignedTo(assignedTo.getValue());
		task.setDate(dateTimePicker.getValue());
	}
public void createCard() {
	 updateMethod();
	 HorizontalLayout container=new HorizontalLayout();
	 Span name=new Span(task.getName());
	 Span assignedTo=new Span(task.getAssignedTo());
	 Span date=new Span(String.valueOf(task.getDate()));
	 IronIcon editIcon = new IronIcon("vaadin", "edit");
		IronIcon deleteIcon = new IronIcon("vaadin", "trash");
	
	 container.add(name,assignedTo,date,editIcon,deleteIcon);
	 vlayout.add(container);
	 
}
public void validation() {
	
	if(!name.getValue().isEmpty()) {
		if(Objects.nonNull(assignedTo.getValue())) {
			if(Objects.nonNull(dateTimePicker.getValue())) {
		createCard();
	}else {
		Notification.show("fill date", 1000, Position.TOP_CENTER);
	}
			
		}else {
		Notification.show("fill assignedto", 1000, Position.TOP_CENTER);
	}
	}	else {
			Notification.show("fill name ", 1000, Position.TOP_CENTER);
		}

}
private void clear() {
	this.name.clear();
	this.assignedTo.clear();
	this.dateTimePicker.clear();
}
}