package uke6.oppgave1;

import java.util.Stack;

public class ParentesSjekker<T> implements StabelADT<T> {
	private Stack<T> stack;

	public ParentesSjekker() {
		stack = new Stack<T>();
	}

	@Override
	public void push(T newEntry) {
		stack.push(newEntry);
	}

	@Override
	public T pop() {
		return stack.pop();
	}

	@Override
	public T peek() {
		return stack.peek();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public void clear() {
		stack.clear();
	}

	public static boolean erStartParentes(char c) {
		return c == '(' || c == '[' || c == '{';
	}

	public static boolean erSluttParentes(char c) {
		return c == ')' || c == ']' || c == '}';
	}

	public static boolean sjekkParentesPar(char start, char slutt) {
		return (start == '(' && slutt == ')') || (start == '{' && slutt == '}') || (start == '[' && slutt == ']');
	}

	public static boolean sjekkParenteser(String s) {
		ParentesSjekker<Character> stack = new ParentesSjekker<>();
		for (char c : s.toCharArray()) {
			if (erStartParentes(c)) {
				stack.push(c);
			} else if (erSluttParentes(c)) {
				if (stack.isEmpty()) {
					return false;
				}
				char start = stack.pop();
				if (!sjekkParentesPar(start, c)) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		String s1 = "{ [ ( ) ] }";
		String s2 = "{ [ ( ) }";
		String s3 = "[ ( ) ] }";
		String s4 = "{ [ ( ] ) }";
		String javakode = """
				class HelloWorld{
					public static void main(String args[]){
						System.out.println("Hello world!");
					}
				}
				""";
		
		System.out.println(sjekkParenteser(s1));
		System.out.println(sjekkParenteser(s2));
		System.out.println(sjekkParenteser(s3));
		System.out.println(sjekkParenteser(s4));
		System.out.println(sjekkParenteser(javakode));
	}
}
