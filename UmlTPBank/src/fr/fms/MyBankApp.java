/**
 * Version 1.0 d'une appli bancaire simplifiée offrant la possibilitée de créer des clients, des comptes bancaires associés et des opérations ou
 * transactions bancaires sur ceux-ci telles que : versement, retrait ou virement 
 * + permet d'afficher l'historique des transactions sur un compte
 * + la gestion des cas particuliers est rudimentaire ici puisque la notion d'exception n'a pas encore été abordée
 * 
 * @author El babili - 2022
 * 
 */

package fr.fms;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.SwingConstants;

import fr.fms.business.IBankBusinessImpl;
import fr.fms.entities.Account;
import fr.fms.entities.Current;
import fr.fms.entities.Customer;
import fr.fms.entities.Saving;
import fr.fms.entities.Transaction;
import fr.fms.exception.OverDrafException;

public class MyBankApp {
	public static void main(String[] args) throws OverDrafException {

		// Flux entrée /sortie
		Scanner scan = new Scanner(System.in);

		// représente l'activité de notre banque
		IBankBusinessImpl bankJob = new IBankBusinessImpl();

		System.out.println("création de 2 comptes bancaires");
		Customer robert = new Customer(1, "dupont", "robert", "robert.dupont@xmail.com");
		Customer julie = new Customer(2, "jolie", "julie", "julie.jolie@xmail.com");
		Current firstAccount = new Current(100200300, new Date(), 1500, 200, robert);
		Saving secondAccount = new Saving(200300400, new Date(), 2000, 5.5, julie);

		System.out.println("Affichage des données des 2 comptes");
		System.out.println(firstAccount);
		System.out.println(secondAccount);

		System.out.println("notre banquier ajoute les 2 comptes");
		bankJob.addAccount(firstAccount);
		bankJob.addAccount(secondAccount);

		// banquier ou client
		bankJob.pay(firstAccount.getAccountId(), 500); // versement de 500 euros sur le compte de robert
		bankJob.pay(secondAccount.getAccountId(), 1000); // versement de 1000 euros sur le compte de julie

		// banquier ou client
		bankJob.withdraw(100200300, 250); // retrait de 250 euros sur le compte de robert
		bankJob.withdraw(200300400, 400); // retrait de 400 euros sur le compte de julie

		// banquier ou client
		bankJob.transfert(firstAccount.getAccountId(), 200300400, 420); // virement de robert chez julie de 200
		System.out.println("----------------------------------------------------------");
		System.out.println("solde de " + firstAccount.getCustomer().getName() + " : "
				+ bankJob.consultAccount(firstAccount.getAccountId()).getBalance());
		System.out.println("solde de " + secondAccount.getCustomer().getName() + " : "
				+ bankJob.consultAccount(secondAccount.getAccountId()).getBalance());
		System.out.println("----------------------------------------------------------");
		bankJob.consultAccount(111111); // test du compte inexistant
		bankJob.withdraw(100200300, 100); // test capacité retrait dépassée
		bankJob.transfert(100200300, 100200300, 55); // test virement sur le même compte
		// banquier
		bankJob.addAccount(firstAccount); // test rajout du même compte au même client
		bankJob.addAccount(new Current(300400500, new Date(), 750, 150, julie)); // ajout nouveau compte à Julie
		System.out
				.println("\n-----------------------Liste des comptes de ma banque-----------------------------------");
		for (Account acc : bankJob.listAccounts())
			System.out.println(acc);
		System.out.println("\n-----------------------Liste des comptes de julie-----------------------------------\n");
		for (Account acc : julie.getListAccounts()) {
			System.out.println(acc);
		}

		// banquier ou client
		System.out.println(
				"\n-------------------liste des transactions de l'unique compte de robert------------------------");
		for (Transaction trans : bankJob.listTransactions(100200300))
			System.out.println(trans);
		System.out.println(
				"-------------------liste des transactions du compte N° 200300400 de Julie------------------------\n");
		for (Transaction trans : bankJob.listTransactions(200300400))
			System.out.println(trans);

		// Recherche d'un numero de compte
		System.out.println("\n\n------------------------------- ADVANCED JAVA ---------------------------------\n");
		
		long accountNumber = getPositiveInput(scan, "Entrez le numéro de compte que vous desirez:");
		Account foundAccount = bankJob.consultAccount(accountNumber);
		System.out.print("Bienvenue " + foundAccount.getCustomer().getName() + ", que souhaitez-vous faire ? ");
		int response = 0;
		while (response != 6) {
			// choix d'operation
			response = (int) getPositiveInput(scan, "Tapez le numero coorespondant:\n"
					+ "1:Versement  -  2:Retrait  -  3:Virement  -  4:Information sur compte  -  5:Afficher les opérations  -  6:Quiter");
			switch (response) {
			case 1:
				double anount = getPositiveInput1(scan, "Entrez le montant à verser sur le compte");
				bankJob.pay(accountNumber, anount);
				break;

			default:
				break;
			}
		}

		System.out.println("au revoir");

	}

	// begin exo 1.2
	/**
	 * 
	 * @param scanner
	 * @param string
	 * @return
	 */
	private static long getPositiveInput(Scanner scanner, String string) {
		String menuresponse = "";
		while (true) {
			System.out.println(string);
			try {
				menuresponse = scanner.next();
				if (Integer.parseInt(menuresponse) > 0)
					break;
				System.out.println("La réponse doit être numerique et positive > 0");
			} catch (NumberFormatException e) {
				System.out.println("La réponse doit être numerique et positive > 0");
			}
		}
		return Long.parseLong(menuresponse);
	}
	/**
	 * 
	 * @param scanner
	 * @param string
	 * @return
	 */
	private static double getPositiveInput1(Scanner scanner, String string) {
		String menuresponse = "";
		while (true) {
			System.out.println(string);
			try {
				menuresponse = scanner.next();
				if (Double.parseDouble(menuresponse) > 0)
					break;
				System.out.println("La réponse doit être numerique et positive > 0");
			} catch (NumberFormatException e) {
				System.out.println("La réponse doit être numerique et positive > 0");
			}
		}
		return Double.parseDouble(menuresponse);
	}
	// end exo 1.2

//		int response = 0;
//		while (response != 6) {
//			System.out.println("Tapez le numero coorespondant:");
//			System.out.println(
//					"1:Versement  -  2:Retrait  -  3:Virement  -  4:Information sur compte  -  5:Afficher les opérations  -  6:Quiter");
//			try {
//				response = scan.nextInt();
//				switch (response) {
//				case 1:
//					System.out.println("Entrez le montant à verser sur le compte");
//					try {
//						double amount = scan.nextDouble();
//						bankJob.pay(accountNumber, amount);
//					} catch (InputMismatchException e) {
//						System.out.println("saisie incorrect");
//						e.printStackTrace();
//					}
//					
//					break;
//				case 2:
//					System.out.println("Entrez le montant à retirer");
//					System.out.println(foundAccount.getBalance()+" "+foundAccount.getAccountId());
//					while (!scan.hasNextDouble()) {
//						scan.next();
//						double withdrawAmount = scan.nextDouble();
//						
//						bankJob.withdraw(accountNumber, withdrawAmount);
//						
//					}
//					break;
//				default:
//					break;
//				}
//			} catch (InputMismatchException e) {
//				System.out.println("Saisie incorrect: nombre entier seulement");
//				e.printStackTrace();
//			}
//
//		}

}
