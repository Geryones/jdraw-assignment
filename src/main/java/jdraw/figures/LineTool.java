package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import java.awt.*;
import java.awt.event.MouseEvent;

public class LineTool extends AbstractDrawTool implements DrawTool {


    private Line newLine=null;

    public LineTool(DrawContext context){
        super(context,"line");

    }

    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if(newLine!=null){
            throw new IllegalStateException();
        }
        anchor = new Point(x,y);
        newLine = new Line(x,y,x,y);
        view.getModel().addFigure(newLine);
    }

    @Override
    public void mouseDrag(int x, int y, MouseEvent e) {
        newLine.setBounds(anchor,new Point(x,y));
        java.awt.Rectangle r = newLine.getBounds();
        this.context.showStatusText("w: " + r.width + ", h: " + r.height);
    }

    @Override
    public void mouseUp(int x, int y, MouseEvent e) {
        newLine=null;
        anchor=null;
        this.context.showStatusText("Line Mode");
    }


}
