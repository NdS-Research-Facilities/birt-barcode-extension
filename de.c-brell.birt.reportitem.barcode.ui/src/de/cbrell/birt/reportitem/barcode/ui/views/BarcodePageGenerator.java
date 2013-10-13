package de.cbrell.birt.reportitem.barcode.ui.views;

import java.util.List;

import org.eclipse.birt.report.designer.internal.ui.views.attributes.page.BindingPage;
import org.eclipse.birt.report.designer.ui.views.attributes.AbstractPageGenerator;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;


public class BarcodePageGenerator extends AbstractPageGenerator {
	
	private BindingPage bindingPage;
	
	protected void buildItemContent(final CTabItem item) {
		
		if (this.itemMap.containsKey(item) && this.itemMap.get(item) == null) {
			final String title = this.tabFolder.getSelection().getText();

			if(BINDINGTITLE.equals(title)) {
				bindingPage = new BindingPage();
				setPageInput( bindingPage );

				refresh( tabFolder, bindingPage, true );
				item.setControl( bindingPage.getControl( ) );
				itemMap.put( item, bindingPage );
			}
		} else if (this.itemMap.get(item) != null) {
			this.setPageInput(this.itemMap.get(item));
			this.refresh(this.tabFolder, this.itemMap.get(item), false);
		}
	}

	@Override
	public void refresh() {
		this.createTabItems(this.input);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void createTabItems(List input) {

		
			super.createTabItems( input );
			this.input = input;
			basicPage.setInput( input );
			addSelectionListener( this );
			basicPage.refresh( );
			createTabItems( );
			if ( tabFolder.getSelection( ) != null )
				buildItemContent( tabFolder.getSelection( ) );
		
	}

	private void createTabItems() {
		createTabItem( BINDINGTITLE, ATTRIBUTESTITLE );
	}

	public void createControl( Composite parent, Object input )
	{
		setCategoryProvider( BarcodeCategoryProvider.getInstance( )
				.getCategoryProvider( input ) );
		super.createControl( parent, input );
	}
}
