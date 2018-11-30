package org.soen387.dom.Mapper.challenge;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.producer.IdentityBasedProducer;
import org.dsrg.soenea.domain.producer.IdentityBasedProducerMethod;
import org.soen387.dom.POJO.challenge.Challenge;

public class ChallengeInputMapper implements IdentityBasedProducer {

	@IdentityBasedProducerMethod
	public static Challenge find(Long id) throws SQLException, MapperException {
		// TODO Auto-generated method stub
		return null;
	}

}
