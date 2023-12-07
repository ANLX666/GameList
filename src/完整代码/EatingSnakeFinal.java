

package 完整代码;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

    public class EatingSnakeFinal
    {
        //TODO:变量“声明”在此

        //蛇长度
        int sheChang=5;

        //蛇列数组
        int[] sheLie=new int[400];

        //蛇行数组
    int[] sheHang=new int[400];

    //蛇头方向
    int fx = 1;

    //豆列位置
    int douLie = 2;

    //豆行位置
    int douHang = 1;


    //得分
    int score=0;


    窗口	ck	= null;
    定时器	ds1	= null;

    EatingSnakeFinal()
    {
        //TODO:程序初始化在此
//	      sheLie[0]=10;
//        sheLie[1]=10;
//        sheLie[2]=10;
//        sheLie[3]=10;
//        sheLie[4]=10;
//
//        sheHang[0]=10;
//        sheHang[1]=11;
//        sheHang[2]=12;
//        sheHang[3]=13;
//        sheHang[4]=14;

        //初始化蛇身
        for (int jie = 0; jie <sheChang; jie++)
        {
            sheHang[jie]=10+jie;
            sheLie[jie]=10;
        }



        ck = new 窗口();
        //窗口 宽+2*立体边, 高+2*立体边+标题栏
        ck.setSize(5 * 2 + 824, 650 + 2 * 5 + 25);
        //设定窗口可见性setVisible  true/false
        ck.setVisible(true);
        //延时的毫秒
        ds1 = new 定时器(200);
    }

    class 窗口 extends JFrame
    {
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
                    //TODO:菜单事件处理
                    if (e.getSource() == xiang1)
                    {

                    }
                }
            }
        }

        class 窗口监听器 extends WindowAdapter
        {
            public void windowClosing(WindowEvent e)
            {
                ds1.xc.stop();
                System.exit(0);
            }
        }

        class 键盘监听器 implements KeyListener
        {
            //TODO:键盘处理，可加入KeyEvent.VK_XXX
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_LEFT://左
                        if(fx!=0)
                        {
                            fx=2;
                        }
                        break;
                    case KeyEvent.VK_RIGHT://右
                        if (fx!=2)
                        {
                            fx=0;
                        }
                        break;
                    case KeyEvent.VK_UP://上
                        if (fx!=3)
                        {
                            fx=1;
                        }
                        break;
                    case KeyEvent.VK_DOWN://下
                        if (fx!=1)
                        {
                            fx=3;
                        }
                        break;
                    case  KeyEvent.VK_SPACE://空格
                        ds1.xc.suspend();// 游戏暂停
                        break;
                    case  KeyEvent.VK_R://R键
                        ds1.xc.resume();// 游戏继续
                        break;
                }

                repaint();
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
                    int mx = e.getX();
                    int my = e.getY();
                    //鼠标左键	BUTTON1、右键BUTTON3
                    if (e.getButton() == MouseEvent.BUTTON1)
                    {
                        //TODO:鼠标左键单击

                    }

                    repaint();
                }
            }

            public void paint(Graphics g)
            {
                //g.setColor(Color.BLUE);	//设定颜色：RED GREEN  BLUE  ORANGE  WHITE  GRAY BLACK PINK
                //g.drawRect(左,上,宽,高);	//矩形
                //g.fillRect(左,上,宽,高);	//实心矩形
                //g.drawOval(左,上,宽,高);	//椭圆
                //g.fillOval(左,上,宽,高);		//实心椭圆

                //Image  tu=(new ImageIcon( 路径 )).getImage();	//路径：图片-右键-属性-路径src/
                //g.drawImage(tu,x,y,null);

                //((Graphics2D)g).setStroke(new BasicStroke(线粗));//设置线的粗细
                //g.drawLine(点1X, 点1y, 点2X, 点2y);//画线

                //g.setFont(new Font("华文新魏", Font.BOLD, 22));//设置字体
                //g.drawString("文字", x, y);//设置文字

                //TODO:绘图在此


                //1.绘制背景图
                Image  bj=(new ImageIcon( "snake/背景.PNG" )).getImage();    //路径：图片-右键-属性-路径src/
                g.drawImage(bj,0,0,null);


                //2.绘制豆图
                Image  dou=(new ImageIcon( "snake/豆.png" )).getImage();    //路径：图片-右键-属性-路径src/
                g.drawImage(dou,douLie*30+10,douHang*30+10,null);


                //3绘制蛇头
                Image  sheTou=(new ImageIcon( "snake/头" + fx + ".png" )).getImage();    //路径：图片-右键-属性-路径src/
                g.drawImage(sheTou,sheLie[0]*30+10,sheHang[0]*30+10,null);

                //4.绘制蛇身
                Image  sheShen=(new ImageIcon( "snake/身.png" )).getImage();    //路径：图片-右键-属性-路径src/
                for (int jie = 1; jie <sheChang; jie++)
                {
                    g.drawImage(sheShen,sheLie[jie]*30+10,sheHang[jie]*30+10,null);
                }


                //5.计分板

                g.setColor(Color.white);
                g.setFont(new Font("微软雅黑", Font.BOLD, 22));//设置字体
                g.drawString("当前得分"+score, 650, 350);//设置文字

            }
        }
    }

    class 定时器 implements Runnable//实现Runnable接口
    {
        Thread	xc	= null;
        long	jianGe;

        定时器(long jianGe)
        {
            this.jianGe = jianGe;
            xc = new Thread(this);
            xc.start();
        }

        public void run()
        {
            while (true)
            {
                try
                {
                    xc.sleep(jianGe);

                    if (this == ds1)
                    {
                        //TODO:定时处理
                        //蛇身动
                        for (int jie = sheChang; jie >= 1; jie--)
                        {
                            sheHang[jie]=sheHang[jie-1];
                            sheLie[jie]=sheLie[jie-1];
                        }

                        //蛇头动
                        switch (fx)
                        {
                            case    0:
                                sheLie[0]++;
                                break;
                            case    1:
                                sheHang[0]--;
                                break;
                            case    2:
                                sheLie[0]--;
                                break;
                            case    3:
                                sheHang[0]++;
                                break;
                        }



                        //吃豆
                        if (douHang==sheHang[0] &&  douLie==sheLie[0])
                        {
                            //1.蛇张长
                            sheChang++;
                            //2.出新豆
                            boolean  keepCh=false;
                            do
                            {
                                keepCh=false;
                                douHang=(int)(Math.random()*20);
                                douLie=(int)(Math.random()*20);
                                for (int jie = 0; jie <sheChang; jie++)
                                {
                                    if (douHang==sheHang[jie] && douLie==sheLie[jie])
                                    {
                                        keepCh=true;
                                        break;//跳出
                                    }
                                }
                            }
                            while (keepCh);
                            //3.分数+10
                            score+=10;
                        }


                        //出界
                        if(sheHang[0]<0 || sheHang[0]>19  || sheLie[0]<0 || sheLie[0]>19)
                        {
                            JOptionPane.showMessageDialog(null, "得分"+score+"分", "游戏结束", JOptionPane.ERROR_MESSAGE);
                            System.exit(0);
                        }


                        //自咬
                        boolean keepYao=false;
                        for (int jie = 1; jie <sheChang; jie++)
                        {
                            if (sheHang[0]==sheHang[jie] && sheLie[0]==sheLie[jie])
                            {
                                keepYao=true;
                            }
                        }

                        if (keepYao)
                        {
                            JOptionPane.showMessageDialog(null, "得分"+score+"分", "游戏结束", JOptionPane.ERROR_MESSAGE);
                            System.exit(0);
                        }










                        ck.repaint();
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
        new EatingSnakeFinal();
    }
}

