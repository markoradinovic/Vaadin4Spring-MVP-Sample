package org.vaadin.spring.sample.nested.two;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.mvp.MvpHasPresenterHandlers;
import org.vaadin.spring.mvp.MvpView;
import org.vaadin.spring.mvp.presenter.AbstractMvpPresenter;
import org.vaadin.spring.sample.nested.NestedPresenterEvent;

@SuppressWarnings("serial")
@UIScope
@VaadinComponent
public class NestedTwoPresenter extends AbstractMvpPresenter<NestedTwoPresenter.NestedTwoView> implements NestedTwoPresenterHandlers {
	
	public interface NestedTwoView extends MvpView, MvpHasPresenterHandlers<NestedTwoPresenterHandlers> {
		
	}
	
	@Autowired	
	public NestedTwoPresenter(NestedTwoView view, EventBus eventBus) {
		super(view, eventBus);	
		getView().setPresenterHandlers(this);
	}

	@Override
	public void sendEvent() {
		getEventBus().publish(EventScope.UI, this, new NestedPresenterEvent(getClass().getName()));
		
	}
	
}
