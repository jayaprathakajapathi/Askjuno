package com.sample;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.IOUtils;

import com.sample.dependsFile.Attachment;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.internal.MessageDigestUtil;
import com.vaadin.flow.server.StreamResource;

public class AttachmentContainer extends FormLayout {

	private VerticalLayout attachLayout = new VerticalLayout();

	private TextField tfield = new TextField("Name");
	private TextField dfield = new TextField("Description");
	private final Attachment newItem ;
	private Button save = new Button("save");

	public AttachmentContainer(Attachment newItem) {
		this.newItem=newItem;
		createLayout();

	}

	private void createLayout() {
		FlexLayout baseContainer = new FlexLayout();

		Label label = new Label("ATTACHMENT");
		Button addNew = new Button("AddNewButton");
		addNew.addClickListener(event -> {
			attachLayout.add(getlayout());

		});
		attachLayout.add(label, addNew);
		baseContainer.add(attachLayout);

		add(baseContainer);
		

	}

	private FormLayout getlayout() {

		FormLayout flayout = new FormLayout();

		MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
		Upload upload = new Upload(buffer);
		upload.setAcceptedFileTypes("image/jpeg", "image/png", "image/gif", "application/pdf",
				"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
				"application/vnd.openxmlformats-officedocument.presentationml.presentation", "text/csv", "text/html");
		Div output = new Div();

		upload.addSucceededListener(event -> {
			Component component = createComponent(event.getMIMEType(), event.getFileName(),
					buffer.getInputStream(event.getFileName()));
			showOutput(event.getFileName(), component, output);
		});
		upload.addFileRejectedListener(event -> {
			Paragraph component = new Paragraph();
			showOutput(event.getErrorMessage(), component, output);
		});
		Button delete = new Button("Delete");
		delete.addClickListener(event -> {
			attachLayout.remove(flayout);
		});
		ComponentEventListener<ClickEvent<Button>> addSaveListener = new AddSaveListener(this, newItem);
		save.addClickListener(addSaveListener);

		flayout.add(tfield, dfield, upload, output, save, delete);

		return flayout;

	}

	

	private Component createComponent(String mimeType, String fileName, InputStream stream) {
		if (mimeType.startsWith("text")) {
			return createTextComponent(stream);
		} else if (mimeType.startsWith("image")) {
			Image image = new Image();
			try {

				byte[] bytes = IOUtils.toByteArray(stream);
				image.getElement().setAttribute("src",
						new StreamResource(fileName, () -> new ByteArrayInputStream(bytes)));
				try (ImageInputStream in = ImageIO.createImageInputStream(new ByteArrayInputStream(bytes))) {
					final Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
					if (readers.hasNext()) {
						ImageReader reader = readers.next();
						try {
							reader.setInput(in);
							image.setWidth(reader.getWidth(0) + "px");
							image.setHeight(reader.getHeight(0) + "px");
						} finally {
							reader.dispose();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			image.setSizeFull();
			return image;
		}
		Div content = new Div();
		String text = String.format("Mime type: '%s'\nSHA-256 hash: '%s'", mimeType,
				MessageDigestUtil.sha256(stream.toString()));
		content.setText(text);
		return content;

	}

	private Component createTextComponent(InputStream stream) {
		String text;
		try {
			text = IOUtils.toString(stream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			text = "exception reading stream";
		}
		return new Text(text);
	}

	private void showOutput(String text, Component content, HasComponents outputContainer) {
		HtmlComponent p = new HtmlComponent(Tag.P);
		p.getElement().setText(text);
		outputContainer.add(p);
		outputContainer.add(content);
	}

	public void addAttachment() {
		
			
				newItem.setAssetName(tfield.getValue());
				newItem.setDescription(dfield.getValue());
			
			Notification.show("Added Successfully", 1000, Position.TOP_CENTER);
			
				
				
}

  public HorizontalLayout createCard(Attachment newItem) {
  HorizontalLayout hLayout = new HorizontalLayout();

			Span name = new Span(this.newItem.getAssetName());
			Span description = new Span(newItem.getDescription());
			IronIcon editIcon = new IronIcon("vaadin", "edit");

			hLayout.add(name, description, editIcon);
			add(hLayout);
  
  
  return hLayout; }
 
}