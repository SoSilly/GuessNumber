package Access;
import java.awt.*;
import java.sql.*;
import javax.swing.border.EmptyBorder;

import Information.DbConnect;
import Information.Member;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FormLogin extends JFrame {
	public static Member member = new Member();
	DbConnect dbConnect = new DbConnect();
	private JPanel contentPane;
	private JTextField txtAccount;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormLogin frame = new FormLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public FormLogin() throws Exception {
		FormLogin form1 = this;
		dbConnect.getCon();
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 430);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtAccount = new JTextField();
		//账户事件
		txtAccount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if((int)e.getKeyChar() == 10){
					System.out.println("tiandao");
					txtPassword.requestFocus();
				}
			}
		});
		txtAccount.setFont(new Font("宋体", Font.BOLD, 18));
		txtAccount.setBounds(207, 199, 217, 41);
		contentPane.add(txtAccount);
		txtAccount.setColumns(10);
		
		JLabel labZhuce = new JLabel("\u6CE8\u518C");
		labZhuce.setFont(new Font("华文琥珀", Font.BOLD, 18));
		labZhuce.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				form1.setVisible(false);
				new Register(dbConnect,FormLogin.this).show();
			}
		});
		labZhuce.setBounds(434, 212, 60, 15);
		contentPane.add(labZhuce);
		
		JButton btnLog = new JButton("\u767B\u5F55");
		btnLog.setFont(new Font("华文琥珀", Font.BOLD, 16));
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean bool = Data();
				if(bool){
					JOptionPane.showMessageDialog(FormLogin.this, "登录成功","Message",JOptionPane.INFORMATION_MESSAGE);
					member.setAccount(txtAccount.getText());
					new Mode().show();
					FormLogin.this.dispose();
				}
				else JOptionPane.showMessageDialog(FormLogin.this, "登录失败","Message",JOptionPane.ERROR_MESSAGE);
			}
		});
		btnLog.setBounds(251, 309, 106, 41);
		contentPane.add(btnLog);
		
		Component glue = Box.createGlue();
		glue.setBounds(361, 236, 45, -37);
		contentPane.add(glue);
		
		JLabel lblIcon = new JLabel("\u5934\u50CF");
		lblIcon.setIcon(new ImageIcon(".\\Image\\标准.jpg"));
		lblIcon.setBounds(107, 194, 90, 96);
		contentPane.add(lblIcon);
		
		txtPassword = new JPasswordField();
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String str = icon();
				if(str!=null){
					ImageIcon imageIcon = new ImageIcon(str);
					imageIcon.setImage(imageIcon.getImage().getScaledInstance(lblIcon.getWidth(),lblIcon.getHeight(),Image.SCALE_DEFAULT));
					lblIcon.setIcon(imageIcon);
				}
			}
		});
		txtPassword.setFont(new Font("宋体", Font.BOLD, 18));
		txtPassword.setBounds(207, 239, 217, 41);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 177, 602, 224);
		ImageIcon imageBei2 = new ImageIcon("E:\\\u6211\u7684Java\u4EE3\u7801\\GuessNumber\\Image\\Login2.jpg");
		imageBei2.setImage(imageBei2.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(imageBei2);
		lblNewLabel.setBackground(new Color(204, 204, 204));
		
		contentPane.add(lblNewLabel);
		
		//背景图片
		JLabel lbl = new JLabel("\u80CC\u666F");
		lbl.setBounds(0, 0, 603, 400);
		ImageIcon imageBeijing = new ImageIcon("./Image/Login.jpg");
		imageBeijing.setImage(imageBeijing.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
		lbl.setIcon(imageBeijing);
		getContentPane().add(lbl);
		
		setResizable(false);
		
		
	}
	public Boolean Data(){
		member = new Member();
		member.setAccount(txtAccount.getText());
		member.setPassword(txtPassword.getText());
		String sql = "select * from Member where account = '"+txtAccount.getText().trim()+"'";
		ResultSet rs;
		try{
			rs = dbConnect.querySql(sql);
			while(rs.next()){
				if(txtPassword.getText().equals(rs.getString(2))){
					member.setPassword(rs.getString(2));
					member.setIcon(rs.getString(3));
					member.setMyNative(rs.getString(7));
					member.setName(rs.getString(4));
					member.setScore(rs.getInt(5));
					member.setSex(rs.getString(6));
					return true;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	public String icon(){
		member = new Member();
		member.setAccount(txtAccount.getText());
		member.setPassword(txtPassword.getText());
		String sql = "select * from Member where account = '"+txtAccount.getText().trim()+"'";
		ResultSet rs;
		try{
			rs = dbConnect.querySql(sql);
			while(rs.next()){
				return rs.getString(3);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
