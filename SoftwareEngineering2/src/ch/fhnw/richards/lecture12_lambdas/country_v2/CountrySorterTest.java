package ch.fhnw.richards.lecture12_lambdas.country_v2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class CountrySorterTest {
	List<Country> countries = new ArrayList<>(Arrays.asList(new Country("Schweiz", 41285),
			new Country("Germany", 357021), new Country("France", 675417), new Country("Italy", 301230),
			new Country("Austria", 83858), new Country("Russia", 17098246), new Country("Luxembourg", 2586),
			new Country("Liechtenstein", 160), new Country("United Kingdom", 244820)));

	@Test
	public void test() {
		Collections.sort(countries, new CountryByAreaComparator());
		for (int i = 0; i < countries.size() - 1; i++) {
			assertTrue(countries.get(i).getArea() <= countries.get(i + 1).getArea());
		}
	}
	
	public static class CountryByAreaComparator implements Comparator<Country> {
		@Override
		public int compare(Country o1, Country o2) {
			return (int) (o1.getArea() - o2.getArea());
			//return Double.compare(o1.getArea(), o2.getArea());
		}
	}	
}
