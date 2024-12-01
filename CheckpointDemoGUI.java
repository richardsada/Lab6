import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckpointDemoGUI extends JFrame {
    private JComboBox<String> vehicleTypeComboBox;
    private JTextField widthField, heightField, lengthField, weightField, speedField;
    private JButton checkButton, saveButton, loadButton, editButton;
    private JTextArea resultArea;
    private JList<String> vehicleList;
    private List<VehicleData> vehicleDataList;
    private int currentIndex = -1;

    public CheckpointDemoGUI() {
        setTitle("Checkpoint Demo");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Панель для выбора типа транспортного средства
        JPanel typePanel = new JPanel();
        typePanel.setLayout(new FlowLayout());
        typePanel.setBackground(new Color(255, 255, 255, 255));
        String[] vehicleTypes = {"Легковая машина", "Грузовик", "Автобус", "Трейлер", "Легковой автомобиль с прицепом"};
        vehicleTypeComboBox = new JComboBox<>(vehicleTypes);
        typePanel.add(new JLabel("Тип транспортного средства:"));
        typePanel.add(vehicleTypeComboBox);

        // Панель для ввода данных
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.setBackground(new Color(255, 255, 255));
        widthField = new JTextField();
        heightField = new JTextField(10);
        lengthField = new JTextField(10);
        weightField = new JTextField(10);
        speedField = new JTextField(10);

        JLabel widthLabel = new JLabel("Ширина (м):", SwingConstants.RIGHT);
        widthLabel.setPreferredSize(new Dimension(150, 20));
        JLabel heightLabel = new JLabel("Высота (м):", SwingConstants.RIGHT);
        heightLabel.setPreferredSize(new Dimension(150, 20));
        JLabel lengthLabel = new JLabel("Длина (м):", SwingConstants.RIGHT);
        lengthLabel.setPreferredSize(new Dimension(150, 20));
        JLabel weightLabel = new JLabel("Масса (кг):", SwingConstants.RIGHT);
        weightLabel.setPreferredSize(new Dimension(150, 20));
        JLabel speedLabel = new JLabel("Скорость (км/ч):", SwingConstants.RIGHT);
        speedLabel.setPreferredSize(new Dimension(150, 20));

        inputPanel.add(widthLabel);
        inputPanel.add(widthField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightField);
        inputPanel.add(lengthLabel);
        inputPanel.add(lengthField);
        inputPanel.add(weightLabel);
        inputPanel.add(weightField);
        inputPanel.add(speedLabel);
        inputPanel.add(speedField);

        // Панель для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 240, 240));
        checkButton = new JButton("Проверить");
        saveButton = new JButton("Сохранить");
        loadButton = new JButton("Загрузить");
        editButton = new JButton("Редактировать");
        buttonPanel.add(checkButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(editButton);

        // Панель для вывода результата
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setPreferredSize(new Dimension(500, 100));
        resultArea.setBackground(new Color(255, 255, 255));
        resultArea.setForeground(new Color(0, 0, 0));
        resultArea.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        // Панель для отображения списка транспортных средств
        vehicleList = new JList<>();
        vehicleList.setBackground(new Color(255, 255, 255));
        vehicleList.setForeground(new Color(0, 0, 0));
        vehicleList.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        vehicleList.addListSelectionListener(e -> {
            currentIndex = vehicleList.getSelectedIndex();
            if (currentIndex != -1) {
                VehicleData vehicleData = vehicleDataList.get(currentIndex);
                vehicleTypeComboBox.setSelectedItem(vehicleData.getType());
                widthField.setText(String.valueOf(vehicleData.getWidth()));
                heightField.setText(String.valueOf(vehicleData.getHeight()));
                lengthField.setText(String.valueOf(vehicleData.getLength()));
                weightField.setText(String.valueOf(vehicleData.getWeight()));
                speedField.setText(String.valueOf(vehicleData.getSpeed()));
            }
        });

        // Панель для центральной части интерфейса
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(typePanel, BorderLayout.NORTH);
        centerPanel.add(inputPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Панель для отображения результата под кнопками
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Добавление панелей в окно
        add(new JScrollPane(vehicleList), BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        // Обработчики событий
        checkButton.addActionListener(new CheckButtonListener());
        saveButton.addActionListener(new SaveButtonListener());
        loadButton.addActionListener(new LoadButtonListener());
        editButton.addActionListener(new EditButtonListener());

        vehicleDataList = new ArrayList<>();
    }

    private class CheckButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double width = Double.parseDouble(widthField.getText());
                double height = Double.parseDouble(heightField.getText());
                double length = Double.parseDouble(lengthField.getText());
                double weight = Double.parseDouble(weightField.getText());
                double speed = Double.parseDouble(speedField.getText());

                Vehicle vehicle = null;
                int choice = vehicleTypeComboBox.getSelectedIndex();

                switch (choice) {
                    case 0:
                        vehicle = new Car(width, height, length, weight, speed);
                        break;
                    case 1:
                        vehicle = new Truck(width, height, length, weight, speed);
                        break;
                    case 2:
                        vehicle = new Bus(width, height, length, weight, speed);
                        break;
                    case 3:
                        vehicle = new Trailer(width, height, length, weight, speed);
                        break;
                    case 4:
                        vehicle = new CarWithTrailer(width, height, length, weight, speed);
                        break;
                }

                if (vehicle != null) {
                    boolean result = vehicle.pass();
                    resultArea.setText("Транспортное средство проходит контроль: " + (result ? "Да" : "Нет"));
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Ошибка: введите корректные числа.");
            }
        }
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double width = Double.parseDouble(widthField.getText());
                double height = Double.parseDouble(heightField.getText());
                double length = Double.parseDouble(lengthField.getText());
                double weight = Double.parseDouble(weightField.getText());
                double speed = Double.parseDouble(speedField.getText());

                String vehicleType = vehicleTypeComboBox.getSelectedItem().toString();
                VehicleData vehicleData = new VehicleData(vehicleType, width, height, length, weight, speed);

                if (currentIndex == -1) {
                    vehicleDataList.add(vehicleData);
                } else {
                    vehicleDataList.set(currentIndex, vehicleData);
                    currentIndex = -1;
                }

                saveDataToFile();
                displayVehicleData();
                clearInputFields();
                resultArea.setText("Данные сохранены.");
            } catch (NumberFormatException ex) {
                resultArea.setText("Ошибка: введите корректные числа.");
            }
        }
    }

    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadDataFromFile();
            displayVehicleData();
        }
    }

    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentIndex != -1) {
                VehicleData vehicleData = vehicleDataList.get(currentIndex);
                vehicleTypeComboBox.setSelectedItem(vehicleData.getType());
                widthField.setText(String.valueOf(vehicleData.getWidth()));
                heightField.setText(String.valueOf(vehicleData.getHeight()));
                lengthField.setText(String.valueOf(vehicleData.getLength()));
                weightField.setText(String.valueOf(vehicleData.getWeight()));
                speedField.setText(String.valueOf(vehicleData.getSpeed()));
            }
        }
    }

    private void saveDataToFile() {
        try (FileWriter writer = new FileWriter("vehicle_data.txt")) {
            for (VehicleData vehicleData : vehicleDataList) {
                writer.write("Тип транспортного средства: " + vehicleData.getType() + "\n");
                writer.write("Ширина (м): " + vehicleData.getWidth() + "\n");
                writer.write("Высота (м): " + vehicleData.getHeight() + "\n");
                writer.write("Длина (м): " + vehicleData.getLength() + "\n");
                writer.write("Масса (кг): " + vehicleData.getWeight() + "\n");
                writer.write("Скорость (км/ч): " + vehicleData.getSpeed() + "\n");
                writer.write("-------------------------\n");
            }
        } catch (IOException ex) {
            resultArea.setText("Ошибка при сохранении данных: " + ex.getMessage());
        }
    }

    private void loadDataFromFile() {
        vehicleDataList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("vehicle_data.txt"))) {
            String line;
            VehicleData vehicleData = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Тип транспортного средства:")) {
                    if (vehicleData != null) {
                        vehicleDataList.add(vehicleData);
                    }
                    vehicleData = new VehicleData();
                    vehicleData.setType(line.substring(line.indexOf(":") + 2).trim());
                } else if (line.startsWith("Ширина (м):")) {
                    vehicleData.setWidth(Double.parseDouble(line.substring(line.indexOf(":") + 2).trim()));
                } else if (line.startsWith("Высота (м):")) {
                    vehicleData.setHeight(Double.parseDouble(line.substring(line.indexOf(":") + 2).trim()));
                } else if (line.startsWith("Длина (м):")) {
                    vehicleData.setLength(Double.parseDouble(line.substring(line.indexOf(":") + 2).trim()));
                } else if (line.startsWith("Масса (кг):")) {
                    vehicleData.setWeight(Double.parseDouble(line.substring(line.indexOf(":") + 2).trim()));
                } else if (line.startsWith("Скорость (км/ч):")) {
                    vehicleData.setSpeed(Double.parseDouble(line.substring(line.indexOf(":") + 2).trim()));
                }
            }
            if (vehicleData != null) {
                vehicleDataList.add(vehicleData);
            }
        } catch (IOException ex) {
            resultArea.setText("Ошибка при загрузке данных: " + ex.getMessage());
        }
    }

    private void displayVehicleData() {
        List<String> vehicleStrings = new ArrayList<>();
        for (int i = 0; i < vehicleDataList.size(); i++) {
            VehicleData vehicleData = vehicleDataList.get(i);
            vehicleStrings.add("Запись " + (i + 1) + ": " + vehicleData.getType());
        }
        vehicleList.setListData(vehicleStrings.toArray(new String[0]));
    }

    private void clearInputFields() {
        vehicleTypeComboBox.setSelectedIndex(0);
        widthField.setText("");
        heightField.setText("");
        lengthField.setText("");
        weightField.setText("");
        speedField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CheckpointDemoGUI gui = new CheckpointDemoGUI();
            gui.setVisible(true);
        });
    }
}

class VehicleData {
    private String type;
    private double width;
    private double height;
    private double length;
    private double weight;
    private double speed;

    public VehicleData() {
    }

    public VehicleData(String type, double width, double height, double length, double weight, double speed) {
        this.type = type;
        this.width = width;
        this.height = height;
        this.length = length;
        this.weight = weight;
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;

    }
}
