/**package com.sample.coherence;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// @Component
public class CoherenceCacheUpdater implements Runnable {

	@Autowired
	CoherenceCacheService coherenceCacheService;

	@PostConstruct
	public void run() {

		System.out.println("=================================> run ");
		System.out.println("=================================> run ");
		System.out.println("=================================> run ");
		System.out.println("=================================> run ");
		System.out.println("=================================> run ");
		// New User are created...
		// Entries which will be inserted via first member of the cluster so
		// before the project is built
		// in order to deploy first member of the cluster, this code block
		// should be opened...

		// Entries are added to cache...
		coherenceCacheService.addToCache("user1", "Shyam-1");

		// //New User are created...
		// //Entries which will be inserted via second member of the cluster so
		// before the project is built
		// // in order to deploy second member of the cluster, this code block
		// should be opened...
		// getUser().setName("Clint");
		// getUser().setSurname("Eastwood");
		//
		// //Entries are added to cache...
		// getCacheService().addToCache("user2", getUser());

		// Cache Entries are printed...
		printCacheEntries();
	}

	private void printCacheEntries() {
		Collection<String> userCollection = null;
		try {
			while (true) {
				userCollection = (Collection<String>) coherenceCacheService.getCache().values();
				for (String name : userCollection) {
					System.out.println("Cache Content : " + name);
				}
				Thread.sleep(10000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
**/