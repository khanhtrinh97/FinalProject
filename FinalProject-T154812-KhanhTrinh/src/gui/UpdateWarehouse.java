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
import da.WarehouseDA;
import dataobject.Brand;
import dataobject.Category;
import dataobject.Product;
import dataobject.UnitOfMeasure;
import dataobject.Warehouse;

public class UpdateWarehouse extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtDescription;
	
	//private ProductDA productDA;
	//private CategoryDA catDA;
	private WarehouseDA wareDA;;
	//private UnitOfMeasureDA unitDA;
	
	private JButton btnUpdate;
	private JButton btnCancel;

	public int wareID = 1;
	private JLabel lblAddress;
	private JTextField txtAddress;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateWarehouse frame = new UpdateWarehouse(1);
					
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
	public UpdateWarehouse(int wid) {
		wareID = wid;
		//productDA = new ProductDA();
		//catDA = new CategoryDA();
		wareDA = new WarehouseDA();
		//unitDA = new UnitOfMeasureDA();
		
		initGUI();
		
		
		Warehouse w = wareDA.getWarehouse(wareID);
		txtName.setText(w.getName());
		txtDescription.setText(w.getDescription());
		txtAddress.setText(w.getAddress());
		
		
		
		
	}

	private void initGUI() {
		setResizable(false);
		setTitle("Update Brand - Trinh");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(26, 68, 102, 20);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(138, 70, 173, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lbProductCode = new JLabel("Description");
		lbProductCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbProductCode.setBounds(26, 171, 89, 14);
		contentPane.add(lbProductCode);
		
		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(138, 170, 173, 20);
		contentPane.add(txtDescription);
		
		lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(26, 114, 89, 14);
		contentPane.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setText((String) null);
		txtAddress.setColumns(10);
		txtAddress.setBounds(138, 113, 173, 20);
		contentPane.add(txtAddress);
		
		JLabel lblAddProduct = new JLabel("Update Warehouse");
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
			updateWarehouse();
			wareDA.getAllWarehouses();
			JOptionPane.showMessageDialog(this,"updated completed");
			
			WarehouseList frame1 = new WarehouseList();
			frame1.setVisible(true);
		}else if(e.getSource() == btnCancel){
			UpdateWarehouse.this.dispose();
		}
		
	}

	private void updateWarehouse() {
		String Name = txtName.getText();
		String Address = txtAddress.getText();
		String Description = txtDescription.getText();
		
		wareDA.update(Name, Address, Description, wareID);
	}
}
