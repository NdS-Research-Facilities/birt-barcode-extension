package de.cbrell.birt.reportitem.barcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.metadata.DimensionValue;
import org.eclipse.birt.report.model.api.util.DimensionUtil;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import de.cbrell.birt.reportitem.barcode.exception.BarcodeGeneratorException;
import de.cbrell.birt.reportitem.barcode.exception.NoSuchBarcodeFormat;

public class BarcodeBuilder {
	
	public static byte[] createBarcodeBytes(BarcodeData data, int dpi) throws BarcodeGeneratorException, NoSuchBarcodeFormat {
		String encoding = data.getEncoding();
		String barcodeType = data.getBarcodeType();
		int width = convertToPx(data.getWidth(), data.getWidthUnits(), dpi);
		int height = convertToPx(data.getHeigth(), data.getHeightUnits(), dpi);
		if(encoding==null) {
			encoding = BarcodeType.getDefaultEncoding();
		}
		String originalEncodingName = encoding;
		encoding = BarcodeType.getEncodingName(encoding);
		if(encoding==null) {
			throw new BarcodeGeneratorException("Encoding "+originalEncodingName+" is not supported on this platform");
		}
		BarcodeFormat barcodeFormat = BarcodeType.fromString(barcodeType);
		if(barcodeFormat==null) {
			throw new NoSuchBarcodeFormat("The barcode format "+barcodeType+" is not supported");
		}
		
		Hashtable<EncodeHintType, Object> hintMap = new Hashtable<EncodeHintType, Object>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		hintMap.put(EncodeHintType.CHARACTER_SET, encoding);
		try {
			Charset charset = Charset.forName(encoding);
		    CharsetEncoder encoder = charset.newEncoder();
		    ByteBuffer byteBuffer = encoder.encode(CharBuffer.wrap(data.getContent()));
		    byte[] textBytes = byteBuffer.array();
		    
		    String encodedText = new String(textBytes, encoding);
		    
			MultiFormatWriter barcodeWriter = new MultiFormatWriter();
			
            BitMatrix bitMatrix = barcodeWriter.encode(encodedText, barcodeFormat, width, height, hintMap);
            BufferedImage img = MatrixToImageWriter.toBufferedImage(bitMatrix);
            img=rotate(img,90);
			
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();		
			ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
			
			ImageIO.write(img, "png", ios);
			ios.flush();
			ios.close();
			return baos.toByteArray();
        }
        catch (Exception ex) {
        	throw new BarcodeGeneratorException(ex);
        }
	}
	
	public static int convertToPx(Double value, String unit, int dpi) {
		if(value!=null && DesignChoiceConstants.UNITS_PX.equals(unit)) {
			return value.intValue();
		}
		
		if(value==null) {
			value = new Double(8);
			unit = DesignChoiceConstants.UNITS_CM;
		}
		
		if(unit==null) {
			unit = DesignChoiceConstants.UNITS_CM;
		}
		
		DimensionValue convertedValue = DimensionUtil.convertTo(value, unit, DesignChoiceConstants.UNITS_PT);
		Double conv = new Double((convertedValue.getMeasure()/72d)*dpi);
		return conv.intValue();
	}

	public static byte[] createBarcodeImage(BarcodeData data, int dpi) throws BarcodeGeneratorException, NoSuchBarcodeFormat {
		byte[] imageBytes = createBarcodeBytes(data, dpi);
		return imageBytes;
//		ImageData imageData = new ImageData(new ByteArrayInputStream(imageBytes));
//		return new Image(Display.getDefault(), imageData);
	}
	
	/**
 * Rotates an image. Actually rotates a new copy of the image.
 * 
 * @param img The image to be rotated
 * @param angle The angle in degrees
 * @return The rotated image
 */
public static Image rotate(Image img, double angle)
{
    double sin = Math.abs(Math.sin(Math.toRadians(angle))),
           cos = Math.abs(Math.cos(Math.toRadians(angle)));

    int w = img.getWidth(null), h = img.getHeight(null);

    int neww = (int) Math.floor(w*cos + h*sin),
        newh = (int) Math.floor(h*cos + w*sin);

    BufferedImage bimg = toBufferedImage(getEmptyImage(neww, newh));
    Graphics2D g = bimg.createGraphics();

    g.translate((neww-w)/2, (newh-h)/2);
    g.rotate(Math.toRadians(angle), w/2, h/2);
    g.drawRenderedImage(toBufferedImage(img), null);
    g.dispose();

    return toImage(bimg);
}
	
	
	
}
