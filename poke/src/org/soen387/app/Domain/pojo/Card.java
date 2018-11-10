package org.soen387.app.Domain.pojo;

public class Card {
	private Long id;
	private int version;
	private CardType type;
	private String name;
	
	public Card(Long id, int version, String name, CardType type) {
		this.id = id;
		this.version =version;
		this.type = type;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
