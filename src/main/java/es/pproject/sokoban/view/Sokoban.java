package es.upm.pproject.sokoban.view;

import java.awt.EventQueue;

import es.upm.pproject.sokoban.controller.GameController;
import es.upm.pproject.sokoban.model.Operations;
import es.upm.pproject.sokoban.model.OperationsImpl;

public class Sokoban {
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
			
			Operations model1 = new OperationsImpl();
			GameController controller1 = new GameController(null, model1);
			GameView view1 = new GameView(controller1);
			controller1.setView(view1);
		});
	}

}