package de.cbrell.birt.reportitem.barcode.ui.views;

import org.eclipse.birt.report.designer.internal.ui.views.attributes.page.GeneralPage;
import org.eclipse.birt.report.designer.internal.ui.views.attributes.page.PageConstants;
import org.eclipse.birt.report.designer.internal.ui.views.attributes.provider.ComboPropertyDescriptorProvider;
import org.eclipse.birt.report.designer.internal.ui.views.attributes.provider.ElementIdDescriptorProvider;
import org.eclipse.birt.report.designer.internal.ui.views.attributes.provider.IDescriptorProvider;
import org.eclipse.birt.report.designer.internal.ui.views.attributes.provider.TextPropertyDescriptorProvider;
import org.eclipse.birt.report.designer.internal.ui.views.attributes.section.ComboSection;
import org.eclipse.birt.report.designer.internal.ui.views.attributes.section.ComplexUnitSection;
import org.eclipse.birt.report.designer.internal.ui.views.attributes.section.ISectionHelper;
import org.eclipse.birt.report.designer.internal.ui.views.attributes.section.ISectionHelperProvider;
import org.eclipse.birt.report.designer.internal.ui.views.attributes.section.Section;
import org.eclipse.birt.report.designer.internal.ui.views.attributes.section.SeperatorSection;
import org.eclipse.birt.report.designer.internal.ui.views.attributes.section.TextSection;
import org.eclipse.birt.report.designer.ui.views.ElementAdapterManager;
import org.eclipse.birt.report.model.api.ReportItemHandle;
import org.eclipse.birt.report.model.api.elements.ReportDesignConstants;
import org.eclipse.birt.report.model.elements.interfaces.IStyleModel;
import org.eclipse.birt.report.model.elements.interfaces.ISupportThemeElementConstants;
import org.eclipse.swt.SWT;

public class BarcodeGeneralPage extends GeneralPage
{

	protected void buildContent( )
	{
		TextPropertyDescriptorProvider nameProvider = new TextPropertyDescriptorProvider( ReportItemHandle.NAME_PROP,
				ReportDesignConstants.EXTENDED_ITEM );
		TextSection nameSection = new TextSection( nameProvider.getDisplayName( ),
				container,
				true );
		nameSection.setProvider( nameProvider );
		nameSection.setLayoutNum( 2 );
		nameSection.setGridPlaceholder( 0, true );
		nameSection.setWidth( 200 );
		addSection(BarcodePageSectionId.ELEMENT_NAME, nameSection );
		
		ElementIdDescriptorProvider elementIdProvider = new ElementIdDescriptorProvider( );
		TextSection elementIdSection = new TextSection( elementIdProvider.getDisplayName( ),
				container,
				true );
		elementIdSection.setProvider( elementIdProvider );
		elementIdSection.setWidth( 200 );
		elementIdSection.setLayoutNum( 4 );
		elementIdSection.setGridPlaceholder( 2, true );
		addSection(BarcodePageSectionId.ELEMENT_ID, elementIdSection );

		SeperatorSection seperator1 = new SeperatorSection( container,
				SWT.HORIZONTAL );
		addSection( BarcodePageSectionId.SEPARATOR_1, seperator1 );

		IDescriptorProvider widthProvider = new BarcodeUnitPropertyDescriptorProvider( ReportItemHandle.WIDTH_PROP,
				ReportDesignConstants.EXTENDED_ITEM );
		ComplexUnitSection widthSection = new ComplexUnitSection( widthProvider.getDisplayName( ),
				container,
				true );
		widthSection.setWidth( 200 );
		widthSection.setProvider( widthProvider );
		widthSection.setLayoutNum( 2 );
		addSection( BarcodePageSectionId.BARCODE_WIDTH, widthSection );

		IDescriptorProvider heightProvider = new BarcodeUnitPropertyDescriptorProvider( ReportItemHandle.HEIGHT_PROP,
				ReportDesignConstants.EXTENDED_ITEM );
		ComplexUnitSection heightSection = new ComplexUnitSection( heightProvider.getDisplayName( ),
				container,
				true );
		heightSection.setProvider( heightProvider );
		heightSection.setWidth( 200 );
		heightSection.setLayoutNum( 4 );
		heightSection.setGridPlaceholder( 2, true );
		addSection( BarcodePageSectionId.BARCODE_HEIGHT, heightSection );

		SeperatorSection seperator2 = new SeperatorSection( container,
				SWT.HORIZONTAL );
		addSection( BarcodePageSectionId.SEPERATOR_2, seperator2 );
		
		
		ComboPropertyDescriptorProvider displayProvider = new ComboPropertyDescriptorProvider( IStyleModel.DISPLAY_PROP,
				ReportDesignConstants.STYLE_ELEMENT );
		ComboSection displaySection = new ComboSection( displayProvider.getDisplayName( ),
				container,
				true );
		displaySection.setProvider( displayProvider );
		displaySection.setLayoutNum( 4 );
		displaySection.setGridPlaceholder( 2, true );
		displaySection.setWidth( 200 );
		addSection( BarcodePageSectionId.BARCODE_DISPLAY, displaySection );

		createSections( );
		layoutSections( );
	}

	public boolean canReset( )
	{
		return false;
	}

	protected void applyCustomSections( )
	{
		Object[] helperProviders = ElementAdapterManager.getAdapters( this,
				ISectionHelperProvider.class );
		if ( helperProviders != null )
		{
			for ( int i = 0; i < helperProviders.length; i++ )
			{
				ISectionHelperProvider helperProvider = (ISectionHelperProvider) helperProviders[i];
				if ( helperProvider != null )
				{
					ISectionHelper helper = helperProvider.createHelper( this,
							PageConstants.THEME_HELPER_KEY );
					if ( helper != null )
					{
						Section section = helper.createSection( container,
								ISupportThemeElementConstants.THEME_PROP,
								BarcodePageSectionId.BARCODE_DISPLAY,
								true );
						
						section.setLayoutNum( 6 );
						section.setGridPlaceholder( 4, true );
						
					}
				}
			}
		}
	}

}
