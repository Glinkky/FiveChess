package bean;

import java.awt.*;

public class Chess {

    public int [][] chess = new int[16][16];
    public Chess(){
        for(int i =0;i<=15;i++)
        {
            for(int j =0;j<=15;j++)
            {
                chess[i][j] = 0;
            }
        }
    }
    public static int side = 40;
    public void PaintB(Graphics g,Point x)
    {
        g.setColor(Color.BLACK);
        g.drawOval(x.x+5, x.y+5, side,side);
        g.fillOval(x.x+5, x.y+5, side,side);
    }

    public void PaintW(Graphics g,Point x)
    {
        g.setColor(Color.WHITE);
        g.drawOval(x.x+5, x.y+5, side,side);
        g.fillOval(x.x+5, x.y+5, side,side);
    }

}
