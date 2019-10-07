package streams;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

/** 
 * Example
 * 
 * @author asanchez
 *
 */

public class CountLongWords {
	public static void main(String[] args) throws IOException {
		String contents = new String(Files.readAllBytes(
				Paths.get("alice.txt")), StandardCharsets.UTF_8); // Read file into string

		List<String> words = Arrays.asList(contents.split("\\PL+"));
		// Split into words; nonletters are delimiters.

		long count = 0;
		for (String s : words) {
			if (s.length() > 9) {
				count++;
			}
		}
		System.out.println(count);

		count = words.stream()
				.filter(w -> w.length() > 9)
				.count();
		System.out.println(count);

		count = words.parallelStream()
				.filter(w -> w.length() > 9)
				.count();
		System.out.println(count);

	}
}
