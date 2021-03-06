package io.github.fcreloaded.minelist_api;

import java.io.IOException;
import java.net.URL;

/**
 * Used to store and get info about a server
 * @author finalchild
 * @version 1.1
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
	protected int rank;
	protected String owner;
	protected String title;
	protected String description;
	
	/**
	 * Creates an instance with the info provided. It does not check whether the info is correct. @see Minelist_API#getServer()
	 * @param id id
	 * @param name name
	 * @param address address
	 * @param version version
	 * @param onlinePlayers onlinePlayers
	 * @param maxPlayers maxPlayers
	 * @param status status
	 * @param website website
	 * @param votes votes
	 * @param owner owner
	 * @param title title
	 */
	public MinelistServer(String id, String name, String address, String version, int onlinePlayers, int maxPlayers, boolean status, URL website, int votes, int rank, String owner, String title) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.version = version;
		this.onlinePlayers = onlinePlayers;
		this.maxPlayers = maxPlayers;
		this.status = status;
		this.website = website;
		this.votes = votes;
		this.rank = rank;
		this.owner = owner;
		this.title = title;
	}
	
	/**
	 * @param server
	 */
	public MinelistServer(MinelistServer server) {
		this.id = server.id;
		this.name = server.name;
		this.address = server.address;
		this.version = server.version;
		this.onlinePlayers = server.onlinePlayers;
		this.maxPlayers = server.maxPlayers;
		this.status = server.status;
		this.website = server.website;
		this.votes = server.votes;
		this.rank = server.rank;
		this.owner = server.owner;
		this.title = server.title;
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
	 * @return rank
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * @return owner
	 */
	public String getOwner() {
		return this.owner;
	}
	
	/**
	 * 
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Refreshes the info. It depends on {@link Minelist_API#getServerById(String)}.
	 * @throws IOException if it can't connect to the Internet etc.
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
		this.rank = refresh.rank;
		this.owner = refresh.owner;
		this.title = refresh.title;
	}

}
