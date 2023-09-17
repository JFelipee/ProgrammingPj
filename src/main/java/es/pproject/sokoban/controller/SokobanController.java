package es.upm.pproject.sokoban.controller;

import es.upm.pproject.sokoban.model.Board;
import es.upm.pproject.sokoban.model.Game;
import es.upm.pproject.sokoban.view.FrameSokoban;

public class SokobanController {

	private Game model;
	private FrameSokoban view;
	
	
	public SokobanController(FrameSokoban view, Game model) {
		this.model = model;
		this.view = view;
	}
	
	
	public Board getBoard() {
		return this.model.getLevel().getBoard();
	}
	
	public void setView(FrameSokoban view) {
		this.view = view;
	}
	
	public Game getGame() {
		return this.model;
	}
	
	public FrameSokoban getView() {
		return this.view;
	}

}
