package ca.sheriadancollege.faridmu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	
	// insert a Dice bean
	public void insertDice(Dice dice) {
		
		String sql="INSERT INTO dice(diceNumber,diceFaces,diceTotal) VALUES(:diceNumber,:diceFaces,:diceTotal)";
		namedParameters.addValue("diceNumber", dice.getNumberOfDices());
		namedParameters.addValue("diceFaces", dice.getNumberOfSides());
		namedParameters.addValue("diceTotal", dice.getSum());
		
		jdbc.update(sql, namedParameters);
	}
	
      public  List<Dice> diceList() {
		
		String sql="SELECT * FROM dice";
		
		return jdbc.query(sql, new BeanPropertyRowMapper<Dice>(Dice.class));
	}
	
      
      public  void diceDelete() {
  		
  		String sql="DELETE FROM dice";
  		
  		jdbc.update(sql, namedParameters);
  	}
  	


}
