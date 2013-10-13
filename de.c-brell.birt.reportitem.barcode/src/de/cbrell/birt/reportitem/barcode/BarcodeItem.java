package de.cbrell.birt.reportitem.barcode;

import org.eclipse.birt.report.model.api.Expression;
import org.eclipse.birt.report.model.api.ExpressionHandle;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.extension.ReportItem;

public class BarcodeItem extends ReportItem {
	public static final String EXTENSION_NAME = "BarcodeItem";
	public static final String BARCODE_TYPE = "barcodeType";
	public static final String BARCODE_CONTENT = "content";
	public static final String BARCODE_ENCODING = "encoding";
	
	private ExtendedItemHandle modelHandle;
	
	public BarcodeItem(ExtendedItemHandle modelHandle) {
		this.modelHandle = modelHandle;
	}

	public ExpressionHandle getEncoding() {
		return modelHandle.getExpressionProperty(BARCODE_ENCODING);
	}
	
	public void setEncoding(Expression expression) throws SemanticException {
		modelHandle.setExpressionProperty(BARCODE_ENCODING, expression);
	}

	public ExpressionHandle getBarcodeType() {
		return modelHandle.getExpressionProperty(BARCODE_TYPE);
	}
	
	public void setBarcodeType(Expression expression) throws SemanticException {
		modelHandle.setExpressionProperty(BARCODE_TYPE, expression);
	}
	
	public ExpressionHandle getBarcodeText() {
		return modelHandle.getExpressionProperty(BARCODE_CONTENT);
	}
	
	public void setBarcodeText(Expression exp) throws SemanticException {
		modelHandle.setExpressionProperty(BARCODE_CONTENT, exp);
	}
	
	public ExtendedItemHandle getModelHandle() {
		return modelHandle;
	}
	
	public Double getHeight() {
		return modelHandle.getHeight()==null ? null : modelHandle.getHeight().getMeasure();
	}
	
	public Double getWidth() {
		return modelHandle.getWidth()==null ? null : modelHandle.getWidth().getMeasure();
	}
	
	public String getHeightUnits() {
		return modelHandle.getHeight()==null ? null : modelHandle.getHeight().getUnits();
	}
	
	public String getWidthUnits() {
		return modelHandle.getWidth()==null ? null : modelHandle.getWidth().getUnits();
	}

}
