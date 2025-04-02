//you as logistic enginerr for a company your task is to max the total priority score of the goods loaded on truck with li ited weight capacity
//eah priority has a weight in kg and a priority score that represents its importance fraction of packages can be loaded
//write program find max priority score that can be achieved while keeping thr truck 
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Package {
    double weight;
    double priorityScore;
    double priorityPerWeight;

    public Package(double weight, double priorityScore) {
        this.weight = weight;
        this.priorityScore = priorityScore;
        this.priorityPerWeight = priorityScore / weight;
    }
}

public class FractionalKnapsack {

    public static double maximizePriority(List<Package> packages, double maxWeight) {
        packages.sort(Comparator.comparingDouble((Package p) -> p.priorityPerWeight).reversed());
        double total = 0.0;
        double remW = maxWeight;
        for (Package p : packages) {
            if (remW >= p.weight) {
                total += p.priorityScore;
                remW -= p.weight;
            } else {
                double fr = remW / p.weight;
                total += p.priorityScore * fr;
                break;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        List<Package> list = new ArrayList<>();
        list.add(new Package(5, 50));
        list.add(new Package(10, 60));
        list.add(new Package(15,90));
        list.add(new Package(20, 100));
        list.add(new Package(25, 75));
        list.add(new Package(30, 150));

        double M = 60;

        double profit = maximizePriority(list, M);
        System.out.println(profit);
    }
}