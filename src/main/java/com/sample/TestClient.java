package com.sample;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.InvocationService;
import com.tangosol.net.NamedCache;

public class TestClient {
	public static void main(String[] asArgs) throws Throwable {
		System.out.println(".................> 1");
		NamedCache cache = CacheFactory.getCache("dist-extend");
		System.out.println(".................> 2");
		Integer IValue = (Integer) cache.get("key");
		System.out.println(".................> 3 =====> "+IValue);
		if (IValue == null) {
			IValue = new Integer(1);
		} else {
			IValue = new Integer(IValue.intValue() + 1);
		}
		System.out.println(".................> 4 =====> "+IValue);
		cache.put("key", IValue);
		Object abc = CacheFactory.getCache("dist-extend").get("key");
		System.out.println("abc: "+abc);
		System.out.println(".................> 5");
		InvocationService service = (InvocationService) CacheFactory.getConfigurableCacheFactory()
				.ensureService("ExtendTcpInvocationService");
		System.out.println(".................> 6");
		
		System.out.println("===> "+ service.isRunning());
		System.out.println("===> "+ service.getCluster().getOldestMember());
		System.out.println("===> "+service.getCluster().getClusterName());
		/*Map map = service.query(new AbstractInvocable() {
			public void run() {
				setResult(CacheFactory.getCache("dist-extend").get("key"));
			}
		}, null);
		System.out.println(".................> 7");
		Integer IValue1 = (Integer) map.get(service.getCluster().getLocalMember());
		System.out.print("The value of the key is " + IValue1);*/
	}
}
