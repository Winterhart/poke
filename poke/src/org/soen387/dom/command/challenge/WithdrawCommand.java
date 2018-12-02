package org.soen387.dom.command.challenge;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.Mapper.challenge.ChallengeInputMapper;
import org.soen387.dom.POJO.challenge.Challenge;

public class WithdrawCommand extends ValidatorCommand  {

	public WithdrawCommand(Helper helper) {
		super(helper);
	}

	@Source
	public String version;
	
	@Override
	public void process() throws CommandException {
		//Grab User Id
		Long parsedUserId = null;
		Long challengeId = null;
		Long chaVersion = null;
		
		try {
			Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
			parsedUserId = Long.parseLong(rawUserId.toString());
			System.out.println("User Id: " + parsedUserId.toString());
			
			String targetCha = (String)helper.getAttribute("targetId");
			challengeId = Long.parseLong(targetCha);
			
			
			String versionC = version;
			chaVersion = Long.parseLong(versionC);
			
		}catch(Exception e) {
			String message = "Invalid Post parameters Id";
			addNotification(message);
			throw new CommandException(message);
		}

		
		//Ensure Parsing right
		if(challengeId == null || parsedUserId == null  || chaVersion == null) {
			String message = "Can't detect user or target or deck Id";
			addNotification(message);
			throw new CommandException(message);
		}
		
		//Business Rule: Challenge must exists
		Challenge cha = null;
		try {
			cha = ChallengeInputMapper.find(challengeId);
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Challenge doesn't exists...";
			addNotification(message);
			throw new CommandException(message);
			
		}
		
		//Business Rule: Can you refuse it...
		if(cha.getChallengerId() != parsedUserId) {
			String message = "You can't withdraw this challenge";
			addNotification(message);
			throw new CommandException(message);
		}
		
		if(cha.getStatus() != 0) {
			String message = "Challenge's status already completed";
			addNotification(message);
			throw new CommandException(message);
		}
		
		// Attempt to Update a Challenge
		try {
			cha.setVersion(chaVersion);
			cha.setStatus(2);
			UoW.getCurrent().registerDirty(cha);
			UoW.getCurrent().commit();
		} catch (Exception e) {
			e.printStackTrace();
			String message = "Not able to Update Challenge";
			addNotification(message);
			throw new CommandException(message);
			
		}
		
	}

}
