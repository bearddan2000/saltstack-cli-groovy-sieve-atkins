#!/usr/bin/env groovy

class AtkinSieve {
	def SieveOfAtkin(int limit)
	{
    String output = "";

		// 2 and 3 are known to be prime
		if (limit > 2)
			output += 2 + " ";

		if (limit > 3)
			output += 3 + " ";

		// Initialise the sieve array with false values

		def sieve = [] as Vector;

		for (int i = 0; i < limit; i++)
			sieve.add(false);

		for (int x = 1; x * x < limit; x++) {
			for (int y = 1; y * y < limit; y++) {

				// Main part of Sieve of Atkin
				int n = (4 * x * x) + (y * y);
				if (n <= limit && (n % 12 == 1 || n % 12 == 5))

					sieve.set(n, sieve.get(n) ^ true);

				n = (3 * x * x) + (y * y);
				if (n <= limit && n % 12 == 7)
					sieve.set(n, sieve.get(n) ^ true);

				n = (3 * x * x) - (y * y);
				if (x > y && n <= limit && n % 12 == 11)
					sieve.set(n, sieve.get(n) ^ true);
			}
		}

		// Mark all multiples of squares as
		// non-prime
		for (int r = 5; r * r < limit; r++) {
			if (sieve.get(r)) {
				for (int i = r * r; i < limit;
					i += r * r)
					sieve.set(i, false);
			}
		}

    print "[OUTPUT] " + output
		// Print primes using sieve[]
		for (int a = 5; a < limit; a++)
			if (sieve.get(a))
				print(a + " ");

    println ""
		return 0;
	}

	// Driver code
	def main(String[] args)
	{
		int limit = 20;
    println "[INPUT] " + limit
		SieveOfAtkin(limit);
	}
}
new AtkinSieve().main()
