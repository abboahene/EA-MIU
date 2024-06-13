package config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "application")
public class ConfigValues {

	private String name;
	private String version;

	private Server server;
	private User user;

	private List<String> countries;

	public ConfigValues() {
	}

	public static class Server {
		private String url;
		private String name;

		// Getters and Setters
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Server{\n" +
					"url='" + url + "'\n" +
					"name='" + name + "'\n" +
					"}\n";
		}
	}

	public static class User {
		private String firstname;
		private String lastname;
		private String username;
		private String password;

		// Getters and Setters
		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public String toString() {
			return "User{\n" +
					"firstname='" + firstname + "'\n" +
					"lastname='" + lastname + "'\n" +
					"username='" + username + "'\n" +
					"password='" + password + "'\n" +
					"}\n";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public void print() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "ConfigValues{\n" +
				"name='" + name + "'\n" +
				"version='" + version + "'\n" +
				 server +
				 user +
				"countries=" + countries +
				"\n}";
	}
}
