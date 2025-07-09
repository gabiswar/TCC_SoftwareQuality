import java.util.*;
import java.time.LocalDate;

// Classe de Armazém
class Warehouse {
    private static int nextId = 1;
    private int id;
    private String name;
    private String location;

    public Warehouse(String name, String location) {
        this.id = nextId++;
        this.name = name;
        this.location = location;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public String toString() {
        return "Armazém ID: " + id + ", Nome: " + name + ", Localização: " + location;
    }
}

// Classe de Produto
class Product {
    private static int nextId = 1;
    private int id;
    private String name;
    private String description;
    private double price;
    private Map<Integer, Integer> stockByWarehouse;

    public Product(String name, String description, double price) {
        this.id = nextId++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockByWarehouse = new HashMap<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }

    public int getStock(int warehouseId) {
        return stockByWarehouse.getOrDefault(warehouseId, 0);
    }

    public void addStock(int warehouseId, int quantity) {
        stockByWarehouse.put(warehouseId, getStock(warehouseId) + quantity);
    }

    public boolean removeStock(int warehouseId, int quantity) {
        int currentStock = getStock(warehouseId);
        if (currentStock < quantity) return false;
        stockByWarehouse.put(warehouseId, currentStock - quantity);
        return true;
    }

    public void transferStock(int fromWarehouseId, int toWarehouseId, int quantity) {
        removeStock(fromWarehouseId, quantity);
        addStock(toWarehouseId, quantity);
    }

    @Override
    public String toString() {
        return "Produto ID: " + id + ", Nome: " + name + ", Descrição: " + description + ", Preço: R$ " + price;
    }
}

// Classe de movimentação de estoque
class StockMovement {
    public enum Type { ENTRADA, SAIDA, TRANSFERENCIA }
    private Type type;
    private Product product;
    private int quantity;
    private LocalDate date;
    private Warehouse source;
    private Warehouse destination;

    public StockMovement(Type type, Product product, int quantity, LocalDate date, Warehouse source, Warehouse destination) {
        this.type = type;
        this.product = product;
        this.quantity = quantity;
        this.date = date;
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Movimentação: " + type + ", Produto: " + product.getName() +
                ", Quantidade: " + quantity + ", Data: " + date +
                (source != null ? ", De: " + source.getName() : "") +
                (destination != null ? ", Para: " + destination.getName() : "");
    }
}

// Sistema principal
public class InventorySystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Warehouse> warehouses = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    private static List<StockMovement> movements = new ArrayList<>();

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("\n===== SISTEMA DE ESTOQUE =====");
            System.out.println("1. Gerenciar Armazéns");
            System.out.println("2. Gerenciar Produtos");
            System.out.println("3. Movimentações de Estoque");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> manageWarehouses();
                case 2 -> manageProducts();
                case 3 -> stockMovements();
            }
        } while (option != 0);
    }

    private static void manageWarehouses() {
        int option;
        do {
            System.out.println("\n--- Armazéns ---");
            System.out.println("1. Criar Armazém");
            System.out.println("2. Visualizar Armazéns");
            System.out.println("3. Atualizar Armazém");
            System.out.println("4. Excluir Armazém");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String name = scanner.nextLine();
                    System.out.print("Localização: ");
                    String location = scanner.nextLine();
                    warehouses.add(new Warehouse(name, location));
                    System.out.println("Armazém criado com sucesso.");
                }
                case 2 -> warehouses.forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID do Armazém: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    warehouses.stream().filter(w -> w.getId() == id).findFirst().ifPresentOrElse(w -> {
                        System.out.print("Novo nome: ");
                        w.setName(scanner.nextLine());
                        System.out.print("Nova localização: ");
                        w.setLocation(scanner.nextLine());
                        System.out.println("Atualizado.");
                    }, () -> System.out.println("Armazém não encontrado."));
                }
                case 4 -> {
                    System.out.print("ID do Armazém: ");
                    int id = scanner.nextInt();
                    warehouses.removeIf(w -> w.getId() == id);
                    System.out.println("Removido.");
                }
            }
        } while (option != 0);
    }

    private static void manageProducts() {
        int option;
        do {
            System.out.println("\n--- Produtos ---");
            System.out.println("1. Registrar Produto");
            System.out.println("2. Visualizar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String name = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String desc = scanner.nextLine();
                    System.out.print("Preço: ");
                    double price = scanner.nextDouble();
                    products.add(new Product(name, desc, price));
                    System.out.println("Produto registrado.");
                }
                case 2 -> products.forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID do Produto: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    products.stream().filter(p -> p.getId() == id).findFirst().ifPresentOrElse(p -> {
                        System.out.print("Novo nome: ");
                        p.setName(scanner.nextLine());
                        System.out.print("Nova descrição: ");
                        p.setDescription(scanner.nextLine());
                        System.out.print("Novo preço: ");
                        p.setPrice(scanner.nextDouble());
                        System.out.println("Atualizado.");
                    }, () -> System.out.println("Produto não encontrado."));
                }
                case 4 -> {
                    System.out.print("ID do Produto: ");
                    int id = scanner.nextInt();
                    products.removeIf(p -> p.getId() == id);
                    System.out.println("Removido.");
                }
            }
        } while (option != 0);
    }

    private static void stockMovements() {
        int option;
        do {
            System.out.println("\n--- Movimentações de Estoque ---");
            System.out.println("1. Entrada de Produto");
            System.out.println("2. Saída de Produto");
            System.out.println("3. Transferência de Produto");
            System.out.println("4. Ver Movimentações");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    Product p = selectProduct();
                    Warehouse w = selectWarehouse();
                    System.out.print("Quantidade: ");
                    int qty = scanner.nextInt();
                    p.addStock(w.getId(), qty);
                    movements.add(new StockMovement(StockMovement.Type.ENTRADA, p, qty, LocalDate.now(), null, w));
                    System.out.println("Entrada registrada.");
                }
                case 2 -> {
                    Product p = selectProduct();
                    Warehouse w = selectWarehouse();
                    System.out.print("Quantidade: ");
                    int qty = scanner.nextInt();
                    if (p.removeStock(w.getId(), qty)) {
                        movements.add(new StockMovement(StockMovement.Type.SAIDA, p, qty, LocalDate.now(), w, null));
                        System.out.println("Saída registrada.");
                    } else System.out.println("Estoque insuficiente.");
                }
                case 3 -> {
                    Product p = selectProduct();
                    Warehouse source = selectWarehouse();
                    Warehouse dest = selectWarehouse();
                    System.out.print("Quantidade: ");
                    int qty = scanner.nextInt();
                    if (p.getStock(source.getId()) >= qty) {
                        p.transferStock(source.getId(), dest.getId(), qty);
                        movements.add(new StockMovement(StockMovement.Type.TRANSFERENCIA, p, qty, LocalDate.now(), source, dest));
                        System.out.println("Transferência registrada.");
                    } else System.out.println("Estoque insuficiente.");
                }
                case 4 -> movements.forEach(System.out::println);
            }
        } while (option != 0);
    }

    private static Product selectProduct() {
        products.forEach(System.out::println);
        System.out.print("ID do Produto: ");
        int id = scanner.nextInt();
        return products.stream().filter(p -> p.getId() == id).findFirst().orElseThrow();
    }

    private static Warehouse selectWarehouse() {
        warehouses.forEach(System.out::println);
        System.out.print("ID do Armazém: ");
        int id = scanner.nextInt();
        return warehouses.stream().filter(w -> w.getId() == id).findFirst().orElseThrow();
    }
}
