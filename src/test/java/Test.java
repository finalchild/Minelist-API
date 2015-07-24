import java.io.IOException;

import io.github.fcreloaded.minelist_api.MinelistServer;
import io.github.fcreloaded.minelist_api.Minelist_API;

/**
 * Used for testing purposes. You should not include this class in any programs.
 */
public class Test {
	public static void main(String args[]) {
		try {
			MinelistServer server = Minelist_API.getServerByRank(20);
			System.out.println(server.getName());
			System.out.println(server.getVersion());
			System.out.println(server.getOnlinePlayers());
			System.out.println(server.getMaxPlayers());
			System.out.println(server.isOnline());
			System.out.println(server.getWebsite());
			System.out.println(server.getVotes());
			System.out.println(server.getOwner());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}