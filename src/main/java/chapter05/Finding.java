package chapter05;

import chapter04.Dish;

import java.util.Optional;

import static chapter04.Dish.menu;

public class Finding {

    public static void main(String[] args) {
        //
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("isVegetarian");
        }
        if (menu.stream().allMatch(dish -> dish.getCalories() < 320)) {
            System.out.println("calories");
        }

        if (menu.stream().noneMatch(dish -> dish.getCalories() >= 320)) {
            System.out.println("calories");
        }

        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        dish.ifPresent(s-> System.out.println(s.getName()));
    }
}
