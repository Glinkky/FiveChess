package bean;

import bean.Chess;

import java.awt.*;
import java.io.Serializable;

public class Game implements Serializable {
    Chess chess = new Chess();
    Map map = new Map();
    Boolean Judge = false;//颜色变化
    int Write = 0;
    int Black = 0;
    public void Paint(Graphics g)
    {
        map.PaintM(g);
        for(int i =0;i<=15;i++)
        {
            for(int j =0;j<=15;j++)
            {
                if(chess.chess[i][j] == 1)
                {
                    chess.PaintB(g,new Point(100+i*50,100+j*50));
                }
                else if(chess.chess[i][j] == 2)
                {
                    chess.PaintW(g,new Point(100+i*50,100+j*50));
                }
            }
        }
    }

    public int[] Judge(int i,int j)
    {
        int[] B = new int[2];
        if(chess.chess[i][j] ==0)
        {
            if (Judge == true)
            {
                chess.chess[i][j] = 1;
                Black++;
                Judge = false;
            }
            else if(Judge == false)
            {
                chess.chess[i][j] = 2;
                Write++;
                Judge = true;
            }
        }
        B[0] = Write;
        B[1] = Black;
        return B;
    }

    public int P()
    {
        for(int i =0;i <= 15;i++)
        {
            for(int j =0;j <= 11;j++)
            {
                if(chess.chess[i][j]!=0 && chess.chess[i][j] == chess.chess[i][j+1]&&chess.chess[i][j] == chess.chess[i][j+2]&&chess.chess[i][j] == chess.chess[i][j+3]&&chess.chess[i][j] == chess.chess[i][j+4])
                {
                    if(chess.chess[i][j] == 1)
                        return 1;
                    else if(chess.chess[i][j] == 2)
                        return 2;
                }
            }
        }
        for(int j =0;j <= 15;j++)
        {
            for(int i =0;i <=11;i++)
            {
                if(chess.chess[i][j]!=0 && chess.chess[i][j] == chess.chess[i+1][j]&&chess.chess[i][j] == chess.chess[i+2][j]&&chess.chess[i][j] == chess.chess[i+3][j]&&chess.chess[i][j] == chess.chess[i+4][j])
                {
                    if(chess.chess[i][j] == 1)
                        return 1;
                    else if(chess.chess[i][j] == 2)
                        return 2;
                }
            }
        }
        for(int i =0;i<=11;i++)
        {
            for(int j=0;j<=11;j++) {
                if (chess.chess[i][j] != 0 && chess.chess[i][j] == chess.chess[i + 1][j + 1] && chess.chess[i][j] == chess.chess[i + 2][j + 2] && chess.chess[i][j] == chess.chess[i + 3][j + 3] && chess.chess[i][j] == chess.chess[i + 4][j + 4]) {
                    if(chess.chess[i][j] == 1)
                        return 1;
                    else if(chess.chess[i][j] == 2)
                        return 2;
                }
            }
        }
        for(int i=0;i<=11;i++)
        {
            for(int j=4;j<=15;j++) {
                if(chess.chess[i][j]!=0&& chess.chess[i][j] ==chess.chess[i+1][j-1] &&chess.chess[i][j] == chess.chess[i+2][j-2]&&chess.chess[i][j] ==chess.chess[i+3][j-3]&&chess.chess[i][j] ==chess.chess[i+4][j-4])
                {
                    if(chess.chess[i][j] == 1)
                        return 1;
                    else if(chess.chess[i][j] == 2)
                        return 2;
                }
            }
        }
        return 0;
    }

    public void Clean()
    {
        for(int i =0;i<=15;i++)
        {
            for(int j =0;j<=15;j++)
            {
                chess.chess[i][j] = 0;
            }
        }
    }

    public int Regret(int x,int y)
    {
        if(chess.chess[x][y] == 1)
        {
            chess.chess[x][y] = 0;
            Judge = true;
            return 1;
        }
        else if(chess.chess[x][y] == 2)
        {
            chess.chess[x][y] = 0;
            Judge = false;
            return 2;
        }
        return 0;
    }

    public Boolean Peace()
    {
        for(int i =0;i <= 15;i++)
        {
            for(int j =0;j <= 15;j++)
            {
                if(chess.chess[i][j] ==0)
                {
                    return false;
                }
            }
        }
        return true;
    }
    public void Rush()
    {
        Write = 0;
        Black = 0;
    }
}
