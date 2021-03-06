/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */
package jdraw.std;

import jdraw.decorators.BorderDecorator;
import jdraw.figures.Group;
import jdraw.figures.LineTool;
import jdraw.figures.OvalTool;
import jdraw.figures.RectTool;
import jdraw.framework.*;
import jdraw.grid.SimpleGrid;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Standard implementation of interface DrawContext.
 * 
 * @see DrawView
 * @author Dominik Gruntz & Christoph Denzler
 * @version 2.6, 24.09.09
 */
@SuppressWarnings("serial")
public class StdContext extends AbstractContext {

	private List<Figure> clipboard = new ArrayList<>() {};
	private List<DrawToolFactory> drawToolFactories = new LinkedList<>();


	/**
	 * Constructs a standard context with a default set of drawing tools.
	 * @param view the view that is displaying the actual drawing.
	 */
	public StdContext(DrawView view) {
		super(view, null);
	}

	/**
	 * Constructs a standard context. The drawing tools available can be
	 * parameterized using <code>toolFactories</code>.
	 * 
	 * @param view  the view that is displaying the actual drawing.
	 * @param toolFactories  a list of DrawToolFactories that are available to the user
	 */
	public StdContext(DrawView view, List<DrawToolFactory> toolFactories) {
		super(view, toolFactories);
		this.drawToolFactories = toolFactories;
	}

	/**
	 * Creates and initializes the "Edit" menu.
	 * 
	 * @return the new "Edit" menu.
	 */
	@Override
	protected JMenu createEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		final JMenuItem undo = new JMenuItem("Undo");
		undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
		editMenu.add(undo);
		undo.addActionListener(e -> {
				final DrawCommandHandler h = getModel().getDrawCommandHandler();
				if (h.undoPossible()) {
					h.undo();
				}
			}
		);

		final JMenuItem redo = new JMenuItem("Redo");
		redo.setAccelerator(KeyStroke.getKeyStroke("control Y"));
		editMenu.add(redo);
		redo.addActionListener(e -> {
				final DrawCommandHandler h = getModel().getDrawCommandHandler();
				if (h.redoPossible()) {
					h.redo();
				}
			}
		);
		editMenu.addSeparator();

		JMenuItem sa = new JMenuItem("SelectAll");
		sa.setAccelerator(KeyStroke.getKeyStroke("control A"));
		editMenu.add(sa);
		sa.addActionListener( e -> {
				for (Figure f : getModel().getFigures()) {
					getView().addToSelection(f);
				}
				getView().repaint();
			}
		);

		editMenu.addSeparator();
		JMenuItem cut = new JMenuItem("Cut");
		cut.addActionListener(e->{
			if(!clipboard.isEmpty()){
				clipboard.clear();
			}

			getView().getSelection().forEach(figure -> {
				clipboard.add(figure.clone());
				getView().getModel().removeFigure(figure);
			});
		});

		editMenu.add(cut).setEnabled(true);
		JMenuItem copy = new JMenuItem("Copy");

		copy.addActionListener(e->{
			if(!clipboard.isEmpty()){
				clipboard.clear();
			}

			getView().getSelection().forEach(figure -> {
				clipboard.add(figure.clone());
			});

		});
		editMenu.add(copy).setEnabled(true);

		JMenuItem paste = new JMenuItem("Paste");
		paste.addActionListener(e->{
			clipboard.forEach(
					figure -> getView().getModel().addFigure(
							figure.clone()));
		});
		editMenu.add(paste).setEnabled(true);

		editMenu.addSeparator();
		JMenuItem clear = new JMenuItem("Clear");
		editMenu.add(clear);
		clear.addActionListener(e -> {
			getModel().removeAllFigures();
		});
		
		editMenu.addSeparator();
		JMenuItem group = new JMenuItem("Group");
		group.addActionListener(e -> {
			Group newGroup = new Group(getView().getSelection());
			getModel().addFigure(newGroup);
			for (Figure f : getView().getSelection()){
				getModel().removeFigure(f);
			}
			getView().addToSelection(newGroup);

		});
		editMenu.add(group);

		JMenuItem ungroup = new JMenuItem("Ungroup");
		ungroup.addActionListener(e -> {
			for (Figure f : getView().getSelection()){
				if (f instanceof Group){
					for (Figure x : ((Group) f).getFigureParts()){
						getModel().addFigure(x);
						getView().addToSelection(x);
					}
					getModel().removeFigure(f);
				}
			}
		});

		editMenu.add(ungroup);
		editMenu.addSeparator();

		JMenu orderMenu = new JMenu("Order...");
		JMenuItem frontItem = new JMenuItem("Bring To Front");
		frontItem.addActionListener(e -> {
			bringToFront(getView().getModel(), getView().getSelection());
		});
		orderMenu.add(frontItem);
		JMenuItem backItem = new JMenuItem("Send To Back");
		backItem.addActionListener(e -> {
			sendToBack(getView().getModel(), getView().getSelection());
		});
		orderMenu.add(backItem);
		editMenu.add(orderMenu);

		JMenu grid = new JMenu("Grid...");

		JMenuItem simpleGrid = new JMenuItem("Simple Grid");
		simpleGrid.addActionListener(e -> {
			getView().setGrid(new SimpleGrid());
		});

