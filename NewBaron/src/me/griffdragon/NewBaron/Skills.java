package me.griffdragon.NewBaron;

import java.util.HashMap;

import org.bukkit.event.Listener;

public class Skills implements Listener {

	@SuppressWarnings("unused")
	private final BaronCore main;

	public Skills(BaronCore main) {
		this.main = main;
	}

	public HashMap<String, Skills> primary = new HashMap<String, Skills>();
	public HashMap<String, Skills> skill1 = new HashMap<String, Skills>();
	public HashMap<String, Skills> skill2 = new HashMap<String, Skills>();
	public HashMap<String, Skills> skill3 = new HashMap<String, Skills>();

	public void registerSkills() {

	}

	public void useSkill() {
		
	}
	

}
