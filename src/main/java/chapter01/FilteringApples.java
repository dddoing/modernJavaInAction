package chapter01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        );

        List<Apple> greenApple = filterApples(inventory,FilteringApples::isGreenApple);
        System.out.println(greenApple);

        List<Apple> heavyApple = filterApples(inventory,FilteringApples::isHeavyApple);
        System.out.println(heavyApple);
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {

        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {
        //
        private int weight=0;
        private String color = "";

        public Apple(int weight,String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() { return this.weight;}

        public void setWeight(int weight) { this.weight = weight; }

        public String getColor() { return this.color; }

        public void setColor(String color) { this.color = color; }

        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}",color,weight);
        }
    }
}
