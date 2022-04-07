package com.sample;

import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;


public class TaskScreen extends Div{
	
	public TaskScreen() {
		FlexLayout base = new FlexLayout();
		VerticalLayout vlayout=new VerticalLayout();
		Label label=new Label("TASK");
		Button addNew=new Button("AddNewTask");
		addNew.addClickListener(event -> {
			vlayout.add(getlayout());
			
		
		});
		vlayout.add(label,addNew);
		base.add(vlayout);
		add(base);
		}
		
	private VerticalLayout getlayout() {
		VerticalLayout vlayout=new VerticalLayout();
	TextField tfield =new TextField();
	tfield.setPlaceholder("name");
	ComboBox<String> assignedTo = new ComboBox ();
	assignedTo.setItems("kavi","Guru");
	DateTimePicker dateTimePicker = new DateTimePicker();
	dateTimePicker.setLabel("Meeting date and time");
	add(dateTimePicker);
	vlayout.add(tfield,assignedTo,dateTimePicker);
	return vlayout;
	}
}