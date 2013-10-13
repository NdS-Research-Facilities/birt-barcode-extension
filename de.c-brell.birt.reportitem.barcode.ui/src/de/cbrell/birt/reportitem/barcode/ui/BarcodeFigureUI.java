package de.cbrell.birt.reportitem.barcode.ui;

import org.eclipse.birt.report.designer.ui.extensions.ReportItemFigureProvider;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.birt.report.model.api.extension.IReportItem;
import org.eclipse.draw2d.IFigure;

import de.cbrell.birt.reportitem.barcode.BarcodeItem;

public class BarcodeFigureUI extends ReportItemFigureProvider {

	@Override
	public IFigure createFigure(final ExtendedItemHandle handle) {
		try {
			final IReportItem item = handle.getReportItem();
			if (item instanceof BarcodeItem)
				return new BarcodeFigure((BarcodeItem) item);
		} catch (final ExtendedElementException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateFigure(final ExtendedItemHandle handle,
			final IFigure figure) {
		try {
			final IReportItem item = handle.getReportItem();
			if (item instanceof BarcodeItem) {
				final BarcodeFigure fig = (BarcodeFigure) figure;
				fig.setBarcodeItem((BarcodeItem) item);
			}
		} catch (final ExtendedElementException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void disposeFigure(final ExtendedItemHandle handle,
			final IFigure figure) {
		((BarcodeFigure) figure).dispose();
	}
}
