package com.sample;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

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
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.internal.MessageDigestUtil;
import com.vaadin.flow.server.StreamResource;

public class AttachmentUploadContainer {
	

	private FormLayout uploadLayout;
	private MultiFileMemoryBuffer uploadBuffer;
	private Div uploadOutput;

	public AttachmentUploadContainer() {
		
	}
	
	public FormLayout buildayout() {

		uploadLayout = new FormLayout();

		uploadBuffer = new MultiFileMemoryBuffer();
		Upload upload = new Upload(uploadBuffer);
		upload.setAcceptedFileTypes("image/jpeg", "image/png", "image/gif", "application/pdf",
				"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
				"application/vnd.openxmlformats-officedocument.presentationml.presentation", "text/csv", "text/html");
		uploadOutput = new Div();

		upload.addSucceededListener(event -> {
			Component component = createComponent(event.getMIMEType(), event.getFileName(),
					uploadBuffer.getInputStream(event.getFileName()));
			showOutput(event.getFileName(), component, uploadOutput);
		});
		upload.addFileRejectedListener(event -> {
			Paragraph component = new Paragraph();
			showOutput(event.getErrorMessage(), component, uploadOutput);
		});
		
	//	this.uploadLayout.add(uploadBuffer, uploadLayout);
		
		return this.uploadLayout; 

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
}