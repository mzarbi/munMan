package com.nogroup.municipality.manager.modules.moduleChooser;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.modules.cleaningCampaign.CleaningCampaignView;
import com.nogroup.municipality.manager.modules.constructionPermit.ConstructionPermitView;
import com.nogroup.municipality.manager.modules.garbageCollection.GarbageCollectionView;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ModuleChooserView extends VerticalLayout{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private VUI ui;
	
	@SuppressWarnings("serial")
	public ModuleChooserView(VUI ui) {
		this.ui = ui ;
		addStyleName("loginBackground");
		setSizeFull();
		
		HorizontalLayout hznl = new HorizontalLayout() ;
		hznl.addStyleName("your-header");
		hznl.setWidth("100%");
		hznl.addStyleName(ValoTheme.LAYOUT_WELL);
		addComponent(hznl);
		
		Label lbl = new Label("Municipality Manager");
		lbl.addStyleName(ValoTheme.LABEL_H2);
		hznl.addComponent(lbl);
		hznl.setComponentAlignment(lbl, Alignment.MIDDLE_CENTER);
		
		GridLayout gl = new GridLayout(4, 2);
		gl.setSizeFull();
		gl.setSpacing(true);
		addComponent(gl);
		setExpandRatio(gl, 1);
		gl.addComponent(new ModuleDescription("Garbage Collection","backgroundGarbageCollecting",new LayoutClickListener() {
			
			@Override
			public void layoutClick(LayoutClickEvent event) {
				ui.setContent(new GarbageCollectionView(ui));
			}
		}), 0,0);
		gl.addComponent(new ModuleDescription("Cleaning Campaign","backgroundGarbageCollecting", new LayoutClickListener() {
			
			@Override
			public void layoutClick(LayoutClickEvent event) {
				ui.setContent(new CleaningCampaignView(ui));
			}
		}), 0,1);
		gl.addComponent(new ModuleDescription("City Appearance","backgroundGarbageCollecting", new LayoutClickListener() {
			
			@Override
			public void layoutClick(LayoutClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		}), 1,0);
		gl.addComponent(new ModuleDescription("Runoff Water","backgroundGarbageCollecting", new LayoutClickListener() {
			
			@Override
			public void layoutClick(LayoutClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		}), 1,1);
		gl.addComponent(new ModuleDescription("Market Management","backgroundGarbageCollecting", new LayoutClickListener() {
			
			@Override
			public void layoutClick(LayoutClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		}), 2,0);
		gl.addComponent(new ModuleDescription("Infractions","backgroundGarbageCollecting",new LayoutClickListener() {
			
			@Override
			public void layoutClick(LayoutClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		}), 2,1);
		gl.addComponent(new ModuleDescription("Construction Permit","backgroundGarbageCollecting", new LayoutClickListener() {
			
			@Override
			public void layoutClick(LayoutClickEvent event) {
				ui.setContent(new ConstructionPermitView(ui));
				
			}
		}), 3,0);
	}
	
	
	protected class ModuleDescription extends VerticalLayout{
		/**
		 * @author medzied
		 */
		private static final long serialVersionUID = 1L;

		public ModuleDescription(String name, String style,LayoutClickListener listener) {
			setMargin(true);
			setSizeFull();
			addStyleName(ValoTheme.LAYOUT_WELL);
			addStyleName("your-special-panel2");
			//addStyleName(style);
			
			
			Label lbl = new Label(name + "<hr>",ContentMode.HTML);
			lbl.setHeight("40px");
			lbl.addStyleName(ValoTheme.LABEL_H2);
			addComponent(lbl);
			setComponentAlignment(lbl, Alignment.TOP_CENTER);
			
			lbl = new Label("Lorem Ipsum is simply dummy text of the "
					+ "printing and typesetting industry. Lorem Ipsum "
					+ "has been the industry's standard dummy text ever "
					+ "since the 1500s, when an",ContentMode.TEXT);
			addComponent(lbl);
			lbl.setSizeFull();
			setExpandRatio(lbl, 1);
			
			addLayoutClickListener(listener);
			
		}
	}

}
