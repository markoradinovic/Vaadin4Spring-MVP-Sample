package org.vaadin.spring.sample.home;

import org.vaadin.spring.mvp.MvpPresenterHandlers;

import com.vaadin.ui.Button.ClickListener;

public interface HomePresenterHandlers extends MvpPresenterHandlers {
	
	public ClickListener getDemoBtnClickListener();

}
