public class Main {
    public static void main(String[] args) {
        Item book = new Item("Book", 20, 2);
        Item shirt = new Item("Shirt", 30, 3);
        Item sunGlasses = new Item("Sunglasses", 20, 1);
        Item sunCream = new Item("Suncream", 5, 3);
        Item jacket = new Item("Jacket", 100, 6);

        int maxWeight = 10;
        Item[] sammlung = {book, shirt, sunGlasses, sunCream, jacket};

        ShoppingCartResult result = findBestValue(sammlung, maxWeight);
        System.out.println("Maximaler Wert: " + result.maxValue);
        System.out.print("Ausgewählte Gegenstände: ");
        for (int index : result.selectedIndices) {
            System.out.print(index + " ");
        }
    }

    public static ShoppingCartResult findBestValue(Item[] items, int maxWeight) {
        int n = items.length;
        int[][] maxValueShoppingCart = new int[n + 1][maxWeight + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= maxWeight; w++) {
                //Item kann aufgenommen werden
                if (items[i - 1].getWeight() <= w) {
                    //Neuer Wert ist der des aktuellen Items + maximale Wert, der für das verbleibende Gewicht, ohne das aktuelle Item erzielt werden kann.
                    maxValueShoppingCart[i][w] = Math.max(maxValueShoppingCart[i - 1][w], maxValueShoppingCart[i - 1][w - items[i - 1].getWeight()] + items[i - 1].getValue());
                } else {
                    // Item kann nicht aufgenommen werden
                    maxValueShoppingCart[i][w] = maxValueShoppingCart[i - 1][w];
                }
            }
        }

        int maxValue = maxValueShoppingCart[n][maxWeight];
        int w = maxWeight;
        //speicher Indexe in Liste
        java.util.List<Integer> selectedIndices = new java.util.ArrayList<>();

        for (int i = n; i > 0 && maxValue > 0; i--) {
            if (maxValue != maxValueShoppingCart[i - 1][w]) {
                selectedIndices.add(i - 1);
                w -= items[i - 1].getWeight();
                maxValue -= items[i - 1].getValue();
            }
        }

        java.util.Collections.reverse(selectedIndices);

        return new ShoppingCartResult(maxValueShoppingCart[n][maxWeight], selectedIndices.stream().mapToInt(Integer::intValue).toArray());
    }
}