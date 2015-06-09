package net.mikeharris.example.etl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	 
	  public static void main(String[] args) {
		App obj = new App();
		obj.run();
	  }
	 
	  private void run() {
	 
		String[] springConfig = { "camel-context.xml" };
	 
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
	 
	
		
		System.out.println("Done");
	  }
	  }