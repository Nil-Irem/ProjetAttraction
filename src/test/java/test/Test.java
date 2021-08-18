package test;

import javax.persistence.EntityManager;


import util.Context;
import metier.Compte;

public class Test {

	public static void main(String[] args) {
				
		Menu.menuPrincipal();
		Context.getInstance().closeEmf();
	}

}
