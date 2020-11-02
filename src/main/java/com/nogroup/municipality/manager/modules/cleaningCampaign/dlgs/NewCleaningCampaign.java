package com.nogroup.municipality.manager.modules.cleaningCampaign.dlgs;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.ccmp.application.MapViewer;
import com.nogroup.municipality.manager.ccmp.wndws.CreateWindow;
import com.vaadin.ui.ComponentContainer;

public class NewCleaningCampaign extends CreateWindow {

	
	public NewCleaningCampaign(VUI ui, MapViewer map, boolean initialize) {
		super(ui, map, initialize);
	}

	@Override
	public void saveButtonClicked() {
		NewCleaningCampaign.this.close();
	}

	@Override
	public String caption() {
		return "New Cleaning Campaign";
	}

	@Override
	public ComponentContainer content() {
		return null;
	}

	@Override
	public void cancelButtonCallBack() {
		NewCleaningCampaign.this.close();
	}

}
