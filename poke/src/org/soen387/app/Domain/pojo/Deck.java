package org.soen387.app.Domain.pojo;

public class Deck {
	private static final Exception InvalidDeckException = null;
	private Long id;
	private int version;
	private Card[] cards;
	
	public Deck(Long id, int version, Card[] cards) throws Exception {
		this.id = id;
		this.version = version;
		if(cards.length != 40) {
			throw InvalidDeckException;
		}
		this.cards = cards;
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

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}
}
