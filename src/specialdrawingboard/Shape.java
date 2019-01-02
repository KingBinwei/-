/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialdrawingboard;

/**
 *
 * @author 康彬玮
 */
public class Shape {
    public final static int CIRCLE=1; 
    public final static int SQUARE=2; 
    public int x; 
    public int y; 
    public int type; 
    public Shape(int type,int x,int y){ 
        this.x=x; 
        this.y=y; 
        this.type=type; 
    } 
}