		grid.add(simpleGrid);

		JMenuItem noGrid = new JMenuItem("Remove Grid");
		noGrid.addActionListener(e -> {
			getView().setGrid(null);
		});
		grid.add(noGrid);
		grid.add(simpleGrid);

		editMenu.add(grid);

		JMenu decorator = new JMenu("Decorators");
		JMenuItem dec = new JMenuItem("Toggle BorderDecorator");
		decorator.add(dec);
		dec.addActionListener(e -> {
					List<Figure> s = getView().getSelection();
					getView().clearSelection();
					for (Figure f : s) {
						Figure f2 = null;
						if (f instanceof BorderDecorator) {
							f2 = ((BorderDecorator) f).getInner();
						} else {
							f2 = new BorderDecorator(f);
						}
						getModel().removeFigure(f);
						getModel().addFigure(f2);
						getView().addToSelection(f2);
					}
				}
		);

		JMenuItem addDec = new JMenuItem("Add Border");
		decorator.add(addDec);
		addDec.addActionListener(e -> {
					List<Figure> s = getView().getSelection();
					getView().clearSelection();
					for (Figure f : s) {
						Figure f2 = null;
							f2 = new BorderDecorator(f);
						getModel().removeFigure(f);
						getModel().addFigure(f2);
						getView().addToSelection(f2);
					}
				}
		);

		

		editMenu.add(decorator);
		return editMenu;
	}

	/**
	 * Creates and initializes items in the file menu.
	 * 
	 * @return the new "File" menu.
	 */
	@Override
	protected JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		JMenuItem open = new JMenuItem("Open");
		fileMenu.add(open);
		open.setAccelerator(KeyStroke.getKeyStroke("control O"));
		open.addActionListener(e -> doOpen());

		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke("control S"));
		fileMenu.add(save);
		save.addActionListener(e ->	doSave());

		JMenuItem exit = new JMenuItem("Exit");
		fileMenu.add(exit);
		exit.addActionListener(e -> System.exit(0));
		
		return fileMenu;
	}

	@Override
	protected void doRegisterDrawTools() {
		for(DrawToolFactory dt : getDrawToolFactories()){
			addTool(dt== null ? null : dt.createTool(this));
		}
	}

	/**
	 * Changes the order of figures and moves the figures in the selection
	 * to the front, i.e. moves them to the end of the list of figures.
	 * @param model model in which the order has to be changed
	 * @param selection selection which is moved to front
	 */
	public void bringToFront(DrawModel model, List<Figure> selection) {
		// the figures in the selection are ordered according to the order in
		// the model
		List<Figure> orderedSelection = new LinkedList<Figure>();
		int pos = 0;
		for (Figure f : model.getFigures()) {
			pos++;
			if (selection.contains(f)) {
				orderedSelection.add(0, f);
			}
		}
		for (Figure f : orderedSelection) {
			model.setFigureIndex(f, --pos);
		}
	}

	/**
	 * Changes the order of figures and moves the figures in the selection
	 * to the back, i.e. moves them to the front of the list of figures.
	 * @param model model in which the order has to be changed
	 * @param selection selection which is moved to the back
	 */
	public void sendToBack(DrawModel model, List<Figure> selection) {
		// the figures in the selection are ordered according to the order in
		// the model
		List<Figure> orderedSelection = new LinkedList<Figure>();
		for (Figure f : model.getFigures()) {
			if (selection.contains(f)) {
				orderedSelection.add(f);
			}
		}
		int pos = 0;
		for (Figure f : orderedSelection) {
			model.setFigureIndex(f, pos++);
		}
	}

	/**
	 * Handles the saving of a drawing to a file.
	 */
	private void doSave() {
		JFileChooser chooser = new JFileChooser(getClass().getResource("").getFile());
		chooser.setDialogTitle("Save Graphic");
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		
		chooser.setFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.draw)", "draw"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.xml)", "xml"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.json)", "json"));
		
		int res = chooser.showSaveDialog(this);

		if (res == JFileChooser.APPROVE_OPTION) {
			// save graphic
			File file = chooser.getSelectedFile();
			FileFilter filter = chooser.getFileFilter();
			if(filter instanceof FileNameExtensionFilter && !filter.accept(file)) {

				file = new File(chooser.getCurrentDirectory(), file.getName() + "." + ((FileNameExtensionFilter)filter).getExtensions()[0]);
			}
			System.out.println("save current graphic to file " + file.getName() + " using format "
					+ ((FileNameExtensionFilter)filter).getExtensions()[0]);
		}
	}

	/**
	 * Handles the opening of a new drawing from a file.
	 */
	private void doOpen() {
		JFileChooser chooser = new JFileChooser(getClass().getResource("")
				.getFile());
		chooser.setDialogTitle("Open Graphic");
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
			@Override
			public String getDescription() {
				return "JDraw Graphic (*.draw)";
			}

			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().endsWith(".draw");
			}
		});
		int res = chooser.showOpenDialog(this);

		if (res == JFileChooser.APPROVE_OPTION) {
			// read jdraw graphic
			System.out.println("read file "
					+ chooser.getSelectedFile().getName());
		}
	}

	protected final List<DrawToolFactory> getDrawToolFactories() {
		return drawToolFactories;
	}
}
