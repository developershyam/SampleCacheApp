package com.sample.service;

import java.math.BigDecimal;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

	@Cacheable(value = "squareCache", key = "#number", condition = "#number>10")
	public BigDecimal cacheableSquare(Long number) {
		BigDecimal square = BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(number));
		System.out.println("cacheableSquare....");
		return square;
	}

	@CacheEvict(value = "squareCache", allEntries = true)
	public String cacheEvictSquare(Long number) {

		System.out.println("cacheEvictSquare....");
		return "Cleared";
	}

	@CachePut(value = "squareCache", key = "#number")
	public BigDecimal cachePutSquare(Long number) {

		BigDecimal square = BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(number+1));
		System.out.println("cachePutSquare...updated.");
		return square;
	}
}
