package temp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdesktop.beansbinding.BeanProperty;
import javax.swing.JTextField;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class Controler {
	private Model model;
	private View view;

	public void show() {
		view.setVisible(true);
	}

	public Controler() {
		view = new View();
		Model tempModel = new Model();
		tempModel.setfName("Test");
		setModel(tempModel);
		
		initDataBindings();
	}
	
	public void setModel(Model model){
		this.model = model;
		BeanProperty<Model, String> modelBeanProperty = BeanProperty
				.create("fName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<Model, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, model,
						modelBeanProperty, view.getTextFirstName(),
						jTextFieldBeanProperty);
		autoBinding.bind();

	}

	protected void initDataBindings() {


		view.getBtnOk().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("First Name: " + model.getfName());
				System.out.println("Last Name: " + model.getlName());
			}
		});

		view.getBtnSetModel().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Model newModel = new Model();
				newModel.setfName("Levi");
				newModel.setlName("Putna");
				setModel(newModel);
			}
		});
	}
}
