package util;

import View.Hall;
import bean.MyJPanel;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class MSUtil {
    private static Socket socket = null;
    public static ObjectInputStream ois = null;
    public static ObjectOutputStream oos = null;
    private static MyJPanel myJPanel;
    private static JFrame jFrame;
    public static Hall hall;

    public static void connection() {
        try {
            socket = new Socket("10.61.18.31",7777);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initData(MyJPanel _myJPanel,JFrame _jFrame){
        myJPanel = _myJPanel;
        jFrame = _jFrame;
    }

//    public static void initRival(ObjectOutputStream _oos2){
//        oos2 = _oos2;
//    }
//
//    public static void send(String type){
//    }

    public static void filterMsgCenter() {
            new Thread(new Runnable() {
            @Override
            public void run() {
                 while (true){
                    try {
                            String flag = ois.readUTF();
                            if (flag.equals("connect")){
                                String rival = (String) ois.readObject();
                                myJPanel.getL4().setText(rival);
                            }else if (flag.equals("down")){
                                int[] chess = (int[]) ois.readObject();
                                myJPanel.makeChess(chess[0],chess[1]);
                                myJPanel.flag = true;
                            }else if (flag.equals("ready")){
                                myJPanel.l6.setText("已准备！");
                                if (myJPanel.l5.getText().equals("已准备！")){
                                    JOptionPane.showMessageDialog(jFrame,"游戏开始,房主先手！");
                                    myJPanel.l5.setText("");
                                    myJPanel.l6.setText("");
                                    if (myJPanel.maker){
                                        myJPanel.flag = true;
                                    }else {
                                        myJPanel.flag = false;
                                    }
                                }
                            }else if (flag.equals("victory")){
                                myJPanel.game.Clean();
                                myJPanel.room.repaint();
                                myJPanel.game.Rush();
                                JOptionPane.showMessageDialog(jFrame,"对方认输，您已经获胜！");
                                myJPanel.l5.setText("未准备");
                                myJPanel.l6.setText("未准备");
                            }else if (flag.equals("regret")){
                                int[] chess = (int[]) ois.readObject();
                                int result = JOptionPane.showConfirmDialog(jFrame,"对方请求悔棋","悔棋",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                                if (result==0){
                                    MSUtil.oos.writeUTF("ok");
                                    MSUtil.oos.flush();
                                    MSUtil.oos.writeObject(myJPanel.l4.getText());
                                    MSUtil.oos.flush();
                                    myJPanel.Regret(chess[0],chess[1]);
                                    myJPanel.flag = false;
                                }else {
                                    MSUtil.oos.writeUTF("no");
                                    MSUtil.oos.flush();
                                    MSUtil.oos.writeObject(myJPanel.l4.getText());
                                    MSUtil.oos.flush();
                                }
                            }else if (flag.equals("ok")){
                                JOptionPane.showMessageDialog(jFrame,"对方同意悔棋！");
                                myJPanel.Regret(myJPanel.chessX,myJPanel.chessY);
                                myJPanel.flag = true;
                            }else if (flag.equals("no")){
                                JOptionPane.showMessageDialog(jFrame,"对方拒绝悔棋！");
                            }else if (flag.equals("out")){
                                if (myJPanel.maker){
                                    myJPanel.game.Clean();
                                    myJPanel.room.repaint();
                                    myJPanel.game.Rush();
                                    myJPanel.l5.setText("");
                                    myJPanel.l4.setText("");
                                    myJPanel.l6.setText("");
                                    JOptionPane.showMessageDialog(jFrame,"对方退出了房间");
                                }else{
                                    JOptionPane.showMessageDialog(jFrame,"本房间已经解散");
                                    myJPanel.game.Clean();
                                    myJPanel.room.repaint();
                                    myJPanel.game.Rush();
                                    MSUtil.out();
                                    MSUtil.hall.setVisible(true);
                                    jFrame.dispose();
                                }
                            }else if (flag.equals("off")){
                                break;
                            }
                    } catch (Exception er) {
                        // 与服务器断开连接进行重连操作
                        er.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void out(){
        try {
            oos.writeUTF("off");
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
