package lotto.domain;

public class Budget{

    private int budget;

    public Budget(int budget) {
        validMinValue(budget);
        this.budget = budget;
    }

    private void validMinValue(int budget) {
        if(budget % 1000 != 0)
            throw new IllegalArgumentException("100원 단위는 입력할 수 없습니다: " + budget);
    }

    public int purchasableQuantity(Price price) {
        return price.divide(budget);
    }

    public boolean isEnoughToPay(Price price) {
        return price.isEnough(budget);
    }

    public boolean isEnoughToPay(Price price, int count) {
        return price.isEnough(budget, count);
    }

    public Budget spend(Price price) {
        return new Budget(budget - price.getValue());
    }

    public Budget spend(Price price, Count issueCount) {
        return new Budget(budget - price.multiple(issueCount));
    }
}
