import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.util.Calendar;
import java.text.SimpleDateFormat;


public class AddEmp {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	
	DefaultTableModel model=new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void secondTab() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmp window = new AddEmp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddEmp() {
		initialize();
		conn = ConnectMysql.ConnDB();
		Object col[]= {"Eid","Ename","Date_of_Join","Year_of_Entry","Designation"};
		model.setColumnIdentifiers(col);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 871, 596);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel.setBounds(66, 33, 158, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Employee Name");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_1.setBounds(66, 74, 213, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DOJ");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_2.setBounds(66, 125, 55, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("YOE");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_3.setBounds(66, 172, 55, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Desingation");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_4.setBounds(66, 226, 158, 22);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(368, 33, 184, 34);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(368, 70, 184, 36);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(368, 118, 184, 34);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(368, 168, 184, 29);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(368, 226, 184, 29);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		
	
		
		JButton btnNewButton = new JButton("Add Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql="INSERT INTO emp_data(Eid,Ename,Date_of_Join,Year_of_Experience,Designation)VALUES(?,?,?,?,?)";
				try {
					pst=conn.prepareStatement(sql);
					pst.setString(1, textField.getText() );
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, textField_3.getText());
					pst.setString(5, textField_4.getText());
					pst.execute();
					rs.close();
					pst.close();
					
					
					
					
				}
				catch (Exception ev) 
				{  
					
					
					JOptionPane.showMessageDialog(null,"Add Successfully");
					
				}
				Object[] columns = new Object[5];
				columns[0]=textField.getText();
            	columns[1]=textField_1.getText();
            	columns[2]=textField_2.getText();
            	columns[3]=textField_3.getText();
            	columns[4]=textField_4.getText();
				EmployeeDetails obj= new EmployeeDetails();
				obj.textData(columns);
				
				
			}
		});
		btnNewButton.setBounds(233, 303, 98, 24);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(708, 485, 98, 24);
		frame.getContentPane().add(btnNewButton_1);
	}
}
