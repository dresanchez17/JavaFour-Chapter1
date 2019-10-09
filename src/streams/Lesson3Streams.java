package streams;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class Lesson3Streams {
	public static void main(String[] args) throws IOException {
		
		String regex = "0{8}[0-9A-Fa-f]+";
		
		String contents = new String(Files.readAllBytes(
				Paths.get("JobResult_124432.txt")), StandardCharsets.UTF_8);
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(contents);
		
		while(matcher.find()) {
			String match = matcher.group();
			System.out.println(match);
		}




	}
}
