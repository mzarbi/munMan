package com.nogroup.municipality.manager.ccmp.application.dlgs;

import com.nogroup.municipality.manager.data.embedded.VFile;

public interface UploadDialogCallbacks {

	public void onOk(VFile vFile, String string) ;
	public void onCancel() ;
}
