import java.util.Scanner;
import java.util.Locale;

/**
 * Основной класс для демонстрации работы пунктов пропуска для различных транспортных средств.
 * Позволяет пользователю выбрать тип транспортного средства и проверить, пройдет ли оно контроль.
 */
public class CheckpointDemo {

    public static void main(String[] args) {
        // Устанавливаем локаль на стандартную, чтобы принимать точку как разделитель для дробных чисел
        Locale.setDefault(Locale.US);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите тип транспортного средства:");
        System.out.println("1. Легковая машина");
        System.out.println("2. Грузовик");
        System.out.println("3. Автобус");
        System.out.println("4. Трейлер");
        System.out.println("5. Легковой автомобиль с прицепом");
        int choice = getIntInput(scanner, "Введите номер: ", 1, 5);

        double width = getPositiveDoubleInput(scanner, "Введите ширину (м): ");
        double height = getPositiveDoubleInput(scanner, "Введите высоту (м): ");
        double length = getPositiveDoubleInput(scanner, "Введите длину (м): ");
        double weight = getPositiveDoubleInput(scanner, "Введите массу (кг): ");
        double speed = getPositiveDoubleInput(scanner, "Введите скорость (км/ч): ");

        Vehicle vehicle = null;

        switch (choice) {
            case 1:
                vehicle = new Car(width, height, length, weight, speed);
                break;
            case 2:
                vehicle = new Truck(width, height, length, weight, speed);
                break;
            case 3:
                vehicle = new Bus(width, height, length, weight, speed);
                break;
            case 4:
                vehicle = new Trailer(width, height, length, weight, speed);
                break;
            case 5:
                vehicle = new CarWithTrailer(width, height, length, weight, speed);
                break;
        }

        if (vehicle != null) {
            boolean result = vehicle.pass();
            System.out.println("Транспортное средство проходит контроль: " + (result ? "Да" : "Нет"));
        }
    }

    /**
     * Получает число типа int с проверкой на диапазон.
     *
     * @param scanner объект Scanner для чтения ввода.
     * @param prompt  Сообщение для пользователя.
     * @param min     Минимальное допустимое значение.
     * @param max     Максимальное допустимое значение.
     * @return Введенное число типа int.
     */
    private static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                }
            } else {
                scanner.next();  // очищаем некорректный ввод
            }
            System.out.println("Ошибка: введите число от " + min + " до " + max + ".");
        }
    }

    /**
     * Получает положительное число типа double с проверкой на корректность и положительность.
     * Использует строковый ввод для корректной работы с точкой как разделителем дробной части.
     *
     * @param scanner объект Scanner для чтения ввода.
     * @param prompt  Сообщение для пользователя.
     * @return Введенное положительное число типа double.
     */
    private static double getPositiveDoubleInput(Scanner scanner, String prompt) {
        double input;
        while (true) {
            System.out.print(prompt);
            String userInput = scanner.next();
            try {
                input = Double.parseDouble(userInput);
                if (input > 0) {
                    return input;
                } else {
                    System.out.println("Ошибка: введите положительное число.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }
}
