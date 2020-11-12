package core;

import java.util.ArrayList;
import java.util.Collection;

public class Exercise {
	
	private String name, description;
	private Collection<Equipment> equipment = new ArrayList<>();
	private BodyPart bodyPart;

	public Exercise(String name, String description, BodyPart bodyPart) {
		setName(name);
		setDescription(description);
		setBodyPart(bodyPart);
	}

	public void addEquipment(Equipment equipment) {
		this.equipment.add(equipment);
	}

	public void addEquipment(Collection<Equipment> equipment) {
		this.equipment.addAll(equipment);
	}

	public void removeEquipment(Equipment equipment) {
		this.equipment.remove(equipment);
	}
	
	public Collection<Equipment> getEquipment(){
		return new ArrayList<>(equipment);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public BodyPart getBodyPart() {
		return bodyPart;		
	}
	
	public void setBodyPart(BodyPart bodyPart) {
		this.bodyPart = bodyPart;		
	}

}
