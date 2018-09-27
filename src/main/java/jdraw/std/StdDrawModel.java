/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.ArrayList;
import java.util.LinkedList;

import jdraw.framework.*;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Jurij
 *
 */
public class StdDrawModel implements DrawModel {

	private LinkedList<Figure> myFigures = new LinkedList<>();
	private ArrayList<DrawModelListener> myDrawModelListener = new ArrayList<>();


	@Override
	public void addFigure(Figure f) {
			myFigures.add(f);
			notifyModelChangeListeners(f, DrawModelEvent.Type.FIGURE_ADDED);

	}

	@Override
	public Iterable<Figure> getFigures() {
		return myFigures;
	}

	@Override
	public void removeFigure(Figure f) {
		myFigures.remove(f);
		notifyModelChangeListeners(f, DrawModelEvent.Type.FIGURE_REMOVED);
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		myDrawModelListener.add(listener);

	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		myDrawModelListener.remove(listener);
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	@Override
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		// TODO to be implemented  
		System.out.println("StdDrawModel.setFigureIndex has to be implemented");
	}

	@Override
	public void removeAllFigures() {
		// TODO to be implemented  
		System.out.println("StdDrawModel.removeAllFigures has to be implemented");
	}

	/**
	 * notifies all the drawModelListeners
	 * @param figure the changed figure
	 * @param type what happened to the figure
	 */
	private void notifyModelChangeListeners(Figure figure, DrawModelEvent.Type type){
		DrawModelEvent drawModelEvent = new DrawModelEvent(this,figure,type);

		for (DrawModelListener listener : myDrawModelListener){
			listener.modelChanged(drawModelEvent);
		}

	}

}
