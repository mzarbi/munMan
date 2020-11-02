package com.nogroup.municipality.manager.ccmp.flds;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import com.nogroup.municipality.manager.VUI;
import com.nogroup.municipality.manager.business.FileBundle;
import com.nogroup.municipality.manager.data.embedded.VFile;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.themes.ValoTheme;

public class UploadField extends HorizontalLayout{
	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	private VUI ui;
	private Label status = new Label("Please select a file to upload");
    private ProgressBar pi = new ProgressBar();
    private UploadReceiver receiver = new UploadReceiver();
    private HorizontalLayout progressLayout = new HorizontalLayout();
    private Upload upload = new Upload(null, receiver);
    private Button btn = new Button() ;
    private VFile value = new VFile();
    protected File tempFile;
    protected String fileName;
    protected String mtype;

    
	public UploadField() {
		init() ;
	}
	
	public UploadField(String caption) {
		init() ;
		setCaption(caption);
	}

	private void init() {
		setWidth("100%");
		
		// Slow down the upload
        receiver.setSlow(true);

        addComponent(status);
        addComponent(upload);
        addComponent(btn);
        addComponent(progressLayout);
        
        setExpandRatio(status, 1); 
        
        btn.setIcon(FontAwesome.SCISSORS);
        btn.addStyleName(ValoTheme.BUTTON_TINY);
        // Make uploading start immediately when file is selected
        upload.setImmediateMode(true);
        upload.addStyleName(ValoTheme.BUTTON_TINY);
        upload.setButtonCaption("Select file");

        progressLayout.setSpacing(true);
        progressLayout.setVisible(false);
        progressLayout.addComponent(pi);
        progressLayout.setComponentAlignment(pi, Alignment.MIDDLE_LEFT);

        final Button cancelProcessing = new Button("Cancel");
        cancelProcessing.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                upload.interruptUpload();
            }
        });
        cancelProcessing.setStyleName("small");
        progressLayout.addComponent(cancelProcessing);

        /**
         * =========== Add needed listener for the upload component: start,
         * progress, finish, success, fail ===========
         */

        upload.addStartedListener(new Upload.StartedListener() {
            public void uploadStarted(StartedEvent event) {
                // This method gets called immediatedly after upload is started
                upload.setVisible(false);
                progressLayout.setVisible(true);
                pi.setValue(0f);
                //pi.setPollingInterval(500);
                status.setValue("Uploading file \"" + event.getFilename()
                        + "\"");
            }
        });

        upload.addProgressListener(new Upload.ProgressListener() {
            public void updateProgress(long readBytes, long contentLength) {
                // This method gets called several times during the update
                pi.setValue(new Float(readBytes / (float) contentLength));
            }

        });

        upload.addSucceededListener(new Upload.SucceededListener() {
            public void uploadSucceeded(SucceededEvent event) {
                // This method gets called when the upload finished successfully
                status.setValue("Uploading file \"" + event.getFilename()
                        + "\" succeeded");
            }
        });

        upload.addFailedListener(new Upload.FailedListener() {
            public void uploadFailed(FailedEvent event) {
                // This method gets called when the upload failed
                status.setValue("Uploading interrupted");
            }
        });

        upload.addFinishedListener(new Upload.FinishedListener() {
            public void uploadFinished(FinishedEvent event) {
                // This method gets called always when the upload finished,
                // either succeeding or failing
                progressLayout.setVisible(false);
                upload.setVisible(true);
                /*
                String str = "" ;
                try {
					str = FileUtils.readFileToString(tempFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
                */
                try {
					value = FileBundle.create(fileName, mtype, tempFile) ;
				} catch (IOException e) {
					e.printStackTrace();
				}
                tempFile.delete();
                
            }
        });

	}
	
	public class UploadReceiver implements Receiver {

        private boolean sleep;
        private int total = 0;

        public OutputStream receiveUpload(String filename, String mimetype) {
            fileName = filename;
            mtype = mimetype;
            try {
				tempFile = File.createTempFile(UUID.randomUUID().toString(), ".temp");
			} catch (IOException e) {
				e.printStackTrace();
			}
            try {
				return new FileOutputStream(tempFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
            return null ;
            /*
            return new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    total++;
                    if (sleep && total % 10000 == 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            };*/
        }

        public String getFileName() {
            return fileName;
        }

        public String getMimeType() {
            return mtype;
        }

        public void setSlow(boolean value) {
            sleep = value;
        }

    }

	public VFile getValue() {
		return value;
	}

	public void setValue(VFile value) {
		this.value = value;
	}

	
}
