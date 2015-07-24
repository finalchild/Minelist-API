package io.github.fcreloaded.minelist_api;

import java.io.IOException;
import java.net.URL;

/**
 * Used to store and get info about a server
 * @author finalchild
 * @version 1.0
 * @since 1.0
 */
public class MinelistServer {

	protected String id;
	protected String name;
	protected String address;
	protected String version;
	protected int onlinePlayers;
	protected int maxPlayers;
	protected boolean status;
	protected URL website;
	protected int votes;
	protected String owner;
	
	/**
	 * Creates an instance with the info provided. It does not check whether the info is correct. @see Minelist_API#getServer()
	 * @param id
	 * @param name
	 * @param address
	 * @param version
	 * @param onlinePlayers
	 * @param maxPlayers
	 * @param status
	 * @param website
	 * @param votes
	 * @param owner
	 */
	public MinelistServer(String id, String name, String address, String version, int onlinePlayers, int maxPlayers, boolean status, URL website, int votes, String owner) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.version = version;
		this.onlinePlayers = onlinePlayers;
		this.maxPlayers = maxPlayers;
		this.status = status;
		this.website = website;
		this.votes = votes;
		this.owner = owner;
	}
	
	/**
	 * @return id
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return address
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * @return version
	 */
	public String getVersion() {
		return this.version;
	}
	
	/**
	 * @return onlinePlayers
	 */
	public int getOnlinePlayers() {
		return this.onlinePlayers;
	}
	
	/**
	 * @return maxPlayers
	 */
	public int getMaxPlayers() {
		return this.maxPlayers;
	}
	
	/**
	 * @return status
	 */
	public boolean isOnline() {
		return this.status;
	}
	
	/**
	 * @return website
	 */
	public URL getWebsite() {
		return this.website;
	}
	
	/**
	 * @return votes
	 */
	public int getVotes() {
		return this.votes;
	}
	
	/**
	 * @return owner
	 */
	public String getOwner() {
		return this.owner;
	}
	
	/**
	 * Refreshes the info. It depends on {@link Minelist_API#getServer)}.
	 * @throws IOException
	 */
	public void refresh() throws IOException {
		MinelistServer refresh = Minelist_API.getServerById(id);
		this.name = refresh.name;
		this.address = refresh.address;
		this.version = refresh.version;
		this.onlinePlayers = refresh.onlinePlayers;
		this.maxPlayers = refresh.maxPlayers;
		this.status = refresh.status;
		this.website = refresh.website;
		this.votes = refresh.votes;
		this.owner = refresh.owner;
	}

}
