package gui;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.text.Caret;

import da.BrandDA;
import da.CategoryDA;

import da.ProductDA;
import da.UnitOfMeasureDA;
import da.UserDA;
import da.WarehouseDA;
import dataobject.Brand;
import dataobject.Category;
import dataobject.UnitOfMeasure;
import javax.swing.JPasswordField;

public class Login extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	
	private ProductDA productDA;
	private CategoryDA catDA;
	private BrandDA brandDA;
	private UnitOfMeasureDA unitDA;
	private WarehouseDA warehousrDA;
	private UserDA userDA;
	
	
	private JButton btnLogin;
	private JButton btnCancel;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		productDA = new ProductDA();
		catDA = new CategoryDA();
		brandDA = new BrandDA();
		unitDA = new UnitOfMeasureDA();
		warehousrDA = new WarehouseDA();
		userDA = new UserDA();
		
		initGUI();
	}

	private void initGUI() {
		setResizable(false);
		setTitle("Login - Trinh");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 396, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("User Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(22, 86, 103, 23);
		contentPane.add(lblName);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(135, 89, 222, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lbDescription = new JLabel("Password");
		lbDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbDescription.setBounds(22, 139, 74, 14);
		contentPane.add(lbDescription);
		
		JLabel lblAddProduct = new JLabel("Welcome to WarehouseManagement");
		lblAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddProduct.setBounds(22, 30, 335, 33);
		contentPane.add(lblAddProduct);
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(91, 193, 95, 23);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(216, 193, 89, 23);
		contentPane.add(btnCancel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(135, 138, 222, 20);
		contentPane.add(passwordField);
		btnCancel.addActionListener(this);
		//Vector<Category> catList = catDA.getAllCategories();
		//Vector<UnitOfMeasure>unitList = unitDA.getUnitOfMeasure();
		//Vector<Brand> brandList = brandDA.getBrands();
	}
	protected Connection connect() {
		String url = "jdbc:sqlite:foc2warehouse.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin){
			
			
			String Username = txtUsername.getText();
			char[] passArray = passwordField.getPassword();
			String password = new String(passArray);
			
			
			
			 if (txtUsername.getText().equals("") || new String(passwordField.getPassword()).equals("")) {
	             JOptionPane.showMessageDialog(Login.this, "Username and password must not empty.", "Invalid", JOptionPane.ERROR_MESSAGE);
	             return;
	         }
	         try {
	             ResultSet res = connect().createStatement().executeQuery(String.format("select * from User where username = '%s' and password = '%s'", txtUsername.getText(), new String(passwordField.getPassword())));
	             if (res != null) {
	                 if (res.next()) {
	                     if (res.getString("Password").equals(new String(passwordField.getPassword()))) {
	                         JOptionPane.showMessageDialog(Login.this, "You has been login successful.", "login successfuly", JOptionPane.INFORMATION_MESSAGE);
	                         // Login successful DO SOMETHING HERE
	                         MainWindow frame = new MainWindow();
	                         frame.setVisible(true);
	                         Login.this.dispose();
	                         
	                     } else {
	                         JOptionPane.showMessageDialog(Login.this, "Password is match case.", "Login failed", JOptionPane.INFORMATION_MESSAGE);
	                     }
	                 } else {
	                     JOptionPane.showMessageDialog(Login.this, "Wrong username or password.", "Login failed", JOptionPane.INFORMATION_MESSAGE);
	                 }
	             }
	         } catch (SQLException ex) {
	             ex.printStackTrace();
	         }
			
		}else if(e.getSource() == btnCancel){
			Login.this.dispose();
		}
		
	}
}
