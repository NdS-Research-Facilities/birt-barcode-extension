package de.cbrell.birt.reportitem.barcode.ui;


import java.io.ByteArrayInputStream;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

import de.cbrell.birt.reportitem.barcode.BarcodeBuilder;
import de.cbrell.birt.reportitem.barcode.BarcodeData;
import de.cbrell.birt.reportitem.barcode.BarcodeItem;
import de.cbrell.birt.reportitem.barcode.EvalType;
import de.cbrell.birt.reportitem.barcode.ui.utils.BarcodeDefaults;
import de.cbrell.birt.reportitem.barcode.ui.utils.BarcodeUIHelper;
import de.cbrell.birt.reportitem.barcode.ui.utils.UIUtils;

public class BarcodeFigure extends Figure {
	
	
	private BarcodeData lastData;
	private BarcodeItem item;
	private Image cachedImage;
	
	public BarcodeFigure(BarcodeItem item) {
		super();
		this.item = item;
		createImage();
	}
	
	@Override
	public Dimension getMinimumSize(final int hint, final int hint2) {
		return this.getPreferredSize(hint, hint2);
	}
	
	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		if(item==null || item.getWidth()<0.0001|| item.getHeight()<0.0001) {
			if(item==null || item.getBarcodeType()==null) {
				return new Dimension(80, 80);
			}
			return BarcodeDefaults.getDefault(item.getBarcodeType().getStringValue()).getDimension();
		}
		int dpi = Display.getDefault().getDPI().x;
		int width = BarcodeBuilder.convertToPx(item.getWidth(), item.getWidthUnits(), dpi);
		int height = BarcodeBuilder.convertToPx(item.getHeight(), item.getHeightUnits(), dpi);
		return new Dimension(width, height);
	}
	
	public void dispose() {
		if (this.cachedImage != null && !this.cachedImage.isDisposed()) {
			this.cachedImage.dispose();
		}
	}
	
	@Override
	protected void paintClientArea(Graphics graphics) {
		final Rectangle r = this.getClientArea().getCopy();
		final BarcodeData data = new BarcodeData(item);
		
		if (this.cachedImage == null
				|| this.cachedImage.isDisposed() || !data.equals(lastData)) {
			this.lastData = data;

			if (this.cachedImage != null && !this.cachedImage.isDisposed())
				this.cachedImage.dispose();

			createImage();
		}

		if (this.cachedImage != null && !this.cachedImage.isDisposed())
			graphics.drawImage(this.cachedImage, r.x, r.y);
	}
	
	private void createImage() {
		try {
			BarcodeData d = new BarcodeData(item);
			BarcodeDefaults barcodeDefaults = BarcodeDefaults.getDefault(d.getBarcodeType());
			if(d.getContentEvalType()==EvalType.RHINO) {
				d.setContent(barcodeDefaults.getContent());
			}
			
			if(d.getWidth()<0.1 || d.getHeigth()<0.1) {
				d.setHeightUnits("px");
				d.setWidthUnits("px");
				d.setWidth(new Double(barcodeDefaults.getDimension().width));
				d.setHeigth(new Double(barcodeDefaults.getDimension().height));
			}
			
			byte[] barcodeImage = BarcodeBuilder.createBarcodeImage(d, UIUtils.screenDpi());
			ImageData imageData = new ImageData(new ByteArrayInputStream(barcodeImage));
			cachedImage = new Image(Display.getDefault(), imageData);
		} catch(Exception e) {
			cachedImage = BarcodeUIHelper.createIconImage("qr-code.jpg", 80, 80);
		}
	}

	public void setBarcodeItem(BarcodeItem barcodeItem) {
		this.item = barcodeItem;
	}
	
	

}
