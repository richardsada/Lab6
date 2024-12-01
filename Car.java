/**
 * Класс Car представляет легковую машину.
 * Наследует класс Vehicle и задает параметры для легкового автомобиля.
 */
public class Car extends Vehicle {
    private static final double MAX_WIDTH = 2.0;
    private static final double MAX_HEIGHT = 2.0;
    private static final double MAX_LENGTH = 5.0;
    private static final double MAX_WEIGHT = 3500;
    private static final double MAX_SPEED = 60;

    /**
     * Конструктор для создания легковой машины.
     *
     * @param width  Ширина машины.
     * @param height Высота машины.
     * @param length Длина машины.
     * @param weight Масса машины.
     * @param speed  Скорость машины.
     */
    public Car(double width, double height, double length, double weight, double speed) {
        super(width, height, length, weight, speed);
    }

    /**
     * Проверяет, проходит ли легковая машина контроль по заданным параметрам.
     *
     * @return true, если машина проходит контроль, иначе false.
     */
    @Override
    public boolean pass() {
        return (width <= MAX_WIDTH && height <= MAX_HEIGHT && length <= MAX_LENGTH &&
                weight <= MAX_WEIGHT && speed <= MAX_SPEED);
    }
}
