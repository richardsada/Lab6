/**
 * Класс Truck представляет грузовик.
 * Наследует класс Vehicle и задает параметры для грузовиков.
 */
public class Truck extends Vehicle {
    private static final double MAX_WIDTH = 2.5;
    private static final double MAX_HEIGHT = 4.0;
    private static final double MAX_LENGTH = 12.0;
    private static final double MAX_WEIGHT = 12000;
    private static final double MAX_SPEED = 50;

    /**
     * Конструктор для создания грузовика.
     *
     * @param width  Ширина грузовика.
     * @param height Высота грузовика.
     * @param length Длина грузовика.
     * @param weight Масса грузовика.
     * @param speed  Скорость грузовика.
     */
    public Truck(double width, double height, double length, double weight, double speed) {
        super(width, height, length, weight, speed);
    }

    /**
     * Проверяет, проходит ли грузовик контроль по заданным параметрам.
     *
     * @return true, если грузовик проходит контроль, иначе false.
     */
    @Override
    public boolean pass() {
        return (width <= MAX_WIDTH && height <= MAX_HEIGHT && length <= MAX_LENGTH &&
                weight <= MAX_WEIGHT && speed <= MAX_SPEED);
    }
}
