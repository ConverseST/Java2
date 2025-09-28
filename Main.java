import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Comparator;
import java.util.Map;

// -------------------- Employee Class --------------------
class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " | Age: " + age + " | Salary: " + salary;
    }
}

// -------------------- Student Class --------------------
class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + " | Marks: " + marks;
    }
}

// -------------------- Product Class --------------------
class Product {
    String name;
    double price;
    String category;

    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return name + " | " + category + " | Price: " + price;
    }
}

// -------------------- Main Program --------------------
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Main Menu (Lambda & Streams) ===");
            System.out.println("1. Sorting Employees (Lambda Expressions)");
            System.out.println("2. Filtering Students (Streams)");
            System.out.println("3. Product Dataset Operations (Streams)");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    employeeSortingDemo();
                    break;
                case 2:
                    studentStreamDemo();
                    break;
                case 3:
                    productStreamDemo();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);
    }

    // -------------------- Part A: Employee Sorting --------------------
    private static void employeeSortingDemo() {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 30, 50000),
            new Employee("Bob", 25, 60000),
            new Employee("Charlie", 35, 45000),
            new Employee("David", 28, 70000)
        );

        System.out.println("\nOriginal List:");
        employees.forEach(System.out::println);

        // Sort by Name
        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));
        System.out.println("\nSorted by Name:");
        employees.forEach(System.out::println);

        // Sort by Age
        employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
        System.out.println("\nSorted by Age:");
        employees.forEach(System.out::println);

        // Sort by Salary Descending
        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("\nSorted by Salary (Descending):");
        employees.forEach(System.out::println);
    }

    // -------------------- Part B: Student Filtering & Sorting --------------------
    private static void studentStreamDemo() {
        List<Student> students = Arrays.asList(
            new Student("Arjun", 82),
            new Student("Priya", 65),
            new Student("Ravi", 90),
            new Student("Meena", 72),
            new Student("Karan", 78)
        );

        System.out.println("\nStudents Scoring > 75 (Sorted by Marks):");
        students.stream()
                .filter(s -> s.marks > 75)
                .sorted(Comparator.comparingDouble(s -> s.marks))
                .map(s -> s.name) // extract only names
                .forEach(System.out::println);
    }

    // -------------------- Part C: Product Dataset Operations --------------------
    private static void productStreamDemo() {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 80000, "Electronics"),
            new Product("Phone", 50000, "Electronics"),
            new Product("Shirt", 2000, "Clothing"),
            new Product("Jeans", 3000, "Clothing"),
            new Product("Fridge", 60000, "Appliances"),
            new Product("Microwave", 15000, "Appliances")
        );

        // Group by category
        System.out.println("\nProducts Grouped by Category:");
        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));
        grouped.forEach((cat, list) -> {
            System.out.println(cat + ": " + list);
        });

        // Most expensive product in each category
        System.out.println("\nMost Expensive Product in Each Category:");
        Map<String, Optional<Product>> maxByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
                ));
        maxByCategory.forEach((cat, prod) -> {
            System.out.println(cat + ": " + prod.get());
        });

        // Average price of all products
        double avgPrice = products.stream()
                .collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("\nAverage Price of All Products: " + avgPrice);
    }
}
