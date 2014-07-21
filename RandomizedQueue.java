import java.util.*;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // randomized data structure requires an array
    private Item[] q; // queue elements
    private int N = 0;
    private int first = 0; // first index of the array
    private int last = 0; // next available slot 
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        // cast needed since no generic array creation in Java
        q = (Item[]) new Object[2];
    }
    
    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }
    
    // return the number of items on the queue
    public int size() {
        return N;
    }
    
    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("Null item");
        if (N == q.length) resize(2*q.length); // resize the array
        q[last++] = item;
        if (last == q.length) last = 0; // wrap around
        N++;
    }
    
    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        // randomly move an element to the first place
        Random ran = new Random();
        int num = ran.nextInt(size());
        int index = (first + num) % q.length;
        Item temp = q[first];
        q[first] = q[index];
        q[index] = temp;
        // remove the first element
        Item item = q[first];
        q[first] = null; // garbage collection
        first++;
        if (first == q.length) first = 0; // wrap around
        N--;
        if (N > 0 && N == q.length/4) resize(q.length/2);
        return item;
    }
    
    // return (but do not delete) a random item
    public Item sample() {
        if (size() == 0) throw new NoSuchElementException();
        Random ran = new Random();
        int num = ran.nextInt(size());
        int index = (first + num) % q.length;
        return q[index];
    }
    
    // resize function
    private void resize (int capacity) {
        assert capacity >= N;
        Item[] copy = (Item[]) new Object[capacity];
        for(int i = 0; i < N; i++){
            copy[i] = q[(i + first) % q.length]; // offset
        }
        q = copy;
        // update the first and the last value
        first = 0;
        last = N;
    }
    
    private class ArrayIterator implements Iterator<Item> {
        private Item[] s;
        private int n = 0;
        
        // constructor
        public ArrayIterator(){
            // construct the array
            s = (Item[]) new Object[N];
            for(int i = 0; i < N; i++){
                s[i] = q[(i + first) % q.length];
            }
            // shuffle the queue
            for(int i = 0; i < N; i++) {
                Random ran = new Random();
                int j = ran.nextInt(i+1);
                Item temp = s[j];
                s[j] = s[i];
                s[i] = temp;
            }
        }
        
        // if the iterator has the next element
        public boolean hasNext(){
            return n < N;
        }
        
        // not supported operation
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        // return the next element of the iterator
        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            return s[n++];
        }
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
    
    //unit testing
    public static void main(String[]args) {
        RandomizedQueue<Integer> rand_q = new RandomizedQueue<Integer>();
        rand_q.enqueue(9);
        rand_q.enqueue(10);
        rand_q.enqueue(1);
        System.out.println("size is " + rand_q.size());
        
        System.out.println("**********");
        Iterator<Integer> iter = rand_q.iterator();
        while(iter.hasNext()){
            int num = iter.next();
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("**********");
        
        rand_q.enqueue(45);
        rand_q.dequeue();
        rand_q.dequeue();
        rand_q.dequeue();
        rand_q.dequeue();
        rand_q.enqueue(100);
        rand_q.enqueue(101);
        System.out.println("size is " + rand_q.size());
        
        System.out.println("**********");
        iter = rand_q.iterator();
        while(iter.hasNext()){
            int num = iter.next();
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("**********");
    }
}

