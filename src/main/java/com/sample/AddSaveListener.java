package com.sample;

import java.util.concurrent.atomic.AtomicInteger;

import com.sample.dependsFile.Attachment;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;

public class AddSaveListener implements ComponentEventListener<ClickEvent<Button>> {
private AttachmentContainer attachmentScreen;
private AtomicInteger attachCounter = new AtomicInteger(0);

private final Attachment attachment;
public  AddSaveListener(AttachmentContainer attachmentScreen,Attachment attachment) {
	this.attachmentScreen=attachmentScreen;
	this.attachment=attachment;
}
	@Override
	public void onComponentEvent(ClickEvent<Button> event) {
		attachmentScreen.addAttachment();
		attachmentScreen.createCard(attachment);
		
	}

}
