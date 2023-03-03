package engine;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import engine.entity.Player;
import javafx.scene.shape.Rectangle;
import threads.GameLoop;

public class EventHandler {

	public boolean isMapChange = false;
	GameLoop gl;
	Rectangle eventRect = new Rectangle(23, 23, 2, 2);
	int eventRectDefaultX, eventRectDefaultY;
	boolean check = true;
	Timer timer = new Timer();
	int numMap = 1;

	public EventHandler(GameLoop gl) {
		this.gl = gl;

		// eventRect = new Rectangle();
		eventRect.setX(23);
		eventRect.setY(23);
		eventRect.setWidth(2);
		eventRect.setHeight(2);
		eventRectDefaultX = (int) eventRect.getX();
		eventRectDefaultY = (int) eventRect.getY();

	}

	public void checkEventDamage() {
		if (hit(2, 2, Direction.RIGHT)) {
			System.out.println(gl.getPlayer().getHearts());
			damagePit();
		}
	}

	public void checkEventChangeMap() {
		
		//De mapa 1 a mapa 2
		if (hit(8, 1, Direction.UP) && numMap == 1) {
			numMap=2;
			System.out.println("hit");
			File nuevoMapa = new File("src/main/resources/maps/mapa2.tmj");
			gl.getTileHandler().setMapFile(nuevoMapa);
			gl.getTileHandler().loadLayers(nuevoMapa);
			isMapChange = true;
			
		}
		// De mapa 2 a mapa 1
		if (hit(6, 23, Direction.DOWN) && numMap == 2) {
			numMap=1;
			System.out.println("hit");
			File nuevoMapa = new File("src/main/resources/maps/mapa1.tmj");
			gl.getTileHandler().setMapFile(nuevoMapa);
			gl.getTileHandler().loadLayers(nuevoMapa);
			isMapChange = true;
			
		}
		// De mapa 2 a mapa 3
		if (hit(7, 1, Direction.UP) && numMap == 2) {
			numMap=3;
			System.out.println(numMap);
			File nuevoMapa = new File("src/main/resources/maps/mapa3.tmj");
			gl.getTileHandler().setMapFile(nuevoMapa);
			gl.getTileHandler().loadLayers(nuevoMapa);
			isMapChange = true;
			
		}
		// De mapa 2 a mapa 4
		if (hit(18, 1, Direction.UP) && numMap == 2) {
			numMap=4;
			System.out.println(numMap);
			File nuevoMapa = new File("src/main/resources/maps/mapa4.tmj");
			gl.getTileHandler().setMapFile(nuevoMapa);
			gl.getTileHandler().loadLayers(nuevoMapa);
			isMapChange = true;
			
		}
		// De mapa 3 a mapa 2

		if (hit(7, 23, Direction.DOWN) && numMap == 3) {
			numMap=2;
			System.out.println(numMap);
			File nuevoMapa = new File("src/main/resources/maps/mapa2.tmj");
			gl.getTileHandler().setMapFile(nuevoMapa);
			gl.getTileHandler().loadLayers(nuevoMapa);
			isMapChange = true;
			
		}
		
		// De mapa 4 a mapa 2
		
		if (hit(7, 23, Direction.DOWN) && numMap == 4) {
			numMap=5;
			System.out.println(numMap);
			File nuevoMapa = new File("src/main/resources/maps/mapa3.tmj");
			gl.getTileHandler().setMapFile(nuevoMapa);
			gl.getTileHandler().loadLayers(nuevoMapa);
			isMapChange = true;
			
		}

	}


	public void damagePit() {

		gl.player.damage(0.5);
	}

	public boolean hit(int eventCol, int eventRow, Direction reqDirection) {
		boolean hit = false;
		double defaultX = gl.player.areaSolid.getX();
		double defaultY = gl.player.areaSolid.getY();
		gl.player.areaSolid.setX(gl.player.worldX + gl.player.areaSolid.getX());
		gl.player.areaSolid.setY(gl.player.worldY + gl.player.areaSolid.getY());
		eventRect.setX(eventCol * GameVariables.TILE_SIZE + eventRect.getX());
		eventRect.setY(eventRow * GameVariables.TILE_SIZE + eventRect.getY());

		if (gl.player.areaSolid.getBoundsInParent().intersects(eventRect.getBoundsInParent())) {
			if (gl.player.direction.equalsByDireccion(reqDirection)) {
				hit = true;
			}
		}
		gl.player.areaSolid.setX(defaultX);
		gl.player.areaSolid.setY(defaultY);
		eventRect.setX(eventRectDefaultX);
		eventRect.setY(eventRectDefaultY);

		return hit;
	}

	public int getNumMap() {
		return numMap;
	}

	public void setNumMap(int numMap) {
		this.numMap = numMap;
	}

	public boolean isMapChange() {
		return isMapChange;
	}

	public void setMapChange(boolean isMapChange) {
		this.isMapChange = isMapChange;
	}

}
