package de.cbrell.birt.reportitem.barcode.ui;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin {

	public static final String PLUGIN_ID = "de.c-brell.birt.reportitem.barcode.ui";
	@Override
	public void start(BundleContext ctx) throws Exception {
		super.start(ctx);
	}

	@Override
	public void stop(BundleContext ctx) throws Exception {
		super.stop(ctx);
	}
	
	public static Image loadImageIcon(String imageFileName) {
		Bundle bundle = Platform.getBundle(PLUGIN_ID); 
		ImageDescriptor imgDescr = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path("icons/"+imageFileName), null)); 
		return imgDescr.createImage();
	}

}
