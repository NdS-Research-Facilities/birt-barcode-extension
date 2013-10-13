package de.cbrell.birt.reportitem.barcode.ui;

import java.util.Comparator;

import org.eclipse.birt.report.designer.internal.ui.dialogs.expression.ExpressionButton;
import org.eclipse.birt.report.designer.internal.ui.util.ExpressionButtonUtil;
import org.eclipse.birt.report.designer.ui.dialogs.ExpressionProvider;
import org.eclipse.birt.report.model.api.Expression;
import org.eclipse.birt.report.model.api.ExpressionType;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.cbrell.birt.reportitem.barcode.BarcodeItem;
import de.cbrell.birt.reportitem.barcode.BarcodeType;
import de.cbrell.birt.reportitem.barcode.EvalType;

public class BarcodeEditorTrayDialog extends TrayDialog {
	protected BarcodeItem barcodeItem;
	
	private Text barcodeText;

	private ComboViewer barcodeTypeComboViewer;

	private ComboViewer barcodeEncodingComboViewer;
	
	//private ExpressionButton barcodeTypeExpressionButton;
	//private ExpressionButton barcodeEncodingExpressionButton;
	//private ExpressionButton barcodeTextExpressionButton;

	
	protected BarcodeEditorTrayDialog(final Shell shell,
			final BarcodeItem textItem) {
		super(shell);
		this.barcodeItem = textItem;
	}
	
	@Override
	protected void configureShell(final Shell newShell) {
		super.configureShell(newShell);
		newShell.setMinimumSize(400, 400);
		newShell.setText("Barcode Settings"); //$NON-NLS-1$
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(3, false));
		
		Label lblBarcodeType = new Label(container, SWT.NONE);
		lblBarcodeType.setText("Barcode Type:");
		
		barcodeTypeComboViewer = new ComboViewer(container, SWT.NONE);
		barcodeTypeComboViewer.setContentProvider(ArrayContentProvider.getInstance());
		barcodeTypeComboViewer.setComparator(new ViewerComparator(new StringComparator()));
		barcodeTypeComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				validate();
			}
		});
		Combo barcodeTypeCombo = barcodeTypeComboViewer.getCombo();
		barcodeTypeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		
//		Listener listener = new Listener( ) {
//
//			public void handleEvent( Event event )
//			{
//				System.out.println(event.toString());
//				
//				if ( event.data instanceof String[] )
//				{
//					
//				}
//				
//				
//				
//			}
//
//		};
		//barcodeTypeExpressionButton = ExpressionButtonUtil.createExpressionButton(container, barcodeTypeComboViewer.getControl(), new ExpressionProvider(barcodeItem.getModelHandle()), barcodeItem.getModelHandle(), listener, true, SWT.PUSH);
		new Label(container, SWT.NONE);
		
		Label lblEncoding = new Label(container, SWT.NONE);
		lblEncoding.setText("Encoding:");
		
		barcodeEncodingComboViewer = new ComboViewer(container, SWT.NONE);
		barcodeEncodingComboViewer.setContentProvider(ArrayContentProvider.getInstance());
		barcodeEncodingComboViewer.setComparator(new ViewerComparator(new StringComparator()));
		barcodeEncodingComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				validate();
			}
		});
		Combo barcodeEncodingCombo = barcodeEncodingComboViewer.getCombo();
		barcodeEncodingCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		//barcodeEncodingExpressionButton = ExpressionButtonUtil.createExpressionButton(container, barcodeEncodingComboViewer.getControl(), new ExpressionProvider(barcodeItem.getModelHandle()), barcodeItem.getModelHandle(), true, SWT.PUSH);
		new Label(container, SWT.NONE);
		
		Label lblBarcodeText = new Label(container, SWT.NONE);
		lblBarcodeText.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblBarcodeText.setText("Barcode Text:");
		
		barcodeText = new Text(container, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		barcodeText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		barcodeText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				validate();
			}
		});
		
		//barcodeTextExpressionButton = 
		ExpressionButtonUtil.createExpressionButton(container, barcodeText, new ExpressionProvider(barcodeItem.getModelHandle()), barcodeItem.getModelHandle(), true, SWT.PUSH);
		

		initElements();
		setContent();
		
		return container;
	}
	
	private void initElements() {
		barcodeTypeComboViewer.setInput(BarcodeType.getAllTypesAsString());
		barcodeEncodingComboViewer.setInput(BarcodeType.getAllSupportedEncodings());
	}
	
	private void setContent() {
		if(barcodeItem.getBarcodeType()!=null && barcodeItem.getBarcodeType().getStringValue()!=null) {
			barcodeTypeComboViewer.setSelection(new StructuredSelection(barcodeItem.getBarcodeType().getStringValue()));
		}
		if(barcodeItem.getEncoding()!=null && barcodeItem.getEncoding().getStringValue()!=null) {
			barcodeEncodingComboViewer.setSelection(new StructuredSelection(barcodeItem.getEncoding().getStringValue()));
		}
		
		if(barcodeItem.getBarcodeText()!=null && EvalType.fromExpressionType(barcodeItem.getBarcodeText().getType())==EvalType.RHINO) {
			barcodeText.setData(ExpressionButtonUtil.EXPR_TYPE, ExpressionType.JAVASCRIPT);
		}
		else {
			barcodeText.setData(ExpressionButtonUtil.EXPR_TYPE, ExpressionType.CONSTANT);
		}
		ExpressionButton button = (ExpressionButton) barcodeText.getData( ExpressionButtonUtil.EXPR_BUTTON );
		if ( button != null )
			button.refresh( );
		
		if(barcodeItem.getBarcodeText()!=null && barcodeItem.getBarcodeText().getStringValue()!=null) {
			barcodeText.setText(barcodeItem.getBarcodeText().getStringValue());
		}
	}
	
	private void validate() {
		
		Button button = this.getButton(IDialogConstants.OK_ID);
		if(button==null) {
			return;
		}
		if(!barcodeTypeComboViewer.getSelection().isEmpty() && 
				!barcodeEncodingComboViewer.getSelection().isEmpty() && 
				barcodeText.getText()!=null && 
				!"".equals(barcodeText.getText().trim())) {
			button.setEnabled(true);
		}
		else {
			button.setEnabled(false);
		}
	}
	
	@Override
	protected Control createButtonBar(Composite parent) {
		Control c = super.createButtonBar(parent);
		validate();
		return c;
	}
	
	@Override
	protected void okPressed() {
		try {
			StructuredSelection selection = (StructuredSelection)barcodeTypeComboViewer.getSelection();
			String barcodeTypeText = (String)selection.getFirstElement();
			barcodeItem.setBarcodeType(new Expression(barcodeTypeText, ExpressionType.CONSTANT));
			
			selection = (StructuredSelection)barcodeEncodingComboViewer.getSelection();
			String encodingText = (String)selection.getFirstElement();
			barcodeItem.setEncoding(new Expression(encodingText, ExpressionType.CONSTANT));
			
			String txt = barcodeText.getText();
			String expType = (String)barcodeText.getData(ExpressionButtonUtil.EXPR_TYPE);
			barcodeItem.setBarcodeText(new Expression(txt, expType));
			
		} catch (SemanticException e) {
		}
		super.okPressed();
		
	}
	
	private static class StringComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			if(o1==null || o2==null) {
				return 0;
			}
			return o1.compareTo(o2);
		}
		
	}



}
