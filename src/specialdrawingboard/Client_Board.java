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
public class Client_Board extends Applet implements MouseListener{ 
    private Graphics gBuf; 
    private Image imgBuf; 
    private ArrayList shapeList=new ArrayList(); 
    public static void main(String[] args) { 
        Frame app = new Frame("客户端 "); 
        app.setSize(250, 250); 
        app.setLocationByPlatform(true); 
        Client_Board applet = new Client_Board(); 
        applet.addMouseListener(applet); 
        app.add(applet); 
        app.addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent event) { 
                event.getWindow().dispose(); 
                System.exit(0); 
                
            } 
        }); 
        app.show(); 
        applet.start(); 
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
public void mousePressed(MouseEvent e) { 
    Shape shape; 
    ByteArrayOutputStream dop = new ByteArrayOutputStream(3);
    if (e.getButton()==e.BUTTON1){ 
        shape=new Shape(Shape.CIRCLE,e.getPoint().x,e.getPoint().y); 
        shapeList.add(shape); 
        try {
            dop.write(Shape.CIRCLE);
            dop.write(e.getPoint().x);
            dop.write(e.getPoint().y);
            byte[] date = dop.toByteArray();
            DatagramPacket packet = new DatagramPacket(date, 3);
            byte[] addr = new byte[] {127,0,0,1};
            InetAddress local = InetAddress.getByAddress(addr);
            packet.setAddress(local);
            packet.setPort(7777);
            DatagramSocket ds = new DatagramSocket();
            ds.send(packet);
        } catch (SocketException e2) {
            e2.printStackTrace();
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
            
    }else if (e.getButton()==e.BUTTON3){ 
        shape=new Shape(Shape.SQUARE,e.getPoint().x,e.getPoint().y); 
        shapeList.add(shape);
        try {
            dop.write(Shape.SQUARE);
            dop.write(e.getPoint().x);
            dop.write(e.getPoint().y);
            byte[] date = dop.toByteArray();
            DatagramPacket packet = new DatagramPacket(date, 3);
            byte[] addr = new byte[] {127,0,0,1};
            InetAddress local = InetAddress.getByAddress(addr);
            packet.setAddress(local);
            packet.setPort(7777);
            DatagramSocket ds = new DatagramSocket();
            ds.send(packet);
        } catch (SocketException e2) {
            e2.printStackTrace();
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    } 
    repaint(); 
} 

public void mouseReleased(MouseEvent e) { 
} 

public void mouseEntered(MouseEvent e) { 
} 

public void mouseExited(MouseEvent e) { 
} 
public void mouseClicked(MouseEvent e) { 

} 
} 
