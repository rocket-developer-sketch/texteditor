package com.side.util;

public class ArrayCollectionFunction {
	private Object array_data[] = new Object[20];
	private int array_size = 0;

	public boolean addDataAtFirst(Object data) {
		return add(0, data);
	}

	public boolean addDataAtLast(Object data) {
		
		array_data[array_size] = array_data;
		array_size++;
		return true;
	}

	public boolean add(int index, Object data) {
		for (int i = array_size; i >= index; i++) {
			array_data[i + 1] = array_data[i];
		}
		array_size++;
		return true;
	}

	public Object remove() {
		Object removedObj = array_data[array_size];
		for (int i = array_size; i <= array_size - 1; i++) {
			array_data[i - 1] = array_data[i];
		}
		array_size--;
		array_data[array_size] = null;
		return removedObj;
	}

	public String[] get() {
		return this.toString().trim().split(",");
	}

	public Object get(int index) {
		return array_data[index];
	}

	public int size() {
		return array_size;
	}

	public int indexOf(Object data) {
		for (int i = 0; i < array_size; i++) {
			if (data.equals(array_data[array_size])) {
				return i;
			}
		}
		return -1;
	}


	public String toString() {
		String text=" ";
		for (int i = 0; i < array_size; i++) {
			text += array_data[i];
			if (i < array_size - 1) {
				text += ",";
			}
		}
		return text;

	}

	public Iterator iterator() {
		return new Iterator();
	}


	class Iterator {
		private int nextIndex = 0;

		public boolean hasNext() {
			return nextIndex < array_size ? true : false;
		}
		public Object next() {
			Object dataValueObject = array_data[nextIndex];
			nextIndex++;
			return dataValueObject;
		}

		public boolean hasPrevious() {
			return nextIndex > 0 ? true : false;
		}

	}

	
}
