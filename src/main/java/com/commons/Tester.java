package com.commons;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tester {

	private Set<Integer> leakingSet = new HashSet<Integer>();

	public static void main(String[] args) {
		new Tester().start();
	}

	private void start() {
		int i = 0;
		try {
			ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
			Testclass testclass = (Testclass) app.getBean("testclass");
			System.out.println("start");
			for (; i < 1000000; i++) {
				Scopetest proxy = testclass.getScopetest();
				leakingSet.add(proxy.uniqueCode());
			}
			System.out.println("size: " + leakingSet.size());
		} catch (Throwable t) {
			System.err.println("stopped at " + i + " with " + t.getLocalizedMessage());
		}
	}

}
