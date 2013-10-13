package de.cbrell.birt.reportitem.barcode;

import org.eclipse.birt.report.model.api.ExpressionHandle;

public class BarcodeData {
	
	private String content;
	private String encoding;
	private String barcodeType;
	
	private EvalType contentEvalType;
	private EvalType encodingEvalType;
	private EvalType barcodeEvalType;
	
	private Double width;
	private Double heigth;
	
	private String heightUnits;
	private String widthUnits;
	
	public BarcodeData(BarcodeItem item) {
		ExpressionHandle barcodeText = item.getBarcodeText();
		contentEvalType = EvalType.fromExpressionType(barcodeText.getType());
		content = barcodeText.getStringValue();
		
		ExpressionHandle expEncoding = item.getEncoding();
		encodingEvalType = EvalType.fromExpressionType(expEncoding.getType());
		encoding = expEncoding.getStringValue();
		
		ExpressionHandle expBarcode = item.getBarcodeType();
		barcodeEvalType = EvalType.fromExpressionType(expBarcode.getType());
		barcodeType = expBarcode.getStringValue();
		
		width = item.getWidth();
		heigth = item.getHeight();
		widthUnits = item.getWidthUnits();
		heightUnits = item.getHeightUnits();
	}
	
	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeigth() {
		return heigth;
	}

	public void setHeigth(Double heigth) {
		this.heigth = heigth;
	}

	public String getHeightUnits() {
		return heightUnits;
	}

	public void setHeightUnits(String heightUnits) {
		this.heightUnits = heightUnits;
	}

	public String getWidthUnits() {
		return widthUnits;
	}

	public void setWidthUnits(String widthUnits) {
		this.widthUnits = widthUnits;
	}

	public String getContent() {
		return content;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getBarcodeType() {
		return barcodeType;
	}

	public EvalType getContentEvalType() {
		return contentEvalType;
	}

	public EvalType getEncodingEvalType() {
		return encodingEvalType;
	}

	public EvalType getBarcodeEvalType() {
		return barcodeEvalType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((barcodeEvalType == null) ? 0 : barcodeEvalType.hashCode());
		result = prime * result
				+ ((barcodeType == null) ? 0 : barcodeType.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((contentEvalType == null) ? 0 : contentEvalType.hashCode());
		result = prime * result
				+ ((encoding == null) ? 0 : encoding.hashCode());
		result = prime
				* result
				+ ((encodingEvalType == null) ? 0 : encodingEvalType.hashCode());
		result = prime * result
				+ ((heightUnits == null) ? 0 : heightUnits.hashCode());
		result = prime * result + ((heigth == null) ? 0 : heigth.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
		result = prime * result
				+ ((widthUnits == null) ? 0 : widthUnits.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BarcodeData other = (BarcodeData) obj;
		if (barcodeEvalType != other.barcodeEvalType)
			return false;
		if (barcodeType == null) {
			if (other.barcodeType != null)
				return false;
		} else if (!barcodeType.equals(other.barcodeType))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (contentEvalType != other.contentEvalType)
			return false;
		if (encoding == null) {
			if (other.encoding != null)
				return false;
		} else if (!encoding.equals(other.encoding))
			return false;
		if (encodingEvalType != other.encodingEvalType)
			return false;
		if (heightUnits == null) {
			if (other.heightUnits != null)
				return false;
		} else if (!heightUnits.equals(other.heightUnits))
			return false;
		if (heigth == null) {
			if (other.heigth != null)
				return false;
		} else if (!heigth.equals(other.heigth))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		if (widthUnits == null) {
			if (other.widthUnits != null)
				return false;
		} else if (!widthUnits.equals(other.widthUnits))
			return false;
		return true;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setBarcodeType(String barcodeType) {
		this.barcodeType = barcodeType;
	}
	
}
