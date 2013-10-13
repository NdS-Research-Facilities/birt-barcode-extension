
package de.cbrell.birt.reportitem.barcode.ui.views;

import org.eclipse.birt.report.designer.ui.views.IPageGenerator;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.core.runtime.IAdapterFactory;

import de.cbrell.birt.reportitem.barcode.BarcodeItem;


public class BarcodePageGeneratorFactory implements IAdapterFactory {

	public Object getAdapter(final Object adaptableObject,
			@SuppressWarnings("rawtypes") final Class adapterType) {
		if (adaptableObject instanceof ExtendedItemHandle) {
			final ExtendedItemHandle extendedItemHandle = (ExtendedItemHandle) adaptableObject;
			final String extensionName = extendedItemHandle.getExtensionName();
			if (BarcodeItem.EXTENSION_NAME.equals(extensionName))
				return new BarcodePageGenerator();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Class[] getAdapterList() {
		return new Class[] { IPageGenerator.class };
	}

}
