
package 完整代码;




import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class 扑克找对Game
{
    //TODO:变量“声明”在此
    /**
     * 游戏的状态<br>
     * 0 选第一张Pai<br>
     * 1 选第二张Pai<br>
     * 2 展示选好的两张Pai<br>
     * 3 判定选好的两张Pai<br>
     */
    int zt= 0;

    /**
     * Pai库,4个花色,52张Pai
     */
    LinkedList<Pai> list= new LinkedList<Pai>();

    /**
     * 储存扑克的二维数组
     */
    Pai[][]pais=new Pai[4][13];

    /**
     *选框的行
     */
    int xuanHang;

    /**
     *选框的列
     */
    int xuanLie;

    /**
     * 第一次翻开的Pai
     */
    Pai first;

    /**
     * 第一次翻开的Pai的行
     */
    int firstHang=-1;

    /**
     * 第一次翻开的Pai的列
     */
    int firstLie=-1;

    /**
     * 控制选框闪烁
     */
    int shan=1;

    /**
     * 亮Pai计时
     */
    int jianGe;

    /**
     * 回合数
     */
    int count=1;


    窗口	ck	= null;
    定时器	ds1	= null;

    扑克找对Game()
    {
        //TODO:程序初始化在此
        //初始化Pai库
        for (int se = 1; se <=4; se++)
        {
            for (int dian = 1; dian <=13; dian++)
            {
                list.add(new Pai(dian,se));
            }
        }
        //打乱
        Collections.shuffle(list);
        //初始化游戏
        for (int hang = 0; hang < 4; hang++)
        {
            for (int lie = 0; lie <13; lie++)
            {
                pais[hang][lie] = list.removeFirst();
            }
        }


        //----------------------------------------------------------------------------
        ck = new 窗口();
        //窗口 宽+2*立体边, 高+2*立体边+标题栏
        ck.setSize(5 * 2 + 1350, 600 + 2 * 5 + 25);
        //设定窗口可见性setVisible  true/false
        ck.setVisible(true);
        //延时的毫秒
        ds1 = new 定时器(500);
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
                        if (是选Pai么()&&xuanLie>0)//选Pai阶段,才可以移动
                        {
                            xuanLie--;
                        }
                        break;
                    case KeyEvent.VK_RIGHT://右
                        if (是选Pai么()&&xuanLie<12)
                        {
                            xuanLie++;
                        }
                        break;
                    case KeyEvent.VK_UP://上
                        if (是选Pai么()&&xuanHang>0)
                        {
                            xuanHang--;
                        }
                        break;
                    case KeyEvent.VK_DOWN://下
                        if (是选Pai么()&&xuanHang<3)
                        {
                            xuanHang++;
                        }
                        break;
                    case KeyEvent.VK_SPACE://空格
                        //在选Pai判定阶段,才可以移动
                        if (是选Pai么())
                        {
                            翻Pai();
                        }

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
                        int hang=(my-10)/(120+10);
                        int lie=(mx-10)/(80+10);
                        if (是选Pai么()&&hang>=0&&hang<=3&&lie>=0&&lie<=12)
                        {
                            xuanHang=hang;
                            xuanLie=lie;
                            翻Pai();
                        }
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
                //背景
                Image  bgTu=(new ImageIcon( "扑克图Q版/bg.jpg" )).getImage();	//路径：图片-右键-属性-路径src/
                g.drawImage(bgTu,0,0,null);
                //显示栏
                Image  title=(new ImageIcon( "扑克图Q版/table.jpg" )).getImage();	//路径：图片-右键-属性-路径src/
                g.drawImage(title,1200,0,null);
                //回合数
                g.setColor(Color.RED);
                g.setFont(new Font("华文新魏", Font.BOLD, 22));//设置字体
                g.drawString("第"+count+"回合", 1230, 200);//设置文字

                //显示扑克Pai,扑克Pai大小,宽80,高120
                for (int hang = 0; hang < 4; hang++)
                {
                    for (int lie = 0; lie <13; lie++)
                    {
                        if (pais[hang][lie]!=null) //如果有Pai
                        {
                            pais[hang][lie].x = lie*(80+10)+10;
                            pais[hang][lie].y  = hang*(120+10)+10;
                            pais[hang][lie].xianShi(g);
                        }
                    }
                }
                //绘制选框
                g.setColor(Color.RED);
                ((Graphics2D)g).setStroke(new BasicStroke(4));//设置线的粗细
                if (shan==1) //让选框闪
                {
                    g.drawRect(7+xuanLie*(80+10),7+xuanHang*(120+10),80+6,120+6);	//矩形
                }

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
                        shan=-shan;
                        运行();


                        //--------------------------------------
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

    private boolean 是选Pai么(){
        return zt==0||zt==1;
    }

    private void 翻Pai()
    {
        //选择Pai的位置不能是空&&选择的不能是第一张Pai
        if (pais[xuanHang][xuanLie]!=null&&pais[xuanHang][xuanLie]!=first)
        {
            //翻开选框位置的Pai
            pais[xuanHang][xuanLie].zheng=true;
            if (zt==0) //如果是选择第一张Pai,那么记录翻开的Pai,以及行和列
            {
                first=pais[xuanHang][xuanLie];
                firstHang=xuanHang;
                firstLie=xuanLie;
            }
            zt++;
        }
    }

    private void 运行(){
        switch (zt)
        {
            case    0://选第一张Pai
                break;
            case    1://选第二张Pai
                break;
            case    2://展示选好的两张Pai
                if (jianGe<1) //展示Pai的时间
                {
                    jianGe++;
                }
                else
                {
                    zt++;
                    jianGe=0;
                }
                break;
            case    3://判定选好的两张Pai
                if (pais[xuanHang][xuanLie].dian==first.dian)
                {
                    //消除
                    pais[xuanHang][xuanLie]=null;
                    pais[firstHang][firstLie]=null;
                }
                else
                {
                    //变成背面
                    pais[xuanHang][xuanLie].zheng=false;
                    pais[firstHang][firstLie].zheng=false;
                }
                //初始化状态
                first=null;
                zt=0;
                firstHang=-1;
                firstLie=-1;
                count++;//回合数+1
                break;
        }
    }

    //main主方法 ，主类的“入口方法”
    public static void main(String[] args)
    {
        new 扑克找对Game();
    }

}

