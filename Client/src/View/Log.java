package View;

import bean.User;
import util.MSUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Log extends JFrame {
    ObjectInputStream ois;
    ObjectOutputStream oos;
    public Log()
    {
        super();
        initFrame();
            MSUtil.connection();
            ois = MSUtil.ois;
            oos = MSUtil.oos;
    }

    public void initFrame()
    {
        setTitle("五子棋");
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MyJPanel myJPanel = new MyJPanel(this);
        setContentPane(myJPanel);
    }

    public static void main(String[] args)
    {
        Log log =new Log();
        log.setVisible(true);
    }

    class MyJPanel extends JPanel implements MouseListener {
        Font font = new Font(Font.SERIF, Font.PLAIN, 24);
        Log logForm;
        JLabel l1 = new JLabel("用户名");
        JLabel l2 = new JLabel("密码");
        JLabel l3 = new JLabel("欢迎使用本游戏平台:作者:338专业团队");
        JTextField username = new JTextField();
        JTextField password = new JPasswordField();
        JButton log = new JButton("登录");

        public MyJPanel(Log logForm){
            super();
            setLayout(null);
            this.logForm =logForm;
            this.addMouseListener(this);
            l1.setBounds(100,110,100,50);
            username.setBounds(200,125,200,25);
            l2.setBounds(100,180,100,50);
            password.setBounds(200,200,200,25);
            log.setBounds(200,300,100,50);
            l3.setBounds(20,50,450,50);
            l3.setFont(font);
            add(l1);
            add(l2);
            add(l3);
            add(username);
            add(password);
            add(log);
            log.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (username.getText().equals("")){
                        JOptionPane.showMessageDialog(logForm,"用户名空！");
                    }else if (password.getText().equals("")){
                        JOptionPane.showMessageDialog(logForm,"密码空！");
                    }else{
                        String name = username.getText();
                        String pass = password.getText();
                        User user = new User(name,pass);
                        try {
                            oos.writeUTF("log");
                            oos.flush();
                            oos.writeObject(user);
                            oos.flush();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try {
                            boolean b = (boolean) ois.readObject();
                            if (b){
                                JOptionPane.showMessageDialog(logForm,"登陆成功！");
                                logForm.dispose();
                                Hall hall = new Hall(name);
                                MSUtil.hall = hall;
                                hall.setVisible(true);
                            }else {
                                JOptionPane.showMessageDialog(logForm,"登陆失败！请检查账号和密码是否匹配");
                            }
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                    }
                }
            });
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

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
    }
}
