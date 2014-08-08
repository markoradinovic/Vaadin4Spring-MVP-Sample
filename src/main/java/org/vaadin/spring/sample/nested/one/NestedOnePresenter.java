package org.vaadin.spring.sample.nested.one;

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
public class NestedOnePresenter extends AbstractMvpPresenter<NestedOnePresenter.NestedOneView> implements NestedOnePresenterHandlers {
	
	public interface NestedOneView extends MvpView, MvpHasPresenterHandlers<NestedOnePresenterHandlers> {
		
	}
	
	@Autowired
	public NestedOnePresenter(NestedOneView view, EventBus eventBus) {
		super(view, eventBus);	
		getView().setPresenterHandlers(this);
	}

	@Override
	public void sendEvent() {
		getEventBus().publish(EventScope.UI, this, new NestedPresenterEvent(getClass().getName()));		
	}

	

}
