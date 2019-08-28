package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.scene.layout.BorderPane;
import model.Autore;
import model.ParolaChiave;

public class ControllerUtility {
	
	private static BorderPane gui;
	public static BorderPane getGui() { return gui; }
	public static void setGui(BorderPane input) { gui= input; }

	/**
	 * controlla se il titolo è troppo lungo
	 * 
	 * @param titolo
	 * @return torna false se il titolo è troppo lungo, true viceversa
	 */
	public static boolean checkTitolo( String titolo )
	{
		if( titolo.length() > 100 ) return false;
		return true;
	}
	 
	/**
	 * controlla se l'isbn è errato
	 * 
	 * @param ISBN
	 * @return torna false se l'isbn è errato, true altrimenti
	 */
	public static boolean checkISBN( String ISBN )
	{
		if( ISBN.length() == 13 || ISBN.length() == 10) return true;
		return false;
	}
	
	/**
	 * controlla se l'URI è troppo lungo
	 * 
	 * @param URI
	 * @return torna false se l'URI è troppo lungo, true viceversa
	 */
	public static boolean checkURI( String URI )
	{
		if( URI.length() > 2083 ) return false;
		return true;
	}
	
	/**
	 * metodo utilizzato per controllare se la stringa in input ha piu' di 45 caratteri
	 * 
	 * @param input
	 * @return
	 */
	public static boolean check45( String input )
	{
		if( input.length() > 45 ) return false;
		return true;
	}
	
	/**
	 * usato per vedere se il testo in input è troppo lungo
	 * 
	 * @param testo
	 * @return torna false se il testo è troppo lungo,true viceversa
	 */
	public static boolean checkTesto( String testo )
	{
		if( testo.length() > 1500 ) return false;
		return true;
	}

	/**
	 * usato per controllare l'email passata
	 * 
	 * @param email
	 * @return torna false se l'email è errata, true altrimenti
	 */
	public static boolean checkEmail( String email )
	{
		if( email.length() > 254 ) return false;
		if( !(Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}").matcher(email).matches() ) ) return false; 
		return true;
	}
	
	/**
	 * formatta l'input per inserirlo nel database
	 * 
	 * @param input stringa da formattare
	 * @return la stringa formattata
	 */
	public static String formatInput( String input )
	{
		if ( input.length() == 0 ) return null;
		if ( input.length() == 1 ) return input.toUpperCase();
		else return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}
	
	/**
	 * formatta le date che tornano dal database per poterle visualizzare da parte dell'utente
	 * 
	 * @param data la data da formattare 
	 * @return la stringa che rappresenta la data formattata
	 */
	public static String formatLocaDateFromDB(LocalDate data) { return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(data); }
	
	/**
	 * formatta i timestamp che tornano dal database per poterli visualizzare da parte dell'utente
	 * 
	 * @param timestamp il timestamp da visualizzare
	 * @return la stringa che rappresenta il timestamp formattato
	 */
	public static String formatLocaDateTimeFromDB(LocalDateTime timestamp) { return timestamp.format( DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss")); }
	
	/**
	 * controlla se la password è accettabile
	 * 
	 * @param password
	 * @return torna false se la password è troppo corta
	 */
	public static boolean checkPassword( String password )
	{
		if( password.length() < 8 ) return false;
		return true;
	}
	
	/**
	 * data la password di un utente la cripta prima di inserirla nel database
	 * 
	 * @param password
	 * @return
	 */
	public static String hashPassword( String password )
	{
		StringBuilder sb = new StringBuilder();
	    try 
	    {
	    	MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(password.getBytes());
	        byte[] bytes = md.digest();
	        for(int i=0; i< bytes.length ;i++) { sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1)); }
	    }
	    catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
	    return sb.toString();
	}
	
	/**
	 * restituisce una lista di autori come una stringa
	 * @param lista la lista di autori
	 * @return la stringa
	 */
	public static String arrayDiAutoriToString( ArrayList<Autore> lista)
	{
		String autori = "";
		int lunghezza;
		for (Autore a : lista) 
		{ 
			if( a.getCognome() == null ) autori = autori + a.getNome() + " , ";
			else autori = autori + a.getNome() + " " + a.getCognome() + ",";
		}
		lunghezza = autori.length();
		if ( lunghezza > 0 )return autori.substring(0,lunghezza-1);
		else return "";
	}
	
	/**
	 * restituisce una lista di parole chiave come stringa
	 * @param lista la lista di parole chiave
	 * @return la string
	 */
	public static String arrayDiParoleToString( ArrayList<ParolaChiave> lista)
	{
		String parola = "";
		int lunghezza;
		for (ParolaChiave pc : lista) 
		{ 
			parola = parola + pc.getParolaChiave() + " , ";
		}
		lunghezza = parola.length();
		if ( lunghezza > 0 )return parola.substring(0,lunghezza-2);
		else return "";
	}
}