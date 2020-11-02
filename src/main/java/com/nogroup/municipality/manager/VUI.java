package com.nogroup.municipality.manager;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;

import com.nogroup.municipality.manager.business.InitDatabase;
import com.nogroup.municipality.manager.ccmp.dlgs.ConfirmDialog;
import com.nogroup.municipality.manager.data.bundles.SessionLang;
import com.nogroup.municipality.manager.modules.login.LoginView;
import com.nogroup.municipality.manager.modules.moduleChooser.ModuleChooserView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("VTheme")
//@JavaScript(value = Constants.host + "VAADIN/themes/VTheme/js/login.js")
public class VUI extends UI {

	private static final long serialVersionUID = 1L;
	private static final int POLL_INTERVAL = 10000;
	public SessionLang lang = SessionLang.ENGLISH;
	
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        try {
			InitDatabase.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        if(getSession().getAttribute("username") == null) {
        	//setContent(new LoginView(this));
        	setContent(new ModuleChooserView(this));
        }else {
        	setContent(new ModuleChooserView(this));
        }
        
    }

    @WebServlet(urlPatterns = "/*", name = "VUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = VUI.class, productionMode = false)
    public static class VUIServlet extends VaadinServlet {
    }

	public Object mapInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}
