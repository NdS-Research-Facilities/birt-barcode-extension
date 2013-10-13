package de.cbrell.birt.reportitem.barcode;

import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.IMessages;
import org.eclipse.birt.report.model.api.extension.IReportItem;
import org.eclipse.birt.report.model.api.extension.ReportItemFactory;

public class BarcodeItemFactory extends ReportItemFactory {

	@Override
	public IReportItem newReportItem(final DesignElementHandle elementHandle) {
		if(elementHandle instanceof ExtendedItemHandle && 
				BarcodeItem.EXTENSION_NAME.equals(((ExtendedItemHandle)elementHandle).getExtensionName())) {
			return new BarcodeItem((ExtendedItemHandle)elementHandle);
		}
		return null;
	}

	@Override
	public IMessages getMessages() {
		return null;
	}
	
}
