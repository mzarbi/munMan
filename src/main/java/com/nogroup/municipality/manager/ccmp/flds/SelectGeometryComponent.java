package com.nogroup.municipality.manager.ccmp.flds;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.ccmp.others.OnCissorsClicked;
import com.nogroup.municipality.manager.ccmp.others.OnGeometryDrawnWindowAction;
import com.nogroup.municipality.manager.ccmp.others.OnVPolygonDrawn;
import com.nogroup.municipality.manager.data.embedded.VPolygon;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class SelectGeometryComponent extends HorizontalLayout implements ClickListener {

	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	
	private VUI ui;
	private TextField latF = new TextField() ;
	private TextField lonF = new TextField() ;
	private Button btn = new Button() ;
	private Button btn2 = new Button() ;
	private VPolygon value = null ;
	private OnGeometryDrawnWindowAction externalCallback;
	private boolean removeOnClose = false;

	private OnCissorsClicked onCissorsClicked;

	
	public SelectGeometryComponent(VUI vui, OnGeometryDrawnWindowAction onGeometryDrawnWindowAction) {
		this.ui = vui ;
		this.externalCallback = onGeometryDrawnWindowAction ;
		init();
		
		setCaption("Select Geometry");
	}
	
	public SelectGeometryComponent(VUI vui, OnGeometryDrawnWindowAction onGeometryDrawnWindowAction, boolean removeOnClose) {
		this.ui = vui ;
		this.externalCallback = onGeometryDrawnWindowAction ;
		this.removeOnClose  = removeOnClose ;
		init();
		
		setCaption("Select Geometry");
	}

	public void init() {
		setWidth("100%");
		
		latF.addStyleName(ValoTheme.TEXTFIELD_TINY);
		latF.setPlaceholder("Lattitude");
		latF.setWidth("100%");
		
		lonF.setWidth("100%");
		lonF.setPlaceholder("Longitude");
		lonF.addStyleName(ValoTheme.TEXTFIELD_TINY);
		
		HorizontalLayout hznl = new HorizontalLayout() ;
		hznl.setWidth("100%");
		hznl.addComponent(latF);
		hznl.addComponent(lonF);
		addComponent(hznl);
		setExpandRatio(hznl, 1);
		
		btn.addStyleName(ValoTheme.BUTTON_TINY);
		btn.setIcon(FontAwesome.MAP_MARKER);
		btn.setWidth("40px");
		addComponent(btn);
		btn.addClickListener(this) ;
		
		btn2.addStyleName(ValoTheme.BUTTON_TINY);
		btn2.setIcon(FontAwesome.SCISSORS);
		btn2.setWidth("40px");
		addComponent(btn2);
		btn2.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				latF.clear();
				lonF.clear();
				value = null ;
				if(onCissorsClicked != null) {
					onCissorsClicked.action("");
				}
			}
		});
	}

	public VPolygon getValue() {
		return value;
	}

	public void setValue(VPolygon value) {
		latF.setValue("" + value.getCentroid().getLatitude());
		lonF.setValue("" + value.getCentroid().getLongitude());
		this.value = value;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		externalCallback.before("");
		this.ui.mapInstance().drawPolygon(new OnVPolygonDrawn() {
			
			@Override
			public void back(String id,VPolygon vp) {
				value = vp ;
				latF.setValue("" + vp.getCentroid().getLatitude());
				lonF.setValue("" + vp.getCentroid().getLongitude());
				externalCallback.after(id);
			}
		},removeOnClose);
	}
	
	public void setOnCissorsClicked(OnCissorsClicked onCissorsClicked) {
		this.onCissorsClicked = onCissorsClicked ;
	}
}
