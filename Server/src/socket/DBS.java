package socket;

import bean.User;
import util.DBUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.*;

public class DBS extends DBUtil {
    private final int Port = 7777;
    private ServerSocket serverSocket;
    private static final String SQL_LOG = " SELECT * FROM CHESSUSER WHERE USERNAME=? AND PASSWORD=? ";
    private static final String SQL_CREATE = " INSERT INTO CHESS VALUES (?,1,?,null,null,null) ";
    private static final String SQL_FIND = " SELECT * FROM CHESS WHERE GID=? ";
    private static final String SQL_FIND_NAME = "SELECT USER1NAME FROM CHESS WHERE GID=?";
    private Random r = new Random();
    static Map<String, ObjectOutputStream> sessionMap = new HashMap<String, ObjectOutputStream>();

    public static void main(String[] args) throws IOException {
        DBS server = new DBS();
        server.start();
    }

    public void start() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    serverSocket = new ServerSocket(Port);
                    while (true){
                        Socket socket = serverSocket.accept();
                        System.out.println("用户连接");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                request(socket);
                            }
                        }).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void request(Socket socket)  {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while (true) {
                String flag = ois.readUTF();
                if (flag.equals("log")){
                    User user = (User) ois.readObject();
                    sessionMap.put(user.getUsername(),oos);
                    Boolean result = false;
                    List list =new ArrayList();
                    list.add(user.getUsername());
                    list.add(user.getPassword());
                    resultSet = query(SQL_LOG, list);
                    try {
                        if (resultSet.next()){
                            result = true;
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }finally {
                        closeAll();
                    }
                    oos.writeObject(result);
                }else if (flag.equals("create")){
                    String username = (String) ois.readObject();
                    int num = 0;
                    while (true) {
                        num = r.nextInt(899999)+100000;
                        List list = new ArrayList();
                        list.add(num);
                        resultSet = query(SQL_FIND,list);
                        try {
                            if (resultSet.next()){
                            }else{
                                break;
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }finally {
                            closeAll();
                        }
                    }
                    List list = new ArrayList();
                    list.add(num);
                    list.add(username);
                    int i = update(SQL_CREATE,list);
                    if (i>0){
                        System.out.println("房间"+num+"已创建");
                        oos.writeObject(num);
                        oos.flush();
                    }
                    closeAll();
                }else if (flag.equals("join")){
                    //收到房间号
                    int num = (int) ois.readObject();
                    //收到姓名
                    String name = (String) ois.readObject();
                    sessionMap.put(name,oos);
                    List list = new ArrayList();
                    list.add(num);
                    resultSet = query(SQL_FIND_NAME,list);
                    try {
                        if (resultSet.next()){
                            String username = resultSet.getString("USER1NAME");
                            oos.writeObject(true);
                            oos.flush();
                            oos.writeObject(username);
                            oos.flush();
                            ObjectOutputStream oos1 = sessionMap.get(username);
                            //给房主发送信息匹配到对手
                            oos1.writeUTF("connect");
                            oos1.flush();
                            oos1.writeObject(name);
                            oos1.flush();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }else if (flag.equals("down")){
                    String rival = (String) ois.readObject();
                    int[] chess = (int[]) ois.readObject();
                    ObjectOutputStream oos1 = sessionMap.get(rival);
                    oos1.writeUTF("down");
                    oos1.flush();
                    oos1.writeObject(chess);
                    oos1.flush();
                }else if (flag.equals("defeat")){
                    String rival = (String) ois.readObject();
                    ObjectOutputStream oos1 = sessionMap.get(rival);
                    oos1.writeUTF("victory");
                    oos1.flush();
                }else if (flag.equals("ok")){
                    String rival = (String) ois.readObject();
                    ObjectOutputStream oos1 = sessionMap.get(rival);
                    oos1.writeUTF("ok");
                    oos1.flush();
                }else if (flag.equals("no")){
                    String rival = (String) ois.readObject();
                    ObjectOutputStream oos1 = sessionMap.get(rival);
                    oos1.writeUTF("no");
                    oos1.flush();
                }else if (flag.equals("ready")){
                    String rival = (String) ois.readObject();
                    ObjectOutputStream oos1 = sessionMap.get(rival);
                    oos1.writeUTF("ready");
                    oos1.flush();
                }else if (flag.equals("regret")){
                    String rival = (String) ois.readObject();
                    int[] chess = (int[]) ois.readObject();
                    ObjectOutputStream oos1 = sessionMap.get(rival);
                    oos1.writeUTF("regret");
                    oos1.flush();
                    oos1.writeObject(chess);
                }else if (flag.equals("out")){
                    String rival = (String) ois.readObject();
                    ObjectOutputStream oos1 = sessionMap.get(rival);
                    oos1.writeUTF("out");
                    oos1.flush();
                }else if (flag.equals("off")){
                    oos.writeUTF("off");
                    oos.flush();
                }else if (flag.equals("exit")){
                    String name = (String) ois.readObject();
                    sessionMap.remove(name);
                }
                else{
                    System.out.println("您的请求服务器无法做出响应！");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
