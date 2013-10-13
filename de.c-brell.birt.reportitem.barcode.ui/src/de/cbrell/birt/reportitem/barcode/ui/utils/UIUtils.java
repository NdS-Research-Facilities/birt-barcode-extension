package de.cbrell.birt.reportitem.barcode.ui.utils;

import java.awt.Toolkit;

public class UIUtils {
	
	public static int screenDpi() {
		int screenDpi = Toolkit.getDefaultToolkit( ).getScreenResolution( );
		if ( screenDpi > 0 ) {
			return screenDpi;
		}
		return 96;
	}

}
