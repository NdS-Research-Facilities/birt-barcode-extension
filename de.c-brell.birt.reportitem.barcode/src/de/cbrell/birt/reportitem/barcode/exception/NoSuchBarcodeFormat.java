package de.cbrell.birt.reportitem.barcode.exception;

import org.eclipse.birt.core.exception.BirtException;

public class NoSuchBarcodeFormat extends BirtException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7256135771400502013L;

	public NoSuchBarcodeFormat(String s) {
		super(s);
	}

}
