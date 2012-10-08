package temp;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model {

	private String fName;
	private String lName;

	private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	private void notifyListeners() {
		for (Iterator iterator = listener.iterator(); iterator.hasNext();) {
			PropertyChangeListener name = (PropertyChangeListener) iterator
					.next();
			name.propertyChange(null);
		}
	}

	public void addChangeListener(PropertyChangeListener newListener) {
		listener.add(newListener);
	}

}
