package it.polimi.ingsw.gui;

public class Risposta {
	private static Risposta risposta;
	private static String nome;

	public static Risposta getRisposta(){
		if (risposta == null){
			risposta = new Risposta();
		}
		return risposta;
	}
	
	private Risposta() {
		
	}
	
	public static String getNome() {
		while(nome == null){
			System.out.println(nome);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nome;
	}
	
	public static void setNome(String nome) {
		Risposta.nome = nome;
	}
	
}