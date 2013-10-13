package de.cbrell.birt.reportitem.barcode.ui.views;

import org.eclipse.birt.report.designer.internal.ui.views.attributes.provider.UnitPropertyDescriptorProvider;
import org.eclipse.birt.report.designer.ui.views.attributes.providers.ChoiceSetFactory;
import org.eclipse.birt.report.designer.util.DEUtil;
import org.eclipse.birt.report.model.api.metadata.IChoiceSet;
import org.eclipse.birt.report.model.api.metadata.IElementPropertyDefn;

import de.cbrell.birt.reportitem.barcode.BarcodeItem;

public class BarcodeUnitPropertyDescriptorProvider extends UnitPropertyDescriptorProvider
{

	public BarcodeUnitPropertyDescriptorProvider( String property, String element ) {
		super( property, element );
	}

	public String[] getUnitItems( ) {
		IChoiceSet choiceSet;
		// use "Barcode Item" instead of "ExtendedItem", following the suggestion of
		// model, bug 170740
		IElementPropertyDefn propertyDefn = DEUtil.getMetaDataDictionary( )
				.getElement( BarcodeItem.EXTENSION_NAME ) //$NON-NLS-1$
				.getProperty( getProperty( ) );
		choiceSet = propertyDefn.getAllowedUnits( );
		if ( choiceSet != null )
		{
			return ChoiceSetFactory.getDisplayNamefromChoiceSet( choiceSet );
		}
		else
		{
			return super.getUnitItems( );
		}

	}

}
