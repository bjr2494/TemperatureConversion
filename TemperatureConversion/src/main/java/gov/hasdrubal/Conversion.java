package gov.hasdrubal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Conversion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Temperature temperatureStart;
	@Enumerated(EnumType.STRING)
	private Scale scaleStart;
	@ManyToOne
	private Temperature temperatureEnd;
	@Enumerated(EnumType.STRING)
	private Scale scaleEnd;
	
	protected boolean hasBothScales() {
		if (scaleStart != null && scaleEnd != null) return true;
		
		else return false;
	}
	
	public Temperature getTemperatureStart() {
		return temperatureStart;
	}
	public void setTemperatureStart(Temperature temperatureStart) {
		this.temperatureStart = temperatureStart;
	}
	public Scale getScaleStart() {
		return scaleStart;
	}
	public void setScaleStart(Scale scaleStart) {
		this.scaleStart = scaleStart;
	}
	public Temperature getTemperatureEnd() {
		return temperatureEnd;
	}
	public void setTemperatureEnd(Temperature temperatureEnd) {
		this.temperatureEnd = temperatureEnd;
	}
	public Scale getScaleEnd() {
		return scaleEnd;
	}
	public void setScaleEnd(Scale scaleEnd) {
		this.scaleEnd = scaleEnd;
	}

	@Override
	public String toString() {
		return "Conversion [id=" + id + ", temperatureStart=" + temperatureStart + ", scaleStart=" + scaleStart
				+ ", temperatureEnd=" + temperatureEnd + ", scaleEnd=" + scaleEnd + ", hasBothScales()=" + hasBothScales()
				+ ", getTemperatureStart()=" + getTemperatureStart() + ", getScaleStart()="
				+ getScaleStart() + ", getTemperatureEnd()=" + getTemperatureEnd() + ", getScaleEnd()=" + getScaleEnd()
				+ "]";
	}
}
