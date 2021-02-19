package base.concurrency;

import java.io.IOException;
import java.util.*;

public class test {
    public static void main(String[] args) throws IOException {
        int n  = 10; // 7 -> 21 11 -> 55

//        7-> 3, 4,  12
//        3 -> 1, 2  2
//                4 -> 2 2 4
//            2 -> 1 * 1 1
//            2 -> 1 * 1
//                2 -> 1 *  1
        System.out.println(new test().unstacking(10));
        HashSet set = new HashSet();

    }

    public int unstacking(int stack) {
        Queue<Integer> queue = new LinkedList();
        int score = 0;
        queue.offer(stack);

        while(!queue.isEmpty()) {
            List<Integer> levelNodes = new ArrayList<>();
            while(!queue.isEmpty()) {
                levelNodes.add(queue.poll());
            }

            for (int node: levelNodes) {
                int node1, node2;
                if (node % 2 == 0) {
                    node1= node/2;
                    node2 = node/2;
                    if (node1 != 1) {
                        queue.offer(node1);
                        queue.offer(node2);
                    }
                } else {
                    node1 = node / 2;
                    node2 = node / 2 + 1;
                    if (node1 != 1) {
                        queue.offer(node1);
                    }
                    if (node2 != 1) {
                        queue.offer(node2);
                    }
                }
                score += node1 * node2;
            }
        }

        return score;
    }


}
