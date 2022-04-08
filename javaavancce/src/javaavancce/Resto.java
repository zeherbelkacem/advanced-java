package javaavancce;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import exception.PositiveIntegerException;

public class Resto {

	public static void main(String[] args) throws PositiveIntegerException {

		Scanner scanner = new Scanner(System.in);
		String[] courseChoice = new String[5];
		String orderSummary = "";
		String menuresponse = "";

		// Partie choisie pour l'exercice 1.2 d'ADVANCED JAVA
		// begin exo 1.2
		menuresponse = getPositiveInput(scanner, "Bonjour, combien de menus souhaitez-vous ?");
		// end exo 1.2

		System.out.println(menuresponse);
		for (

				int i = 0; i < Integer.parseInt(menuresponse); i++) {
			System.out.println("Commande numéro " + (i + 1));

			// Starter coice
			System.out.println("Choix entrée ");
			String choice = getPositiveInput(scanner, "[1 - SALADE] [2 - SOUPE] [3 - QUICHE] [4 - AUCUNE]\n"
					+ "Que souhaitez-vous comme plat ? [saisir le chiffre correspondant]");
			courseChoice[0] = choiceOfStarter(choice);

			// main course choice
			System.out.println("Choix du plat ");
			choice = getPositiveInput(scanner,
					"[1 - POULET] [2 - BOEUF] [3 - POISSON] [4 - VEGETARIEN]  [5 - VEGAN] [6 - AUCUN]\n "
							+ "Que souhaitez-vous comme entree ? [saisir le chiffre correspondant]");
			courseChoice[1] = choiceOfCourse(choice);

			// Accompaniement choice
			System.out.println("Choix accompagnements ");
			choice = getPositiveInput(scanner, "[1 - RIZ] [2 - PATES] [3 - FRITES] [4 - LEGUMES]  [5 - AUCUN]\n0 "
					+ "Que souhaitez-vous comme plat ? [saisir le chiffre correspondant]");
			courseChoice[2] = choiceOfAccompaniement(choice);

			// Drinks choice
			System.out.println("Choix boissons ");
			choice = getPositiveInput(scanner, "[1 - EAU PLATE] [2 - EAU GAZEUSE] [3 - SODA] [4 - VIN]  [5 - AUCUN]\n "
					+ "Que souhaitez-vous comme plat ? [saisir le chiffre correspondant]");
			courseChoice[3] = choiceOfDrink(choice);

			// Desserts choice
			System.out.println("Choix desserts ");
			choice = getPositiveInput(scanner,
					"[1 - TARTE MAISON] [2 - MOUSSE AU CHOCOLAT] [3 - TIRAMISU]  [4 - AUCUN]\n"
							+ "Que souhaitez-vous comme plat ? [saisir le chiffre correspondant]");
			courseChoice[4] = choiceOfDessert(choice);

			// Enregistrer la commande dans un fichier
			int startSemicolon = 0; // start to put the semicolon after the valid courseChoice array first element

			try {
				File file = new File("order.txt");
				FileWriter fr;
				if (i == 0) // vider le contenu ancien le fichier à la prise de commande
					fr = new FileWriter(file, false);
				else // remplir le fichier en fonction de nombre de commnades (indice i)
					fr = new FileWriter(file, true);

				BufferedWriter br = new BufferedWriter(fr);
				br.write("\n*************** Resumé de la commande " + (i + 1) + "***************\n\n");
				for (int j = 0; j < courseChoice.length; j++) {
					if (!courseChoice[j].equals("AUCUN") && !courseChoice[j].equals("AUCUNE")) {
						br.write(courseChoice[j]);
						br.write("\n");
					}
				}
				br.close();
				fr.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			// Order summary
			System.out.println("Resum� de la commande " + (i + 1));
			System.out.print("[");
			for (int j = 0; j < courseChoice.length; j++) {
				if (!courseChoice[j].equals("AUCUN") && !courseChoice[j].equals("AUCUNE")) {
					if (startSemicolon != 0) {
						System.out.print(", ");
					}
					startSemicolon++;
					System.out.print(courseChoice[j]);
				}
			}
			System.out.println("]");
		}
		scanner.close();
	}

	// begin exo 1.2
	/**
	 * 
	 * @param scanner
	 * @param string
	 * @return
	 */
	private static String getPositiveInput(Scanner scanner, String string) {
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
		return menuresponse;
	}
	// end exo 1.2

	/*
	 * 
	 */
	private static String choiceOfCourse(String choice) {
		String mainCourseChoice = " ";
		switch (choice) {
		case "1":
			mainCourseChoice = "POULET";
			break;
		case "2":
			mainCourseChoice = "BOEUF";
			break;
		case "3":
			mainCourseChoice = "POISSON";
			break;
		case "4":
			mainCourseChoice = "VEGETARIEN";
			break;
		case "5":
			mainCourseChoice = "VEGAN";
			break;
		case "6":
			mainCourseChoice = "AUCUN";
			break;
		default:
			break;
		}
		return mainCourseChoice;
	}

	/*
	 * 
	 */
	private static String choiceOfAccompaniement(String choice) {
		String accompaniementChoice = " ";
		switch (choice) {
		case "1":
			accompaniementChoice = "RIZ";
			break;
		case "2":
			accompaniementChoice = "PATES";
			break;
		case "3":
			accompaniementChoice = "FRITES";
			break;
		case "4":
			accompaniementChoice = "LEGUMES";
			break;
		case "5":
			accompaniementChoice = "AUCUN";
			break;
		default:
			break;
		}
		return accompaniementChoice;
	}

	/*
	 * 
	 */
	private static String choiceOfDrink(String choice) {

		String drinkChoice = " ";
		switch (choice) {
		case "1":
			drinkChoice = "EAU PLATE";
			break;
		case "2":
			drinkChoice = "EAU GAZEUSE";
			break;
		case "3":
			drinkChoice = "SODA";
			break;
		case "4":
			drinkChoice = "VIN";
			break;
		case "5":
			drinkChoice = "AUCUN";
			break;
		default:
			break;
		}
		return drinkChoice;
	}

	/*
	 * 
	 */
	private static String choiceOfDessert(String choice) {
		String dessertChoice = " ";

		switch (choice) {
		case "1":
			dessertChoice = "TARTE MAISON";
			break;
		case "2":
			dessertChoice = "MOUSSE AU CHOCOLAT";
			break;
		case "3":
			dessertChoice = "TIRMISU";
			break;
		case "4":
			dessertChoice = "AUCUN";
			break;
		default:
			break;
		}
		return dessertChoice;
	}

	/*
	 * 
	 */
	private static String choiceOfStarter(String choice) {
		String starterDishChoice = " ";
		switch (choice) {
		case "1":
			starterDishChoice = "SALADE";
			break;
		case "2":
			starterDishChoice = "SOUPE";
			break;
		case "3":
			starterDishChoice = "QUICHE";
			break;
		case "4":
			starterDishChoice = "AUCUNE";
			break;
		default:
			break;
		}
		return starterDishChoice;
	}

}
