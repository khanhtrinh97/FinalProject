package gui;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import da.BrandDA;
import da.CategoryDA;
import da.ProductDA;
import da.UnitOfMeasureDA;
import da.UserDA;
import da.WarehouseDA;
import dataobject.Brand;
import dataobject.Category;
import dataobject.Product;
import dataobject.UnitOfMeasure;
import dataobject.User;
import dataobject.Warehouse;

public class UpdateUser extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	
	//private ProductDA productDA;
	//private CategoryDA catDA;
	private UserDA userDA;;
	//private UnitOfMeasureDA unitDA;
	
	private JButton btnUpdate;
	private JButton btnCancel;

	public int usID = 1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateUser frame = new UpdateUser(1);
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param selectedProductID 
	 */
	public UpdateUser(int usid) {
		usID = usid;
		//productDA = new ProductDA();
		//catDA = new CategoryDA();
		userDA = new UserDA();
		//unitDA = new UnitOfMeasureDA();
		
		initGUI();
		
		
		User us = userDA.getUser(usID);
		txtUsername.setText(us.getName());
		txtPassword.setText(us.getPassword());
		
		
		
		
	}

	private void initGUI() {
		setResizable(false);
		setTitle("Update User - Trinh");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("User Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(26, 88, 102, 20);
		contentPane.add(lblName);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(138, 90, 173, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lbProductCode = new JLabel("Password");
		lbProductCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbProductCode.setBounds(26, 144, 89, 14);
		contentPane.add(lbProductCode);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(138, 143, 173, 20);
		contentPane.add(txtPassword);
		
		JLabel lblAddProduct = new JLabel("Update User");
		lblAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddProduct.setBounds(56, 27, 230, 30);
		contentPane.add(lblAddProduct);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(56, 241, 89, 23);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(180, 241, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(this);
		//Vector<Category> catList = catDA.getAllCategories();
		//Vector<UnitOfMeasure>unitList = unitDA.getUnitOfMeasure();
		//Vector<Brand> brandList = brandDA.getBrands();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnUpdate){
			updateUser();
			userDA.getAllUsers();
			JOptionPane.showMessageDialog(this,"updated completed");
			
			UserList frame1 = new UserList();
			frame1.setVisible(true);
		}else if(e.getSource() == btnCancel){
			UpdateUser.this.dispose();
		}
		
	}

	private void updateUser() {
		String Name = txtUsername.getText();
	
		String Password = txtPassword.getText();
		
		userDA.update(Name, Password, usID);
	}
}
