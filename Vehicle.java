/**
 * Абстрактный класс Vehicle представляет транспортное средство.
 * Описывает основные параметры и метод для проверки, может ли транспортное средство пройти контроль.
 */
public abstract class Vehicle {
    protected double width;
    protected double height;
    protected double length;
    protected double weight;
    protected double speed;

    /**
     * Конструктор для создания транспортного средства.
     *
     * @param width  Ширина транспортного средства.
     * @param height Высота транспортного средства.
     * @param length Длина транспортного средства.
     * @param weight Масса транспортного средства.
     * @param speed  Скорость транспортного средства.
     */
    public Vehicle(double width, double height, double length, double weight, double speed) {
        this.width = width;
        this.height = height;
        this.length = length;
        this.weight = weight;
        this.speed = speed;
    }

    /**
     * Абстрактный метод, который должен быть реализован в дочерних классах.
     * Выполняет проверку, может ли транспортное средство пройти контроль.
     *
     * @return true, если транспортное средство проходит контроль, иначе false.
     */
    public abstract boolean pass();
}
