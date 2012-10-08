package temp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import no.tornado.databinding.BindingGroup;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class View extends JFrame {

	private JPanel contentPane;
	private JTextField textFirstName;
	private JTextField textLastName;
	private Model model;
	private JButton btnOk;
	private JButton btnSetModel;

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));

		JLabel lblNewLabel = new JLabel("First Name");
		contentPane.add(lblNewLabel, "cell 0 0,alignx trailing");

		textFirstName = new JTextField();
		contentPane.add(textFirstName, "cell 1 0,growx");
		textFirstName.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		contentPane.add(lblLastName, "cell 0 1,alignx trailing");

		textLastName = new JTextField();
		contentPane.add(textLastName, "cell 1 1,growx");
		textLastName.setColumns(10);

		btnOk = new JButton("Ok");
		

		btnSetModel = new JButton("Set Model");
		
		contentPane.add(btnSetModel, "flowx,cell 1 3,alignx right");
		contentPane.add(btnOk, "cell 1 3,alignx right");
		initDataBindings();
	}

	public JTextField getTextFirstName() {
		return textFirstName;
	}

	protected void initDataBindings() {

	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public JButton getBtnSetModel() {
		return btnSetModel;
	}
}
