package com.sample;

import com.sample.dependsFile.Attachment;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AttachmentContainer extends FlexLayout {

	private final AttachmentUploadWidget attachmentUploadWidget;
	
	private final  Attachment attachment;
	public AttachmentContainer(AttachmentUploadWidget attachmentUploadWidget,Attachment attachment) {
	this.attachmentUploadWidget=attachmentUploadWidget;
	this.attachment=attachment;
	 VerticalLayout hLayout = new VerticalLayout();
	 hLayout.add(attachmentUploadWidget.createLayout());
	 
	 add(hLayout);
	}

	
}