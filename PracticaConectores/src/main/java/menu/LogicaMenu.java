package menu;

import java.util.Scanner;

import logicaTablas.AdministrarScript;

public class LogicaMenu {
	public static int logicaMenuPrincipal() {
		Scanner sc = new Scanner(System.in);
		System.out.print("[>]: ");
		return Integer.parseInt(sc.nextLine());
	}

	public static int logicModificarTablas() {
		Scanner sc = new Scanner(System.in);
		System.out.print("[>]: ");
		return Integer.parseInt(sc.nextLine());
	}	
}
