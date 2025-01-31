import animals.AbsAnimal;
import data.AnimalTypesData;
import data.ColorData;
import data.CommandsData;
import factory.FactoryAnimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FactoryAnimal factoryAnimal = new FactoryAnimal();
        List<AbsAnimal> animals = new ArrayList<>();

        CommandsData[] commandsData = CommandsData.values();
        List<String> commandsNames = new ArrayList<>();
        for (CommandsData commandData : commandsData) {
            commandsNames.add(commandData.name().toLowerCase());
        }

        while (true) {
            System.out.printf("Введите одну из команд %s:\n", String.join("/", commandsNames));
            String commandStrUser = scanner.nextLine();
            String commandStrUserUpper = commandStrUser.trim().toUpperCase();

            boolean isCommandExist = false;
            for (CommandsData command : commandsData) {
                if (command.name().equals(commandStrUserUpper)) {
                    isCommandExist = true;
                    break;
                }
            }
            if (!isCommandExist) {
                System.out.println("Вы ввели неверную команду, повторите ввод");
                continue;
            }

            try {
                switch (CommandsData.valueOf(commandStrUserUpper)) {
                    case ADD: {
                        System.out.println("Введите тип животного (CAT, DOG, DUCK):");
                        String animalTypeStr = scanner.nextLine().trim().toUpperCase();
                        AnimalTypesData animalType = AnimalTypesData.valueOf(animalTypeStr);
                        AbsAnimal animal = factoryAnimal.create(animalType);

                        System.out.println("Введите имя:");
                        String name = scanner.nextLine();
                        animal.setName(name);

                        System.out.println("Введите возраст:");
                        int age = Integer.parseInt(scanner.nextLine());
                        animal.setAge(age);

                        System.out.println("Введите вес:");
                        double weight = Double.parseDouble(scanner.nextLine());
                        animal.setWeight((int) weight);

                        System.out.println("Введите цвет:");
                        String color = scanner.nextLine();
                        animal.setColor(ColorData.valueOf(color));

                        animals.add(animal);
                        animal.say();

                        System.out.println("Животное " + animalType.name() + " добавлено!");

                        break;
                    }
                    case LIST: {
                        animals.forEach(an -> System.out.println(an.toString()));
                        break;
                    }
                    case EXIT: {
                        System.out.println("Пока!");
                        System.exit(0);
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Неверная команда: " + commandStrUser);
            }

        }
    }
}