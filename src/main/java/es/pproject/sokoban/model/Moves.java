package es.upm.pproject.sokoban.model;

import java.awt.Image;

public abstract class Moves {
	private int x;
	private int y;
	private Image image;

	/**
	 * 
	 * @param x
	 * @param y
	 * @see Moves
	 */
	public Moves (int x , int y ) {
		this.x = x; 
		this.y = y; 
	}
	
	/**
	 * @return int
	 */
	public int getX() {
		return this.x;
	}
	
	
	/**
	 * 
	 * @return int
	 */
	public int getY() {
		return this.y;
	}
	
	
	/**
	 * @param x
	 */
	public void setX( int x) {
		this.x = x;
	}
	
	
	/**
	 * @param y
	 */
	public void setY( int y) {
		this.y = y;
	}
	/**
	 * @return Image
	 */
	public Image getImage () {
		return this.image;
	}
	
	/**
	 * @param image
	 */
	
	public void setImage (Image image) {
		this.image = image;
	}
	
	
	public abstract String toString ();
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null)
			return false;
		if(getClass() != o.getClass())
			return false;
		Moves element = (Moves) o;
		return this.getX() == element.getX()
				&& this.getY() == element.getY();
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.x;
		result = 31 * result + this.y;
		return result;
	}

}
