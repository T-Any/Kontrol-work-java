import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class ToyLottery {
    private static class Toy {
        int id;
        String name;
        int frequency;

        public Toy(int id, String name, int frequency) {
            this.id = id;
            this.name = name;
            this.frequency = frequency;
        }
    }

    public static void main(String[] args) {
        
        PriorityQueue<Toy> toyQueue = new PriorityQueue<>((t1, t2) -> t2.frequency - t1.frequency);

        
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            System.out.println("Enter information for toy " + (i + 1) + ":");
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // clear buffer after nextInt()
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Frequency of occurrence: ");
            int frequency = scanner.nextInt();
            scanner.nextLine(); // clear buffer after nextInt()

            Toy toy = new Toy(id, name, frequency);
            toyQueue.add(toy);
        }

        
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("toy_results.txt"), "UTF-8")) {
            for (int i = 0; i < 10; i++) {
                Toy luckyToy = toyQueue.poll();
                writer.write("The toy drawn is: " + luckyToy.name + "\n");
            }
            System.out.println("The results of the lottery have been written to the toy_results.txt file");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
