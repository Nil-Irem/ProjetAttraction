package attraction;

import org.springframework.beans.factory.annotation.Autowired;

import attraction.menu.Menu;

public class AppSpring {
	
	@Autowired
	Menu menu;
	
	
	public void test() {
		
		menu.menuPrincipal();
		
	}

}
