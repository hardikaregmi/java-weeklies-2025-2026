import java.util.*;

public class App {

    // Calculate weighted average
    public static double calculateAverage(List<Double> grades, List<Double> weights) {
        double total = 0;
        double totalWeights = 0;

        for (int i = 0; i < grades.size(); i++) {
            total += grades.get(i) * (weights.get(i) / 100.0);
            totalWeights += weights.get(i);
        }

        return totalWeights == 0 ? 0 : total; // return weighted score
    }

    // Calculate what you need on final exam
    public static double neededOnFinal(double currentAverage, double finalWeight, double target) {
        double currentContribution = currentAverage * ((100 - finalWeight) / 100.0);
        double requiredFinal = (target - currentContribution) / (finalWeight / 100.0);
        return requiredFinal;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Grade What-If Calculator ===\n");

        System.out.print("How many graded items do you have so far? ");
        int n = sc.nextInt();

        List<Double> grades = new ArrayList<>();
        List<Double> weights = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.print("Grade for item " + i + " (0–100): ");
            grades.add(sc.nextDouble());

            System.out.print("Weight of item " + i + " (%): ");
            weights.add(sc.nextDouble());
        }

        double currentAverage = calculateAverage(grades, weights);
        System.out.printf("\nCurrent average (weighted): %.2f%%\n", currentAverage);

        System.out.print("\nEnter weight of FINAL exam (%): ");
        double finalWeight = sc.nextDouble();

        System.out.print("Target course grade you want (%): ");
        double target = sc.nextDouble();

        double needed = neededOnFinal(currentAverage, finalWeight, target);

        System.out.printf("\nTo get %.2f%% in the class, you need %.2f%% on the final.\n\n",
                          target, needed);

        if (needed > 100) {
            System.out.println("⚠️ It's mathematically impossible, but don't panic — do your best.");
        } else if (needed < 50) {
            System.out.println("🎉 You're sitting comfortably. Just stay steady.");
        } else {
            System.out.println("You can do it — study smart and finish strong.");
        }
    }
}

