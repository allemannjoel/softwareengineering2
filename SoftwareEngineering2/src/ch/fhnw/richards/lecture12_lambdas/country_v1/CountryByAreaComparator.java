package ch.fhnw.richards.lecture12_lambdas.country_v1;

import java.util.Comparator;

/**
 * @author Dieter Holz
 */
public class CountryByAreaComparator implements Comparator<Country> {
	@Override
	public int compare(Country o1, Country o2) {
		return (int) (o1.getArea() - o2.getArea());
		//return Double.compare(o1.getArea(), o2.getArea());
	}
}
