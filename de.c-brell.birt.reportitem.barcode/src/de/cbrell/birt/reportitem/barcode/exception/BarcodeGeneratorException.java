package de.cbrell.birt.reportitem.barcode.exception;

import org.eclipse.birt.core.exception.BirtException;

public class BarcodeGeneratorException extends BirtException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4016462462048415050L;

	public BarcodeGeneratorException(String s) {
		super(s);
	}
	
	public BarcodeGeneratorException(Exception e) {
		this(e.getClass().getName()+":"+e.getMessage());
	}

}
