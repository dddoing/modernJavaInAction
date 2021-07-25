package chapter05;

import chapter04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static chapter04.Dish.menu;
import static java.util.stream.Collectors.toList;

public class Filtering {

    public static void main(String[] args) {
        //
        System.out.println("Filtering Predicate");
        List<Dish> vegetarianMenu = menu.stream().filter(Dish::isVegetarian).collect(toList());
        vegetarianMenu.forEach(System.out::println);

        System.out.println("Filtering unique number");
        List<Integer> number = Arrays.asList(1,2,3,4,5,2,4);
        number.stream().filter(i->i%2==0).distinct().forEach(System.out::println);

        System.out.println("Filtering sorted menu");
        List<Dish> specialMenu = Arrays.asList(
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));

        List<Dish> filterMenu = specialMenu.stream().filter(dish -> dish.getCalories() <320)
                .collect(toList());

        filterMenu.forEach(System.out::println);

        System.out.println("Sorted menu sliced with takeWhile():");
        List<Dish> slicedMenu1 = specialMenu.stream().takeWhile(dish -> dish.getCalories()<320).collect(toList());
        slicedMenu1.forEach(System.out::println);

        System.out.println("Sorted menu sliced with dropWhile():");
        List<Dish> slicedMenu2 = specialMenu.stream().dropWhile(dish -> dish.getCalories()<320).collect(toList());
        slicedMenu2.forEach(System.out::println);

        System.out.println("Normal:");
        List<Dish> dishesN = specialMenu.stream().filter(dish -> dish.getCalories() > 300).collect(toList());
        dishesN.forEach(System.out::println);

        System.out.println("Truncating a stream:");
        List<Dish> dishes = specialMenu.stream().filter(dish -> dish.getCalories() > 300).limit(2).collect(toList());
        dishes.forEach(System.out::println);

        System.out.println("Skipping elements:");
        List<Dish> dishes1 = specialMenu.stream().filter(dish -> dish.getCalories()>300).skip(2).collect(toList());
        dishes1.forEach(System.out::println);

        System.out.println("Quiz");
        List<Dish> quiz = specialMenu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT).limit(2).collect(toList());
        quiz.forEach(System.out::println);
    }

}
