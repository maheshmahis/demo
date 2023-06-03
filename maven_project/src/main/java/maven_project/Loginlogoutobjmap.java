package maven_project;

import org.openqa.selenium.By;

public interface Loginlogoutobjmap {
	
	public By login_btn = By.id("login-button");
	public By username_field = By.id("user-name");
	public By password_field = By.id("password");
	public By backpack = By.xpath("//div[text()='Sauce Labs Backpack']");
	public By three_lines = By.id("react-burger-menu-btn");
	public By logout_pop = By.id("logout_sidebar_link");
		
	}


