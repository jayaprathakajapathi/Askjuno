package com.sample;

import com.sample.dependsFile.Attachment;
import com.sample.dependsFile.Task;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "activitiesscreen")
@RouteAlias(value = "activity")
@PageTitle("Activities")
public class LeadActivitiesContainer extends FormLayout {
	private final AttachmentUploadWidget attachmentUploadWidget;
	private  Attachment attachment;
	private Task task;
	public LeadActivitiesContainer() {
		this.attachmentUploadWidget=new AttachmentUploadWidget(attachment);
		setResponsiveSteps(
		        new FormLayout.ResponsiveStep("0em", 1, FormLayout.ResponsiveStep.LabelsPosition.ASIDE)
		);
		createLayout();
	}
	private void createLayout() {
		AttachmentContainer attachScreen=new AttachmentContainer(attachmentUploadWidget, attachment);
		TaskScreen taskScreen=new TaskScreen(task);
		MeetingScreen meetingScreen=new MeetingScreen();
		NotificationScreen notificationScreen=new NotificationScreen();
		
		
		  Board borad =new Board();
		  borad.addRow(attachScreen,taskScreen,meetingScreen,notificationScreen);
		  add(borad);
		 
	}
}
