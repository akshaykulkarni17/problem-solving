import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

class Employee{
    int id;
    String name;
    double salary;
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}
class User {

    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", age=" + age + '}';
    }

    public static class streams {

        public static void main(String[] args) {

            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
            Set<Integer> set = new HashSet<>(numbers);
            int result1 = numbers.stream().reduce(0, (subtotal, element) -> subtotal + element);
            System.out.println(result1);

            int result2 = numbers.stream().reduce(0, Integer::max);
            System.out.println(result2);

            List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
            String result3 = letters.stream().reduce("", (partialString, element) -> partialString + element);
            System.out.println(result3);

            String result4 = letters.stream().reduce("", String::concat);
            System.out.println(result4);

            String result5 = letters.stream().reduce("", (partialString, element) -> partialString.toUpperCase() + element.toUpperCase());
            System.out.println(result5);

            List<User> users = Arrays.asList(new User("John", 30), new User("Julie", 35));
            int result6 = users.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
            System.out.println(result6);

            String result7 = letters.parallelStream().reduce("", String::concat);
            System.out.println(result7);

            int result8 = users.parallelStream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
            System.out.println(result8);

            Employee[] arrayOfEmps = {
                    new Employee(1, "Jeff Bezos", 100000.0),
                    new Employee(2, "Bill Gates", 200000.0),
                    new Employee(3, "Mark Zuckerberg", 300000.0)
            };
            Stream.of(arrayOfEmps);
        }
    }
}