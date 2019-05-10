package com.sample.model;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CustomerTest1 {

public static void main(String args[])
{
Customer cust = new Customer("Ahmet Fuat", "Sungur", "Bahcelievler/Istanbul", "afsungur@gmail.com", "5321231231", 34111);

NamedCache cache = CacheFactory.getCache("customers");
cache.put(1, cust) ;
System.out.println("Cache:"+cache.size());

CacheFactory.shutdown();

}
}

