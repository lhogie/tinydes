package cnrs.minides;

import toools.exceptions.NotYetImplementedException;

public class TerminationEvent extends Event
{

	public TerminationEvent(DES s, double date)
	{
		super(s, date);
	}

	@Override
	protected void doIt()
	{
		throw new IllegalStateException("this event should never be executed");
	}

	@Override
	protected void undoIt()
	{
		throw new NotYetImplementedException();
	}

}
