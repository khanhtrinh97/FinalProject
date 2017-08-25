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
import da.UnitOfMeasureDA;
import dataobject.Category;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class UnitofMeasureList extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable tableUnit;
	UnitOfMeasureDA unitDA;
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
					UnitofMeasureList frame = new UnitofMeasureList();
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
	public UnitofMeasureList() {
		setTitle("Unit of Measure Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		tableUnit = new JTable();
		getUnitList();
		scrollPane.setViewportView(tableUnit);
		
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

	private void getUnitList() {
		unitDA = new UnitOfMeasureDA();
		DefaultTableModel model = unitDA.getUnits1();
		tableUnit.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd){
			addUnit();
		}else if(e.getSource() == btnUpdate){
			updateUnit();
		}
		
	}

	private void updateUnit() {
		
		int selectedRowIndex = tableUnit.getSelectedRow();
		if(selectedRowIndex != -1){ 
			int selectedunitID = (int) tableUnit.getModel().getValueAt(selectedRowIndex, 0);
			UpdateUnit updateGUI = new UpdateUnit(selectedunitID);
			updateGUI.setVisible(true);
			UnitofMeasureList.this.dispose();
			
		}
				
	}

	private void addUnit() {
		AddUnit addGui = new AddUnit();
		addGui.setVisible(true);
		UnitofMeasureList.this.dispose();
		
	}
}
