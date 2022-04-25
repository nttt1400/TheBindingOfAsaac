package gameobjects.character;

import java.util.LinkedList;

public class Larmes {
	private LinkedList<Tear> tears = new LinkedList<Tear>();
	Tear tmpTears;
	
	/*
	 * TODO
	 */
	
	public void parcours() {
		for(int i=0;i<tears.size();i++ ) {
			tmpTears=tears.get(i);
		}
	}

	/*
	 * Getters and Setters
	 */

	public LinkedList<Tear> getTears() {
		return tears;
	}
	
	public void setTears(LinkedList<Tear> tears) {
		this.tears = tears;
	}

	public Tear getTmpTears() {
		return tmpTears;
	}

	public void setTmpTears(Tear tmpTears) {
		this.tmpTears = tmpTears;
	}
}
