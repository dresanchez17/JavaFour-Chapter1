package streams;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

/**
 * Example of creating different Streams
 * @author asanchez
 *
 */


public class CreatingStreams {
	public static <T> void show(String title, Stream<T> stream) {
		final int SIZE = 10;
		List<T> firstElements = stream
				.limit(SIZE + 1)
				.collect(Collectors.toList());
		System.out.print(title + ": ");
		for (int i = 0; i < firstElements.size(); i++) {
			if (i > 0) System.out.print(", ");
			if (i < SIZE) System.out.print(firstElements.get(i));
			else System.out.print(". . .");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("short_alice.txt");
		String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
		
		Stream<String> words = Stream.of(contents.split("\\PL+"));
		show("words", words);
		Stream<String> songs = Stream.of("gently", "down", "the", "stream");
		show("songs", songs);
		Stream<String> silence = Stream.empty();
		show("silence", silence);
		
		Stream<String> echos = Stream.generate(() -> "Echo");
		show("echos", echos);
		
		Stream<Double> randoms = Stream.generate(Math::random);
		show("randoms", randoms);
		
		Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, 
				n -> n.add(BigInteger.ONE));
		show("integers", integers);
		
		Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
		show("wordsAnotherWay", wordsAnotherWay);
		
		try(Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
			show("lines", lines);
		}
		
		Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
		Stream<Path> rootDirectories = StreamSupport.stream(iterable.spliterator(), false);
		show("rootDirectories", rootDirectories);
		
		
		
		
		
	}
}
