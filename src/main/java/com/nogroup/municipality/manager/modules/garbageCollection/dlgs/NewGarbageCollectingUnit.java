package com.nogroup.municipality.manager.modules.garbageCollection.dlgs;

import java.util.Date;
import java.util.UUID;

import com.fo0.advancedtokenfield.main.AdvancedTokenField;
import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.ccmp.application.MapViewer;
import com.nogroup.municipality.manager.ccmp.wndws.CreateWindow;
import com.nogroup.municipality.manager.data.daos.GarbageCollectingUnitD;
import com.nogroup.municipality.manager.data.embedded.GCUType;
import com.nogroup.municipality.manager.data.entities.GarbageCollectingUnit;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.themes.ValoTheme;

public class NewGarbageCollectingUnit extends CreateWindow implements CloseListener{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private GarbageCollectingUnit entity ;
	protected String drawnFeatureId;
	private TextField tfCode;
	private ComboBox<GCUType> tfType;
	private AdvancedTokenField tfAgents;
	
	public NewGarbageCollectingUnit(VUI ui,MapViewer map) {
		super(ui,map);
		addCloseListener(this);
	}

	@Override
	public void saveButtonClicked() {
		fill() ;
		new GarbageCollectingUnitD().create(entity) ;
		NewGarbageCollectingUnit.this.close();
	}

	@Override
	public String caption() {
		return "New Garbage Collecting Unit";
	}

	@Override
	public ComponentContainer content() {
		VerticalLayout vl = new VerticalLayout() ;
		vl.setWidth("100%");
		
		tfCode = new TextField("Code") ;
		tfCode.setValue("GCU_" + UUID.randomUUID().toString().substring(0,3));
		tfCode.setWidth("100%");
		tfCode.addStyleName(ValoTheme.TEXTFIELD_TINY);
		vl.addComponent(tfCode);
		
		tfType = new ComboBox<GCUType>("Type") ;
		tfType.setEmptySelectionAllowed(false);
		tfType.setItems(GCUType.values());
		tfType.setWidth("100%");
		tfType.addStyleName(ValoTheme.COMBOBOX_TINY);
		vl.addComponent(tfType);
		
		return vl ;
	}

	@Override
	public void cancelButtonCallBack() {
		NewGarbageCollectingUnit.this.close();
	}
	
	private void fill() {
		//entity.setAgents(tfAgents.getValue());
		entity.setCode(tfCode.getValue());
		entity.setdCreated(new Date());
		entity.setLastKnownLocation(null);
		entity.setType(tfType.getValue());
	}

	@Override
	public void windowClose(CloseEvent e) {
		getMap().removeFeatureFromLayer("SELCTION LAYER",drawnFeatureId) ;
	}

}
