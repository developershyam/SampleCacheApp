package com.sample.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sample.service.CacheService;

@RestController
public class CacheController {

	@Autowired
	private CacheService cacheService;

	@GetMapping(path = "/cacheableSquare/{number}")
	public BigDecimal cacheSquareCalculated(@PathVariable Long number) {

		return cacheService.cacheableSquare(number);
	}
	
	@GetMapping(path = "/cacheEvictSquare/{number}")
	public String cacheEvictSquare(@PathVariable Long number) {

		return cacheService.cacheEvictSquare(number);
	}
	
	@GetMapping(path = "/cachePutSquare/{number}")
	public BigDecimal cachePutSquare(@PathVariable Long number) {

		return cacheService.cachePutSquare(number);
	}

	
}
