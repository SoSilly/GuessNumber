package Access;
import java.awt.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import Information.DbConnect;

import java.awt.event.*;
import java.lang.reflect.Field;

public class Register extends JFrame {
	private Connection con;
	private JPanel contentPane;
	private JTextField txtName;
	private JPasswordField txtPassword;
	private JPasswordField txtRePassword;
	private JTextField txtNative;
	private String myIcon = "./Image/标准.jpg";

	public Register(DbConnect dbConnect,JFrame form1) {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 539, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setFont(new Font("华文楷体", Font.BOLD, 16));
		txtName.setBounds(271, 46, 190, 33);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel label = new JLabel("\u59D3    \u540D  \uFF1A");
		label.setFont(new Font("华文行楷", Font.BOLD, 17));
		label.setBounds(188, 56, 98, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u6027    \u522B  \uFF1A");
		label_1.setFont(new Font("华文行楷", Font.BOLD, 17));
		label_1.setBounds(188, 98, 98, 15);
		contentPane.add(label_1);
		
		JRadioButton radBoy = new JRadioButton("\u7537");
		radBoy.setOpaque(false);
		radBoy.setSelected(true);
		radBoy.setFont(new Font("宋体", Font.BOLD, 13));
		radBoy.setBounds(292, 94, 43, 23);
		contentPane.add(radBoy);
		
		JRadioButton radGirl = new JRadioButton("\u5973");
		radGirl.setOpaque(false);
		radGirl.setFont(new Font("宋体", Font.BOLD, 13));
		radGirl.setBounds(369, 94, 43, 23);
		contentPane.add(radGirl);
		
		ButtonGroup sexRadGrp = new ButtonGroup();
		sexRadGrp.add(radBoy);
		sexRadGrp.add(radGirl);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("华文楷体", Font.BOLD, 16));
		txtPassword.setBounds(271, 180, 190, 33);
		contentPane.add(txtPassword);
		
		txtRePassword = new JPasswordField();
		txtRePassword.setFont(new Font("华文楷体", Font.BOLD, 16));
		txtRePassword.setBounds(271, 224, 190, 38);
		contentPane.add(txtRePassword);
		
		JLabel label_2 = new JLabel("\u5BC6    \u7801  \uFF1A");
		label_2.setFont(new Font("华文行楷", Font.BOLD, 17));
		label_2.setBounds(188, 189, 101, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_3.setFont(new Font("华文行楷", Font.BOLD, 17));
		label_3.setBounds(188, 235, 101, 15);
		contentPane.add(label_3);
		
		JButton btnZhuce = new JButton("\u6CE8\u518C");
		btnZhuce.setFont(new Font("华文行楷", Font.BOLD, 17));
		
		
		btnZhuce.setBounds(299, 284, 93, 38);
		contentPane.add(btnZhuce);
		
		JLabel label_4 = new JLabel("\u7C4D    \u8D2F  \uFF1A");
		label_4.setFont(new Font("华文行楷", Font.BOLD, 17));
		label_4.setBounds(185, 143, 101, 15);
		contentPane.add(label_4);
		
		txtNative = new JTextField();
		txtNative.setFont(new Font("华文楷体", Font.BOLD, 16));
		txtNative.setColumns(10);
		txtNative.setBounds(271, 133, 190, 33);
		contentPane.add(txtNative);
		
		JLabel lblIcon = new JLabel("\u5934\u50CF");
		lblIcon.setIcon(new ImageIcon(".\\Image\\标准.jpg"));
		lblIcon.setBounds(73, 99, 93, 105);
		contentPane.add(lblIcon);
		
		JButton btnIcon = new JButton("\u5934\u50CF");
		
		btnIcon.setFont(new Font("华文行楷", Font.BOLD, 17));
		btnIcon.setBounds(73, 46, 93, 38);
		contentPane.add(btnIcon);
		
		JLabel label_5 = new JLabel("\u80CC\u666F");
		label_5.setBounds(0, 0, 535, 365);
		ImageIcon imageBei = new ImageIcon("E:\\\u6211\u7684Java\u4EE3\u7801\\GuessNumber\\Image\\\u6CE8\u518C.jpg");
		imageBei.setImage(imageBei.getImage().getScaledInstance(label_5.getWidth(), label_5.getHeight(), Image.SCALE_DEFAULT));
		label_5.setIcon(imageBei);
		
		contentPane.add(label_5);
		setLocationRelativeTo(this);
		setResizable(false);
		
		

		//设置头像
		btnIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fileDialog = new FileDialog(Register.this,"选择需要打开的文件",FileDialog.LOAD);
				fileDialog.setVisible(true);
				myIcon = fileDialog.getDirectory()+fileDialog.getFile();
				ImageIcon imageIcon = new ImageIcon(myIcon);
				imageIcon.setImage(imageIcon.getImage().getScaledInstance(lblIcon.getWidth(),lblIcon.getHeight(),Image.SCALE_DEFAULT));
				lblIcon.setIcon(imageIcon);
			}
		});
		//窗口关闭事件
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				form1.setVisible(true);
			}
		});
		
		//注册
		btnZhuce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = "";
				Random rdm = new Random();
				if(txtName.getText().equals("")||txtPassword.getText().equals("")||txtRePassword.getText().equals("")||!(radBoy.isSelected()||radGirl.isSelected())){
					JOptionPane.showMessageDialog(radBoy, "您填写的信息不完整！");
				}
				else if(txtPassword.getText().equals(txtRePassword.getText())){
					String sex;
					if(radBoy.isSelected()){
						sex = "男";
					}
					else sex = "女";
					try {
						do{
							str = "";
							for(int i = 0 ; i < 8; i++)
								str += ""+ rdm.nextInt(10);
						}while(dbConnect.querySql("select * from member where account ='"+str+"'").next());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block"
						e1.printStackTrace();
					}
					String sql = "insert into Member values('"+str+"','"+txtPassword.getText()+"','"+myIcon+"','"+txtName.getText()+"',0,'"+sex+"','"+txtNative.getText()+"')";
					try {
						dbConnect.Insert(sql);
						JOptionPane.showMessageDialog(Register.this, "恭喜你注册成功！您的账户是："+str);
						setVisible(false);
						form1.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else JOptionPane.showMessageDialog(Register.this, "两次输入密码不一致！");
			}
		});
	}
}
