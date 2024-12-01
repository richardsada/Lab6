/**
 * Класс Bus представляет автобус.
 * Наследует класс Vehicle и задает параметры для автобусов.
 */
public class Bus extends Vehicle {
    private static final double MAX_WIDTH = 2.5;
    private static final double MAX_HEIGHT = 4.0;
    private static final double MAX_LENGTH = 15.0;
    private static final double MAX_WEIGHT = 18000;
    private static final double MAX_SPEED = 50;

    /**
     * Конструктор для создания автобуса.
     *
     * @param width  Ширина автобуса.
     * @param height Высота автобуса.
     * @param length Длина автобуса.
     * @param weight Масса автобуса.
     * @param speed  Скорость автобуса.
     */
    public Bus(double width, double height, double length, double weight, double speed) {
        super(width, height, length, weight, speed);
    }

    /**
     * Проверяет, проходит ли автобус контроль по заданным параметрам.
     *
     * @return true, если автобус проходит контроль, иначе false.
     */
    @Override
    public boolean pass() {
        return (width <= MAX_WIDTH && height <= MAX_HEIGHT && length <= MAX_LENGTH &&
                weight <= MAX_WEIGHT && speed <= MAX_SPEED);
    }
}
