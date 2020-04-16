package com.sample.service;

import java.math.BigDecimal;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceWithoutRepo {

	@Cacheable(value = "squareCache", key = "#number")
	public BigDecimal cacheableSquare(String number) {
		BigDecimal square = BigDecimal.valueOf(Long.valueOf(number)).multiply(BigDecimal.valueOf(Long.valueOf(number)));
		System.out.println("cacheableSquare....");
		return square;
	}

	@CachePut(value = "squareCache", key = "#number")
	public BigDecimal cachePutSquare(String number) {

		BigDecimal square = BigDecimal.valueOf(Long.valueOf(number)).multiply(BigDecimal.valueOf(Long.valueOf(number)));
		System.out.println("cachePutSquare...updated.");
		return square;
	}
	
	@CacheEvict(value = "squareCache", key = "#number")
	public String cacheEvictSquare(String number) {

		System.out.println("cacheEvictSquare....");
		return "Cleared";
	}
	
	@CacheEvict(value = "squareCache", allEntries = true)
	public String cacheEvictSquare() {

		System.out.println("cacheEvictSquare....all");
		return "Cleared All";
	}
}
