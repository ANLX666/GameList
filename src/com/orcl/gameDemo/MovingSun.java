package com.orcl.gameDemo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovingSun
{
	//TODO a1全局变量“声明”在此

	int width = 50;
	int height = 50;
	int x = 300 ;
	int y = 300 ;
	窗口	ck	= null;
	定时器	ds1	= null;
	Dimension newSize;
	MovingSun()
	{
		//TODO b程序初始化在此



		ck = new 窗口();
		//窗口 宽+2*立体边, 高+2*立体边+标题栏
		ck.setSize(5 * 2 + 600, 600 + 2 * 5 + 25);
		//设定窗口可见性setVisible  true/false
		ck.setVisible(true);
		//TODO a2设置游戏快慢
		ds1 = new 定时器(1000); //延时的毫秒 1000毫秒等于1秒



//		SwingUtilities.invokeLater(() -> {
//			JFrame frame = new JFrame("Window Size Example");
//
//			// 设置窗口的大小
//			frame.setSize(600, 600);
//
//			// 为确保能够获取到窗口大小，最好在窗口显示之前获取大小
//			Dimension size = frame.getSize();
//			System.out.println("Initial Window Size: " + size.width + "x" + size.height);
//
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			frame.setVisible(true);
//
//			// 添加组件、事件处理等其他逻辑
//
//			// 在需要的时候获取当前窗口大小
//			frame.addComponentListener(new java.awt.event.ComponentAdapter() {
//				public void componentResized(java.awt.event.ComponentEvent evt) {
//					// 在窗口大小变化时触发
//					Dimension newSize = ck.getSize();
//					System.out.println("Window Size Changed: " + newSize.width + "x" + newSize.height);
//				}
//			});
//		});
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
				System.out.println(e);
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_LEFT://键盘 左键
						if (x-5>=0){
							x-=5; // px
							break;
						}
						x = 600 - width- 25;
						break;
					case KeyEvent.VK_RIGHT://键盘 右键
						if(x+5<=600-width-25){
							x+=5;
							break;
						}
						x = 0;
						break;
					case KeyEvent.VK_UP://键盘 上键
						if(y-5>=0){
							y-=5;
							break;
						}
						y = 600-height-25;
						break;
					case KeyEvent.VK_DOWN://键盘 下键
						if(y+5<=600-height-25){
							y+=5;
							break;
						}
						y = 0;
						break;
					case KeyEvent.VK_W://w
						width+=5;
						height+=5;
						break;
					case KeyEvent.VK_S://s
						width-=5;
						height-=5;
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
				g.setColor(Color.RED);


				g.fillOval(x,y,width,height);

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
			int flag = 0;

			while (true)
			{
				try
				{
					xc.sleep(时间间隔);

					if (this == ds1)
					{
						//TODO d1定时处理
						if(flag ==0 ){
							if (width < 300 && height < 300 ) {
								width += 50;
								height += 50;
							}
						}
						if (width == 300 && height == 300) {
							flag =  1 ;
						}
						System.out.println(height +  " " + width);
						System.out.println(flag );
						if (flag == 1 ){
							System.out.println("开始缩小");
							if(width > 0 && height >0 ) {
								width -= 50;
								height -= 50;
							}
						}
						if(width == 0 && height == 0 ) {
							flag = 0 ;
						}
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
		new MovingSun();
	}
}

