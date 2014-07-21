public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> rand_q = new RandomizedQueue<String>();
        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rand_q.enqueue(s);
        }
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            StdOut.println(rand_q.dequeue());
        }
        
    }

}
