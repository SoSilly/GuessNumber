package Information;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Zhanji extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public Zhanji(JFrame mode) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mode.setVisible(true);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 521, 455);
		
		//列名
		Vector<String> columnVector = new Vector<String>();
		columnVector.add("账户");
		columnVector.add("姓名");
		columnVector.add("分数");
		columnVector.add("排名");

		Vector<Vector<String>> dataVector = new Vector<>();
		addRow(dataVector);
		
		
		JTable table = new JTable(dataVector,columnVector);
		table.setBounds(0, 0,420, 435);
		//装饰
		table.setGridColor(Color.green);
		table.setSelectionBackground(Color.black); 
		table.setSelectionForeground(Color.white);
		
		JScrollPane jsp = new JScrollPane(table);
		
		jsp.setBounds(0, 0,420, 435);
		add(jsp);
		
		//内容居中显示
		DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setDefaultRenderer(Object.class, renderer);
		setLocationRelativeTo(this);
		setResizable(false);
	}
	public void addRow(Vector<Vector<String>> dataVector){
		DbConnect dbConnect = new DbConnect();
		String sql = "select account,name,score from member order by score desc";
		ResultSet  rs = null;
		try {
			rs = dbConnect.querySql(sql);
			int i = 0;
			while(rs.next()){
				i++;
				Vector<String> rowVector = new Vector<>();
				rowVector.add(rs.getString(1));
				System.out.println(rs.getString(1));
				rowVector.add(rs.getString(2));
				System.out.println(rs.getString(2));
				rowVector.add(""+rs.getInt(3));
				rowVector.add("第"+i+"名");
				dataVector.add(rowVector);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
