package test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import util.Context;
import metier.*;

public class Test {

	public static void main(String[] args) {
		/*
		Joueur joueur = new Joueur("admin","admin");
		joueur = (Joueur) Context.getInstance().getDaoCpt().insert(joueur);
		
		Attraction attraction =new Attraction("attract",166498,1658,6,315,664,648);
		Boutique boutique = new Boutique("bout",165,197,16,648,54,46,64);
		Parc parc = new Parc(joueur,"yololo2",2501,0,25946.02,Difficulte.Facile);
		
		parc = Context.getInstance().getDaoP().insert(parc);
		attraction = Context.getInstance().getDaoA().insert(attraction);
		boutique = Context.getInstance().getDaoB().insert(boutique);
		
		Achat a1 =new Achat(attraction,0,"attraction",parc);
		Achat a2 =new Achat(boutique,0,"boutique",parc);
		
		a1 = Context.getInstance().getDaoAc().insert(a1);
		a2 = Context.getInstance().getDaoAc().insert(a2);
		
		
		System.out.println(Context.getInstance().getDaoAc().findByType("attraction",parc));
		*/
		Menu.menuPrincipal();
		Context.getInstance().closeEmf();
	}

}
