package org.vaadin.spring.sample.nested;

import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.mvp.view.AbstractMvpView;
import org.vaadin.spring.sample.ViewToken;
import org.vaadin.spring.sample.nested.NestedPresenter.NestedView;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
@UIScope
@VaadinComponent
public class NestedViewImpl extends AbstractMvpView implements NestedView, ClickListener {

	private NestedPresenterHandlers mvpPresenterHandlers;
	
	private HorizontalLayout layout;
	
	private VerticalLayout sidebar;
	
	private Panel nestedContainer;
	
	@Override
	public void postConstruct() {	
		super.postConstruct();
		setSizeFull();
		layout = new HorizontalLayout();
		layout.setSizeFull();
		layout.setSpacing(true);
		setCompositionRoot(layout);
		
		sidebar = new VerticalLayout();
		sidebar.setWidth("250px");
//		sidebar.setWidth("100%");
		sidebar.setSpacing(true);
		sidebar.setMargin(true);
//		sidebar.setHeight("100%");
		layout.addComponent(sidebar);
		
		final Button btnShowOne = new Button("Show nested 1", FontAwesome.STAR);
		btnShowOne.setData(ViewToken.NESTED_ONE);
		btnShowOne.setWidth("100%");
		btnShowOne.addClickListener(this);
		sidebar.addComponent(btnShowOne);
		
		final Button btnShowTwo = new Button("Show nested 2", FontAwesome.STAR);
		btnShowTwo.setData(ViewToken.NESTED_TWO);
		btnShowTwo.addClickListener(this);
		btnShowTwo.setWidth("100%");
		sidebar.addComponent(btnShowTwo);
		
		nestedContainer = new Panel();
		nestedContainer.setSizeFull();
		layout.addComponent(nestedContainer);
		layout.setExpandRatio(nestedContainer, 1);
	}
	
	@Override
	public void setPresenterHandlers(
			NestedPresenterHandlers mvpPresenterHandlers) {
		this.mvpPresenterHandlers = mvpPresenterHandlers;
		
	}

	@Override
	public void setNestedView(Component c) {
		nestedContainer.setContent(c);		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		mvpPresenterHandlers.showView((String) event.getButton().getData());
		
	}


}
