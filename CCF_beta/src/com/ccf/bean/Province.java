package com.ccf.bean;

public class Province {
	private int id;
	private String name;
	private String cnName;
	private String cnShortName;
	private String capital;
	private int cityAmount;
	private int brightness;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getCnShortName() {
		return cnShortName;
	}
	public void setCnShortName(String cnShortName) {
		this.cnShortName = cnShortName;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public int getCityAmount() {
		return cityAmount;
	}
	public void setCityAmount(int cityAmount) {
		this.cityAmount = cityAmount;
	}
	public int getBrightness() {
		return brightness;
	}
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	
}
