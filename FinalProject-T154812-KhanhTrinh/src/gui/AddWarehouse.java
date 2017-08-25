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
import da.WarehouseDA;
import dataobject.Brand;
import dataobject.Category;
import dataobject.UnitOfMeasure;

public class AddWarehouse extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtDescription;
	
	private ProductDA productDA;
	private CategoryDA catDA;
	private BrandDA brandDA;
	private UnitOfMeasureDA unitDA;
	private WarehouseDA warehousrDA;
	
	private JButton btnAdd;
	private JButton btnCancel;
	private JTextField txtAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddWarehouse frame = new AddWarehouse();
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
	public AddWarehouse() {
		productDA = new ProductDA();
		catDA = new CategoryDA();
		brandDA = new BrandDA();
		unitDA = new UnitOfMeasureDA();
		warehousrDA = new WarehouseDA();
		
		initGUI();
	}

	private void initGUI() {
		setResizable(false);
		setTitle("Add Warehouse - Trinh");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(22, 74, 103, 23);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(135, 77, 173, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lbDescription = new JLabel("Description");
		lbDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbDescription.setBounds(22, 166, 74, 14);
		contentPane.add(lbDescription);
		
		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(134, 163, 173, 20);
		contentPane.add(txtDescription);
		
		JLabel lblAddProduct = new JLabel("Add Warehouse");
		lblAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddProduct.setBounds(60, 30, 229, 33);
		contentPane.add(lblAddProduct);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(60, 243, 89, 23);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(179, 243, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(22, 121, 74, 14);
		contentPane.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(134, 118, 173, 20);
		contentPane.add(txtAddress);
		btnCancel.addActionListener(this);
		//Vector<Category> catList = catDA.getAllCategories();
		//Vector<UnitOfMeasure>unitList = unitDA.getUnitOfMeasure();
		//Vector<Brand> brandList = brandDA.getBrands();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd){
			addWarehouse();
			warehousrDA.getAllWarehouses();
			JOptionPane.showMessageDialog(this,"add completed");
			
			WarehouseList frame1 = new WarehouseList();
			frame1.setVisible(true);
			
		}else if(e.getSource() == btnCancel){
			AddWarehouse.this.dispose();
		}
		
	}

	private void addWarehouse() {
		String Name = txtName.getText();
		String Address = txtAddress.getText();
		String Description = txtDescription.getText();
		
		warehousrDA.insert(Name, Address, Description);
	}
}
