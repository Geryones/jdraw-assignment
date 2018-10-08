package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LineTool extends AbstractDrawTool implements DrawTool {


    private Line newLine=null;

    public LineTool(DrawContext context){
        super(context,"line");

    }

    @Override protected Figure newFigure(int x, int y) {
        return new Line(x,y,x,y);
    }


}
