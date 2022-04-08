package fr.fms.business;

import java.util.ArrayList;
import fr.fms.entities.Account;
import fr.fms.entities.Transaction;
import fr.fms.exception.OverDrafException;

/**
 * @author El babili - 2022
 * @since 1.0
 * Interface qui représente la couche métier de l'application bancaire, reprenant fonctionnalités du doc de spécifications fonctionnelles 
 */

public interface IBankBusiness {
	public void addAccount(Account account);							//ajoute un compte associé à un client à notre banque
	public Account consultAccount(long accountId);						//renvoi le compte correspondant à l'id 
	public void pay(long accountId, double amount);						//faire un versement sur un compte 
	public boolean withdraw(long accountId, double amount) throws OverDrafException;				//faire un retrait sur un compte
	public void transfert(long accIdSrc, long accIdDest, double amount) throws OverDrafException;	//faire un virement d'un compte source vers destination
	public ArrayList<Transaction> listTransactions(long accountId); 	//renvoi la liste des opérations sur un compte donné
}
