package Information;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Access.FormLogin;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonInf extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtNative;

	/**
	 * Create the frame.
	 */
	public PersonInf(JFrame mode) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mode.setVisible(true);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 523, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setFont(new Font("华文楷体", Font.BOLD, 16));
		txtName.setColumns(10);
		txtName.setBounds(108, 42, 212, 33);
		contentPane.add(txtName);
		
		JLabel label = new JLabel("\u59D3    \u540D  \uFF1A");
		label.setFont(new Font("华文行楷", Font.BOLD, 17));
		label.setBounds(25, 52, 98, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u6027    \u522B  \uFF1A");
		label_1.setFont(new Font("华文行楷", Font.BOLD, 17));
		label_1.setBounds(25, 241, 98, 15);
		contentPane.add(label_1);
		
		JRadioButton radBoy = new JRadioButton("\u7537");
		radBoy.setSelected(true);
		radBoy.setOpaque(false);
		radBoy.setFont(new Font("宋体", Font.BOLD, 13));
		radBoy.setBounds(129, 237, 43, 23);
		contentPane.add(radBoy);
		
		JRadioButton radGirl = new JRadioButton("\u5973");
		radGirl.setOpaque(false);
		radGirl.setFont(new Font("宋体", Font.BOLD, 13));
		radGirl.setBounds(206, 237, 43, 23);
		contentPane.add(radGirl);
		
		ButtonGroup sexRadGrp = new ButtonGroup();
		sexRadGrp.add(radBoy);
		sexRadGrp.add(radGirl);
		
		JButton btnAlter = new JButton("\u4FEE\u6539\u4FE1\u606F");
		btnAlter.setFont(new Font("华文行楷", Font.BOLD, 17));
		btnAlter.setBounds(373, 151, 128, 38);
		contentPane.add(btnAlter);
		
		JLabel label_4 = new JLabel("\u7C4D    \u8D2F  \uFF1A");
		label_4.setFont(new Font("华文行楷", Font.BOLD, 17));
		label_4.setBounds(22, 286, 101, 15);
		contentPane.add(label_4);
		
		txtNative = new JTextField();
		txtNative.setFont(new Font("华文楷体", Font.BOLD, 16));
		txtNative.setColumns(10);
		txtNative.setBounds(108, 276, 212, 33);
		contentPane.add(txtNative);
		
		JLabel lblIcon = new JLabel("\u5934\u50CF");
		lblIcon.setBounds(124, 108, 93, 105);
		contentPane.add(lblIcon);
		
		JButton btnBrowser = new JButton("\u6D4F\u89C8");
		btnBrowser.setFont(new Font("华文行楷", Font.BOLD, 17));
		btnBrowser.setBounds(227, 141, 93, 38);
		contentPane.add(btnBrowser);
		
		JLabel label_2 = new JLabel("\u5934    \u50CF  \uFF1A");
		label_2.setFont(new Font("华文行楷", Font.BOLD, 17));
		label_2.setBounds(25, 153, 98, 15);
		contentPane.add(label_2);
		
		JButton btnOk = new JButton("\u786E\u5B9A");
		btnOk.setFont(new Font("华文行楷", Font.BOLD, 17));
		btnOk.setBounds(166, 346, 93, 38);
		contentPane.add(btnOk);
		
		//初始化
		txtName.setText(FormLogin.member.getName());
		txtNative.setText(FormLogin.member.getMyNative());
		if(FormLogin.member.getSex().equals("男")){
			radBoy.setSelected(true);
		}
		else radGirl.setSelected(true);
		ImageIcon imageIcon = new ImageIcon(FormLogin.member.getIcon());
		imageIcon.setImage(imageIcon.getImage().getScaledInstance(lblIcon.getWidth(), lblIcon.getHeight(), Image.SCALE_DEFAULT));
		lblIcon.setIcon(imageIcon);
		txtName.setEditable(false);
		txtNative.setEditable(false);
		radBoy.setEnabled(false);
		//radBoy.set
		radGirl.setEnabled(false);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 518, 405);
		 float alpha = 0.2f; // 透明度   
        
		ImageIcon imageIcon1 = new ImageIcon(".\\Image\\背景4.jpg");
		
		imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(imageIcon1);
		
		contentPane.add(lblNewLabel);
		btnBrowser.setVisible(false);
		btnOk.setVisible(false);
		
		//修改按钮事件
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setEditable(true);
				txtNative.setEditable(true);
				radBoy.setEnabled(true);
				radGirl.setEnabled(true);
				btnBrowser.setVisible(true);
				btnOk.setVisible(true);
			}
		});
		
		//浏览按钮事件
		btnBrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fileDialog = new FileDialog(PersonInf.this,"选择需要打开的文件",FileDialog.LOAD);
				fileDialog.setVisible(true);
				FormLogin.member.setIcon(fileDialog.getDirectory()+fileDialog.getFile());
				ImageIcon imageIcon = new ImageIcon(FormLogin.member.getIcon());
				imageIcon.setImage(imageIcon.getImage().getScaledInstance(lblIcon.getWidth(),lblIcon.getHeight(),Image.SCALE_DEFAULT));
				lblIcon.setIcon(imageIcon);
			}
		});
		
		//确定按钮事件
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLogin.member.setName(txtName.getText());
				FormLogin.member.setMyNative(txtNative.getText());
				if(radBoy.isSelected()){
					FormLogin.member.setSex("男");
				}
				else FormLogin.member.setSex("女");
				txtName.setEditable(false);
				txtNative.setEditable(false);
				radBoy.setEnabled(false);
				radGirl.setEnabled(false);
				btnBrowser.setVisible(false);
				btnOk.setVisible(false);
			}
		});
		setResizable(false);
		setLocationRelativeTo(this);
	}
}
