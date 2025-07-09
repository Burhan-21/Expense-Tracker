// filepath: /ExpenseTracker/ExpenseTracker/src/Expense.java
import java.time.LocalDate;

public class Expense {
    private static int counter = 1;
    private final int id;
    private String category;
    private double amount;
    private LocalDate date;
    private String note;

    public Expense(String category, double amount, LocalDate date, String note) {
        this.id = counter++;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | $%.2f | %s | %s", id, category, amount, date, note);
    }
}