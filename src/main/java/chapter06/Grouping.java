package chapter06;

import java.util.*;

import static chapter06.Dish.dishTags;
import static chapter06.Dish.menu;
import static java.util.stream.Collectors.*;

public class Grouping {
    //
    enum CaloricLevel { DIET, NORMAL, FAT }
    public static void main(String[] args) {
        //
        Map<Dish.Type, List<Dish>>  dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);

        System.out.println(dishesByCaloricLevel());
        System.out.println(caloricDishesByType());
        System.out.println(dishNamesByType());
        System.out.println(groupDisTagsByType());
        System.out.println(dishesByTypeCaloricLevel());
        System.out.println(typesCount());
        System.out.println(mostCaloricByType());
        System.out.println(mostCaloricByType2());
        System.out.println(totalCaloriesByType());
        System.out.println(caloricLevelsByType());
        System.out.println(caloricLevelsByType2());
    }

    private static Map<CaloricLevel, List<Dish>> dishesByCaloricLevel() {
        return menu.stream().collect(
                groupingBy( dish -> {
                    if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })
        );
    }

    private static Map<Dish.Type,List<Dish>> caloricDishesByType() {
        //
        return menu.stream().collect(groupingBy(Dish::getType,filtering(dish -> dish.getCalories()>500,toList())));
    }

    private static Map<Dish.Type, List<String>> dishNamesByType() {
        return menu.stream().collect(groupingBy(Dish::getType,mapping(Dish::getName,toList())));
    }

    private static Map<Dish.Type, Set<String>> groupDisTagsByType() {
        return menu.stream().collect(groupingBy(Dish::getType,flatMapping(dish -> dishTags.get(dish.getName()).stream(),toSet())));
    }

    private static Map<Dish.Type, Map<CaloricLevel,List<Dish>>> dishesByTypeCaloricLevel() {
        //
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish ->{
                            if (dish.getCalories() <=400) return CaloricLevel.DIET;
                            else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                ));
    }

    private static Map<Dish.Type,Long> typesCount() {
        return menu.stream().collect(groupingBy(Dish::getType,counting()));
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricByType() {
        return menu.stream().collect(groupingBy(Dish::getType,maxBy(Comparator.comparingInt(Dish::getCalories))));
    }

    private static Map<Dish.Type, Dish> mostCaloricByType2() {
        return menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        )));
    }

    private static Map<Dish.Type, Integer> totalCaloriesByType() {
        return menu.stream().collect(groupingBy(Dish::getType,summingInt(Dish::getCalories)));
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        mapping( dish -> {
                            if (dish.getCalories() <=400) return CaloricLevel.DIET;
                            else if(dish.getCalories() <=700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        },toSet()))
        );
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType2() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        mapping( dish -> {
                            if (dish.getCalories() <=400) return CaloricLevel.DIET;
                            else if(dish.getCalories() <=700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        },toCollection(HashSet::new)))
        );
    }
}
