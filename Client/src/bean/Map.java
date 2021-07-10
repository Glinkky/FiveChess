package bean;

import javax.swing.*;
import java.awt.*;

public class Map {

    static int side = 800;
    public void PaintM(Graphics g)
    {
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(100,100,side,side);
        g.fillRect(100,100,side,side);
        for(int i = 100;i<=900;i = i+50)
        {
            g.setColor(Color.darkGray);
            g.drawLine(i,100,i,900);
        }
        for(int i = 100;i<=900;i = i+50)
        {
            g.setColor(Color.darkGray);
            g.drawLine(100,i,900,i);
        }
    }
}
