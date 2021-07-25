package chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class Sort {
    //
    public static void main(String[] args) {
        //
        List<Apple> inventory = new ArrayList<>();

        inventory.addAll(Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        ));

        inventory.sort(new AppleComparator());
        System.out.println(inventory);

        inventory.set(1,new Apple(30,Color.GREEN));

        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });

        System.out.println(inventory);
        inventory.set(1,new Apple(20,Color.RED));

        inventory.sort((a1,a2)-> a1.getWeight()- a2.getWeight());
        System.out.println(inventory);

        inventory.set(1,new Apple(10,Color.RED));
        inventory.sort(comparing(Apple::getWeight));

        System.out.println(inventory);


    }

    static class AppleComparator implements Comparator<Apple> {

        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

}
