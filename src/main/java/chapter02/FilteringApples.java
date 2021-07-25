package chapter02;

public class FilteringApples {

    public static void main(String args[]) {

    }

    static class Apple{

        private int weight = 0;
        private Color color;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }
    }

    enum Color {
        RED,
        GREEN
    }

    interface Predicate{

        boolean test(Apple a);
    }

    static class AppleWeightPredicate implements Predicate{

        @Override
        public boolean test(Apple a) {
            return a.getWeight() >150;
        }
    }

    static class AppleColorPredicate implements Predicate{

        @Override
        public boolean test(Apple a) {
            return Color.GREEN == a.getColor();
        }
    }

    static class AppleRedHeavyPredicate implements Predicate{

        @Override
        public boolean test(Apple a) {
            return a.getWeight() > 150 && a.getColor()==Color.RED;
        }
    }
}
