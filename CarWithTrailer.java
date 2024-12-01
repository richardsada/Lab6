/**
 * Класс CarWithTrailer представляет легковой автомобиль с прицепом.
 * Наследует класс Vehicle и задает параметры для таких транспортных средств.
 */
public class CarWithTrailer extends Vehicle {
    private static final double MAX_WIDTH = 2.2;
    private static final double MAX_HEIGHT = 2.5;
    private static final double MAX_LENGTH = 6.5;
    private static final double MAX_WEIGHT = 5000;
    private static final double MAX_SPEED = 50;

    /**
     * Конструктор для создания легкового автомобиля с прицепом.
     *
     * @param width  Ширина автомобиля с прицепом.
     * @param height Высота автомобиля с прицепом.
     * @param length Длина автомобиля с прицепом.
     * @param weight Масса автомобиля с прицепом.
     * @param speed  Скорость автомобиля с прицепом.
     */
    public CarWithTrailer(double width, double height, double length, double weight, double speed) {
        super(width, height, length, weight, speed);
    }

    /**
     * Проверяет, проходит ли легковой автомобиль с прицепом контроль по заданным параметрам.
     *
     * @return true, если автомобиль с прицепом проходит контроль, иначе false.
     */
    @Override
    public boolean pass() {
        return (width <= MAX_WIDTH && height <= MAX_HEIGHT && length <= MAX_LENGTH &&
                weight <= MAX_WEIGHT && speed <= MAX_SPEED);
    }
}
