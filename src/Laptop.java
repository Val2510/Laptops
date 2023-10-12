import java.util.*;


    public class Laptop {
        String name;
        int hdd;
        int ram;
        String operationSys;
        String color;
        String brand;


        public Laptop(String name, int hdd, int ram, String operationSys, String color, String brand) {
            this.name = name;
            this.hdd = hdd;
            this.ram = ram;
            this.operationSys = operationSys;
            this.color = color;
            this.brand = brand;
        }

        public String toString(){
            return "name = " + name + ", hdd = " + hdd + ", ram = " + ram + ", operationSys = " + operationSys + ", color = " + color + ", brand = " + brand;
        }

        public boolean equals(Object laptop){
            Laptop laptop1 = (Laptop) laptop;
            return this.name.equals(laptop1.name) &&
                    this.hdd == laptop1.hdd &&
                    this.ram == laptop1.ram &&
                    this.operationSys.equals(laptop1.operationSys) &&
                    this.color.equals(laptop1.color) &&
                    this.brand.equals(laptop1.brand);
        }

        public int hashCode(){
            return this.name.hashCode() + this.hdd + this.ram;
        }

        public static void main(String[] args) {
            Laptop laptop = new Laptop("N2210", 1, 8, "Windows", "Black", "Lenovo");
            Laptop laptop2 = new Laptop("L40", 1, 8, "Windows", "Grey", "ASUS");
            Laptop laptop3 = new Laptop("Play3707", 3, 16, "Ubuntu", "Red", "Lenovo");
            Laptop laptop4 = new Laptop("K9010", 2, 8, "Windows", "Black", "HP");
            Laptop laptop5 = new Laptop("P80", 2, 16, "MacOs", "White", "Apple");
            Laptop laptop6 = new Laptop("A613", 5, 8, "Windows", "Green", "Digma");
            Laptop laptop7 = new Laptop("A613", 5, 8, "Windows", "Green", "Digma");


            Set<Laptop> data = new HashSet<>(List.of(laptop, laptop2, laptop3, laptop4, laptop5, laptop6, laptop7));

            for (Laptop l : data) {
                System.out.println(l);
            }

            searchLaptop(data);

        }

        private static void searchLaptop(Set<Laptop> data) {
            Scanner scanner = new Scanner(System.in);
            Map<Integer, String> filter = new HashMap<>();

            filter.put(1, "ram");
            filter.put(2, "hdd");



            Map<String, Object> filters = new HashMap<>();

            for (Map.Entry<Integer, String> entry : filter.entrySet()) {
                System.out.print("Введите значение для " + entry.getValue() + ": ");
                Object value = getValue(entry.getValue(), scanner);
                filters.put(entry.getValue(), value);
            }

            List<Laptop> filteredLaptops = data.stream()
                    .filter(laptop -> {
                        for (Map.Entry<String, Object> entry : filters.entrySet()) {
                            Object filterValue = entry.getValue();
                            String property = entry.getKey();
                            Object laptopValue = null;

                            switch (property) {
                                case "ram":
                                    laptopValue = laptop.getRam();
                                    break;
                                case "hdd":
                                    laptopValue = laptop.getHdd();
                                    break;
                            }

                            if (laptopValue == null || !laptopValue.equals(filterValue)) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .toList();

            if (filteredLaptops.isEmpty()) {
                System.out.println("Таких ноутбуков нет.");
            } else {
                System.out.println("Варианты ноутбуков:");
                for (Laptop laptop : filteredLaptops) {
                    System.out.println(laptop);
                }
            }
        }


        private Object getRam() {
            return ram;
        }

        private Object getHdd() {
            return hdd;
        }


        private static Object getValue(String property, Scanner scanner) {
            if (property.equals("ram") || property.equals("hdd")) {
                return scanner.nextInt();
            } else {
                return scanner.nextLine().trim();
            }
        }
    }


