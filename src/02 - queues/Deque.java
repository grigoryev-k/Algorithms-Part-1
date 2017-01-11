import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private class Element<T> {
        private T         item;
        public Element<T> next;
        public Element<T> prev;

        public Element(T newItem) {
            if (newItem == null) {
                throw new NullPointerException();
            }
            item = newItem;
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Element<Item> current;

        public DequeIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (this.hasNext()) {
                Item value = current.item;
                current = current.next;
                return value;
            } else {
                throw new NoSuchElementException();
            }

        }

    }

    private Element<Item> first;
    private Element<Item> last;
    private int n;

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return (size() == 0);

    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        ++n;
        Element<Item> insertedElement = new Element<>(item);
        if (first == null) {
            first = insertedElement;
            last = insertedElement;
            return;
        } else {
            insertedElement.next = first;
            first.prev = insertedElement;
            first = insertedElement;
            return;
        }
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        ++n;
        Element<Item> insertedElement = new Element<>(item);
        if (last == null) {
            last = insertedElement;
            first = insertedElement;
        } else {
            insertedElement.prev = last;
            last.next = insertedElement;
            last = insertedElement;
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        --n;
        Element<Item> removedElement = first;
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = removedElement.next;
            first.prev = null;
        }
        return removedElement.item;

    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        --n;
        Element<Item> removedElement = last;
        if (first == last) {
            first = null;
            last = null;
        } else {
            last = removedElement.prev;
            last.next = null;
        }
        return removedElement.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

//    public static void main(String[] args) {
//        Deque<Integer> d = new Deque<>();
//        d.addFirst(9);
//        d.addFirst(4);
//        d.addLast(7);
//        for (Integer i : d) {
//            StdOut.println(i);
//        }
//
//        StdOut.println(d.size());
//
//        d.removeFirst();
//        d.removeLast();
//        d.removeFirst();
//        StdOut.println(d.size());
//        for (Integer i : d) {
//            StdOut.println(i);
//        }
//    }
}
