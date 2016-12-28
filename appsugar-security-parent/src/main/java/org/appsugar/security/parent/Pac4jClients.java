package org.appsugar.security.parent;

import java.util.ArrayList;
import java.util.List;

import org.pac4j.core.client.Client;
import org.pac4j.core.client.Clients;

@SuppressWarnings("rawtypes")
public class Pac4jClients extends Clients {

	public Pac4jClients() {
		super();
	}

	public Pac4jClients(Client... clients) {
		super(clients);
	}

	public Pac4jClients(Client client) {
		super(client);
	}

	public Pac4jClients(List<Client> clients) {
		super(clients);
	}

	public Pac4jClients(String callbackUrl, Client... clients) {
		super(callbackUrl, clients);
	}

	public Pac4jClients(String callbackUrl, Client client) {
		super(callbackUrl, client);
	}

	public Pac4jClients(String callbackUrl, List<Client> clients) {
		super(callbackUrl, clients);
	}

	/**
	 * 添加一个Client
	 * @author NewYoung
	 * 2016年12月28日上午10:45:13
	 */
	public void addClient(Client client) {
		List<Client> clientList = getClients();
		clientList = clientList == null ? new ArrayList<>() : new ArrayList<>(clientList);
		if (clientList.contains(client)) {
			return;
		}
		clientList.add(client);
		setClients(clientList);
	}
}
