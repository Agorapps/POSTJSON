package prueba.envioxml.alvaro.postjson.vo;

public class Person {

	private String name;
	private String country;
	private String social;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSocial() {
		return social;
	}

	public void setSocial(String social) {
		this.social = social;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", country=" + country + ", twitter="
				+ social + "]";
	}
	
	
}
