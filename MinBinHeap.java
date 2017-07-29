package minBinHeap;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; // load this array
	private int size = 0;
	private static final int arraySize = 10000;
	//Everything in the array will initially be null.

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); 
		//0th will be unused for simplicity of child/parent computations...
	}

	@Override
	public EntryPair[] getHeap() {
		return this.array;
	}

	public void insert(EntryPair entry) {

		size++;
		array[size] = entry;
		up();

	}

	@Override
	public void delMin() {

		if (isEmpty()) {
			return;
		}

		array[1] = array[size];
		array[size] = null;
		size--;

		down();

	}

	@Override
	public EntryPair getMin() {
		return size == 0 ? null : array[1];
	}

	@Override
	public int size() {
		return size;
	}

	public void build(EntryPair[] entries) {
		
		for (int i = 0; i < entries.length; i++) {
			array[i + 1] = entries[i];
			size++;
		}
		
		for (int i = size; i > 0; i -= 2) {
			int index = i/2;
			down(index, 0);
		}
		
	}

	public void up() {

		int index = size;

		while (index > 0 && (array[index / 2].priority > array[index].priority)) {

			swap(index, index / 2);
			index = index / 2;

		}
	}

	public void down() {

		int index = 1;

		while (index * 2 <= size) {

			int smaller = index * 2;

			if (index * 2 + 1 <= size && array[index * 2].priority > array[index * 2 + 1].priority) {
				smaller = index * 2 + 1;
			}
			if (array[index].priority > array[smaller].priority) {
				swap(index, smaller);
			} else {
				break;
			}
			index = smaller;
		}
	}

	public void down(int current, int nuevo) {
		int temp = nuevo;

		while (minChild(current) != 0) {

			temp = minChild(current);

			if (array[current].priority > array[temp].priority) {
				swap(current, temp);
				current = temp;
			} else {
				return;
			}

		}
	}

	public void swap(int one, int two) {
		EntryPair temp = array[one];
		array[one] = array[two];
		array[two] = temp;
	}

	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	public int minChild(int pos) {

		if (array[pos * 2] != null && array[pos * 2 + 1] != null) {

			return array[pos * 2].priority > array[pos * 2 + 1].priority ? pos * 2 + 1 : pos * 2;

		} else if (array[pos * 2] != null && array[pos * 2 + 1] == null) {
			return array[pos * 2].priority < array[pos].priority ? pos * 2 : 0;
		} else {

			return 0;

		}

	}

}
