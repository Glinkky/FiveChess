package View;

import bean.MyJPanel;
import util.MSUtil;

import javax.swing.*;


public class Room extends JFrame {
    private String username1="";
    private String username2="";
    private int num;
    private Boolean maker;

    public Room(int num, String username1)
    {
        super();
        this.num =num;
        this.username1 =username1;
        maker = true;
        initFrame();
    }

    public Room(int num, String username1, String username2)
    {
        super();
        this.num =num;
        this.username1 =username1;
        this.username2 =username2;
        maker = false;
        initFrame();
    }



    public void initFrame()
    {
        setTitle("五子棋");
        setSize(1000,1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MyJPanel myJPanel = new MyJPanel(this);
        myJPanel.l7.setText(""+num);
        myJPanel.l3.setText(username1);
        myJPanel.l4.setText(username2);
        myJPanel.maker = maker;
        setContentPane(myJPanel);
        MSUtil.initData(myJPanel,this);
        MSUtil.filterMsgCenter();
    }


}

