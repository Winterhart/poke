package org.soen387.dom.command.deck;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.Mapper.deck.CardInputMapper;
import org.soen387.dom.POJO.deck.ICard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ViewDetailDeckCommand extends ValidatorCommand  {

	public ViewDetailDeckCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			String detail = (String) helper.getAttribute("id");
			Long idOfDeck = Long.parseLong(detail);
			List<ICard> cards = CardInputMapper.findAllByDeckId(idOfDeck);
			List<CardJsonHelper> cardsHelper = new ArrayList<CardJsonHelper>();
			for(ICard c: cards) {
				cardsHelper.add(new CardJsonHelper(c.getId(), c.getCardType().toString(), c.getName(), c.getBase()));
			}
			
			String allCards = gson.toJson(cardsHelper);
			helper.setRequestAttribute("cards", allCards);
			
		}catch(Exception e) {
			e.printStackTrace();
			addNotification(e.getMessage());
			throw new CommandException(e.getMessage());
		}

		
		
		
	}
	
	private class CardJsonHelper{
		private Long id;
		private String t;
		private String n;
		private String b;
		
		public CardJsonHelper(Long id, String t, String n, String b) {
			super();
			this.id = id;
			this.t = t;
			this.n = n;
			this.b = b;
		}
		
		
	}

}
