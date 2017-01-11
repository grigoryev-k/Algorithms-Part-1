import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class RQIterator implements Iterator<Item> {

        private int[] orderOfIteration;
        private int   current;

        // @SuppressWarnings("unchecked")
        public RQIterator() {
            orderOfIteration = new int[numberOfItems];
            for (int i = 0; i < numberOfItems; i++) {
                orderOfIteration[i] = i;
            }
            StdRandom.shuffle(orderOfIteration);
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current != orderOfIteration.length - 1;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[orderOfIteration[current++]];
        }

    }

    private Item[]           items;
    private int              numberOfItems;
    private static final int INITIAL_CAPACITY = 2;

    // @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        items = (Item[]) new Object[INITIAL_CAPACITY];
        numberOfItems = 0;
    }

    public boolean isEmpty() {
        return numberOfItems == 0;
    }

    public int size() {
        return numberOfItems;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (numberOfItems == items.length) {
            resize(2 * items.length);
        }
        items[numberOfItems++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int pickedNumber = StdRandom.uniform(numberOfItems);
        Item picked = items[pickedNumber];
        if (pickedNumber != numberOfItems - 1) {
            items[pickedNumber] = items[numberOfItems - 1];
        }
        items[--numberOfItems] = null;
        if (numberOfItems > 0 && numberOfItems == items.length / 4) {
            resize(items.length / 2);
        }
        return picked;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int pickedNumber = StdRandom.uniform(numberOfItems);
        return items[pickedNumber];
    }

    @Override
    public Iterator<Item> iterator() {
        // TODO Auto-generated method stub
        return new RQIterator();
    }

    // public static void main(String[] args) {
    // RandomizedQueue<Integer> rq = new RandomizedQueue<>();
    // rq.enqueue(6);
    // rq.enqueue(7);
    // StdOut.println(rq.size());
    // StdOut.println(rq.dequeue());
    // StdOut.println(rq.size());
    // StdOut.println(rq.sample());
    // }

    // @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        // TODO Auto-generated method stub
        Item[] copy = (Item[]) new Object[newSize];
        for (int j = 0; j < numberOfItems; j++) {
            copy[j] = items[j];
        }
        items = copy;
    }
}
