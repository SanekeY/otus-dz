import animals.AbsAnimal;
import data.AnimalTypesData;
import data.CommandsData;
import factory.FactoryAnimal;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FactoryAnimal factoryAnimal = new FactoryAnimal();
        AbsAnimal animal = factoryAnimal.create(AnimalTypesData.DOG);

        List<AbsAnimal> animals = new ArrayList<>();

        CommandsData[] commandsData = CommandsData.values();

        List<String> commandsNames = new ArrayList<>();
        for (CommandsData commandData : commandsData) {
            commandsNames.add(commandData.name().toLowerCase());
        }

        while (true) {
            System.out.printf("Введите одну из команд %s:\n", String.join("/", commandsNames));
            String commandStrUser = scanner.next();

            boolean isCommandExist = false;
            for (CommandsData command : commandsData) {
                if (command.name().equals(commandStrUser.trim().toUpperCase())) {
                    isCommandExist = true;
                    break;
                }
            }
            if (!isCommandExist) {
                System.out.println("Вы ввели неверную команду, повторите ввод");
            }
            switch (CommandsData.valueOf(commandStrUser)) {
                case ADD: {

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
        }
    }
}





