package Java_professional.Lesson_1;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> boxItems = new ArrayList<>();

    public void add(Class<T> fruit, int count) {
        try {
            for (int i = 0; i < count; i++) {
                boxItems.add(fruit.getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Float getWeight(List<? extends Fruit> obj) {
        Float res=0f;
        if (obj.size()>0) {
            res = obj.size() * obj.get(0).getWeight();
        }
        return res;
    }

    public Float getWeight() {
        Float res=0f;
        if (boxItems.size()>0) {
            res = boxItems.size() * boxItems.get(0).getWeight();
        }
        return res;
    }

    public boolean compare(Box<?> another) {
        boolean res=false;
        if (boxItems.size()>0 && another.getBoxItems().size()>0) {
            if (Math.abs(getWeight() - getWeight(another.getBoxItems()))<0.00001) res = true;
        }
        return res;
    }

    public void fillBox(Box<T> another) {
        List<T> mas = another.getBoxItems();
        another.getBoxItems().addAll(boxItems);
        boxItems.clear();
    }

    public List<T> getBoxItems() {
        return boxItems;
    }
}
