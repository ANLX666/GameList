

package 完整代码;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ELuoSiFangKuaiGame
{
    // TODO:变量“声明”在此
    /**
     * 可以下落的方块
     */
    int[][] luoKuai    = new int[20][10];

    /**
     * 落实 不动的方块
     */
    int[][] shiKuai    = new int[20][10];

    /**
     * 记录得分
     */
    int     fenShu     = 0;

    /**
     * 定位数30的行
     */
    int     dingH      = 1;

    /**
     * 定位数30的列
     */
    int     dingL      = 1;

    /**
     * 辅助原数组
     */
    int[][] fks        = new int[3][3];

    /**
     * 替换成的新数组
     */
    int[][] ths        = new int[3][3];

    /**
     * 当前方块种类
     */
    int     dqZhongLei = 1;

    // --------------------------------------------------------------------------------------

    窗口      ck         = null;
    定时器     ds1        = null;

    ELuoSiFangKuaiGame()
    {
        // TODO:程序初始化在此
        // luoKuai[0][3] = 1;luoKuai[0][4] = 30;luoKuai[0][5] = 1;
        // luoKuai[1][4] = 1;

        luoKuai[0][3] = 1;
        luoKuai[1][3] = 1;
        luoKuai[2][3] = 1;
        luoKuai[3][3] = 30;

        ck = new 窗口();
        // 窗口 宽+2*立体边, 高+2*立体边+标题栏
        ck.setSize(5 * 2 + 600, 650 + 2 * 5 + 25);
        // 设定窗口可见性setVisible true/false
        ck.setVisible(true);
        // 延时的毫秒
        ds1 = new 定时器(500);
    }

    class 窗口 extends JFrame
    {
        菜单    cd   = null;
        面板    mb   = null;
        窗口监听器 exit = null;
        键盘监听器 jp   = null;

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
            JMenu     dan;   // 菜单
            JMenuItem xiang1; // 菜单项

            菜单监听器     cdjtq;

            菜单()
            {
                dan = new JMenu("游戏"); // 菜单
                xiang1 = new JMenuItem("开局"); // 菜单项

                this.add(dan);
                dan.add(xiang1);

                cdjtq = new 菜单监听器();
                xiang1.addActionListener(cdjtq);
            }

            class 菜单监听器 implements ActionListener
            {
                public void actionPerformed(ActionEvent e)
                {
                    // TODO:菜单事件处理
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
            // TODO:键盘处理，可加入KeyEvent.VK_XXX
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_LEFT:// 左

                        // TODO A1左不出界 true
                        boolean nengZuo = true;
                        for (int hang = 0; hang < 20; hang++)
                        {
                            if (luoKuai[hang][0] != 0)
                            {
                                nengZuo = false;
                            }
                        }
                        // TODO A2左不挡住 true
                        boolean zuoBuDang = true;
                        for (int lie = 1; lie < 10; lie++)
                        {
                            for (int hang = 0; hang < 20; hang++)
                            {
                                if (luoKuai[hang][lie] != 0
                                        && shiKuai[hang][lie - 1] != 0)
                                {
                                    zuoBuDang = false;
                                }
                            }
                        }

                        if (nengZuo == true && zuoBuDang == true)
                        {
                            // TODO A3左移动
                            for (int lie = 1; lie < 10; lie++)
                            {
                                for (int hang = 0; hang < 20; hang++)
                                {
                                    luoKuai[hang][lie - 1] = luoKuai[hang][lie];
                                    luoKuai[hang][lie] = 0;
                                }
                            }
                        }

                        break;
                    case KeyEvent.VK_RIGHT:// 右
                        // TODO B1右不出界 true
                        boolean nengYou = true;
                        for (int hang = 0; hang < 20; hang++)
                        {
                            if (luoKuai[hang][9] != 0)
                            {
                                nengYou = false;
                            }
                        }
                        // TODO B2右不挡住 true
                        boolean youBuDang = true;
                        for (int lie = 8; lie >= 0; lie--)
                        {
                            for (int hang = 0; hang < 20; hang++)
                            {
                                if (luoKuai[hang][lie] != 0
                                        && shiKuai[hang][lie + 1] != 0)
                                {
                                    youBuDang = false;
                                }
                            }
                        }

                        if (nengYou == true && youBuDang == true)
                        {
                            // TODO B3右移动
                            for (int lie = 8; lie >= 0; lie--)
                            {
                                for (int hang = 0; hang < 20; hang++)
                                {
                                    luoKuai[hang][lie + 1] = luoKuai[hang][lie];
                                    luoKuai[hang][lie] = 0;
                                }
                            }
                        }
                        break;
                    case KeyEvent.VK_UP:// 上
                        // TODO 顺时针变化
                        // 记录定位的行和列
                        for (int hang = 0; hang < 20; hang++)
                        {
                            for (int lie = 0; lie < 10; lie++)
                            {
                                if (luoKuai[hang][lie] == 30)
                                {
                                    dingH = hang;
                                    dingL = lie;
                                }
                            }
                        }
                        // 判断dingH位置和方块种类
                        if (dingH != 19 && dqZhongLei != 1 && dqZhongLei != 0) // 不是竖条和方块的话
                        {
                            if (dingL >= 1 && dingL <= 8) // dingL最多到8,到9会越界
                            {
                                // 以luoKuai[hang][lie]==30定位
                                // 行和列为(1,1),把luoKuai复制进fks
                                for (int hang = 0; hang < fks.length; hang++)
                                {
                                    for (int lie = 0; lie < fks[hang].length; lie++)
                                    {
                                        // fks[0][0]=luoKuai[dingH-1][dingL-1];
                                        // fks[0][1]=luoKuai[dingH-1][dingL];
                                        // fks[0][2]=luoKuai[dingH-1][dingL+1];
                                        // fks[0][3]=luoKuai[dingH-1][dingL+2];
                                        //
                                        // fks[1][0]=luoKuai[dingH][dingL-1];
                                        // fks[1][1]=luoKuai[dingH][dingL];
                                        // fks[1][2]=luoKuai[dingH][dingL+1];
                                        // fks[1][3]=luoKuai[dingH][dingL+2];
                                        fks[hang][lie] = luoKuai[dingH - 1 + hang][dingL
                                                - 1 + lie];
                                    }
                                }
                                // 变换>>顺时针
                                for (int hang = 0; hang < fks.length; hang++)
                                {
                                    for (int lie = 0; lie < fks[hang].length; lie++)
                                    {
                                        ths[lie][ths[hang].length - 1 - hang] = fks[hang][lie];
                                    }
                                }
                                // 判断旋转之后,是否遇到实块
                                boolean youWeiZhi = true;
                                for (int hang = 0; hang < ths.length; hang++)
                                {
                                    for (int lie = 0; lie < ths[hang].length; lie++)
                                    {
                                        if (ths[hang][lie] != 0
                                                && shiKuai[dingH - 1 + hang][dingL
                                                - 1 + lie] != 0)
                                        {
                                            youWeiZhi = false;
                                        }
                                    }
                                }
                                // 把ths复制给luoKuai
                                if (youWeiZhi)
                                {
                                    for (int hang = 0; hang < ths.length; hang++)
                                    {
                                        for (int lie = 0; lie < ths[hang].length; lie++)
                                        {
                                            luoKuai[dingH - 1 + hang][dingL - 1
                                                    + lie] = ths[hang][lie];
                                        }
                                    }
                                }
                            }
                            else if (dingL == 0)
                            {
                                // 以luoKuai[hang][lie]==30定位
                                // 行和列为(1,1),把luoKuai复制进fks
                                for (int hang = 0; hang < fks.length; hang++)
                                {
                                    for (int lie = 0; lie < fks[hang].length; lie++)
                                    {
                                        fks[hang][lie] = luoKuai[dingH - 1 + hang][dingL
                                                + lie];
                                    }
                                }
                                // 变换>>顺时针
                                for (int hang = 0; hang < fks.length; hang++)
                                {
                                    for (int lie = 0; lie < fks[hang].length; lie++)
                                    {
                                        ths[lie][ths[hang].length - 1 - hang] = fks[hang][lie];
                                    }
                                }
                                // 判断旋转之后,是否遇到实块
                                boolean youWeiZhi = true;
                                for (int hang = 0; hang < ths.length; hang++)
                                {
                                    for (int lie = 0; lie < ths[hang].length; lie++)
                                    {
                                        if (ths[hang][lie] != 0
                                                && shiKuai[dingH - 1 + hang][dingL
                                                + lie] != 0)
                                        {
                                            youWeiZhi = false;
                                        }
                                    }
                                }
                                // 把ths复制给luoKuai
                                if (youWeiZhi)
                                {
                                    for (int hang = 0; hang < ths.length; hang++)
                                    {
                                        for (int lie = 0; lie < ths[hang].length; lie++)
                                        {
                                            luoKuai[dingH - 1 + hang][dingL + lie] = ths[hang][lie];
                                        }
                                    }
                                }
                            }
                            else if (dingL == 9)
                            {
                                // 以luoKuai[hang][lie]==30定位
                                // 行和列为(1,1),把luoKuai复制进fks
                                for (int hang = 0; hang < fks.length; hang++)
                                {
                                    for (int lie = 0; lie < fks[hang].length; lie++)
                                    {
                                        fks[hang][lie] = luoKuai[dingH - 1 + hang][dingL
                                                - 2 + lie];
                                    }
                                }
                                // 变换>>顺时针
                                for (int hang = 0; hang < fks.length; hang++)
                                {
                                    for (int lie = 0; lie < fks[hang].length; lie++)
                                    {
                                        ths[lie][ths[hang].length - 1 - hang] = fks[hang][lie];
                                    }
                                }
                                // 判断旋转之后,是否遇到实块
                                boolean youWeiZhi = true;
                                for (int hang = 0; hang < ths.length; hang++)
                                {
                                    for (int lie = 0; lie < ths[hang].length; lie++)
                                    {
                                        if (ths[hang][lie] != 0
                                                && shiKuai[dingH - 1 + hang][dingL
                                                - 2 + lie] != 0)
                                        {
                                            youWeiZhi = false;
                                        }
                                    }
                                }
                                // 把ths复制给luoKuai
                                if (youWeiZhi)
                                {
                                    for (int hang = 0; hang < ths.length; hang++)
                                    {
                                        for (int lie = 0; lie < ths[hang].length; lie++)
                                        {
                                            luoKuai[dingH - 1 + hang][dingL - 2
                                                    + lie] = ths[hang][lie];
                                        }
                                    }
                                }
                            }
                        }
                        else if (dqZhongLei == 1)// 如果是条形(dqZhongLei=1),定位30在最下面
                        {
                            if (luoKuai[dingH - 1][dingL] == 0)// 判断是横条(30在最左侧,变成竖条30在最下面)
                            {
                                luoKuai[dingH][dingL + 1] = 0;
                                luoKuai[dingH][dingL + 2] = 0;
                                luoKuai[dingH][dingL + 3] = 0;

                                luoKuai[dingH - 3][dingL] = 1;
                                luoKuai[dingH - 2][dingL] = 1;
                                luoKuai[dingH - 1][dingL] = 1;
                            }
                            else if (luoKuai[dingH - 1][dingL] == 1)// 判断是竖条(转成横条之后,30在最左侧)
                            {
                                if (dingL <= 6) // 有地方转
                                {
                                    boolean youWeiZhi = true;
                                    // 判断旋转之后,是否遇到实块
                                    if (shiKuai[dingH][dingL + 1] != 0
                                            || shiKuai[dingH][dingL + 2] != 0
                                            || shiKuai[dingH][dingL + 3] != 0)
                                    {
                                        youWeiZhi = false;
                                    }
                                    if (youWeiZhi)
                                    {
                                        luoKuai[dingH - 3][dingL] = 0;
                                        luoKuai[dingH - 2][dingL] = 0;
                                        luoKuai[dingH - 1][dingL] = 0;

                                        luoKuai[dingH][dingL + 1] = 1;
                                        luoKuai[dingH][dingL + 2] = 1;
                                        luoKuai[dingH][dingL + 3] = 1;
                                    }
                                }
                                else
                                {
                                    // 没地方转
                                    boolean youWeiZhi = true;
                                    if (shiKuai[dingH][6] != 0
                                            || shiKuai[dingH][7] != 0
                                            || shiKuai[dingH][8] != 0
                                            || shiKuai[dingH][9] != 0)
                                    {
                                        youWeiZhi = false;
                                    }
                                    // 判断旋转之后,是否遇到实块
                                    if (youWeiZhi)
                                    {
                                        luoKuai[dingH - 3][dingL] = 0;
                                        luoKuai[dingH - 2][dingL] = 0;
                                        luoKuai[dingH - 1][dingL] = 0;
                                        luoKuai[dingH][dingL] = 0;

                                        luoKuai[dingH][6] = 30;
                                        luoKuai[dingH][7] = 1;
                                        luoKuai[dingH][8] = 1;
                                        luoKuai[dingH][9] = 1;
                                    }
                                }
                            }
                        }

                        break;
                    case KeyEvent.VK_DOWN:// 下
                        // 下落 "途中"
                        // C1下不挡住
                        boolean xiaBuDang = true;
                        for (int hang = 18; hang >= 0; hang--)
                        {
                            for (int lie = 0; lie < 10; lie++)
                            {
                                if (luoKuai[hang][lie] != 0
                                        && shiKuai[hang + 1][lie] != 0)
                                {
                                    xiaBuDang = false;
                                }
                            }
                        }

                        // C2能下出界
                        boolean nengXia = true;// 假设能
                        for (int lie = 0; lie < 10; lie++)
                        {
                            if (luoKuai[19][lie] != 0)
                            {
                                nengXia = false;// 推翻假设
                            }
                        }

                        if (nengXia == true && xiaBuDang == true)
                        {
                            // C3下落
                            for (int hang = 18; hang >= 0; hang--)
                            {
                                for (int lie = 0; lie < 10; lie++)
                                {
                                    luoKuai[hang + 1][lie] = luoKuai[hang][lie];
                                    luoKuai[hang][lie] = 0;
                                }
                            }
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
            鼠标监听器 sb = null;

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

                public void mouseClicked(MouseEvent e)// 鼠标单击
                {
                    // mx my鼠标的位置
                    int mx = e.getX();
                    int my = e.getY();
                    // 鼠标左键 BUTTON1、右键BUTTON3
                    if (e.getButton() == MouseEvent.BUTTON1)
                    {
                        // TODO:鼠标左键单击

                    }

                    repaint();
                }
            }

            public void paint(Graphics g)
            {
                // g.setColor(Color.BLUE); //设定颜色：RED GREEN BLUE ORANGE WHITE
                // GRAY BLACK PINK
                // g.drawRect(左,上,宽,高); //矩形
                // g.fillRect(左,上,宽,高); //实心矩形
                // g.drawOval(左,上,宽,高); //椭圆
                // g.fillOval(左,上,宽,高); //实心椭圆

                // Image tu=(new ImageIcon( 路径 )).getImage();
                // //路径：图片-右键-属性-路径src/
                // g.drawImage(tu,x,y,null);

                // TODO:绘图在此
                Image beiJingTu = (new ImageIcon("俄罗斯图片/2/背景-1.png"))
                        .getImage();
                g.drawImage(beiJingTu, 0, 0, null);

                for (int hang = 0; hang < 20; hang++)
                {
                    for (int lie = 0; lie < 10; lie++)
                    {
                        if (luoKuai[hang][lie] != 0)
                        {
                            Image qiuTu = (new ImageIcon("俄罗斯图片/2/3.png"))
                                    .getImage();
                            g.drawImage(qiuTu, lie * 30 + 30, hang * 30 + 5,
                                    null);
                        }
                    }
                }

                for (int hang = 0; hang < 20; hang++)
                {
                    for (int lie = 0; lie < 10; lie++)
                    {
                        if (luoKuai[hang][lie] == 30)
                        {
                            Image qiuTu = (new ImageIcon("俄罗斯图片/2/11.png"))
                                    .getImage();
                            g.drawImage(qiuTu, lie * 30 + 30, hang * 30 + 5,
                                    null);
                        }
                    }
                }

                for (int hang = 0; hang < 20; hang++)
                {
                    for (int lie = 0; lie < 10; lie++)
                    {
                        if (shiKuai[hang][lie] != 0)
                        {
                            Image qiuTu = (new ImageIcon("俄罗斯图片/2/7.png"))
                                    .getImage();
                            g.drawImage(qiuTu, lie * 30 + 30, hang * 30 + 5,
                                    null);
                        }
                    }
                }
                g.setColor(Color.WHITE);
                g.setFont(new Font("黑体", Font.BOLD, 20));

                g.drawString(fenShu + "分", 380, 240);

            }
        }
    }

    class 定时器 implements Runnable// 实现Runnable接口
    {
        Thread xc = null;
        long   jianGe;

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
                        // TODO:定时处理
                        // 下落 "途中"
                        // TODO C1下不挡住
                        boolean xiaBuDang = true;
                        for (int hang = 18; hang >= 0; hang--)
                        {
                            for (int lie = 0; lie < 10; lie++)
                            {
                                if (luoKuai[hang][lie] != 0
                                        && shiKuai[hang + 1][lie] != 0)
                                {
                                    xiaBuDang = false;
                                }
                            }
                        }

                        // TODO C2能下出界
                        boolean nengXia = true;// 假设能
                        for (int lie = 0; lie < 10; lie++)
                        {
                            if (luoKuai[19][lie] != 0)
                            {
                                nengXia = false;// 推翻假设
                            }
                        }

                        if (nengXia == true && xiaBuDang == true)
                        {
                            // TODO C3下落
                            for (int hang = 18; hang >= 0; hang--)
                            {
                                for (int lie = 0; lie < 10; lie++)
                                {
                                    luoKuai[hang + 1][lie] = luoKuai[hang][lie];
                                    luoKuai[hang][lie] = 0;
                                }
                            }
                        }
                        else
                        {
                            // TODO C4合并
                            for (int hang = 0; hang < 20; hang++)
                            {
                                for (int lie = 0; lie < 10; lie++)
                                {
                                    if (luoKuai[hang][lie] != 0)
                                    {
                                        shiKuai[hang][lie] = luoKuai[hang][lie];
                                        luoKuai[hang][lie] = 0;//
                                    }
                                }
                            }
                            // TODO C5消行
                            int xiaoHangShu = 0;
                            for (int kh = 0; kh < 20; kh++)
                            {
                                boolean khnengXiao = true; // 能消
                                for (int kl = 0; kl < 10; kl++)
                                {
                                    if (shiKuai[kh][kl] == 0)
                                    {
                                        khnengXiao = false; // 不能消
                                    }
                                }
                                if (khnengXiao == true)
                                {
                                    xiaoHangShu++;
                                    for (int xh = kh - 1; xh >= 0; xh--)
                                    {
                                        for (int xl = 0; xl < 10; xl++)
                                        {
                                            shiKuai[xh + 1][xl] = shiKuai[xh][xl];
                                            shiKuai[xh][xl] = 0;
                                        }
                                    }
                                }
                            }

                            // TODO C6得分
                            switch (xiaoHangShu)
                            {
                                case 0:
                                    fenShu += 0;
                                    break;
                                case 1:
                                    fenShu += 10;
                                    break;
                                case 2:
                                    fenShu += 30;
                                    break;
                                case 3:
                                    fenShu += 70;
                                    break;
                                case 4:
                                    fenShu += 100;
                                    break;
                                default:
                                    fenShu += 1000;
                                    break;

                            }
                            // TODO C7升级

                            // TODO C8能结束
                            boolean nengJieShu = false;
                            for (int l = 0; l < 10; l++)
                            {
                                if (shiKuai[0][l] != 0)
                                {
                                    nengJieShu = true;
                                }
                            }

                            if (nengJieShu == true)
                            {
                                System.exit(0);
                            }
                            // TODO C9出新块
                            int zhongLei = (int) (Math.random() * 7);
                            dqZhongLei = zhongLei;
                            switch (zhongLei)
                            {
                                case 0:// 方块
                                    luoKuai[0][4] = 1;
                                    luoKuai[0][5] = 1;
                                    luoKuai[1][4] = 1;
                                    luoKuai[1][5] = 1;
                                    break;
                                case 1:// 竖条
                                    luoKuai[0][4] = 1;
                                    luoKuai[1][4] = 1;
                                    luoKuai[2][4] = 1;
                                    luoKuai[3][4] = 30;
                                    break;
                                case 2:
                                    luoKuai[0][4] = 1;
                                    luoKuai[1][3] = 1;
                                    luoKuai[1][4] = 30;
                                    luoKuai[1][5] = 1;
                                    break;
                                case 3:
                                    luoKuai[0][3] = 1;
                                    luoKuai[1][3] = 1;
                                    luoKuai[1][4] = 30;
                                    luoKuai[1][5] = 1;

                                    break;
                                case 4:
                                    luoKuai[0][5] = 1;
                                    luoKuai[1][3] = 1;
                                    luoKuai[1][4] = 30;
                                    luoKuai[1][5] = 1;

                                    break;
                                case 5:
                                    luoKuai[0][3] = 1;
                                    luoKuai[1][3] = 30;
                                    luoKuai[1][4] = 1;
                                    luoKuai[2][4] = 1;
                                    break;
                                case 6:
                                    luoKuai[0][5] = 1;
                                    luoKuai[1][4] = 30;
                                    luoKuai[1][5] = 1;
                                    luoKuai[2][4] = 1;
                                    break;
                                default:
                                    break;
                            }
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

    // main主方法 ，主类的“入口方法”
    public static void main(String[] args)
    {
        new ELuoSiFangKuaiGame();
    }
}
