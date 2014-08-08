package org.vaadin.spring.sample.nested;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NestedPresenterEvent implements Serializable {
	
	private final String message;
	
	public NestedPresenterEvent(final String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
