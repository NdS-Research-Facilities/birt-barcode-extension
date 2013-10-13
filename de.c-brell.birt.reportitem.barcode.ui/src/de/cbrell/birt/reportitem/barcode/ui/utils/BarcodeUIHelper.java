package de.cbrell.birt.reportitem.barcode.ui.utils;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import de.cbrell.birt.reportitem.barcode.ui.Activator;

public class BarcodeUIHelper {
	
	public static Image createIconImage(String fileName, int width, int height) {
		Image img = Activator.loadImageIcon(fileName);
		Image scaledImg = new Image(Display.getDefault(), img.getImageData().scaledTo(width, height));
		img.dispose();
		return scaledImg;
	}

}
