package de.cbrell.birt.reportitem.barcode;

import org.eclipse.birt.report.model.api.ExpressionType;

public enum EvalType {
	RHINO, CONSTANT;
	
	public static EvalType fromExpressionType(String expType) {
		if(ExpressionType.CONSTANT.equals(expType)) {
			return EvalType.CONSTANT;
		}
		else if(ExpressionType.JAVASCRIPT.equals(expType)) {
			return EvalType.RHINO;
		}
		return null;
	}
}
