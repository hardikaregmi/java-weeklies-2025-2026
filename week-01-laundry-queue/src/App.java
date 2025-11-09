import java.util.*;

public class App {
    static class Machine {
        int id, availableAt;
        int cycleTime;
        Machine(int id, int cycleTime) {
            this.id = id;
            this.cycleTime = cycleTime;
            this.availableAt = 0;
        }
    }

    static class Job {
        int id;
        String student;
        int submittedAt;
        Job(int id, String student, int submittedAt) {
            this.id = id;
            this.student = student;
            this.submittedAt = submittedAt;
        }
    }

    static class Assignment {
        Job job;
        Machine machine;
        int startMinute, endMinute, wait;
        Assignment(Job j, Machine m, int start, int end) {
            job = j; machine = m;
            startMinute = start; endMinute = end;
            wait = start - j.submittedAt;
        }
    }

    public static void main(String[] args) {
        int cycleTime = 45;
        List<Machine> machines = List.of(
            new Machine(1, cycleTime),
            new Machine(2, cycleTime),
            new Machine(3, cycleTime)
        );

        Queue<Job> jobs = new ArrayDeque<>();
        jobs.add(new Job(1, "Aarav", 0));
        jobs.add(new Job(2, "Maya", 2));
        jobs.add(new Job(3, "Sam", 4));
        jobs.add(new Job(4, "Bianca", 6));
        jobs.add(new Job(5, "Noah", 8));
        jobs.add(new Job(6, "Luna", 10));
        jobs.add(new Job(7, "Ishan", 12));

        List<Assignment> done = new ArrayList<>();
        int t = 0;
        while (!jobs.isEmpty()) {
            for (Machine m : machines) {
                if (m.availableAt <= t && !jobs.isEmpty() && jobs.peek().submittedAt <= t) {
                    Job j = jobs.poll();
                    int start = t;
                    int end = start + m.cycleTime;
                    m.availableAt = end;
                    done.add(new Assignment(j, m, start, end));
                }
            }
            int next = Integer.MAX_VALUE;
            if (!jobs.isEmpty()) next = Math.min(next, jobs.peek().submittedAt);
            for (Machine m : machines) next = Math.min(next, m.availableAt);
            t = Math.max(t + 1, next);
            if (next == Integer.MAX_VALUE) break;
        }

        System.out.println("Dorm Laundry Queue — Simulation");
        System.out.println("Job  Student  Machine  Start  End  Wait");
        int totalWait = 0;
        for (Assignment a : done) {
            System.out.printf("%-4d %-8s %-7d %-5d %-4d %-4d%n",
                a.job.id, a.job.student, a.machine.id,
                a.startMinute, a.endMinute, a.wait);
            totalWait += a.wait;
        }
        double avg = done.isEmpty() ? 0 : (totalWait * 1.0 / done.size());
        System.out.printf("%nAverage wait: %.2f minutes%n", avg);
    }
}

