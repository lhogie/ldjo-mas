package cnrs.mas;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import bigobject.Allocation;
import bigobject.LiveDistributedObject;

public class BigMultiAgentSystem
		extends LiveDistributedObject<Set<MobileAgent>, Serializable>
{
	private Set<MobileAgent> localAgents = new HashSet<>();

	public BigMultiAgentSystem(String id, Allocation a)
	{
		super(id, a);
	}

	@Override
	public void clearLocalData()
	{
		localAgents.clear();
	}

	public Set<MobileAgent> getLocalAgents()
	{
		return Collections.unmodifiableSet(localAgents);
	}

	public void addLocally(final MobileAgent newAgent)
	{
		localAgents.add(newAgent);
		newAgent.mas = this;
		newAgent.justArrivedAt(getAllocation().getLocalNode());

		for (MobileAgent a : localAgents)
		{
			if (a != newAgent)
			{
				a.agentHasJoined(newAgent);
			}
		}
	}

	public void removeFromLocalAgents(MobileAgent departingAgent)
	{
		localAgents.remove(departingAgent);
		departingAgent.mas = null;

		for (MobileAgent a : localAgents)
		{
			a.agentHasGone(departingAgent);
		}
	}
}
