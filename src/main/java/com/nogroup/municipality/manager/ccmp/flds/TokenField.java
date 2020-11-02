package com.nogroup.municipality.manager.ccmp.flds;

import java.util.ArrayList;
import java.util.List;

import com.nogroup.municipality.manager.data.embedded.CMultiLangName;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("deprecation")
public class TokenField extends HorizontalLayout{

	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private ComboBox<CMultiLangName> cbx;
	private HorizontalLayout hznl = new HorizontalLayout() ;
	private List<CMultiLangName> values = new ArrayList<>() ;

	@SuppressWarnings("serial")
	public TokenField(String caption){
		setCaption(caption);
		addComponent(hznl);
		hznl.addStyleName(ValoTheme.LAYOUT_WELL);
		
		cbx = new ComboBox<CMultiLangName>() ;
		cbx.setEmptySelectionAllowed(false);
		cbx.setItems(new CMultiLangName("dd", "ddd", "rrr", "dddd"),new CMultiLangName("ddss", "dddq", "rrr", "dddd"));
		cbx.setWidth("100%");
		cbx.addStyleName(ValoTheme.COMBOBOX_TINY);
		addComponent(cbx);
		cbx.addValueChangeListener(new ValueChangeListener<CMultiLangName>() {

			@SuppressWarnings("deprecation")
			@Override
			public void valueChange(ValueChangeEvent<CMultiLangName> event) {
				for(CMultiLangName tmp : values) {
					if(tmp.toString().equals(event.getValue().toString())) {
						return ;
					}
				}
				Button btn = null;
				hznl.addComponent(btn = new Button(event.getValue().getFrName(), new ClickListener() {
					
					/**
					 * @author medzied
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent ev) {
						values.remove(event.getValue()) ;
						hznl.removeComponent(ev.getButton());
					}
				}));
				btn.setIcon(FontAwesome.SCISSORS);
				btn.addStyleName(ValoTheme.BUTTON_TINY);
				btn.setData(event.getValue().getFrName());
			}
		});
	}

	public List<CMultiLangName> getValues() {
		return values;
	}

	public void setValues(List<CMultiLangName> values) {
		this.values = values;
	}
	
	
}
