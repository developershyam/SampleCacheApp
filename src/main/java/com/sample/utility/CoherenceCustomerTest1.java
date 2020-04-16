package com.sample.utility;

import com.sample.model.Customer;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceCustomerTest1 {

	public static void main(String args[]) {
		Customer cust = new Customer("shyam", "pareek", "Pune", "abc@gmail.com", "12345", 7890);

		NamedCache cache = CacheFactory.getCache("customers");
		cache.put(1, cust);
		System.out.println("Cache:" + cache.size());

		CacheFactory.shutdown();

	}
}
