package org.vaadin.spring.sample;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.VaadinUI;
import org.vaadin.spring.navigator.SpringViewProvider;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@VaadinUI
@Title("Vaadin4Spring MVP Nested demo")
@Theme("valo")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {
	
	@Autowired
	SpringViewProvider springViewProvider;
	
	@Autowired
	MainLayout mainLayout;

    @Override
    protected void init(VaadinRequest request) {
    	setLocale(new Locale.Builder().setLanguage("sr").setScript("Latn").setRegion("RS").build());
    	
        Navigator navigator = new Navigator(MyVaadinUI.this, (ViewDisplay)mainLayout);
        navigator.addProvider(springViewProvider);
        
        navigator.addViewChangeListener(mainLayout);
        setContent(mainLayout);
    }

}
