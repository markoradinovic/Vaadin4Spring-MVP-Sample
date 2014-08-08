package org.vaadin.spring.sample;

import javax.annotation.PostConstruct;

import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.mvp.MvpPresenterView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@UIScope
@VaadinComponent
@SuppressWarnings("serial")
public class MainLayout extends VerticalLayout implements ViewDisplay, ClickListener, ViewChangeListener {
	
	private Panel viewContainer;
	
	private HorizontalLayout navbar;
	
	private Button btnHome;
	private Button btnNested;
	
	@PostConstruct
	public void postConstuct() {
		setSizeFull();
		
		navbar = new HorizontalLayout();
		navbar.setWidth("100%");
		navbar.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
		addComponent(navbar);
		
		final Label brand = new Label("Nested demo");
		brand.addStyleName(ValoTheme.LABEL_H1);
		brand.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		navbar.addComponent(brand);
		navbar.setComponentAlignment(brand, Alignment.MIDDLE_LEFT);
		navbar.setExpandRatio(brand, 1);
		
		btnHome = new Button("Home", FontAwesome.HOME);
		btnHome.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		btnHome.setData(ViewToken.HOME);
		btnHome.addClickListener(this);
		navbar.addComponent(btnHome);
		
		btnNested = new Button("nested", FontAwesome.COFFEE);
		btnNested.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		btnNested.setData(ViewToken.NESTED);
		btnNested.addClickListener(this);
		navbar.addComponent(btnNested);
		
		viewContainer = new Panel();
		viewContainer.setSizeFull();
		addComponent(viewContainer);
		setExpandRatio(viewContainer, 1);
		
	}
	
	@Override
	public void showView(View view) {
		
		if (view instanceof MvpPresenterView) {
			viewContainer.setContent(((MvpPresenterView) view).getViewComponent());
		}
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		UI.getCurrent().getNavigator().navigateTo((String) event.getButton().getData());
		
	}

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {		
		return true;
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {
		for (int i=0; i<navbar.getComponentCount(); i++) {
			
			if (navbar.getComponent(i) instanceof Button) {
				final Button btn = (Button) navbar.getComponent(i);
				btn.removeStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
				
				String view = (String) btn.getData();
				
				if (event.getViewName().equals(view)) {
					btn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
				}
			}
		}
		
		
		
	}

}
