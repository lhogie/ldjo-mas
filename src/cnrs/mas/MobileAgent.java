package cnrs.mas;

import java.io.Serializable;
import java.util.List;

import octojus.OctojusNode;

public abstract class MobileAgent implements Serializable
{
	transient BigMultiAgentSystem mas;

	public BigMultiAgentSystem getMAS()
	{
		return mas;
	}

	public List<OctojusNode> getAccessibleNodes()
	{
		return getMAS().getAllocation().getNodes();
	}

	public void migrateTo(OctojusNode destinationNode)
	{
		// leave the current node
		justParting();
		mas.removeFromLocalAgents(this);

		AgentRemoteRunner migrator = new AgentRemoteRunner();
		migrator.agent = this;
		migrator.masID = mas.getID();

		// move the another node
		try
		{
			migrator.runOn(destinationNode);
		}
		catch (Throwable e)
		{
			throw new IllegalStateException(e);
		}
	}

	protected abstract void agentHasJoined(MobileAgent ma);

	protected abstract void agentHasGone(MobileAgent ma);

	protected abstract void justArrivedAt(OctojusNode localNode);

	protected abstract void justParting();
}
