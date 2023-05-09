package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringModuleConfiguration.class);
		
		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();

		cfg.setTitle("Asteroids");
		cfg.setResizable(false);
		cfg.setWindowedMode(1200,1000);

		new Lwjgl3Application(applicationContext.getBean(Game.class), cfg);
	}
}
