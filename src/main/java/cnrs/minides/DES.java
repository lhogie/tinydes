package cnrs.minides;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import toools.io.Utilities;
import toools.thread.Threads;

public class DES<S> implements Runnable
{
	private double time = -1;
	private final EventQueue<S> eventQueue = new EventPriorityQueue<S>();
	private final S system;
	private boolean stepped = false;
	private int numberOfProcessedEvents = 0;
	private final Random random = new Random();
	private double realTimeAcceleration = Double.POSITIVE_INFINITY;
	private final List<DESListener<S>> listeners = new ArrayList<DESListener<S>>();

	public List<DESListener<S>> getListeners()
	{
		return listeners;
	}

	public boolean isStepped()
	{
		return stepped;
	}

	public void setStepped(boolean stepped)
	{
		this.stepped = stepped;
	}

	public Random getRandom()
	{
		return random;
	}

	public DES(S system)
	{
		this.system = system;
	}

	public S getSystem()
	{
		return system;
	}

	public EventQueue<S> getEventQueue()
	{
		return eventQueue;
	}

	public double getTime()
	{
		return time;
	}

	public int getNumberOfProcessedEvents()
	{
		return numberOfProcessedEvents;
	}

	public boolean isTerminated()
	{
		return false;
	}

	public Random getPRNG()
	{
		return random;
	}

	public void stop()
	{
		eventQueue.add(new TerminationEvent(this, time));
	}

	public void setRealTimeAccelerationFactor(double acceleration)
	{
		if (acceleration <= 0)
			throw new IllegalArgumentException("acceleration factor must be a strictly positive number");

		this.realTimeAcceleration = acceleration;
	}

	@Override
	public void run()
	{
		if (eventQueue.size() == 0)
			throw new IllegalStateException("no event to schedule");

		while (eventQueue.size() > 0 && !isTerminated())
		{
			Event<S> nextEvent = eventQueue.getNextEvent();

			if (nextEvent.getClass() == TerminationEvent.class)
				break;

			if (!nextEvent.isDiscarded())
			{
				processEvent(nextEvent);
			}
		}
	}

	private void processEvent(Event<S> nextEvent)
	{
		if (realTimeAcceleration != Double.POSITIVE_INFINITY)
		{
			double virtualWait = nextEvent.getOccurenceDate() - getTime();
			double readWait = virtualWait / realTimeAcceleration;
			Threads.sleepMs((int) (readWait * 1000));
		}

		double eventDate = nextEvent.getOccurenceDate();

		if (eventDate <= this.time)
			throw new IllegalStateException("anterior and simultaneous events are not allowed:" +nextEvent);

		this.time = eventDate;
		nextEvent.doIt();
		++numberOfProcessedEvents;

		for (DESListener<S> l : listeners)
			l.eventJustExecuted(nextEvent);

		if (stepped && eventQueue.size() > 0)
		{
			Utilities.pressEnterToContinue("Press enter to execute next event...");
		}
	}

}
