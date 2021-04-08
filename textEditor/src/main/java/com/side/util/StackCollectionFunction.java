package com.side.util;

import java.util.EmptyStackException;

public class StackCollectionFunction {
	private int top;
	private String[] array;

	public StackCollectionFunction(int size) {
		this.top = -1;
		this.array = new String[size];
	}
	
	public int size() {
		return top;
	}
	
	public String push(String item) {
		if (top == array.length - 1) {
			throw new StackOverflowError("오버플로우");
		}
		array[++top] = item;

		return item;
	}
	
	public String pop() {
		String value = peek();

		array[top--] = null;

		return value;
	}

	public String peek() {
		if (top == -1)
			throw new EmptyStackException();
		return array[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public int search(Object o) {
		for (int i = top; i > -1; i--) {
			if (array[i].equals(o))
				return array.length - i;
		}
		return -1;
	}


}
