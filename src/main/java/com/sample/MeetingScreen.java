package com.sample;

import java.util.Objects;

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

public class MeetingScreen extends Div {
	private VerticalLayout vlayout = new VerticalLayout();
	private TextField name = new TextField();
	private ComboBox<String> meetType = new ComboBox();
	private ComboBox<String> assignedTo = new ComboBox();
	private DateTimePicker dateTimePicker = new DateTimePicker();

	public MeetingScreen() {

		createlayout();

	}

	private void createlayout() {
		FlexLayout base = new FlexLayout();

		Label label = new Label("MEETING");
		Button save = new Button("AddNew");
		name.setLabel("name");

		meetType.setItems("Direct Meeting", "GMeet");
		meetType.setLabel("MeetType");

		TextField meetingInfo = new TextField();
		meetingInfo.setLabel("Info");

		assignedTo.setItems("kavi", "guru");
		assignedTo.setLabel("AssignedTo");
		dateTimePicker.setLabel("Meeting date and time");
		vlayout.add(label, name, meetType, meetingInfo, assignedTo, dateTimePicker, save);
		save.addClickListener(event -> {

			validation();

		});

		base.add(vlayout);
		add(base);
	}

	private void createCard() {
		HorizontalLayout container = new HorizontalLayout();
		Span mname = new Span(name.getValue());
		Span mtype = new Span(meetType.getValue());
		Span dateTime = new Span(String.valueOf(dateTimePicker.getValue()));
		IronIcon editIcon = new IronIcon("vaadin", "edit");
		IronIcon deleteIcon = new IronIcon("vaadin", "trash");
		IronIcon downloadIcon = new IronIcon("vaadin", "download");
		container.add(mname, mtype, dateTime, editIcon, deleteIcon, downloadIcon);
		vlayout.add(container);
	}

	private void validation() {

		if (!name.getValue().isEmpty()) {
			if (Objects.nonNull(meetType.getValue()) && Objects.nonNull(dateTimePicker.getValue())
					&& Objects.nonNull(assignedTo.getValue())) {

				createCard();
			}

			else {
				Notification.show("fill mandatory Fields", 1000, Position.TOP_CENTER);
			}
		} else {
			Notification.show("fill name ", 1000, Position.TOP_CENTER);
		}
	}

	private void update() {

	}

}
