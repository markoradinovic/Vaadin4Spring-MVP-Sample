package org.vaadin.spring.sample.nested;

import org.vaadin.spring.mvp.MvpPresenterHandlers;

public interface NestedPresenterHandlers extends MvpPresenterHandlers {

	public void showView(String viewToken);
	
}
