/**
 * 
 */
package io.github.fcreloaded.minelist_api;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * A collection of methods which can be used to get a MinelistServer instance, features will be added
 * @author finalchild
 * @version 1.0
 * @since 1.0
 */
public class Minelist_API {

	/**
	 * @return The revision string of Minelist
	 * @throws IOException if it can't connect to the Internet etc.
	 */
	public static String getRevision() throws IOException {
		Document doc = Jsoup.connect("https://minelist.kr/").userAgent("Mozilla").get();
		return doc.select(".ml-content").first().child(4).ownText();
	}
	
	/**
	 * @return The version string of Minelist-API
	 */
	public static String getAPIVersion() {
		return "1.1.0-SNAPSHOT";
	}
	
	/**
	 * @return The revision string of Minelist which this version of Minelist-API is based on
	 */
	public static String getAPIRevision() {
		return "aab9797";
	}
	
	/**
	 * Checks the string and uses either {@link #getServerById(String)} or {@link #getServerByAddress(String)}
	 * @param string The string which you want to get the server with
	 * @return A MinelistServer instance with the string
	 * @throws IOException if it can't connect to the Internet etc.
	 */
	public static MinelistServer getServer(String string) throws IOException {
		if(string.contains(".")) {
			return Minelist_API.getServerByAddress(string);
		} else {
			return Minelist_API.getServerById(string);
		}
	}
	/**
	 * Used when you want to get a MinelistServer instance with an id
	 * @param id The id which you want to get the server with
	 * @return A MinelistServer instance with the id
	 * @throws IOException if it can't connect to the Internet etc.
	 */
	public static MinelistServer getServerById(String id) throws IOException {
		Document doc = Jsoup.connect("https://minelist.kr/servers/" + id).userAgent("Mozilla").timeout(100000).get();
		String name = doc.select("#mc-server-panel-heading").first().ownText();
		String address = doc.select(".mc-server-address").first().ownText();
		String version = doc.select(".panel-body").first().child(1).child(1).child(1).ownText();
		String players = doc.select("td[itemprop=playersOnline]").first().ownText();
		int onlinePlayers = Integer.parseInt(players.split("/")[0]);
		int maxPlayers = Integer.parseInt(players.split("/")[1]);
		boolean status = doc.select("td[itemprop=serverStatus]").first().child(0).ownText().equalsIgnoreCase("Online");
		URL website;
		int votes;
		String owner;
		Element ownerElement;
		try {
			website = new URL(doc.select("div[itemprop=url]").first().child(0).ownText());
			votes = Integer.parseInt(doc.select(".panel-body").first().child(1).child(5).child(1).ownText());
			ownerElement = doc.select(".panel-body").first().child(1).child(6).child(1);
			if (ownerElement.children().size() == 0) {
				owner = ownerElement.ownText();
			} else {
				owner = null;
			}
		} catch(NullPointerException npe) {
			website = null;
			votes = Integer.parseInt(doc.select(".panel-body").first().child(1).child(4).child(1).ownText());
			ownerElement = doc.select(".panel-body").first().child(1).child(5).child(1);
			if (ownerElement.children().size() == 0) {
				owner = ownerElement.ownText();
			} else {
				owner = null;
			}
		}
		return new MinelistServer(id, name, address, version, onlinePlayers, maxPlayers, status, website, votes, owner);
	}
	
	/**
	 * Used when you want to get a MinelistServer instance with an address
	 * @param id The address which you want to get the server with
	 * @return A MinelistServer instance with the address
	 * @throws IOException if it can't connect to the Internet etc.
	 */
	public static MinelistServer getServerByAddress(String address) throws IOException {
		Document doc = Jsoup.connect("https://minelist.kr/servers/" + address).userAgent("Mozilla").timeout(100000).get();
		String id = doc.select(".btn-sm").first().attr("href").substring(9);
		String name = doc.select("#mc-server-panel-heading").first().ownText();
		String version = doc.select(".panel-body").first().child(1).child(1).child(1).ownText();
		String players = doc.select("td[itemprop=playersOnline]").first().ownText();
		int onlinePlayers = Integer.parseInt(players.split("/")[0]);
		int maxPlayers = Integer.parseInt(players.split("/")[1]);
		boolean status = doc.select("td[itemprop=serverStatus]").first().child(0).ownText().equalsIgnoreCase("Online");
		URL website;
		int votes;
		String owner;
		Element ownerElement;
		try {
			website = new URL(doc.select("div[itemprop=url]").first().child(0).ownText());
			votes = Integer.parseInt(doc.select(".panel-body").first().child(1).child(5).child(1).ownText());
			ownerElement = doc.select(".panel-body").first().child(1).child(6).child(1);
			if (ownerElement.children().size() == 0) {
				owner = ownerElement.ownText();
			} else {
				owner = null;
			}
		} catch(NullPointerException npe) {
			website = null;
			votes = Integer.parseInt(doc.select(".panel-body").first().child(1).child(4).child(1).ownText());
			ownerElement = doc.select(".panel-body").first().child(1).child(5).child(1);
			if (ownerElement.children().size() == 0) {
				owner = ownerElement.ownText();
			} else {
				owner = null;
			}
		}
		return new MinelistServer(id, name, address, version, onlinePlayers, maxPlayers, status, website, votes, owner);
	}
	
	/**
	 * Used when you want to get a MinelistServer instance with a rank
	 * @param rank The rank which you want to get the server with
	 * @return A MinelistServer instance with the rank
	 * @throws IOException if it can't connect to the Internet etc.
	 */
	public static MinelistServer getServerByRank(int rank) throws IOException {
		if (rank < 1) {
			return null;
		}
		int page = (rank - 1) / 10 + 1;
		rank %= 10;
		if (rank == 0) rank = 10;
		Document doc = Jsoup.connect("https://minelist.kr/servers?page=" + page).userAgent("Mozilla").timeout(10000).get();
		String id;
		try {
		id = doc.select("#server:not(.ml-premium-server)").get(rank - 1).parent().attr("href").substring(9);
		} catch(IndexOutOfBoundsException ioobe) {
			return null;
		}
		return Minelist_API.getServerByAddress(id);	
	}
	
	/**
	 * Used when you want to search a server
	 * @param search The method will search with the parameter string
	 * @return A MinelistServer instance
	 * @throws IOException if it can't connect to the Internet etc.
	 */
	public static MinelistServer getServerBySearch(String search) throws IOException {
		Document doc = Jsoup.connect("https://cse.google.com/cse?cx=001654289243094593526:ndmxr8xyff0&q=" + search).userAgent("Mozilla").get();
		String id;
		try {
		id = doc.select("a.l").first().attr("href").substring(28);
		} catch(NullPointerException npe) {
			return null;
		}
		return Minelist_API.getServer(id);
	}
}
