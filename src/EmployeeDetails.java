import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.Panel;
import javax.swing.border.EtchedBorder;


public class EmployeeDetails {

	private JFrame frmEmp;
	private JTextField txtSerch;
	JTable table;
	private JScrollPane scrollPane;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	DefaultTableModel model=new DefaultTableModel();
	

	/**
	 * Launch the application.
	 * 
	 */
	public void textData(Object col[]) {
		conn= ConnectMysql.ConnDB();
		DefaultTableModel model=(DefaultTableModel) table.getModel();
		model.addRow(col);
		if(table.getSelectedRow()==-1) {
			if(table.getRowCount()==0) {
				JOptionPane.showMessageDialog(null, "done");
			}
		}
		
		
	}
	public void dataShow() {
		 DefaultTableModel model=(DefaultTableModel) table.getModel();
		 conn= ConnectMysql.ConnDB();
			if(conn !=null) {
				String sql = "select Eid,Ename,Date_of_Join,Year_of_Experience,Designation from emp_data ";
				
			
			try {
				pst=conn.prepareStatement(sql);
				
				rs= pst.executeQuery();
				Object[] columns = new Object[5];
				
	            while(rs.next()) {
	            	columns[0]=rs.getString("Eid");
	            	columns[1]=rs.getString("Ename");
	            	columns[2]=rs.getString("Date_of_Join");
	            	columns[3]=rs.getString("Year_of_Experience");
	            	columns[4]=rs.getString("Designation");
	            	
	            	model.addRow(columns);
	            }
	           pst.close();
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null,e);
			}
			}
	}
	
	
	public void serch_data (String str) {
		DefaultTableModel model=(DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
		table.setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(str));
		
		
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeDetails window = new EmployeeDetails();
					window.frmEmp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	
	
	public EmployeeDetails() {
		initialize();

		

		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmp = new JFrame();
		frmEmp.setTitle("Emp");
		frmEmp.setBounds(100, 100, 1039, 646);
		frmEmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Add Emp");
		btnNewButton.setBounds(863, 43, 98, 45);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AddEmp sTab= new AddEmp();
				AddEmp.secondTab();
			}
		});
		frmEmp.getContentPane().setLayout(null);
		frmEmp.getContentPane().add(btnNewButton);
		
		txtSerch = new JTextField();
		txtSerch.setFont(new Font("Inter Black", Font.PLAIN, 15));
		txtSerch.setBounds(134, 43, 314, 45);
		frmEmp.getContentPane().add(txtSerch);
		txtSerch.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(112, 142, 656, 417);
		frmEmp.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee ID", "Employee Name", "Date of Join", "Year of Experience", "Designation"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(97);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(129);
		table.getColumnModel().getColumn(4).setPreferredWidth(152);
		table.getColumnModel().getColumn(4).setMinWidth(30);
		dataShow();
		table.setFont(new Font("Dialog", Font.BOLD, 13));
		scrollPane.setViewportView(table);
		
		JButton Refresh_Data = new JButton("Exit");
		Refresh_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				
				System.exit(0);
			}
		});
		Refresh_Data.setBounds(815, 511, 130, 24);
		frmEmp.getContentPane().add(Refresh_Data);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			serch_data(txtSerch.getText());
			}
		});
		btnNewButton_1.setBounds(460, 43, 98, 45);
		frmEmp.getContentPane().add(btnNewButton_1);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
