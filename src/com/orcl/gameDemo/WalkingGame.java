package com.orcl.gameDemo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WalkingGame
{
	//TODO a1全局变量“声明”在此


	int width = 280;
	int height = 300;
	窗口	ck	= null;
	定时器	ds1	= null;
	String _img = "fox/0-0.png";
	int flag_you = 0;
	int flag_shang = 0;
	int flag_xia = 0;
	int flag_zuo = 0;
	String J_img = "";
	WalkingGame()
	{
		//TODO b程序初始化在此



		ck = new 窗口();
		//窗口 宽+2*立体边, 高+2*立体边+标题栏
		ck.setSize(5 * 2 + 600, 600 + 2 * 5 + 25);
		//设定窗口可见性setVisible  true/false
		ck.setVisible(true);
		//TODO a2设置游戏快慢
		ds1 = new 定时器(1000); //延时的毫秒 1000毫秒等于1秒
	}

	class 窗口 extends JFrame
	{

		private static final long serialVersionUID = 1L;
		菜单		cd		= null;
		面板		mb		= null;
		窗口监听器	exit	= null;
		键盘监听器	jp		= null;

		窗口()
		{
			jp = new 键盘监听器();
			this.addKeyListener(jp);

			cd = new 菜单();
			this.setJMenuBar(cd);

			mb = new 面板();
			this.add(mb);

			exit = new 窗口监听器();
			this.addWindowListener(exit);

			this.repaint();
		}

		class 菜单 extends JMenuBar
		{
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;
			JMenu		dan;	//菜单
			JMenuItem	xiang1; //菜单项

			菜单监听器		cdjtq;

			菜单()
			{
				dan = new JMenu("游戏"); //菜单
				xiang1 = new JMenuItem("开局"); //菜单项

				this.add(dan);
				dan.add(xiang1);

				cdjtq = new 菜单监听器();
				xiang1.addActionListener(cdjtq);
			}

			class 菜单监听器 implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					// 菜单事件处理
					if (e.getSource() == xiang1)
					{

					}
				}
			}
		}

		class 窗口监听器 extends WindowAdapter
		{
			@SuppressWarnings("deprecation")
			public void windowClosing(WindowEvent e)
			{
				ds1.xc.stop();
				System.exit(0);
			}
		}

		class 键盘监听器 implements KeyListener
		{
			//TODO d2键盘处理，可加入KeyEvent.VK_XXX
			public void keyPressed(KeyEvent e)
			{
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_LEFT://键盘 左键
						System.out.println(flag_zuo);
						if(flag_zuo==3){
							_img = "fox/2-3.png";
							width -= 5;
							flag_zuo = 0;
						}
						if(flag_zuo==2){
							_img = "fox/2-2.png";
							width -= 5;
							flag_zuo = 3;
						}
						if(flag_zuo==1){
							_img = "fox/2-1.png";
							width -= 5;
							flag_zuo = 2 ;
						}
						if(flag_zuo == 0){
							_img = "fox/2-0.png";
							width -= 5;
							flag_zuo = 1 ;
						}
						break;
					case KeyEvent.VK_RIGHT://键盘 右键
						System.out.println(flag_you);
						if(flag_you==2){
							_img = "fox/0-3.png";
							width += 5;
							flag_you = 0;
						}
						if(flag_you==1){
							_img = "fox/0-2.png";
							width += 5;
							flag_you = 2 ;
						}
						if(flag_you == 0){
							_img = "fox/0-1.png";
							width += 5;
							flag_you = 1 ;
						}
						break;
					case KeyEvent.VK_UP://键盘 上键
						System.out.println(flag_shang);
						if(flag_shang==3){
							_img = "fox/1-3.png";
							height -= 5;
							flag_shang = 0;
						}
						if(flag_shang==2){
							_img = "fox/1-2.png";
							height -= 5;
							flag_shang = 3;
						}
						if(flag_shang==1){
							_img = "fox/1-1.png";
							height -= 5;
							flag_shang = 2 ;
						}
						if(flag_shang == 0){
							_img = "fox/1-0.png";
							height -= 5;
							flag_shang = 1 ;
						}
						break;
					case KeyEvent.VK_DOWN://键盘 下键
						System.out.println(flag_xia);
						if(flag_xia==3){
							_img = "fox/3-3.png";
							height += 5;
							flag_xia = 0;
						}
						if(flag_xia==2){
							_img = "fox/3-2.png";
							height += 5;
							flag_xia = 3;
						}
						if(flag_xia==1){
							_img = "fox/3-1.png";
							height += 5;
							flag_xia = 2 ;
						}
						if(flag_xia == 0){
							_img = "fox/3-0.png";
							height += 5;
							flag_xia = 1 ;
						}
						break;
					case KeyEvent.VK_J://键盘 J 发起攻击
						J_img = "fox/gj.gif";
						break;
					case KeyEvent.VK_Q://键盘 Q 取消攻击
						J_img = "";
						break;
				}
				repaint();//界面刷新
			}

			public void keyReleased(KeyEvent e)
			{
			}

			public void keyTyped(KeyEvent e)
			{
			}
		}

		class 面板 extends JPanel
		{

			private static final long serialVersionUID = 1L;
			鼠标监听器	sb	= null;

			面板()
			{
				sb = new 鼠标监听器();
				this.addMouseListener(sb);
				this.addMouseMotionListener(sb);
			}

			class 鼠标监听器 extends MouseAdapter implements MouseMotionListener
			{
				public void mousePressed(MouseEvent e)
				{
				}

				public void mouseDragged(MouseEvent e)
				{
				}

				public void mouseMoved(MouseEvent e)
				{
				}

				public void mouseReleased(MouseEvent e)
				{
				}

				public void mouseClicked(MouseEvent e)//鼠标单击
				{
					//mx my鼠标的位置
					int mx = e.getX(); //鼠标x坐标
					int my = e.getY();//鼠标y坐标
					//鼠标左键	BUTTON1、右键BUTTON3
					if (e.getButton() == MouseEvent.BUTTON1)
					{
						//TODO d3鼠标左键单击

					}

					repaint();//界面刷新
				}
			}
			/**
			 * 界面刷新方法的实现
			 */
			public void paint(Graphics g)
			{
				//g.setColor(Color.BLUE);	//设定颜色：RED红 GREEN绿  BLUE蓝  ORANGE橙  WHITE白  GRAY灰 BLACK黑 PINK粉红
				//g.drawRect(左,上,宽,高);	//绘制矩形
				//g.fillRect(左,上,宽,高);	//绘制填充矩形
				//g.drawOval(左,上,宽,高);	//绘制椭圆
				//g.fillOval(左,上,宽,高);		//绘制填充椭圆

				//Image  tu=(new ImageIcon( 路径 )).getImage();	//路径：图片-右键-属性-路径src/
				//g.drawImage(tu,x,y,null);

				//((Graphics2D)g).setStroke(new BasicStroke(线粗));//设置线的粗细
				//g.drawLine(点1X, 点1y, 点2X, 点2y);//画线

				//g.setFont(new Font("华文新魏", Font.BOLD, 22));//设置字体
				//g.drawString("文字", x, y);//设置文字

				//TODO c绘图在此

				// 加载背景图片
				Image bgImg = new ImageIcon("fox/backGround.png").getImage();
				g.drawImage(bgImg, 0, 0, null);
				Image image = new ImageIcon(_img).getImage();
				g.drawImage(image, width, height, null);
				Image Jimage = new ImageIcon(J_img).getImage();
				g.drawImage(Jimage, width-50, height, null);

			}
		}
	}

	class 定时器 implements Runnable//实现Runnable接口
	{
		Thread	xc	= null;
		long	时间间隔;

		定时器(long jianGe)
		{
			this.时间间隔 = jianGe;
			xc = new Thread(this);
			xc.start();
		}

		public void run()
		{
			while (true)
			{
				try
				{
					xc.sleep(时间间隔);

					if (this == ds1)
					{
						//TODO d1定时处理


						//---------------------------
						ck.repaint();//界面刷新
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	//main主方法 ，主类的“入口方法”
	public static void main(String[] args)
	{
		new WalkingGame();
	}
}

