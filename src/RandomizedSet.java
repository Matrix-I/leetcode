import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class RandomizedSet {
  private final HashMap<Integer, Integer> locationsByValue;
  private final ArrayList<Integer> values;
  private final Random random;

  public RandomizedSet() {
    locationsByValue = new HashMap<>();
    values = new ArrayList<>();
    random = new Random();
  }

  public boolean insert(int val) {
    Integer location = locationsByValue.get(val);
    if (location != null) {
      return false;
    }

    values.add(val);
    locationsByValue.put(val, values.size() - 1);
    return true;
  }

  public boolean remove(int val) {
    Integer location = locationsByValue.remove(val);
    if (location == null) {
      return false;
    }

    Integer tmp = values.get(values.size() - 1);
    values.set(location, tmp);
    values.remove(values.size() - 1);

    if (tmp != val) {
      locationsByValue.put(tmp, location);
    }

    return true;
  }

  public int getRandom() {
    return values.get(random.nextInt(values.size()));
  }
}

/**
 * Your RandomizedSet object will be instantiated and called as such: RandomizedSet obj = new
 * RandomizedSet(); boolean param_1 = obj.insert(val); boolean param_2 = obj.remove(val); int
 * param_3 = obj.getRandom();
 */
