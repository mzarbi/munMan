package com.nogroup.municipality.manager.ccmp.flds;

import com.nogroup.municipality.manager.data.embedded.MultiLangName;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class MultiLangNameField extends HorizontalLayout{

	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	
	TextField tfAr = new TextField() ;
	TextField tfFr = new TextField() ;
	TextField tfEn = new TextField() ;
	
	private MultiLangName value = new MultiLangName();
	
	public MultiLangNameField() {
		init() ;
	}

	public MultiLangNameField(String string) {
		init() ;
		setCaption(string);
	}

	private void init() {
		setWidth("100%");
		addComponents(tfAr,tfFr,tfEn);
		tfAr.setPlaceholder("اللغة العربية");
		tfAr.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfAr.addStyleName("right-to-left");
		
		tfFr.setPlaceholder("Français");
		tfFr.addStyleName(ValoTheme.TEXTFIELD_TINY);
		
		tfEn.setPlaceholder("English");
		tfEn.addStyleName(ValoTheme.TEXTFIELD_TINY);
	}

	public MultiLangName getValue() {
		value = new MultiLangName(tfAr.getValue(), tfFr.getValue(), tfEn.getValue()) ;
		return value;
	}

	public void setValue(MultiLangName value) {
		tfAr.setValue(value.getArName());
		tfFr.setValue(value.getFrName());
		tfEn.setValue(value.getEnName());
		this.value = value;
	}
	
	

}
