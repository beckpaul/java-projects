import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Layered implementations of secondary method {@code sort} for
 * {@code Queue<String>}.
 */
public final class Queue1LSort1 extends Queue1L<String> {

    /**
     * No-argument constructor.
     */
    public Queue1LSort1() {
        super();
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * (q * <removeMin>) is permutation of #q  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        assert q != null : "Violation of: q is not null";
        assert order != null : "Violation of: order is not null";

        if (q.length() == 1) {
            return q.dequeue();
        }

        int l = q.length();
        String min = q.dequeue();

        for (int i = 0; i < l; i++) {
            // remove next test from front of queue
            String test = q.dequeue();
            String tmp = "";
            /*
             * Returns a negative integer, zero, or a positive integer as the
             * first argument is less than, equal to, or greater than the
             * second.
             */

            int z = order.compare(min, test);
            // current minimum is less or equal to test
            // Put test back into the queue
            if (z <= 0) {
                q.enqueue(test);
            } else {
                tmp = test;
                // Put the previous minimum back in queue
                q.enqueue(min);
                // Update the minimum
                min = tmp;

            }
        }
        return min;
    }

    @Override
    public void sort(Comparator<String> order) {
        assert order != null : "Violation of: order is not null";

        Queue<String> newQueue = new Queue1LSort1();
        while (this.length() != 0) {
            newQueue.enqueue(removeMin(this, order));
        }
        while (newQueue.length() != 0) {
            String tmp = newQueue.dequeue();
            this.enqueue(tmp);
        }

    }

}
