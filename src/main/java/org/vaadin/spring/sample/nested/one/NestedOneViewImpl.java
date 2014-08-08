package org.vaadin.spring.sample.nested.one;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.sample.nested.one.NestedOnePresenter.NestedOneView;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@UIScope
@VaadinComponent
public class NestedOneViewImpl extends VerticalLayout implements NestedOneView {

	private NestedOnePresenterHandlers mvpPresenterHandlers;
	
	@PostConstruct
	@Override
	public void postConstruct() {
		setWidth("100%");		
		setSpacing(true);
		setMargin(true);
		
		final Label caption = new Label("This is NestedOnePresenter view");
		caption.addStyleName(ValoTheme.LABEL_H2);
		addComponent(caption);
		
		final Button button = new Button("Send Message", FontAwesome.ENVELOPE);
		addComponent(button);
		
		button.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				mvpPresenterHandlers.sendEvent();				
			}
		});
		
		
	}

	@PreDestroy
	@Override
	public void preDestroy() {
		
	}

	@Override
	public void setPresenterHandlers(
			NestedOnePresenterHandlers mvpPresenterHandlers) {
		this.mvpPresenterHandlers = mvpPresenterHandlers;
		
	}

}
