package cnrs.minides.demo;

import cnrs.minides.DES;
import cnrs.minides.Event;

class ClientLeaveDesk extends Event<Office>
{
	final Desk desk;

	public ClientLeaveDesk(DES<Office> simulation, double date, Desk c)
	{
		super(simulation, date);
		this.desk = c;
	}

	@Override
	protected void doIt()
	{
		Office shop = getSimulation().getSystem();

		if ( ! shop.queue.isEmpty())
		{
			desk.customer = shop.queue.remove(0);
			double d = future(getSimulation().getPRNG().nextDouble() * 3);
		}
	}

	@Override
	protected void undoIt()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String toString()
	{
		return "casher freed, accepting new client";
	}

}