package com.example.BackendConsumer;

public class ConsumerApplication {

	public static void main(String[] args) {
		ApiConsumer apic = new ApiConsumer();
		System.out.println(apic.getUserList());
		
	}

}
