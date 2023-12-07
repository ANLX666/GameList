package 完整代码;

import javax.swing.*;
import java.awt.*;

public class Pai {
    public int		x;
    public int		y;
    public int		dian;
    public int		se;
    public String	tuLuJing;
    public boolean	zheng;

    public Pai(int dian, int se)
    {
        this.dian = dian;
        this.se = se;
    }

    public void xianShi(Graphics g)
    {
        if (zheng) //如果是正面
        {
            tuLuJing = "扑克图Q版/" + dian + "-" + se + ".png";
        }
        else
        {
            tuLuJing="扑克图Q版/背面.png";
        }
        Image tu = new ImageIcon(tuLuJing).getImage();
        g.drawImage(tu, x, y, null);
    }
}
