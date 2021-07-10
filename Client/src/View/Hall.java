package View;

import util.MSUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Random;

public class Hall extends JFrame{
    ObjectInputStream ois;
    ObjectOutputStream oos;
    String username1;
    Random r = new Random();


    public Hall(String username1)
    {
        super();
        this.username1 = username1;
        initFrame();
        ois = MSUtil.ois;
        oos = MSUtil.oos;
    }

    public void initFrame()
    {
        setTitle("五子棋");
        setSize(1000,1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        HallJPanel myJPanel = new HallJPanel(this);
        this.getContentPane().add(myJPanel);
    }

    class HallJPanel extends JPanel implements MouseListener {
        Hall hallForm;
        Font font1 = new Font(Font.SERIF, Font.PLAIN, 20);
        Font font2 = new Font(Font.SERIF, Font.PLAIN, 15);
        //JButton create = new JButton("创建房间");
        JLabel create = new JLabel("创建房间");
        JLabel co = new JLabel("欢迎您来到本对战平台，"+username1);
        JLabel room = new JLabel("房间名");
        JTextField roomName = new JTextField();
        //JButton join = new JButton("进入房间");
        JLabel join = new JLabel("进入房间");
        JLabel out = new JLabel("退出");
        Image image;
        int x;
        int y;

        public HallJPanel(Hall hallForm){
            super();
            setLayout(null);
            this.hallForm =hallForm;
            this.addMouseListener(this);
            co.setFont(font1);
            co.setForeground(Color.orange);
            co.setBounds(100,100,500,200);
            create.setBounds(100,400,100,50);
            create.setFont(font1);
            create.setForeground(Color.orange);
            room.setBounds(100,600,100,50);
            room.setForeground(Color.yellow);
            room.setFont(font2);
            roomName.setBounds(200,610,200,25);
            join.setBounds(100,700,100,50);
            join.setFont(font1);
            join.setForeground(Color.orange);
            out.setFont(font1);
            out.setBounds(600,700,100,50);
            out.setForeground(Color.orange);
            add(co);
            add(create);
            add(room);
            add(roomName);
            add(join);
            add(out);
            InputStream is = null;
            try {
                is = new BufferedInputStream(
                        new FileInputStream("image.jpg"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                image = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            }

//            create.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    int num = 0;
//                    try {
//                        oos.writeUTF("create");
//                        oos.flush();
//                        oos.writeObject(username1);
//                        oos.flush();
//                    } catch (IOException ioException) {
//                        ioException.printStackTrace();
//                    }
//                    try {
//                        num = (int) ois.readObject();
//                        if (num!=0){
//                            JOptionPane.showMessageDialog(hallForm,"创建成功！");
//                            hallForm.dispose();
//                            Form1 f = new Form1(num,username1);
//                            f.setVisible(true);
//                        }
//                    } catch (IOException ioException) {
//                        ioException.printStackTrace();
//                    } catch (ClassNotFoundException classNotFoundException) {
//                        classNotFoundException.printStackTrace();
//                    }
//                }
//            });
//            join.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    try {
//                        oos.writeUTF("join");
//                        oos.flush();
//                        //发送房间号
//                        int num = Integer.parseInt(roomName.getText());
//                        oos.writeObject(num);
//                        oos.flush();
//                        //发送名字
//                        oos.writeObject(username1);
//                        oos.flush();
//                        Boolean b = (Boolean) ois.readObject();
//                        if (b){
//                            JOptionPane.showMessageDialog(hallForm,"加入成功！");
//                            String username2 = (String) ois.readObject();
//                            hallForm.dispose();
//                            Form1 f = new Form1(num,username1,username2);
//                            f.setVisible(true);
//                        }else {
//                            JOptionPane.showMessageDialog(hallForm,"加入失败！房间不存在或者已满");
//                        }
//                    } catch (IOException | ClassNotFoundException ioException) {
//                        ioException.printStackTrace();
//                    }
//                }
//            });
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
            if (x>=100 && x<=150  && y>=400 && y<=415) {
                try {
                    oos.writeUTF("create");
                    oos.flush();
                    oos.writeObject(username1);
                    oos.flush();
                    Integer num = (Integer) ois.readObject();
                    if (num!=0){
                        JOptionPane.showMessageDialog(hallForm,"创建成功！");
                        hallForm.dispose();
                        Room f = new Room(num,username1);
                        f.setVisible(true);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }else if (x>=100 && x<=150  && y>=715 && y<=730){
                try {
                    oos.writeUTF("join");
                    oos.flush();
                    //发送房间号
                    int num = Integer.parseInt(roomName.getText());
                    oos.writeObject(num);
                    oos.flush();
                    //发送名字
                    oos.writeObject(username1);
                    oos.flush();
                    Boolean b = (Boolean) ois.readObject();
                    if (b){
                        JOptionPane.showMessageDialog(hallForm,"加入成功！");
                        String username2 = (String) ois.readObject();
                        hallForm.dispose();
                        Room f = new Room(num,username1,username2);
                        f.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(hallForm,"加入失败！房间不存在或者已满");
                    }
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }else if (x>=600 && x<=750 && y>=715 && y<=730){
                    try {
                        oos.writeUTF("exit");
                        oos.flush();
                        oos.writeObject(username1);
                        oos.flush();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                JOptionPane.showMessageDialog(hallForm,"退出程序！");
                    dispose();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getSize().width,
                    getSize().height, this);// 图片会自动缩放
//    g.drawImage(icon.getImage(), x, y,this);//图片不会自动缩放
        }
    }
}
