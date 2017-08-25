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
import javax.swing.text.Caret;

import da.BrandDA;
import da.CategoryDA;
import da.ProductDA;
import da.UnitOfMeasureDA;
import dataobject.Brand;
import dataobject.Category;
import dataobject.UnitOfMeasure;

public class MainWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private ProductDA productDA;
	private CategoryDA catDA;
	private BrandDA brandDA;
	private UnitOfMeasureDA unitDA;
	
	private JButton btnProduct;
	private JButton btnCategory;
	private JButton btnBrand;
	private JButton btnUnit;
	private JButton btnUser;
	private JButton btnDepartment;
	private JButton btnWarehouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		productDA = new ProductDA();
		catDA = new CategoryDA();
		brandDA = new BrandDA();
		unitDA = new UnitOfMeasureDA();
		
		initGUI();
	}

	private void initGUI() {
		setResizable(false);
		setTitle("Warehouse Management - Trinh");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddProduct = new JLabel("Warehouse Management");
		lblAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddProduct.setBounds(94, 22, 229, 33);
		contentPane.add(lblAddProduct);
		
		btnProduct = new JButton("Product");
		btnProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnProduct.setBounds(37, 66, 147, 23);
		contentPane.add(btnProduct);
		btnProduct.addActionListener(this);
		
		btnCategory = new JButton("Category");
		btnCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCategory.setBounds(37, 114, 147, 23);
		contentPane.add(btnCategory);
		btnCategory.addActionListener(this);
		
		btnBrand = new JButton("Brand");
		btnBrand.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBrand.setBounds(37, 159, 147, 23);
		contentPane.add(btnBrand);
		btnBrand.addActionListener(this);
		
		btnUnit = new JButton("Unit of Measure");
		btnUnit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUnit.setBounds(37, 208, 147, 23);
		contentPane.add(btnUnit);
		btnUnit.addActionListener(this);
		
		btnDepartment = new JButton("Department");
		btnDepartment.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDepartment.setBounds(220, 66, 141, 23);
		contentPane.add(btnDepartment);
		btnDepartment.addActionListener(this);
		
		btnWarehouse = new JButton("Warehouse");
		btnWarehouse.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnWarehouse.setBounds(220, 114, 141, 23);
		contentPane.add(btnWarehouse);
		btnWarehouse.addActionListener(this);
		
		btnUser = new JButton("User");
		btnUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUser.setBounds(220, 159, 141, 23);
		contentPane.add(btnUser);
		btnUser.addActionListener(this);
		
		//Vector<Category> catList = catDA.getAllCategories();
		//Vector<UnitOfMeasure>unitList = unitDA.getUnitOfMeasure();
		//Vector<Brand> brandList = brandDA.getBrands();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnProduct){
			ProductList frame1 = new ProductList();
			frame1.setVisible(true);	
		}else if(e.getSource() == btnCategory){
			CategoryList frame = new CategoryList();
			frame.setVisible(true);	
		}
		else if(e.getSource() == btnBrand){
			BrandList frame = new BrandList();
			frame.setVisible(true);
		}
		else if(e.getSource() == btnUnit){
			UnitofMeasureList frame = new UnitofMeasureList();
			frame.setVisible(true);
		}
		else if(e.getSource() == btnUser){
			UserList frame = new UserList();
			frame.setVisible(true);
		}
		else if(e.getSource() == btnDepartment){
			DepartmentList frame = new DepartmentList();
			frame.setVisible(true);
		}
		else if(e.getSource() == btnWarehouse){
			WarehouseList frame = new WarehouseList();
			frame.setVisible(true);
		}
		
	}
}
