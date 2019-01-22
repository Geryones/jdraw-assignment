package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.Figure;

import java.awt.*;

public class SetBoundsCommand implements DrawCommand {

    private final Figure figure;
    private final Point fromOrig, fromCorn, toOrig, toCorn;

    public SetBoundsCommand(Figure figure, Point fromOrig, Point fromCorn, Point toOrig, Point toCorn) {
        this.figure = figure;
        this.fromOrig = fromOrig;
        this.fromCorn = fromCorn;
        this.toOrig = toOrig;
        this.toCorn = toCorn;
    }

    @Override
    public void redo() {
        figure.setBounds(toOrig, toCorn);
    }

    @Override
    public void undo() {
        figure.setBounds(fromOrig, fromCorn);
    }
}
