package model.db;

import java.sql.Time;
import commons.Level;

public class MiniMain 
{
	public static void main(String[] args) 
	{
		Manager manager = Manager.getInstance();
		
		User user1 = new User("Yadrdden");
		User user2 = new User("Isaddn");
		manager.add(user1);
		manager.add(user2);
		
		Level level1 = new Level("level1");
		manager.add(level1);
		Level level2 = new Level("MAAFdAdN");
		manager.add(level2);
		
		Record record1 = new Record(level1.getLevelID(), user1.getName(), 1, new Time(0));
		manager.add(record1);
		
		Record record2 = new Record(level2.getLevelID(), user2.getName(), 1, new Time(0));
		manager.add(record2);
		
		QueryParameters q = new QueryParameters("level1", null, "steps");
		
		manager.recordsQuery(q);
	}
}