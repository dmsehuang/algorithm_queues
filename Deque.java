import java.util.*;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int N = 0;
    
    // inner class, doubly linked-list 
    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    
    // construct an empty deque
    public Deque() {
        // do nothing
    }
    
    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }
    
    // return the number of items on the deque
    public int size() {
        return N;
    }
    
    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        N++;
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldFirst;
        if (oldFirst == null) {
            last = first;
        } else{
            oldFirst.prev = first;
        }
    }
    
    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        N++;
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        last.next = null;
        if (oldLast == null) {
            first = last;
        } else{
            oldLast.next = last;
        }
    }
    
    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        N--;
        Item item = first.item;
        Node oldFirst = first;
        first = first.next;
        oldFirst.next = null;
        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        return item;
    }
    
    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        N--;
        Item item = last.item;
        Node oldLast = last;
        last = last.prev;
        oldLast.prev = null;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        return item;
    }
    
    // iterator class
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        
        // if the iterator has the next element
        public boolean hasNext() {
            return current != null;
        }
        
        // not supported operation
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        // return the next element of the iterator
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    //unit testing
    public static void main(String[]args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(8);
        deque.addFirst(6);
        deque.addFirst(1);
        deque.addLast(45);
        deque.addLast(43);
        deque.addFirst(9);
        deque.addLast(100);
        
        System.out.println("size is " + deque.size());
        System.out.println("size is " + deque.size());
        
        Iterator<Integer> iter = deque.iterator();
        while(iter.hasNext()) {
            int num = iter.next();
            System.out.print(num + " ");
        }
        System.out.println("========");
        
        deque.removeFirst();
        deque.removeLast();
        iter = deque.iterator();
        while(iter.hasNext()) {
            int num = iter.next();
            System.out.print(num + " ");
        }
        System.out.println("========");
        
        System.out.println("size is " + deque.size());
        
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        iter = deque.iterator();
        while(iter.hasNext()) {
            int num = iter.next();
            System.out.print(num + " ");
        }
        System.out.println("========");
        
        deque.removeLast();
        iter = deque.iterator();
        while(iter.hasNext()) {
            int num = iter.next();
            System.out.print(num + " ");
        }
        System.out.println("========");
        
        System.out.println("size is " + deque.size());
        
        deque.removeLast();
        iter = deque.iterator();
        while(iter.hasNext()) {
            int num = iter.next();
            System.out.print(num + " ");
        }
        System.out.println("========");
        
        System.out.println("size is " + deque.size());
        
        deque.removeLast();
    }    
}
