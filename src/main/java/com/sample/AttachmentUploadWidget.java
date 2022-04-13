package com.sample;

import com.sample.dependsFile.Attachment;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class AttachmentUploadWidget extends FormLayout {
	private final AttachmentUploadContainer uploadContainer;
	private TextField name = new TextField("Name");
	private TextField description = new TextField("Description");
	private Attachment attachment;
private FlexLayout base = new FlexLayout();
private VerticalLayout vLayout = new VerticalLayout();
	public AttachmentUploadWidget(Attachment attachment) {
		this.uploadContainer = new AttachmentUploadContainer(this);
		this.attachment = attachment;

	}

	public FlexLayout createLayout() {
		
		Label label = new Label("ATTACHMENT");
		
		vLayout.add(label,name, description);

		vLayout.add(uploadContainer.buildayout());

		base.add(vLayout);
		Button AddNew = new Button("Save", click -> {
			uploadContainer.validate();
        this.clear();
		});

		vLayout.add(AddNew);
		add(base);
		return base;
	}

	public void createCard() {
		updateNewAttcahment();
		
			HorizontalLayout Container = new HorizontalLayout();
			Span name = new Span(attachment.getAssetName());
			Span description = new Span();
			IronIcon editIcon = new IronIcon("vaadin", "edit");
			IronIcon deleteIcon = new IronIcon("vaadin", "trash");
			IronIcon downloadIcon = new IronIcon("vaadin", "download");
			deleteIcon.addClickListener(event->{
				
			});
			Container.add(name, description, editIcon,deleteIcon,downloadIcon);

			vLayout.add(Container);

			}

	public void updateNewAttcahment() {
		attachment = new Attachment();

		attachment.setAssetName(name.getValue());
		attachment.setDescription(description.getValue());

	}

	public void clear() {
		this.description.clear();
		this.name.clear();
		this.attachment = null;
		uploadContainer.clear();
	}

	public TextField getName() {
		return name;
	}

	public TextField getDescription() {
		return description;
	}

	
	}

