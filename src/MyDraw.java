import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class MyDraw extends Canvas {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6137963199132816179L;
	static final int SCREEN_LENGTH = 700;
	static final int SCREEN_HEIGHT = 700;
//	static final int SCREEN_LENGTH = 630;
//	static final int SCREEN_HEIGHT = 730;
	
	static final Font MY_FONT = new Font ("Courier New", 0, 12);
	static final Color MY_RED = new Color(204, 51, 0);
	static final Color MY_BLUE = new Color(34, 67, 89);
	static final Color MY_GREEN = new Color(0, 153, 51);
	
    public static void main(String[] args) {
        JFrame frame = new JFrame("Delaunay");
        Canvas canvas = new MyDraw();
        canvas.setSize(SCREEN_LENGTH, SCREEN_HEIGHT);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        canvas.setBackground(new Color(220, 220, 220));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
    	drawMain(g);
//    	drawIncremental(g);
//    	drawTest01(g);
    }
    
    
    /**
     * @param g
     */
    public void drawMain(Graphics g) {
    	g.setColor(MY_BLUE);
    	drawTriangles(g, Main.triangles);
    	g.setColor(MY_RED);
    	drawVertices(g, Main.vertices);
    	g.setColor(MY_GREEN);
    	g.setFont(MY_FONT);
    	drawNums(g, Main.vertices);
	}
    
    
    /**
     * @param g
     */
    public void drawIncremental(Graphics g) {
    	g.setColor(MY_BLUE);
    	drawTriangles(g, DelaunayIncremental.triangles);
    	g.setColor(MY_RED);
    	drawVertices(g, DelaunayIncremental.vertices);
    	g.setColor(MY_GREEN);
    	g.setFont(MY_FONT);
    	drawNums(g, DelaunayIncremental.vertices);
	}
    
    
    /**
     * @param g
     */
    public void drawTest01(Graphics g) {
//    	g.setColor(MY_BLUE);
    	g.setColor(MY_RED);

//    	drawTriangles(g, Test01.triangleSolution);
    	drawTriangles(g, Test01.triangleIncremental);
    	
    	
//    	g.setColor(MY_RED);
//    	drawVertices(g, Test01.vertices);
//    	g.setColor(MY_GREEN);
//    	g.setFont(MY_FONT);
//    	drawNums(g, Test01.vertices);
//    	g.setColor(MY_RED);
//    	drawTriangles(g, Test01.errors);
    	
//    	drawTriangleText(g, Test01.triangleSolution);
	}
     
    
    /**
     * @param g
     * @param toDraw
     */
    public void drawVertices(Graphics g, ArrayList <MyVector> toDraw) {
    	for (int i = 0; i < toDraw.size(); i++) {
    		MyVector vertex = toDraw.get(i);
    		int radius = 4;
    		g.fillRect((int)vertex.getCoordX() - radius / 2,
    				SCREEN_HEIGHT - (int)vertex.getCoordY() - radius / 2,
    				radius, radius);
    	}
	}
    
    
    /**
     * @param g
     * @param toDraw
     */
    public void drawNums(Graphics g, ArrayList <MyVector> toDraw) {
    	for (int i = 0; i < toDraw.size(); i++) {
    		MyVector vertex = toDraw.get(i);
    		g.drawString(Integer.toString(vertex.getVectorId()), 
    				(int)vertex.getCoordX() + 3,
    				SCREEN_HEIGHT - (int)vertex.getCoordY() - 3);
    	}

    }
    
    
    /**
     * @param g
     * @param toDraw
     */
    public void drawTriangles(Graphics g, ArrayList<MyTriangle> toDraw) {
    	Graphics2D g2 = (Graphics2D) g;
    	for (int i = 0; i < toDraw.size(); i++) {
    		MyTriangle triangle = toDraw.get(i);
    		for (int j = 0; j < triangle.getVerticesArray().length; j++) {
    			int k = (j < 2)? j + 1 : 0;
    			Line2D l = new Line2D.Double(
    					triangle.getVerticesArray()[j].getCoordX(),
    					SCREEN_HEIGHT - triangle.getVerticesArray()[j].getCoordY(), 
    					triangle.getVerticesArray()[k].getCoordX(),
    					SCREEN_HEIGHT - triangle.getVerticesArray()[k].getCoordY());
    			g2.draw(l);
    		}
    	}
    }
    
    
    /**
     * @param g
     * @param toDraw
     */
    public void drawTriangleText(Graphics g, ArrayList <MyTriangle> toDraw) {
    	for (int i = 0; i < toDraw.size(); i++) {
    		int textCoordX = (int) ((toDraw.get(i).getVertex1().getCoordX() +
    				toDraw.get(i).getVertex2().getCoordX() + 
    				toDraw.get(i).getVertex3().getCoordX()) / 3);
    		int textCoordY = (int) ((toDraw.get(i).getVertex1().getCoordY() +
    				toDraw.get(i).getVertex2().getCoordY() + 
    				toDraw.get(i).getVertex3().getCoordY()) / 3);

    		g.drawString(Integer.toString(i), textCoordX,
    				SCREEN_HEIGHT - textCoordY);
    	}
    }
    
        
    /**
     * integer coords graphics method
     * @param g
     * @param toDraw
     */
    public void drawTriangles2(Graphics g, ArrayList<MyTriangle> toDraw) {
    	for (int i = 0; i < toDraw.size(); i++) {
    		MyTriangle triangle = toDraw.get(i);
    		for (int j = 0; j < triangle.getVerticesArray().length; j++) {
    			int k = (j < 2)? j + 1 : 0;
    		 	g.drawLine(
    					(int)triangle.getVerticesArray()[j].getCoordX(),
    					SCREEN_HEIGHT - (int)triangle.getVerticesArray()[j].getCoordY(), 
    					(int)triangle.getVerticesArray()[k].getCoordX(),
    					SCREEN_HEIGHT - (int)triangle.getVerticesArray()[k].getCoordY());
    		}
    	}
    }
    
    
}