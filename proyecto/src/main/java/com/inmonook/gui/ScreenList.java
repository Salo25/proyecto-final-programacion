package com.inmonook.gui;

public enum ScreenList {
	MAIN_SCREEN(0), SIGN_IN_SCREEN(1), PROPERTIES_SCREEN(2);
	
	private final int screen;
	
	ScreenList(int numScreen) {
		this.screen = numScreen;
	}
	
	public int getValue() {
		return this.screen;
	}
}
