package de.cbrell.birt.reportitem.barcode;

import java.awt.Toolkit;
import java.io.ByteArrayInputStream;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.extension.IBaseResultSet;
import org.eclipse.birt.report.engine.extension.ICubeResultSet;
import org.eclipse.birt.report.engine.extension.IQueryResultSet;
import org.eclipse.birt.report.engine.extension.ReportItemPresentationBase;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;

import com.google.zxing.BarcodeFormat;

import de.cbrell.birt.reportitem.barcode.exception.BarcodeGeneratorException;
import de.cbrell.birt.reportitem.barcode.exception.NoSuchBarcodeFormat;

public class BarcodePresentationImpl extends ReportItemPresentationBase {
	private BarcodeItem barcodeItem;
	
	
	
	@Override
	public void setModelObject(ExtendedItemHandle modelHandle) {
		try {
			barcodeItem = (BarcodeItem)modelHandle.getReportItem();
		} catch(ExtendedElementException e) {
			
		}
	}
	
	@Override
	public int getOutputType() {
		return OUTPUT_AS_IMAGE;
	}
	
	private String getResult(String input, EvalType type, IBaseResultSet[] results) throws BirtException {
		if(type==EvalType.CONSTANT) {
			return input;
		}
		else if(type==EvalType.RHINO){
			return this.evaluate(input, results);
		}
		else {
			throw new RuntimeException(type+ " is not a valid type");
		}
	}
	
	@Override
	public Object onRowSets(IBaseResultSet[] results) throws BirtException {
		if(this.barcodeItem==null) {
			return null;
		}
		BarcodeData data = new BarcodeData(barcodeItem);
		String text = getResult(data.getContent(), data.getContentEvalType(), results);
		if (text == null || text.length() == 0)
			text = " ";
		String encoding = getResult(data.getEncoding(), data.getEncodingEvalType(), results);
		if(encoding==null) {
			encoding = BarcodeType.getDefaultEncoding();
		}
		String originalEncodingName = encoding;
		encoding = BarcodeType.getEncodingName(encoding);
		
		
		
		if(encoding==null) {
			throw new BarcodeGeneratorException("Encoding "+originalEncodingName+" is not supported on this platform");
		}
		String barcodeType = getResult(data.getBarcodeType(), data.getBarcodeEvalType(), results);
		BarcodeFormat barcodeFormat = BarcodeType.fromString(barcodeType);
		if(barcodeFormat==null) {
			throw new NoSuchBarcodeFormat("The barcode format "+barcodeType+" is not supported");
		}
		
		data.setContent(text);
		data.setEncoding(encoding);
		data.setBarcodeType(barcodeType);
		
		byte[] barcodeBytes = BarcodeBuilder.createBarcodeBytes(data, getRenderDpi());
		return new ByteArrayInputStream(barcodeBytes);
		
//		Hashtable<EncodeHintType, Object> hintMap = new Hashtable<EncodeHintType, Object>();
//		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//		hintMap.put(EncodeHintType.CHARACTER_SET, encoding);
//		
//		try {
//			Charset charset = Charset.forName(encoding);
//		    CharsetEncoder encoder = charset.newEncoder();
//		    ByteBuffer byteBuffer = encoder.encode(CharBuffer.wrap(text));
//		    byte[] textBytes = byteBuffer.array();
//		    
//		    String encodedText = new String(textBytes, encoding);
//		    
//			MultiFormatWriter barcodeWriter = new MultiFormatWriter();
//            BitMatrix bitMatrix = barcodeWriter.encode(encodedText, barcodeFormat, 80, 80, hintMap);
//            BufferedImage img = MatrixToImageWriter.toBufferedImage(bitMatrix);
//           
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
//			
//			ImageIO.write(img, "png", ios);
//			ios.flush();
//			ios.close();
//			ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
//			return bis;
//        }
//        catch (Exception ex) {
//        	throw new BarcodeGeneratorException(ex);
//        }
	}
	
	private String evaluate(final String expression,
			final IBaseResultSet[] results) throws BirtException {
		String text = null;
		if (expression == null || "null".equalsIgnoreCase(expression))
			return null;
		if (results != null && results.length > 0) {
			final IBaseResultSet baseResultSet = results[0];
			if (baseResultSet instanceof IQueryResultSet) {
				final IQueryResultSet queryResultSet = (IQueryResultSet) baseResultSet;
				if (queryResultSet.isBeforeFirst())
					queryResultSet.next();
				final Object object = queryResultSet.evaluate(expression);
				text = String.valueOf(object);
			} else if (baseResultSet instanceof ICubeResultSet) {
				final ICubeResultSet cubeResultSet = (ICubeResultSet) baseResultSet;
				final Object object = cubeResultSet.evaluate(expression);
				text = String.valueOf(object);
			} else if (baseResultSet != null)
				text = baseResultSet.getClass().getName() + " " + expression;
			else
				text = expression;
		} else {
			final Object object = this.context.evaluate(expression);
			text = String.valueOf(object);
		}
		return text;
	}
	
	public int getRenderDpi( )
	{
		Object renderDpi = context.getRenderOption( )
				.getOption( IRenderOption.RENDER_DPI );
		if ( renderDpi!=null && renderDpi instanceof Integer )
		{
			return (Integer) renderDpi;
		}
		
		modelHandle = barcodeItem.getModelHandle();
		
		if ( modelHandle.getModuleHandle( ) instanceof ReportDesignHandle )
		{
			dpi = ( (ReportDesignHandle) modelHandle.getModuleHandle( ) ).getImageDPI( );
		}
		
		if ( modelHandle!=null && modelHandle.getRoot( ) instanceof ReportDesignHandle )
		{
			int imageDpi = ( (ReportDesignHandle) modelHandle.getRoot( ) ).getImageDPI( );
			if ( imageDpi > 0 )
			{
				return imageDpi;
			}
		}
		try
		{
			int screenDpi = Toolkit.getDefaultToolkit( ).getScreenResolution( );
			if ( screenDpi > 0 )
			{
				return screenDpi;
			}
		}
		catch ( Exception e )
		{
			// Since there isn't related display device under some cases, the calling
			// 'Toolkit.getDefaultToolkit( ).getScreenResolution( );' will throw
			// HeadlessException. Here we just catch this exception to avoid
			// breaking program, and still return 96 as default DPI.
		}
		return 96;
	}
}
