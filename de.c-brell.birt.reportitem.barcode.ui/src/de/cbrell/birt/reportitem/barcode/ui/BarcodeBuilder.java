package de.cbrell.birt.reportitem.barcode.ui;

import org.eclipse.birt.report.designer.ui.extensions.ReportItemBuilderUI;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.IReportItem;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import de.cbrell.birt.reportitem.barcode.BarcodeItem;

/**
 *
 * @author Brellcx
 *
 */
public class BarcodeBuilder extends ReportItemBuilderUI {
	@Override
	public int open(final ExtendedItemHandle handle) {
		try {
			final IReportItem item = handle.getReportItem();
			
			if (item instanceof BarcodeItem) {
				final BarcodeEditorTrayDialog editor = new BarcodeEditorTrayDialog(Display
						.getCurrent().getActiveShell(), (BarcodeItem) item);
				return editor.open();
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return Window.CANCEL;
	}
}
