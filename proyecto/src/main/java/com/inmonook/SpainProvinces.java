package com.inmonook;

public enum SpainProvinces {
	LA_CORUGNA("La Coruña"), OURENSE("Ourense"), ASTURIAS("Asturias"), CANTABRIA("Cantabria"), ALAVA("Álava"), 
	NAVARRA("Navarra"), RIOJA("La Rioja"), ZARAGOZA("Zaragoza"), BARCELONA("Bracelona"), MADRID("Madrid"), 
	VALENCIA("Valencia"), BALEARES("Islas Baleares"), MALAGA("Málaga"), GRANADA("Granada"), JAEN("Jaén"), CEUTA("Ceuta"),
	MELILLA("Melilla"), LAS_PALMAS("Las Palmas"), AVILA("Ávila"), ALBACETE("Albacete");

	private SpainProvinces(final String spainProvince) {
		this.province = spainProvince;
	}
	
	private final String province;
	
	public String getValue() {
		return province;
	}
	
	@Override
	public String toString() {
		return province;
	}
}
