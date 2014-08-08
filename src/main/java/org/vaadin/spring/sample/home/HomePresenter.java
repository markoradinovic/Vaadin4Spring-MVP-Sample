package org.vaadin.spring.sample.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.mvp.MvpHasPresenterHandlers;
import org.vaadin.spring.mvp.MvpView;
import org.vaadin.spring.mvp.presenter.AbstractMvpPresenterView;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.sample.ViewToken;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
@UIScope
@VaadinView(name=ViewToken.HOME)
public class HomePresenter extends AbstractMvpPresenterView<HomePresenter.HomeView> implements HomePresenterHandlers {
	
	public interface HomeView extends MvpView, MvpHasPresenterHandlers<HomePresenterHandlers> {
		public void initView();
		public void showMessage(String message);
	}
	
	private int count = 0;
	
	@Autowired
	public HomePresenter(HomeView view, EventBus eventBus) {
		super(view, eventBus);
		getView().setPresenterHandlers(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {		
		getView().initView();
		count=0;
	}

	@Override
	public ClickListener getDemoBtnClickListener() {		
		return new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				count++;
				getView().showMessage("Button was clicked " + count + " times");				
			}
		};
	}

	
	
}
