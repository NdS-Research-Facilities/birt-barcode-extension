package de.cbrell.birt.reportitem.barcode.ui.utils;

import java.util.HashMap;

import org.eclipse.draw2d.geometry.Dimension;

public class BarcodeDefaults {
	
	private static HashMap<String, BarcodeDefaults> barcodeDefaults = new HashMap<String, BarcodeDefaults>();
	
	private String content;
	private Dimension dimension;
	
	static {
		barcodeDefaults.put("EAN_8", new BarcodeDefaults("12345670", new Dimension(90, 35)));
		barcodeDefaults.put("EAN_13", new BarcodeDefaults("1234567890128", new Dimension(90, 35)));
		barcodeDefaults.put("UPC_A", new BarcodeDefaults("12345678901", new Dimension(90, 35)));
		barcodeDefaults.put("QR_CODE", new BarcodeDefaults("BIRT rocks!", new Dimension(90, 90)));
		barcodeDefaults.put("CODE_39", new BarcodeDefaults("ABCDE12345", new Dimension(90, 90)));
		barcodeDefaults.put("CODE_128", new BarcodeDefaults("BIRT rocks", new Dimension(90, 90)));
		barcodeDefaults.put("ITF", new BarcodeDefaults("30712345000010", new Dimension(90, 35)));
		barcodeDefaults.put("PDF_417", new BarcodeDefaults("BIRT rocks!", new Dimension(90, 90)));
		barcodeDefaults.put("CODABAR", new BarcodeDefaults("A1234A", new Dimension(90, 35)));
	}
	
	private BarcodeDefaults(String content, Dimension dimension) {
		this.content = content;
		this.dimension = dimension;
	}
	
	public String getContent() {
		return content;
	}
	
	public Dimension getDimension() {
		return dimension;
	}
	
	public static BarcodeDefaults getDefault(String barcode) {
		return barcodeDefaults.get(barcode);
	}

}