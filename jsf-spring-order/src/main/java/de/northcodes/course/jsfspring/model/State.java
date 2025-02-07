package de.northcodes.course.jsfspring.model;

public enum State {

	AVAILABLE("Verfügbar"),
	REQUESTED("Angefragt"),
	RESERVED("Reserviert"),
	UNAVAILABLE("Ausgeliehen");


	private final String description;

	State(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
}
