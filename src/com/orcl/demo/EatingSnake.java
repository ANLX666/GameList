package com.orcl.demo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EatingSnake
{
	//TODO a1全局变量“声明”在此
	//X-列   Y-行
	int  foodL=2,foodH=1;
	int  direction=1;
	int  sLength=5;
	int[]  sLie=new int[400];
	int[]  sHang=new int[400];
	int  score=0;

	窗口	ck	= null;
	定时器	ds1	= null;

	EatingSnake()
	{
		//TODO b程序初始化在此
		for(int  i=0;i<sLength;i++)
		{
			sLie[i]=10;
			sHang[i]=10+i;
		}


		ck = new 窗口();
		//窗口 宽+2*立体边, 高+2*立体边+标题栏
		ck.setSize(5 * 2 + 824, 650 + 2 * 5 + 25);
		//设定窗口可见性setVisible  true/false
		ck.setVisible(true);
		//TODO a2设置游戏快慢
		ds1 = new 定时器(100); //延时的毫秒 1000毫秒等于1秒
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
						direction=2;
						break;
					case KeyEvent.VK_RIGHT://键盘 右键
						direction=0;
						break;
					case KeyEvent.VK_UP://键盘 上键
						direction=1;
						break;
					case KeyEvent.VK_DOWN://键盘 下键
						direction=3;
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
				//1.背景的显示
				Image  bg=(new ImageIcon( "snake/背景.PNG" )).getImage();	//路径：图片-右键-属性-路径src/
				g.drawImage(bg,0,0,null);

				//2.食物的显示
				Image  food=(new ImageIcon( "snake/西瓜.png" )).getImage();	//路径：图片-右键-属性-路径src/
				g.drawImage(food,foodL*30+10,foodH*30+10,null);

				//3.蛇头的显示
				Image  sHead=(new ImageIcon( "snake/头"+direction+".png" )).getImage();	//路径：图片-右键-属性-路径src/
				g.drawImage(sHead,sLie[0]*30+10,sHang[0]*30+10,null);
				//蛇身的显示
				Image  sBody=(new ImageIcon( "snake/身.png" )).getImage();	//路径：图片-右键-属性-路径src/
//				g.drawImage(sBody,10*30+10,11*30+10,null);
//				g.drawImage(sBody,10*30+10,12*30+10,null);
//				g.drawImage(sBody,10*30+10,13*30+10,null);
//				g.drawImage(sBody,10*30+10,14*30+10,null);
				for(int i=1;i<sLength;i++)
				{
					g.drawImage(sBody,sLie[i]*30+10,sHang[i]*30+10,null);
				}

				//4.计分板的显示
				g.setColor(Color.white);
				g.setFont(new Font("微软雅黑",Font.BOLD,23));
				g.drawString("当前得"+score+"分",650,420);

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
						//1.蛇爬
						//1.1 蛇身爬
						for(int i=sLength;i>=1;i--)
						{
							//蛇身行上一节的位置 赋值给当前蛇行的位置
							sHang[i]=sHang[i-1];
							//蛇身列上一节的位置 赋值给当前蛇列的位置
							sLie[i]=sLie[i-1];
						}

						//1.2 蛇头爬
						switch(direction)
						{
							case  0://右
								sLie[0]++;
								break;
							case 1://上
								sHang[0]--;
								break;
							case 2://左
								sLie[0]--;
								break;
							case 3://下
								sHang[0]++;
								break;
						}

						//2.蛇吃食物
						if(sHang[0]==foodH  &&  sLie[0]==foodL)
						{
							//1.蛇身变长
							sLength++;
							//2.分数增加
							score+=5;
							//3.食物随机出现-->>随机--范围
							foodL=(int)(Math.random()*20);
							foodH=(int)(Math.random()*20);
						}

						//3.游戏结束
						//3.1 出边界结束
						if(sLie[0]>19  || sLie[0]<0  || sHang[0]>19 || sHang[0]<0  )
						{
							System.exit(0);
						}

						//3.2 吃到自己结束
						boolean  flag=false;

						//用蛇头的行列位置跟蛇身的每一节行列位置进行匹配
						for(int i=1;i<sLength;i++)
						{
							//如果碰到蛇身的任意一节 状态变成true
							if(sHang[0]==sHang[i]  && sLie[0]==sLie[i])
							{
								flag=true;
								break;
							}
						}

						if(flag)
						{
							System.exit(0);
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
		new EatingSnake();
	}
}

