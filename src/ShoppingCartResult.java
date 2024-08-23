public class ShoppingCartResult {
    public int maxValue;
    public int[] selectedIndices;

    public ShoppingCartResult(int maxValue, int[] selectedIndices) {
        this.maxValue = maxValue;
        this.selectedIndices = selectedIndices;
    }
}