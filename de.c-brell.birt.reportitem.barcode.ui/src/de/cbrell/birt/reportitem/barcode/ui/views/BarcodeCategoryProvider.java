package de.cbrell.birt.reportitem.barcode.ui.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.birt.report.designer.ui.views.attributes.AttributesUtil;
import org.eclipse.birt.report.designer.ui.views.attributes.providers.CategoryProviderFactory;
import org.eclipse.birt.report.designer.ui.views.attributes.providers.ICategoryProvider;
import org.eclipse.birt.report.designer.ui.views.attributes.providers.ICategoryProviderFactory;

public class BarcodeCategoryProvider extends CategoryProviderFactory {
	
	private static ICategoryProviderFactory instance = new BarcodeCategoryProvider();
	
	private BarcodeCategoryProvider() {
	}
	
	public static ICategoryProviderFactory getInstance() {
		return instance;
	}
	
	@Override
	public ICategoryProvider getCategoryProvider(Object input) {
		CategoryHolder categoryHolder = new CategoryHolder(
		new String[]{CATEGORY_KEY_GENERAL},		
		new String[]{"General"},
		new Class[]{BarcodeGeneralPage.class}
		);
		
		List<String> categories = new ArrayList<String>( Arrays.asList( new String[]{
				null,
				CATEGORY_KEY_BORDERS,
				CATEGORY_KEY_MARGIN,
				null,
				CATEGORY_KEY_SECTION,
				CATEGORY_KEY_ALTTEXT,
				CATEGORY_KEY_VISIBILITY,
				CATEGORY_KEY_TOC,
				CATEGORY_KEY_BOOKMARK,
				CATEGORY_KEY_COMMENTS,
				CATEGORY_KEY_USERPROPERTIES,
				CATEGORY_KEY_NAMEDEXPRESSIONS,
				CATEGORY_KEY_ADVANCEPROPERTY,
		} ) );
		
//		if ( AttributesUtil.containCategory( AttributesUtil.EVENTHANDLER ) )
//		{
//			categoryHolder.insertBefore( null,
//					AttributesUtil.EVENTHANDLER,
//					AttributesUtil.getCategoryDisplayName( AttributesUtil.EVENTHANDLER ),
//					EventHandlerPage.class );
//
//			categories.add( categories.size( ) - 1, null );
//		}

		return AttributesUtil.createCategoryProvider( categories.toArray( new String[categories.size( )] ),
				categoryHolder.getKeys( ),
				categoryHolder.getLabels( ),
				categoryHolder.getClasses( ) );
		
	}

}
