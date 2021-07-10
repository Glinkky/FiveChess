package bean;

import View.Room;
import util.MSUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class MyJPanel extends JPanel implements MouseListener {
    public static int num;
    public static Room room;
//    public static JButton b1 = new JButton("准备");
//    public static JButton b2 = new JButton("认输");
//    public static JButton b3 = new JButton("悔棋");
//    public static JButton b4 = new JButton("关于本游戏");
//    public static JButton b5 = new JButton("退出");
    public static JLabel b1 = new JLabel("  准备");
    public static JLabel b2 = new JLabel("  认输");
    public static JLabel b3 = new JLabel("  悔棋");
    public static JLabel b4 = new JLabel("关于本游戏");
    public static JLabel b5 = new JLabel("  退出");
    public static Game game = new Game();
    public static JLabel l1 = new JLabel();
    public static JLabel l2 = new JLabel();
    public static JLabel l3 = new JLabel();
    public static JLabel l4 = new JLabel();
    public static JLabel l5 = new JLabel("");
    public static JLabel l6 = new JLabel("");
    public static JLabel l7 = new JLabel(""+num);
    public static JLabel l8 = new JLabel("房间名");
    public static JLabel l9 = new JLabel("我");
    public static JLabel l10 = new JLabel("对手");
    public static Boolean maker;
    public static Boolean flag = false;

    public JLabel getL4() {
        return l4;
    }

    public void setL4(JLabel l4) {
        this.l4 = l4;
    }


    public int x;
    public int y;
    public int chessX;
    public int chessY;
    public MyJPanel(Room room)
    {
        super();
        setLayout(null);
        this.room = room;
        this.addMouseListener(this);
        Font font1 = new Font("微软雅黑",Font.BOLD,20);
        b1.setFont(font1);
        b2.setFont(font1);
        b3.setFont(font1);
        b4.setFont(font1);
        b5.setFont(font1);
        b1.setBounds(0,0,100,50);
        b2.setBounds(200,0,100,50);
        b3.setBounds(400,0,100,50);
        b4.setBounds(600,0,100,50);
        b5.setBounds(800,0,100,50);
        l9.setBounds(0,350,100,50);
        l10.setBounds(900,350,100,50);
        l3.setBounds(0,400,100,50);
        l4.setBounds(900,400,100,50);
        l5.setBounds(0,500,100,50);
        l6.setBounds(900,500,100,50);
        l7.setBounds(0,200,100,50);
        l8.setBounds(0,150,100,50);
//        b1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (l4.getText().equals("")){
//                    JOptionPane.showMessageDialog(form1,"还没有对手进入！");
//                }else {
//                    l5.setText("已准备！");
//                    try {
//                        MSUtil.oos.writeUTF("ready");
//                        MSUtil.oos.flush();
//                        MSUtil.oos.writeObject(l4.getText());
//                        MSUtil.oos.flush();
//                    } catch (IOException ioException) {
//                        ioException.printStackTrace();
//                    }
//                    if (l6.getText().equals("已准备！")){
//                        JOptionPane.showMessageDialog(form1,"游戏开始,房主先手！");
//                        l5.setText("");
//                        l6.setText("");
//                        if (maker){
//                            flag = true;
//                        }else {
//                            flag = false;
//                        }
//                    }
//                }
//            }
//        });
//        b2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int result = JOptionPane.showConfirmDialog(form1,"您确定要认输吗？","认输",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
//                if (result==0){
//                    try {
//                        MSUtil.oos.writeUTF("defeat");
//                        MSUtil.oos.flush();
//                        MSUtil.oos.writeObject(l4.getText());
//                        MSUtil.oos.flush();
//                    } catch (IOException ioException) {
//                        ioException.printStackTrace();
//                    }
//                    game.Clean();
//                    form1.repaint();
//                    game.Rush();
//                    JOptionPane.showMessageDialog(form1,"您已经认输！");
//                    l5.setText("未准备");
//                    l6.setText("未准备");
//                }
//            }
//        });
//        b3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (flag==false){
//                    JOptionPane.showMessageDialog(form1,"等待对方同意悔棋");
//                    try {
//                        MSUtil.oos.writeUTF("regret");
//                        MSUtil.oos.flush();
//                        MSUtil.oos.writeObject(l4.getText());
//                        MSUtil.oos.flush();
//                        int[] chess = new int[]{x,y};
//                        MSUtil.oos.writeObject(chess);
//                        MSUtil.oos.flush();
//                    } catch (IOException ioException) {
//                        ioException.printStackTrace();
//                    }
//                }else {
//                    JOptionPane.showMessageDialog(form1,"现在是您的回合，不能悔棋");
//                }
//            }
//        });
//        b4.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(form1,"本游戏为五子棋游戏，黑方和白方依次落子，若有一方凑齐5个棋子连在一起，则其获胜");
//            }
//        });
//        b5.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int result = JOptionPane.showConfirmDialog(form1,"您确定要退出吗？","退出",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
//                if (result==0){
//                    if (!l4.getText().equals("")){
//                        try {
//                            MSUtil.oos.writeUTF("out");
//                            MSUtil.oos.flush();
//                            MSUtil.oos.writeObject(l4.getText());
//                            MSUtil.oos.flush();
//                        } catch (IOException ioException) {
//                            ioException.printStackTrace();
//                        }
//                    }
//                    open = false;
//                    MSUtil.out();
//                    Hall hall1 = new Hall(form1.username1);
//                    form1.dispose();
//                    hall1.setVisible(true);
//                }
//            }
//        });
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(l8);
        add(l9);
        add(l10);
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        game.Paint(g);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        x = e.getX();
        y = e.getY();
        if (x>=100 && x<=900  && y>=100 && y<=900) {
            chessX = (x - 100) / 50;
            chessY = (y - 100) / 50;
            if (flag){
                makeChess(chessX,chessY);
                int[] chess = new int[]{chessX,chessY};
                try {
                    MSUtil.oos.writeUTF("down");
                    MSUtil.oos.flush();
                    MSUtil.oos.writeObject(l4.getText());
                    MSUtil.oos.flush();
                    MSUtil.oos.writeObject(chess);
                    MSUtil.oos.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                flag = false;
            }
//            b1.setBounds(0,0,100,50);
//            b2.setBounds(200,0,100,50);
//            b3.setBounds(400,0,100,50);
//            b4.setBounds(600,0,100,50);
//            b5.setBounds(800,0,100,50);
        }else if (x>=0 && x<=100 && y>=0 && y<=50){
            if (l4.getText().equals("")){
                    JOptionPane.showMessageDialog(room,"还没有对手进入！");
                }else {
                    l5.setText("已准备！");
                    try {
                        MSUtil.oos.writeUTF("ready");
                        MSUtil.oos.flush();
                        MSUtil.oos.writeObject(l4.getText());
                        MSUtil.oos.flush();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    if (l6.getText().equals("已准备！")){
                        JOptionPane.showMessageDialog(room,"游戏开始,房主先手！");
                        l5.setText("");
                        l6.setText("");
                        if (maker){
                            flag = true;
                        }else {
                            flag = false;
                        }
                    }
                }
        }else if (x>=200 && x<=300 && y>=0 && y<=50){
            int result = JOptionPane.showConfirmDialog(room,"您确定要认输吗？","认输",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (result==0){
                    try {
                        MSUtil.oos.writeUTF("defeat");
                        MSUtil.oos.flush();
                        MSUtil.oos.writeObject(l4.getText());
                        MSUtil.oos.flush();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    game.Clean();
                    room.repaint();
                    game.Rush();
                    JOptionPane.showMessageDialog(room,"您已经认输！");
                    l5.setText("未准备");
                    l6.setText("未准备");
                }
        }else if (x>=400 && x<=500 && y>=0 && y<=50){
            if (flag==false){
                    JOptionPane.showMessageDialog(room,"等待对方同意悔棋");
                    try {
                        MSUtil.oos.writeUTF("regret");
                        MSUtil.oos.flush();
                        MSUtil.oos.writeObject(l4.getText());
                        MSUtil.oos.flush();
                        int[] chess = new int[]{chessX,chessY};
                        MSUtil.oos.writeObject(chess);
                        MSUtil.oos.flush();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(room,"现在是您的回合，不能悔棋");
                }
        }else if (x>=600 && x<=700 && y>=0 && y<=50){
                JOptionPane.showMessageDialog(room,"本游戏为五子棋游戏，黑方和白方依次落子，若有一方凑齐5个棋子连在一起，则其获胜");
        }else if (x>=800 && x<=900 && y>=0 && y<=50){
            int result = JOptionPane.showConfirmDialog(room,"您确定要退出吗？","退出",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (result==0){
                    if (!l4.getText().equals("")){
                        try {
                            MSUtil.oos.writeUTF("out");
                            MSUtil.oos.flush();
                            MSUtil.oos.writeObject(l4.getText());
                            MSUtil.oos.flush();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    game.Clean();
                    game.Rush();
                    MSUtil.out();
                    MSUtil.hall.setVisible(true);
                    room.dispose();
                }
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void makeChess(int x,int y){
        int[] B = game.Judge(x, y);
        this.repaint();
        int b = game.P();
        if (b == 1)
        {
            JOptionPane.showMessageDialog(this,"游戏结束,黑棋获胜");
            game.Clean();
            game.Rush();
            flag = false;
            l5.setText("未准备");
            l6.setText("未准备");
        }
        else if (b == 2)
        {
            JOptionPane.showMessageDialog(this,"游戏结束,白棋获胜");
            game.Clean();
            game.Rush();
            flag = false;
            l5.setText("未准备");
            l6.setText("未准备");
        }
        Boolean Pe = game.Peace();
        if (Pe == true)
        {
            JOptionPane.showMessageDialog(this,"游戏结束,双方和棋");
            game.Clean();
            game.Rush();
            flag = false;
            l5.setText("未准备");
            l6.setText("未准备");
        }
        this.repaint();
    }

    public void Regret(int x,int y){
        int answer = game.Regret(x,y);
        room.repaint();
        if(answer ==1)
        {
            JOptionPane.showMessageDialog(room,"黑方悔棋");
        }
        else if(answer ==2)
        {
            JOptionPane.showMessageDialog(room,"白方悔棋");
        }
        else if(answer ==0)
        {
            JOptionPane.showMessageDialog(room,"棋局还未开始");
        }
    }
}
