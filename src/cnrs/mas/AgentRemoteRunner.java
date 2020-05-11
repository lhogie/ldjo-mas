package cnrs.mas;

import java.net.InetAddress;

import octojus.ComputationRequest;
import octojus.NoReturn;
import bigobject.BigObjectRegistry;

class AgentRemoteRunner extends ComputationRequest<NoReturn>
{
	MobileAgent agent;
	String masID;

	@Override
	protected NoReturn compute() throws Throwable
	{
		final BigMultiAgentSystem mas = (BigMultiAgentSystem) BigObjectRegistry.defaultRegistry.get(masID);
		mas.addLocally(agent);
		return null;
	}
}
