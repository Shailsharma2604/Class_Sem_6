// Designing  a schedule system, sort based on priority ; JOB SCHEDULING
// High priority -> shorter codes
// low priority -> longer codes

import java.util.*;

public class day_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of jobs
        System.out.print("Enter the number of jobs: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Define a list of jobs with their priorities
        List<Job> jobs = new ArrayList<>();

        // Read job details from the user
        for (int i = 0; i < n; i++) {
            System.out.print("Enter job code for job " + (i + 1) + ": ");
            String code = scanner.nextLine();
            System.out.print("Enter priority for job " + (i + 1) + ": ");
            int priority = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            jobs.add(new Job(code, priority));
        }

        // Generate Huffman codes for the jobs
        Map<Job, String> huffmanCodes = generateHuffmanCodes(jobs);

        // Print the Huffman codes
        for (Job job : jobs) {
            System.out.println("Job: " + job.getCode() + ", Priority: " + job.getPriority() + ", Huffman Code: " + huffmanCodes.get(job));
        }

        scanner.close();
    }

    // Job class representing a job with a code and priority
    static class Job {
        private String code;
        private int priority;

        public Job(String code, int priority) {
            this.code = code;
            this.priority = priority;
        }

        public String getCode() {
            return code;
        }

        public int getPriority() {
            return priority;
        }
    }

    // Node class for Huffman tree
    static class Node {
        Job job;
        int frequency;
        Node left, right;

        public Node(Job job, int frequency) {
            this.job = job;
            this.frequency = frequency;
            this.left = this.right = null;
        }
    }

    // Comparator for the priority queue
    static class NodeComparator implements Comparator<Node> {
        public int compare(Node x, Node y) {
            return x.frequency - y.frequency;
        }
    }

    // Generate Huffman codes for the jobs
    public static Map<Job, String> generateHuffmanCodes(List<Job> jobs) {
        PriorityQueue<Node> pq = new PriorityQueue<>(jobs.size(), new NodeComparator());

        // Create a leaf node for each job and add it to the priority queue
        for (Job job : jobs) {
            pq.add(new Node(job, job.getPriority()));
        }

        // Iterate until the size of the queue is 1
        while (pq.size() > 1) {
            // Remove the two nodes of highest priority (lowest frequency)
            Node left = pq.poll();
            Node right = pq.poll();

            // Create a new internal node with these two nodes as children and with frequency equal to the sum of the two nodes' frequencies
            Node newNode = new Node(null, left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;

            // Add the new node to the priority queue
            pq.add(newNode);
        }

        // The remaining node is the root of the Huffman tree
        Node root = pq.poll();

        // Traverse the Huffman tree and store the Huffman codes in a map
        Map<Job, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        return huffmanCodes;
    }

    // Recursive function to generate Huffman codes
    public static void generateCodes(Node root, String code, Map<Job, String> huffmanCodes) {
        if (root == null) {
            return;
        }

        // If this is a leaf node, then it contains one of the input jobs
        if (root.job != null) {
            huffmanCodes.put(root.job, code);
        }

        // Traverse the left and right subtrees
        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }
}