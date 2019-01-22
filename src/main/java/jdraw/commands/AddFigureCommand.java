package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

public class AddFigureCommand implements DrawCommand {

    private Figure figure;
    private DrawModel model;

    public AddFigureCommand(Figure figure, DrawModel model) {
        this.figure = figure;
        this.model = model;
    }

    @Override
    public void redo() {
        model.addFigure(figure);
    }

    @Override
    public void undo() {
        model.removeFigure(figure);
    }
}
