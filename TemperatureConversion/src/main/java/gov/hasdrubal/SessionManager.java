package gov.hasdrubal;

import java.util.List;

public class SessionManager {

	protected List<Conversion> conversions;
	private Conversion currentConversion;

	public Conversion getCurrentConversion() {
		return currentConversion;
	}

	public void setCurrentConversion(Conversion currentConversion) {
		this.currentConversion = currentConversion;
	}

	public List<Conversion> getConversions() {
		return conversions;
	}

	public void setConversions(List<Conversion> conversions) {
		this.conversions = conversions;
	}
	
	@Override
	public String toString() {
		return "SessionManager [conversions=" + conversions + "]";
	}
}
