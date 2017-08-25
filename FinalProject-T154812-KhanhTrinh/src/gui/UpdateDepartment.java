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
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

import da.BrandDA;
import da.CategoryDA;
import da.DepartmentDA;
import da.ProductDA;
import da.UnitOfMeasureDA;
import da.UserDA;
import da.WarehouseDA;
import dataobject.Brand;
import dataobject.Category;
import dataobject.Department;
import dataobject.Product;
import dataobject.UnitOfMeasure;
import dataobject.User;
import dataobject.Warehouse;

public class UpdateDepartment extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDepartmentname;
	private JTextField txtDescription;
	
	//private ProductDA productDA;
	//private CategoryDA catDA;
	private DepartmentDA departmentDA;;
	//private UnitOfMeasureDA unitDA;
	
	private JButton btnUpdate;
	private JButton btnCancel;

	public int deID = 1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDepartment frame = new UpdateDepartment(1);
					
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
	public UpdateDepartment(int deid) {
		deID = deid;
		//productDA = new ProductDA();
		//catDA = new CategoryDA();
		departmentDA = new DepartmentDA();
		//unitDA = new UnitOfMeasureDA();
		
		initGUI();
		
		
		Department depart = departmentDA.getDepartment(deID);
		txtDepartmentname.setText(depart.getDepartmentname());
		txtDescription.setText(depart.getDescription());
		
		
		
		
	}

	private void initGUI() {
		setResizable(false);
		setTitle("Update Department - Trinh");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(26, 88, 102, 20);
		contentPane.add(lblName);
		
		txtDepartmentname = new JTextField();
		txtDepartmentname.setBounds(138, 90, 173, 20);
		contentPane.add(txtDepartmentname);
		txtDepartmentname.setColumns(10);
		
		JLabel lbProductCode = new JLabel("Description");
		lbProductCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbProductCode.setBounds(26, 144, 89, 14);
		contentPane.add(lbProductCode);
		
		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(138, 143, 173, 20);
		contentPane.add(txtDescription);
		
		JLabel lblAddProduct = new JLabel("Update Department");
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
			updateDepartment();
			departmentDA.getAllDepartments();
			JOptionPane.showMessageDialog(this,"updated completed");
			
			DepartmentList frame1 = new DepartmentList();
			frame1.setVisible(true);
		}else if(e.getSource() == btnCancel){
			UpdateDepartment.this.dispose();
		}
		
	}

	private void updateDepartment() {
		String Departmentname = txtDepartmentname.getText();
	
		String Description = txtDescription.getText();
		
		departmentDA.update(Departmentname, Description, deID);
	}
}
