package test;

import util.Context;

public class Test {

	public static void main(String[] args) {
		Menu.menuPrincipal();
		Context.getInstance().closeEmf();
	}

}
