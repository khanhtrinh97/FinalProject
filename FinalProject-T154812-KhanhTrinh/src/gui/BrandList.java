package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import da.BrandDA;
import da.CategoryDA;
import da.ProductDA;
import dataobject.Category;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class BrandList extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable tableBrand;
	BrandDA brandDA;
	private JPanel panelTop;
	private JButton btnUpdate;
	private JButton btnAdd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrandList frame = new BrandList();
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
	public BrandList() {
		setTitle("Brand Management - Trinh");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		tableBrand = new JTable();
		getBrandList();
		scrollPane.setViewportView(tableBrand);
		
		panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(200, 30));
		panelTop.setLayout(null);
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(0, 0, 62, 23);
		btnAdd.addActionListener(this);
		panelTop.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(72, 0, 81, 23);
		btnUpdate.addActionListener(this);
		panelTop.add(btnUpdate);
	}

	private void getBrandList() {
		brandDA = new BrandDA();
		DefaultTableModel model = brandDA.getBrands1();
		tableBrand.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd){
			addBrand();
		}else if(e.getSource() == btnUpdate){
			updateBrand();
		}
		
	}

	private void updateBrand() {
		
		int selectedRowIndex = tableBrand.getSelectedRow();
		if(selectedRowIndex != -1){ 
			int selectedbraID = (int) tableBrand.getModel().getValueAt(selectedRowIndex, 0);
			UpdateBrand updateGUI = new UpdateBrand(selectedbraID);
			updateGUI.setVisible(true);
			BrandList.this.dispose();
			
		}
				
	}

	private void addBrand() {
		AddBrand addGui = new AddBrand();
		addGui.setVisible(true);
		BrandList.this.dispose();
		
	}
}
