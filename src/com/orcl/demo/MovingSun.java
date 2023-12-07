package com.orcl.demo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovingSun
{
	//TODO a1全局变量“声明”在此
	int  x=140,y=254;
	int  size=80;//保存太阳宽高的一个变量

	int  flag=0;//0代表最小状态  1代表最大状态

	int  direction=0;//0右 1上  2左  3下

	窗口	ck	= null;
	定时器	ds1	= null;

	MovingSun()
	{
		//TODO b程序初始化在此



		ck = new 窗口();
		//窗口 宽+2*立体边, 高+2*立体边+标题栏
		ck.setSize(5 * 2 + 600, 600 + 2 * 5 + 25);
		//设定窗口可见性setVisible  true/false
		ck.setVisible(true);
		//TODO a2设置游戏快慢
		ds1 = new 定时器(10); //延时的毫秒 1000毫秒等于1秒
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
					//如果没有碰到左侧边界 就继续向左移动
					direction=2;
					break;
				case KeyEvent.VK_RIGHT://键盘 右键
					//如果没碰到右侧边界 就继续向右移动
					direction=0;
					break;
				case KeyEvent.VK_UP://键盘 上键
					//如果没有碰到上方边界 就继续向上移动
					direction=1;
					break;
				case KeyEvent.VK_DOWN://键盘 下键
					//如果没有碰到下方边界 就继续向下移动
					direction=3;
					break;
				case  KeyEvent.VK_W://键盘w键 太阳变大
					size+=5;
					break;
				case  KeyEvent.VK_S://键盘S键  太阳变小
					size-=5;
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
				//g.fillOval(左,上,宽,高);	//绘制填充椭圆

				//Image  tu=(new ImageIcon( 路径 )).getImage();	//路径：图片-右键-属性-路径src/
				//g.drawImage(tu,x,y,null);

				//((Graphics2D)g).setStroke(new BasicStroke(线粗));//设置线的粗细
				//g.drawLine(点1X, 点1y, 点2X, 点2y);//画线

				//g.setFont(new Font("华文新魏", Font.BOLD, 22));//设置字体
				//g.drawString("文字", x, y);//设置文字

				//TODO c绘图在此
				g.setColor(Color.RED);
				g.fillOval(x,y,size,size);


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
						//1.根据太阳的运动方向来决定太阳如何移动
						switch(direction)
						{
							case  0://右
								//如果碰到右侧边界运动的方向变成反方向移动
								//否则继续向右移动
								if(x>600-size-15)
								{
									direction=2;
								}
								else
								{
									x+=5;
								}
								break;
							case  1://上
								//如果碰到上方边界 方向变下
								//否则继续向上移动
								if(y<0)
								{
									direction=3;
								}
								else
								{
									y-=5;
								}
								break;
							case  2://左
								//如果碰到左侧边界 就方向变右
								//否则 继续向左移动
								if(x<0)
								{
									direction=0;
								}
								else
								{
									x-=5;
								}
								break;
							case  3://下
								//如果碰到下方边界  方向变上
								//否则继续向下移动
								if(y>600-size-25-10)
								{
									direction=1;
								}
								else
								{
									y+=5;
								}
								break;
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

