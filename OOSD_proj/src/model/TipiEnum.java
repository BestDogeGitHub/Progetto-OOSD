package model;

public class TipiEnum {
	public enum TipoStoria
	{
		nullo, InserimentoPubb, CancellazionePubb, ModificaPubb, ApprovazioneRecensione, InserimentoRecensione,
		eliminazioneRecensione, InserimentoLike, EliminazioneLike;
	}
	
	public enum RuoloUtente
	{
		nullo, Passivo, Attivo, Moderatore, Amministratore, SuperAmministratore;
	}
	
	public enum tipoRicerca
	{
		Autore, ISBN, Titolo, ParolaChiave;
	}
	
	public enum tipoRecensioni
	{
		UtenteConnesso, ProfiloUtente, NonApprovate, Pubblicazione;
	}
}