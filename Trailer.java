/**
 * Класс Trailer представляет трейлер.
 * Наследует класс Vehicle и задает параметры для трейлеров.
 */
public class Trailer extends Vehicle {
    private static final double MAX_WIDTH = 2.6;
    private static final double MAX_HEIGHT = 4.5;
    private static final double MAX_LENGTH = 18.0;
    private static final double MAX_WEIGHT = 30000;
    private static final double MAX_SPEED = 40;

    /**
     * Конструктор для создания трейлера.
     *
     * @param width  Ширина трейлера.
     * @param height Высота трейлера.
     * @param length Длина трейлера.
     * @param weight Масса трейлера.
     * @param speed  Скорость трейлера.
     */
    public Trailer(double width, double height, double length, double weight, double speed) {
        super(width, height, length, weight, speed);
    }

    /**
     * Проверяет, проходит ли трейлер контроль по заданным параметрам.
     *
     * @return true, если трейлер проходит контроль, иначе false.
     */
    @Override
    public boolean pass() {
        return (width <= MAX_WIDTH && height <= MAX_HEIGHT && length <= MAX_LENGTH &&
                weight <= MAX_WEIGHT && speed <= MAX_SPEED);
    }
}
