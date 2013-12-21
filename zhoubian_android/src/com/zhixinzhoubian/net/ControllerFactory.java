package com.zhixinzhoubian.net;


public class ControllerFactory {

	private static IRequestController controller = null;

	public static IRequestController getRequestController() {
		if (controller == null) {
			controller = ControllerImpl.getInstance();
		}
		return controller;
	}
}
