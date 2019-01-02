/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialdrawingboard;
import java.applet.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 
import java.util.*; 
/**
 *
 * @author 康彬玮
 */
public class Server_Board extends Applet { 
    private Graphics gBuf; 
    private Image imgBuf; 
    private ArrayList shapeList=new ArrayList(); 
    public static void main(String[] args) { 
       
        Frame app = new Frame("服务器"); 
        app.setSize(250, 250); 
        app.setLocationByPlatform(true); 
        Server_Board applet = new Server_Board(); 
       
        app.add(applet); 
        app.addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent event) { 
                event.getWindow().dispose(); 
                System.exit(0); 
            } 
        }); 
        app.show(); 
        applet.start(); 
        try { 
            DatagramPacket packet = new DatagramPacket(new byte[3], 3); 
            DatagramSocket ds = new DatagramSocket(7777); 
            Shape shape; 
            while (true) { 
                ds.receive(packet); 
                byte[] data = packet.getData(); 
                ByteArrayInputStream di = new ByteArrayInputStream(data);
                int c;
                int[] graph=new int [3];
                for(int y = 0 ; y < 1; y++ ) {
                    int z = 0;
                    while(( c= di.read())!= -1) {
                        graph[z]=((int)c);
                        z++;
                    }
                    di.reset();
                }
                shape=new Shape(graph[0],graph[1],graph[2]); 
                applet.shapeList.add(shape); 
                System.out.println("data:"+shape.type); 
                applet.repaint(); 
            } 
        } catch (IOException e) { 
            // TODO Auto-generated catch block 
            e.printStackTrace(); 
        } 
    } 
    public void init() { 
    } 
    public void paint(Graphics g) {  
        int graphWidth = bounds().width; 
        int graphHeight = bounds().height; 
        imgBuf = createImage(graphWidth, graphHeight); 
        gBuf = imgBuf.getGraphics(); 
        gBuf.clearRect(0, 0, graphWidth, graphHeight); 
        Iterator it=shapeList.iterator(); 
        Shape shape; 
        while (it.hasNext()) 
        { 
            shape=(Shape)it.next(); 
            if (shape.type==Shape.CIRCLE) 
                gBuf.drawOval(shape.x-10,shape.y-10,20,20); 
            else if (shape.type==Shape.SQUARE) 
                gBuf.drawRect(shape.x-10,shape.y-10,20,20); 
            } 
            g.drawImage(imgBuf, 0, 0, this); 
    } 
        public void update(Graphics g) { 
            paint(g); 
        } 
} 

