package cnrs.minides.demo;

import cnrs.minides.DES;
import cnrs.minides.Event;

class NewClientInQueue extends Event<Office>
{
	final Customer customer;

	public NewClientInQueue(DES<Office> des, double date, Customer c)
	{
		super(des, date);
		this.customer = c;
	}

	@Override
	protected void doIt()
	{
		getSimulation().getSystem().queue.add(customer);

		for (Desk d : getSimulation().getSystem().desks)
		{
			// if this desk is free
			if (d.customer == null)
			{
				d.customer = customer;
				getSimulation().getEventQueue().add(new ClientLeaveDesk(getSimulation(),
						future(getSimulation().getPRNG().nextDouble() * 5 * 60 + 30), d));
				break;
			}
		}

		getSimulation().getEventQueue().add(new NewClientInQueue(getSimulation(),
				future(getSimulation().getPRNG().nextDouble() * 3 + 1), new Customer()));
	}

	@Override
	public String toString()
	{
		return "new client in queue, its length now is "
				+ getSimulation().getSystem().queue.size();
	}

	@Override
	protected void undoIt()
	{
	}
}