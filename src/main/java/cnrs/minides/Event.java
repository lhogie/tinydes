package cnrs.minides;

public abstract class Event<S>
{
	private final DES<S> simulation;
	private final double occurenceDate;
	private boolean discarded = false;

	public Event(DES<S> simulation, double occurenceDate)
	{
		if (simulation == null)
			throw new IllegalArgumentException();

		if (occurenceDate <= simulation.getTime())
			throw new IllegalArgumentException(
					"new event must be scheduled in the future");

		this.simulation = simulation;
		this.occurenceDate = occurenceDate;
	}

	public DES<S> getSimulation()
	{
		return simulation;
	}

	public boolean isDiscarded()
	{
		return discarded;
	}

	public void setDiscarded(boolean b)
	{
		discarded = b;
	}

	public double getOccurenceDate()
	{
		return occurenceDate;
	}

	protected double future(double d)
	{
		assert d > 0;
		return getOccurenceDate() + d;
	}

	protected abstract void doIt();

	protected abstract void undoIt();
}
