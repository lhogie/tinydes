package cnrs.minides.demo;

import cnrs.minides.DES;
import cnrs.minides.StdOutLogger;

/*
 * One shop is made of cashers.
 */

public class Demo
{
	public static void main(String[] args)
	{
		// creates an office with 20 desks
		Office s = new Office(1);

		DES des = new DES(s);
		
		// log to stdout every event occurence
		des.getListeners().add(new StdOutLogger());
		des.getEventQueue().add(new NewClientInQueue(des, 0, new Customer()));

		des.run();
	}
}
