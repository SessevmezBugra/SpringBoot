package com.javaegitimleri.petclinic;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="petclinic")
public class PetClinicProperties {
	private boolean displayOwnersWidthPets = false;

	public boolean isDisplayOwnersWidthPets() {
		return displayOwnersWidthPets;
	}

	public void setDisplayOwnersWidthPets(boolean displayOwnersWidthPets) {
		this.displayOwnersWidthPets = displayOwnersWidthPets;
	}
}
