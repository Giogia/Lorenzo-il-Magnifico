package it.polimi.ingsw.manager;

import java.net.Socket;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.view.CliRmi;

public class User {
	private String username;
	private Player player;
	private Socket socket;
	private ConnectionManagerSocketServer connectionManagerSocketServer;
	private CliRmi cliRmi;
	
	
	public User(Socket socket) {
		this.socket = socket;
	}
	
	public User(CliRmi cliRmi) {
		this.cliRmi = cliRmi;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public ConnectionManagerSocketServer getConnectionManagerSocketServer() {
		return connectionManagerSocketServer;
	}
	public void setConnectionManagerSocketServer(ConnectionManagerSocketServer connectionManagerSocketServer) {
		this.connectionManagerSocketServer = connectionManagerSocketServer;
	}
	public CliRmi getCliRmi() {
		return cliRmi;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setCliRmi(CliRmi cliRmi) {
		this.cliRmi = cliRmi;
	}
}
