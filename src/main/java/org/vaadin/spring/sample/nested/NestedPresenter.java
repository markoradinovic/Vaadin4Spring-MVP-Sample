package org.vaadin.spring.sample.nested;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventBusListenerMethod;
import org.vaadin.spring.mvp.MvpHasPresenterHandlers;
import org.vaadin.spring.mvp.MvpView;
import org.vaadin.spring.mvp.presenter.AbstractMvpPresenterView;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.sample.ViewToken;
import org.vaadin.spring.sample.nested.one.NestedOnePresenter;
import org.vaadin.spring.sample.nested.two.NestedTwoPresenter;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
@UIScope
@VaadinView(name=ViewToken.NESTED)
public class NestedPresenter extends AbstractMvpPresenterView<NestedPresenter.NestedView> implements NestedPresenterHandlers {
	
	public interface NestedView extends MvpView, MvpHasPresenterHandlers<NestedPresenterHandlers> {
		void setNestedView(Component c);
	}
	
	private boolean isEntered = false;
	
	@Autowired
	public NestedPresenter(NestedView view, EventBus eventBus) {
		super(view, eventBus);	
		getView().setPresenterHandlers(this);
	}

	@Autowired
	NestedOnePresenter nestedOnePresenter;
	
	@Autowired
	NestedTwoPresenter nestedTwoPresenter;

	@Override
	public void enter(ViewChangeEvent event) {		
		if (!isEntered) {
			getView().setNestedView(nestedOnePresenter.getViewComponent());
			isEntered = true;
		}
	}

	@Override
	public void showView(String viewToken) {
		if (viewToken.equals(ViewToken.NESTED_ONE)) {
			getView().setNestedView(nestedOnePresenter.getViewComponent());
			
		} else if (viewToken.equals(ViewToken.NESTED_TWO)) {
			getView().setNestedView(nestedTwoPresenter.getViewComponent());
		}
		
	}
	
	@EventBusListenerMethod
	public void onNestedPresenterEvent(Event<NestedPresenterEvent> event) {
		Notification.show("Reseived NestedPresenterEvent from:", event.getPayload().getMessage(), Type.HUMANIZED_MESSAGE);
	}
	
	

}
