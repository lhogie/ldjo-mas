package cnrs.mas;

import toools.thread.Threads;

public abstract class TimeSlicingMobileAgent extends ThreadedMobileAgent
{
	@Override
	public void run()
	{
		while ( ! requestTermination)
		{
			executeOneSlice();
			sliceDone();
		}
	}

	protected void sliceDone()
	{
		if ( ! requestTermination)
		{
			Threads.sleepMs(1000);
		}
	}

	public abstract void executeOneSlice();
}
