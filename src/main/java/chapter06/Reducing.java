package chapter06;


import static java.util.stream.Collectors.reducing;

import static chapter06.Dish.menu;

public class Reducing {
    //
    public static void main(String[] args) {
        //
        int totalCalories = menu.stream().collect(reducing(0,Dish::getCalories,Integer::sum));
        System.out.println(totalCalories);

        int totalCalories2 = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        System.out.println(totalCalories2);

        int totalCalories3 = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(totalCalories3);
    }
}
