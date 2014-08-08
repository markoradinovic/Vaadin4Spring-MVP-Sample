package org.vaadin.spring.sample.home;

import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.mvp.view.AbstractMvpView;
import org.vaadin.spring.sample.home.HomePresenter.HomeView;

import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@UIScope
@VaadinComponent
public class HomeViewImpl extends AbstractMvpView implements HomeView  {

	private HomePresenterHandlers mvpPresenterHandlers;
	
	
	CssLayout content;
	Button button;
	
	@Override
	public void postConstruct() {	
		super.postConstruct();
		
		content = new CssLayout();
		content.setWidth("100%");
		setCompositionRoot(content);
				
		final CssLayout wrap = new CssLayout();
		wrap.setWidth("100%");
		content.addComponent(wrap);
		
		final Label caption = new Label("This is Home View");
		caption.addStyleName(ValoTheme.LABEL_H1);
		wrap.addComponent(caption);
		
		button = new Button("Click me");		
		wrap.addComponent(button);
		
				
	}
	
	@Override
	public void setPresenterHandlers(HomePresenterHandlers mvpPresenterHandlers) {
		this.mvpPresenterHandlers = mvpPresenterHandlers;
		button.addClickListener(this.mvpPresenterHandlers.getDemoBtnClickListener());
		
	}

	@Override
	public void initView() {
		while ( content.getComponentCount() > 1) {
			if (content.getComponent(content.getComponentCount() - 1) instanceof Label) {
				content.removeComponent(content.getComponent(content.getComponentCount() - 1));
			}
		}
		
	}

	@Override
	public void showMessage(String message) {
		final Label caption = new Label(message);
		caption.addStyleName(ValoTheme.LABEL_H2);
		content.addComponent(caption);
		
	}

}
