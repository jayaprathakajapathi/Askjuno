package com.sample;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class NotificationScreen extends Div{
	
	public NotificationScreen() {
		FlexLayout base = new FlexLayout();
		VerticalLayout vlayout=new VerticalLayout();
		Label label=new Label("NOTIFICATION");
		
		
		vlayout.add(label,getlayout());
		base.add(vlayout);
		
		add(base);
		}
		
	private VerticalLayout getlayout() {
		VerticalLayout vlayout=new VerticalLayout();
		Span sms=new Span("SMS");
		Span whatsapp=new Span("WHATSAPP");
		Span email=new Span("EMAIL");
		vlayout.add(sms,whatsapp,email);
	return vlayout;
	}
}
