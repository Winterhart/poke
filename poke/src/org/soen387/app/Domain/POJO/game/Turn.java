package org.soen387.app.Domain.POJO.game;

public class Turn implements ITurn {
	
	
	private IHand challengerHand;
	private IHand challengeeHand;
	
	private IBench challengerBench;
	private IBench challengeeBench;
	
	private IDiscard challengerDiscard;
	private IDiscard challengeeDiscard;
}
