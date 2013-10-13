package de.cbrell.birt.reportitem.barcode;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.CharacterSetECI;

public class BarcodeType {
	
	private static Map<String, BarcodeFormat> cachedFormats = new HashMap<String, BarcodeFormat>();
	private static Set<String> cachedEncodings = new HashSet<String>();
	private static Map<String, String> supportedEncodings = new HashMap<String, String>();
	
	static {
		Set<String> allEncodings = Charset.availableCharsets().keySet();
		for(String encoding: allEncodings) {
			if(CharacterSetECI.getCharacterSetECIByName(encoding)!=null) {
				cachedEncodings.add(encoding);
				supportedEncodings.put(encoding.toUpperCase(Locale.ENGLISH), encoding);
			}
		}
		BarcodeFormat[] values = BarcodeFormat.values();
		for(BarcodeFormat value: values) {
			if(isSupportedBarcodeFormat(value)) {
				cachedFormats.put(value.name().toUpperCase(Locale.ENGLISH), value);
			}
		}
	}
	
	private static boolean isSupportedBarcodeFormat(BarcodeFormat format) {
		return (format==BarcodeFormat.EAN_8 || format==BarcodeFormat.EAN_13 || format==BarcodeFormat.UPC_A || 
				format==BarcodeFormat.QR_CODE || format==BarcodeFormat.CODE_39 || format==BarcodeFormat.CODE_128 ||
				format==BarcodeFormat.ITF || format==BarcodeFormat.PDF_417 || format==BarcodeFormat.CODABAR); 
	}
	
	public static List<String> getAllSupportedEncodings() {
		return Collections.unmodifiableList(new ArrayList<String>(cachedEncodings));
	}
	
	public static BarcodeFormat fromString(String format) {
		format = format.toUpperCase(Locale.ENGLISH);
		return cachedFormats.get(format);
	}
	
	public static List<BarcodeFormat> getAllTypes() {
		List<BarcodeFormat> retVal = new ArrayList<BarcodeFormat>();
		for(String key:cachedFormats.keySet()) {
			retVal.add(cachedFormats.get(key));
		}
		return retVal;
	}
	
	public static String[] getAllTypesAsString() {
		Set<String> values = cachedFormats.keySet();
		String[] retVal = new String[values.size()];
		int i = 0;
		for(String value:values) {
			retVal[i] = value;
			i++;
		}
		return retVal;
	}
	
	public static String fromType(BarcodeFormat format) {
		return format.name();
	}
	
	public static boolean isEncodingSupported(String encodingName) {
		return supportedEncodings.get(encodingName.toUpperCase(Locale.ENGLISH))!=null;
	}
	
	public static String getEncodingName(String fuzzyName) {
		return supportedEncodings.get(fuzzyName.toUpperCase(Locale.ENGLISH));
	}
	
	public static String getDefaultEncoding() {
		return "UTF-8";
	}
}
