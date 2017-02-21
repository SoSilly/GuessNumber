package Single;
import java.awt.*;

import javax.swing.border.EmptyBorder;

import Access.FormLogin;
import Information.DbConnect;
import Information.Judge;

import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.*;

public class SingleGuess extends JFrame {

	private JPanel contentPane;
	private JTextField txtPlayer;
	private int time = 21;
	PKGuess pkGuess = null;
	JLabel labScorePl;
	JLabel labScorePC;
	Boolean isStart = false;//是否开始过游戏
	public static Boolean isGuess = false;
	/**
	 * Create the frame.
	 */
	public SingleGuess(JFrame Mode) {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 582, 534);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtAPlayer = new JTextArea();
		//设置边框
		txtAPlayer.setBorder (BorderFactory.createLineBorder(Color.gray,5));
		txtAPlayer.setBackground(Color.WHITE);
		txtAPlayer.setFont(new Font("华文楷体", Font.BOLD, 15));
		txtAPlayer.setText("\u73A9\u5BB6\uFF1A\r\n");
		txtAPlayer.setBounds(0, 200, 236, 305);
		
		contentPane.add(txtAPlayer);
		
		JTextArea txtAPC = new JTextArea();
		txtAPC.setBorder (BorderFactory.createLineBorder(Color.gray,5));
		txtAPC.setBackground(Color.WHITE);
		txtAPC.setFont(new Font("华文楷体", Font.BOLD, 15));
		txtAPC.setText("\u7535\u8111\uFF1A\n");
		txtAPC.setBounds(341, 200, 236, 305);
		contentPane.add(txtAPC);
		
		JLabel lblNewLabel = new JLabel("\u65F6 \u95F4");
		lblNewLabel.setFont(new Font("华文中宋", Font.BOLD, 20));
		lblNewLabel.setBounds(260, 288, 60, 21);
		contentPane.add(lblNewLabel);
		
		txtPlayer = new JTextField();
		txtPlayer.setBounds(113, 11, 85, 21);
		contentPane.add(txtPlayer);
		txtPlayer.setColumns(10);
		
		JLabel label = new JLabel("\u73A9\u5BB6\u8F93\u5165\uFF1A");
		label.setFont(new Font("宋体", Font.BOLD, 15));
		label.setBounds(40, 14, 80, 15);
		contentPane.add(label);
		
		JLabel labTime = new JLabel(" 0");
		labTime.setFont(new Font("华文琥珀", Font.PLAIN, 23));
		labTime.setBounds(270, 308, 29, 33);
		contentPane.add(labTime);
		
		JButton button = new JButton("\u786E\u5B9A");
		
