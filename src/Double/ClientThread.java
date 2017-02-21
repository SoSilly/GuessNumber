package Double;
import java.net.*;
import java.util.regex.*;

import javax.swing.*;

import Access.FormLogin;
import Information.Judge;

import java.awt.Image;
import java.io.*;
public class ClientThread implements Runnable {
	Socket s;
	JTextArea textAP;
	BufferedReader br = null;
	PrintStream pr = null;
	Timer timer;
	JButton btnOk;
	JLabel lblIcon2;
	JLabel lblScoreP2;
	JLabel lblP2;
	Timer timer1;
	JFrame doubleGame;
	public Boolean exit = true;
	public ClientThread(JFrame doubleGame,Socket s,JTextArea textAP2,Timer timer,Timer timer1,JButton btnOk,JLabel lblIcon2,JLabel lblScoreP2,JLabel lblP2) {
		this.s = s;
		textAP = textAP2;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pr = new PrintStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.timer = timer;
		this.timer1 = timer1;
		this.btnOk = btnOk;
		this.lblIcon2 = lblIcon2;
		this.lblScoreP2 = lblScoreP2;
		this.lblP2 = lblP2;
		this.doubleGame = doubleGame;
	}
	@Override
	public void run() {
		Pattern p1 = Pattern.compile("(?i).+?\\.(jpg|gif|bmp|png)"); //ƥ��Է�ͼƬ
		Pattern p2 = Pattern.compile(".+\\dA\\dB"); //ƥ������ֽ��
		Pattern p3 = Pattern.compile("\\d*");	//ƥ�����
		Pattern p4 = Pattern.compile("\\d\\d\\d\\d");	//ƥ����Ҫ�²������
		try{
			String content = null;
			while((content = br.readLine())!=null){
				if(!exit) break;
				if(p1.matcher(content).matches()){
					ImageIcon imageIcon = new ImageIcon(content);
					imageIcon.setImage(imageIcon.getImage().getScaledInstance(lblIcon2.getWidth(), lblIcon2.getHeight(), Image.SCALE_DEFAULT));
					lblIcon2.setIcon(imageIcon);
				}
				else if(p4.matcher(content).matches()){
					Judge.number = content;
				}
				else if(p3.matcher(content).matches()){
					lblScoreP2.setText(content);
				}
				else if(content.equals("����")){
					DoubleGame.flag = true;
					pr.println(FormLogin.member.getIcon());
					pr.println(FormLogin.member.getScore());
					pr.println("������"+FormLogin.member.getName());
				}
				else if(p2.matcher(content).matches()){
					textAP.append(content+"\n");
					btnOk.setEnabled(true);
					timer.start();
					timer1.stop();
				}
				else if(content.equals("Ӯ")){
					DoubleGame.isGuess2 = true;
				}
				//����
				else if(content.equals("����")){
					JDialog jDialog = new JDialog();
					JLabel lblPrepare = new JLabel("������...");
					lblPrepare.setBounds(100,30, 100, 50);
					jDialog.setSize(200, 100);
					jDialog.setLocationRelativeTo(doubleGame);
					jDialog.getContentPane().add(lblPrepare);
					jDialog.setVisible(true);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					jDialog.setVisible(false);
					timer.start();
					btnOk.setEnabled(true);
					timer1.stop();
				}
				//����
				else if(content.equals("������")){
					JDialog jDialog = new JDialog();
					JLabel lblPrepare = new JLabel("������...");
					lblPrepare.setBounds(100,30, 100, 50);
					jDialog.setSize(200, 100);
					jDialog.setLocationRelativeTo(doubleGame);
					jDialog.getContentPane().add(lblPrepare);
					jDialog.setVisible(true);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					jDialog.setVisible(false);
					timer1.start();
					timer.stop();
				}
				//����
				else{
					String[] str = content.split("������");
					lblP2.setText(str[1]);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

