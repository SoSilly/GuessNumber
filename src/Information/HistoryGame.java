package Information;
import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import Access.FormLogin;

import java.awt.event.*;
public class HistoryGame extends JFrame {
	private JTable table;


	/**
	 * Create the frame.
	 */
	public HistoryGame(JFrame mode) {
		getContentPane().setLayout(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mode.setVisible(true);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 435, 474);
		//����
		Vector<String> columnVector = new Vector<String>();
		columnVector.add("����");
		columnVector.add("����");
		columnVector.add("��������");
		columnVector.add("����");
		columnVector.add("ʱ��");
		//����
		Vector<Vector<String>> dataVector = new Vector<>();
		
		String fileName = "./��ʷ��Ϸ��Ϣ/"+FormLogin.member.getAccount()+".txt";
		File file = new File(fileName);
		if(file.exists()){
			Find(file,dataVector);
		}
		else {
			JOptionPane.showMessageDialog(this,"����������ʷ��Ϸ��¼��");
			return;
		}
		
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(dataVector, columnVector);
		model.setDataVector(dataVector, columnVector);
		table.setBounds(0, 0,420, 435);
		//װ��
		table.setGridColor(Color.green);
		table.setSelectionBackground(Color.black); 
		table.setSelectionForeground(Color.white);
		//�����п�
		TableColumn column = table.getColumnModel().getColumn(4);
		
		column.setPreferredWidth(300);
		
		JScrollPane jsp = new JScrollPane(table);
		
		jsp.setBounds(0, 0,429, 445);
		getContentPane().add(jsp);
		
		
		
		//���ݾ�����ʾ
		DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setDefaultRenderer(Object.class, renderer);
		setLocationRelativeTo(this);
		setResizable(false);
		
	}
	public void Find(File file,Vector<Vector<String>> dataVector){
		try {  
            //��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�  
            Scanner reader = new Scanner(file);
            String content;
            while(reader.hasNextLine()){
            	content = reader.nextLine();
            	String[] str = content.split(",");
            	Vector<String> rowVector = new Vector<String>();
            	rowVector.add(FormLogin.member.getName());
            	rowVector.add(str[0]);
            	rowVector.add(str[1]);
            	rowVector.add(str[2]);
            	rowVector.add(str[3]);
            	dataVector.add(rowVector);
            }
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
	}
}