		button.setBounds(208, 10, 64, 23);
		contentPane.add(button);
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtPlayer.getText().equals("")){
					JOptionPane.showMessageDialog(SingleGuess.this, "请输入所猜测数值！");
				}
				else if(!Judge.distinguish(txtPlayer.getText())){
					JOptionPane.showMessageDialog(SingleGuess.this, "您输入的值有误！");
				}
				else{
					txtAPlayer.append(txtPlayer.getText());
					String strJudge = Judge.judge(txtPlayer.getText());
					txtAPlayer.append("----------"+strJudge+"\n");
					txtPlayer.setText("");
					Score(strJudge);
					PKGuess.flag = true;
				}
			}
		});
		
		//时间控件
		Timer timer = new Timer(1000,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isGuess)
					time--;
				labTime.setText(""+time);
				if(time <= 0){
					time = 0;
					if(!isGuess){
						JOptionPane.showMessageDialog(SingleGuess.this, "正确答案为："+Judge.number);
					}
					Timer t = (Timer)e.getSource();
					button.setEnabled(false);
					t.stop();
					Judge.number = "";
					Judge.generate();
				}
			}
		});
		timer.stop();
		
		JButton button_1 = new JButton("\u5F00\u59CB\u6E38\u620F");
		button_1.setIcon(null);
		
		//电脑分数
		labScorePC = new JLabel(" 0");
		labScorePC.setFont(new Font("华文琥珀", Font.PLAIN, 23));
		labScorePC.setBounds(462, 161, 29, 33);
		contentPane.add(labScorePC);
	    
		//开始游戏按钮
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isStart = true;
				txtAPlayer.setText("玩家:\n");
				txtAPC.setText("电脑:\n");
				Judge.number = "";
				Judge.generate();
				System.out.println(Judge.number);
				isGuess = false;
				time = 21;
				button.setEnabled(true);
				timer.start();
				/**
					 * 多线程
					 */
				if(pkGuess == null)
				{
					pkGuess = new PKGuess(txtAPC,labScorePC,"PC",SingleGuess.this);
					pkGuess.start();  
				}
			}
		});
		
		
		button_1.setFont(new Font("幼圆", Font.BOLD, 12));
		button_1.setBounds(445, 3, 91, 38);
		contentPane.add(button_1);
		
		JLabel labPl = new JLabel("\u73A9\u5BB6");
		labPl.setText(FormLogin.member.getName());
		labPl.setFont(new Font("华文中宋", Font.BOLD, 20));
		labPl.setBounds(144, 100, 60, 21);
		contentPane.add(labPl);
		
		JLabel label_2 = new JLabel("\u7535\u8111");
		label_2.setFont(new Font("华文中宋", Font.BOLD, 20));
		label_2.setBounds(361, 100, 60, 21);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u5206\u6570\uFF1A");
		label_3.setFont(new Font("华文中宋", Font.BOLD, 20));
		label_3.setBounds(40, 164, 70, 21);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u5206\u6570\uFF1A");
		label_4.setFont(new Font("华文中宋", Font.BOLD, 20));
		label_4.setBounds(394, 164, 70, 21);
		contentPane.add(label_4);
		
		//玩家分数
		labScorePl = new JLabel(" 0");
		labScorePl.setFont(new Font("华文琥珀", Font.PLAIN, 23));
		labScorePl.setBounds(108, 161, 29, 33);
		contentPane.add(labScorePl);
		labScorePl.setText(""+FormLogin.member.getScore());
		
		
		JLabel lblVs = new JLabel("VS");
		lblVs.setFont(new Font("华文琥珀", Font.BOLD, 27));
		lblVs.setBounds(260, 93, 50, 33);
		contentPane.add(lblVs);
		
		//玩家头像
		JLabel lblIconPl = new JLabel("\u5934\u50CF");
		lblIconPl.setBorder (BorderFactory.createLineBorder(Color.black,2));
		lblIconPl.setBounds(42, 52, 92, 102);
		String str = FormLogin.member.getIcon();
		ImageIcon imageIconPl = new ImageIcon(str); 
		imageIconPl.setImage(imageIconPl.getImage().getScaledInstance(lblIconPl.getWidth(), lblIconPl.getHeight(), Image.SCALE_DEFAULT));
		lblIconPl.setIcon(imageIconPl);
		contentPane.add(lblIconPl);
		
		//电脑
		JLabel lblIconPC = new JLabel();
		lblIconPC.setText("0");
		lblIconPC.setBorder (BorderFactory.createLineBorder(Color.black,2));
		lblIconPC.setBounds(415, 51, 92, 102);
		ImageIcon imageIconPC = new ImageIcon("./Image/电脑.jpg");
		imageIconPC.setImage(imageIconPC.getImage().getScaledInstance(lblIconPC.getWidth(), lblIconPC.getHeight(), Image.SCALE_DEFAULT));
		lblIconPC.setIcon(imageIconPC);
		contentPane.add(lblIconPC);
		
		//背景
		JLabel lbl = new JLabel("New label");
		lbl.setBounds(0, 0, 579, 505);
		ImageIcon imageIcon = new ImageIcon(".\\Image\\1.jpg");
		imageIcon.setImage(imageIcon.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
		lbl.setIcon(imageIcon);
		contentPane.add(lbl);
		
		//窗口关闭事件
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Mode.setVisible(true);
				timer.stop();
				if(!isStart)
					return;
				DbConnect dbConnect = new DbConnect();
				try {
					dbConnect.Save(FormLogin.member.getAccount());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String fileName = "./历史游戏信息/"+FormLogin.member.getAccount()+".txt";
				File file = new File(fileName);
				if(file.exists()){
					Save(file);
				}
				else {
					try {
						if(file.createNewFile()){
							Save(file);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		setResizable(false);
	}
	
	public void Score(String str){
		int x = FormLogin.member.getScore();
		int a = (int)str.charAt(0)-48;
		int b = (int)str.charAt(2)-48;
		if(a!=0||b!=0){
			if(b==4){
				x += 4;
			}
			else if(a==4){
				x += 5;
				isGuess = true; 
				JOptionPane.showMessageDialog(this, "恭喜"+FormLogin.member.getName()+"回答正确！");
			}
			else if(a+b ==4){
				x += 3;
			}
			else if(a+b >2){
				x += 2;
			}else if(a+b >=1){
				x += 1;
			}
		}
		FormLogin.member.setScore(x);
		labScorePl.setText(""+x);
	}
	
	public void Save(File file){
		try {  
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件  
            FileWriter writer = new FileWriter(file, true);  
            Date date = new Date();
            String s = String.format("%tY-%<tm-%<td %<tT",date);
            writer.write(""+FormLogin.member.getScore()+",电脑,"+labScorePC.getText()+","+s+"\r\n");  
            writer.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
	}
}
