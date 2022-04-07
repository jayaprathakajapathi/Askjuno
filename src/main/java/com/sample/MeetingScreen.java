package com.sample;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class MeetingScreen extends Div{
	
	public MeetingScreen() {
		FlexLayout base = new FlexLayout();
		VerticalLayout vlayout=new VerticalLayout();
		Label label=new Label("MEETING");
		Button addNew=new Button("AddNew");
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
	assignedTo.setItems("Direct Meeting","GMeet");
	DateTimePicker dateTimePicker = new DateTimePicker();
	dateTimePicker.setLabel("Meeting date and time");
	vlayout.add(tfield,assignedTo,dateTimePicker);
	return vlayout;
	}
} 

