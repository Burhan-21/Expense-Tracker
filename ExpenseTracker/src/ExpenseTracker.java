// filepath: /ExpenseTracker/ExpenseTracker/src/ExpenseTracker.java
import java.util.*;
import java.time.LocalDate;

// ExpenseTracker class to manage expenses
public class ExpenseTracker {
    private final List<Expense> expenses = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new ExpenseTracker().run();
    }

    private void run() {
        System.out.println("=== Expense Tracker ===");
        while (true) {
            System.out.println("\n1. Add Expense\n2. View All Expenses\n3. Edit Expense\n4. Delete Expense\n5. Filter by Category\n6. Monthly Summary\n7. Search by Note\n8. Set Budget & Alert\n9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt(); scanner.nextLine();
            switch (choice) {
                case 1: addExpense(); break;
                case 2: viewExpenses(); break;
                case 3: editExpense(); break;
                case 4: deleteExpense(); break;
                case 5: filterByCategory(); break;
                case 6: monthlySummary(); break;
                case 7: searchByNote(); break;
                case 8: setBudgetAndAlert(); break;
                case 9: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private void addExpense() {
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = scanner.nextDouble(); scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Note: ");
        String note = scanner.nextLine();
        expenses.add(new Expense(category, amount, date, note));
        System.out.println("Expense added!");
    }

    private void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        expenses.forEach(System.out::println);
    }

    private void editExpense() {
        System.out.print("Enter Expense ID to edit: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Expense exp = findExpenseById(id);
        if (exp == null) {
            System.out.println("Expense not found.");
            return;
        }
        System.out.print("New Category (" + exp.getCategory() + "): ");
        String category = scanner.nextLine();
        if (!category.isEmpty()) exp.setCategory(category);
        System.out.print("New Amount (" + exp.getAmount() + "): ");
        String amtStr = scanner.nextLine();
        if (!amtStr.isEmpty()) exp.setAmount(Double.parseDouble(amtStr));
        System.out.print("New Date (" + exp.getDate() + "): ");
        String dateStr = scanner.nextLine();
        if (!dateStr.isEmpty()) exp.setDate(LocalDate.parse(dateStr));
        System.out.print("New Note (" + exp.getNote() + "): ");
        String note = scanner.nextLine();
        if (!note.isEmpty()) exp.setNote(note);
        System.out.println("Expense updated!");
    }

    private void deleteExpense() {
        System.out.print("Enter Expense ID to delete: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Expense exp = findExpenseById(id);
        if (exp != null) {
            expenses.remove(exp);
            System.out.println("Expense deleted.");
        } else {
            System.out.println("Expense not found.");
        }
    }

    private void filterByCategory() {
        System.out.print("Enter category to filter: ");
        String category = scanner.nextLine();
        expenses.stream()
            .filter(e -> e.getCategory().equalsIgnoreCase(category))
            .forEach(System.out::println);
    }

    private void monthlySummary() {
        System.out.print("Enter month (YYYY-MM): ");
        String month = scanner.nextLine();
        double total = expenses.stream()
            .filter(e -> e.getDate().toString().startsWith(month))
            .mapToDouble(Expense::getAmount)
            .sum();
        System.out.println("Total spent in " + month + ": $" + total);
    }

    private void searchByNote() {
        System.out.print("Enter keyword to search in notes: ");
        String keyword = scanner.nextLine().toLowerCase();
        expenses.stream()
            .filter(e -> e.getNote().toLowerCase().contains(keyword))
            .forEach(System.out::println);
    }

    private void setBudgetAndAlert() {
        System.out.print("Set monthly budget: ");
        double budget = scanner.nextDouble(); scanner.nextLine();
        System.out.print("Enter month (YYYY-MM): ");
        String month = scanner.nextLine();
        double total = expenses.stream()
            .filter(e -> e.getDate().toString().startsWith(month))
            .mapToDouble(Expense::getAmount)
            .sum();
        System.out.println("Total spent: $" + total);
        if (total > budget) {
            System.out.println("Alert: You have exceeded your budget!");
        } else {
            System.out.println("You are within your budget.");
        }
    }

    private Expense findExpenseById(int id) {
        return expenses.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }
}