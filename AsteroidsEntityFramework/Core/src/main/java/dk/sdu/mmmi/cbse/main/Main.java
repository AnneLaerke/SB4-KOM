package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	
	public static void main(String[] args) {

		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();

		cfg.setTitle("Asteroids");
		cfg.setResizable(false);
		cfg.setWindowedMode(1200,1000);

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.scan("dk.sdu.mmmi.cbse.main");
		applicationContext.refresh();

		new Lwjgl3Application((ApplicationListener) applicationContext.getBean("game"), cfg);
	}
}
