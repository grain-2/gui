package book.demo;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

public class ExpandStatusComp extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ExpandStatusComp(Composite parent, int style) {
		super(parent, style);
		setSize(new Point(200, 33));
		setLayout(new GridLayout(2, false));
		
		DateTime dateTime = new DateTime(this, SWT.BORDER);
		
		Spinner spinner = new Spinner(this, SWT.BORDER);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
