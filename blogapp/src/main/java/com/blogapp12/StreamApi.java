package com.blogapp12;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StreamApi {

    public static void main(String[] args) {
     /*   Predicate<Integer> val = n-> n ==100;
        boolean res = val.test(10);
        System.out.println(res);

        Predicate<Integer> val = n -> n % 2 != 0;
        boolean res = val.test(64);
        System.out.println(res);

        //equalIgnoreCase --> is not caseSensitive it returns true if values are same even if the case values are different
        Predicate<String> data = str-> str.equalsIgnoreCase("Mike");
        boolean result = data.test("mikE");
        System.out.println(result);

        //Interger values
        List<Integer> data = Arrays.asList(10, 20 , 30 ,55 , 76);
        List<Integer> newData = data.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println(newData);

        //String values
        List<String> data = Arrays.asList("mike", "sam", "tom", "steven");
        List<String> newData = data.stream().filter(str -> str.startsWith("s")).collect(Collectors.toList());
        System.out.println(newData);

        List<String> data = Arrays.asList("adam", "steve", "adam", "noel");
        List<String> newData = data.stream().filter(str -> str.equalsIgnoreCase("adam")).collect(Collectors.toList());
        System.out.println(newData.size());

        List<String> data = Arrays.asList("adam", "akash", "amith", "adam", "ajay","amith");
        List<String> newData = data.stream().distinct().collect(Collectors.toList());
        System.out.println(newData);

        List<Integer> data = Arrays.asList(10, 34, 66, 34, 29, 66);
        List<Integer> newdata = data.stream().distinct().collect(Collectors.toList());
        System.out.println(newdata);

        Function<Integer, Integer> val = n->n * n;
        Integer res = val.apply(10);
        System.out.println(res);

       int n = 10;
        System.out.println( 100 + n + "100" );

        Function<Integer, String> val = n-> "The output is :" + n;
        String res = val.apply(18);
        System.out.println(res);

        List<Integer> data = Arrays.asList(10,5, 6 , 9);
        List<Integer> newData = data.stream().map(n-> n * n).collect(Collectors.toList());
        System.out.println(newData);

        List<String> data = Arrays.asList("adam", "eve", "john");
        List<String> newData = data.stream().map(str-> str.toUpperCase()).collect(Collectors.toList());
        System.out.println(newData);

        Consumer<Integer> val = n -> System.out.println(n);
        val.accept(67);

        Supplier<Double> val = ()->Math.random();
       Double data = val.get();
        System.out.println(data);

        Predicate<Integer> val = n-> n %2==0;
        boolean res = val.test(76);
        System.out.println(res);

        Function<Integer, Integer> val = n-> n*n;
        Integer res = val.apply(10);
        System.out.println(res);

        Consumer<String> val = n-> System.out.println(n);
        val.accept("hello");

        Supplier<Double> val = ()->Math.random();
        double res = val.get();
        System.out.println(res);

       List<Integer> data = Arrays.asList(10, 20 , 30 );
       List<Integer> newData = data.stream().map(n-> n * n).collect(Collectors.toList());
        System.out.println(newData);

        List<String> data = Arrays.asList("adam", "adam", "ajay", "lohith");
       // List<String> newData = data.stream().filter(n-> n.startsWith("a")).collect(Collectors.toList());
     // List<String> newData = data.stream().filter(str->str.equalsIgnoreCase("adam")).collect(Collectors.toList());
      //  List<String> newData = data.stream().distinct().collect(Collectors.toList());
        List<String> newData = data.stream().map(str -> str.toUpperCase()).collect(Collectors.toList());
        System.out.println(newData); */
        List<Employee> data = Arrays.asList(new Employee(1, "swati", 5000),
                new Employee(2, "john", 3000),
                new Employee(3, "ram", 7000),
        new Employee(4, "sam", 9000)
        );
//Map<Integer, List<Employee>> newData = data.stream().collect(Collectors.groupingBy(e->e.getSalary()));
        Map<Long, List<Employee>> newData = data.stream().collect(Collectors.groupingBy(e -> e.getSalary()));
        // List<Employee> newData = data.stream().filter(e -> e.getSalary() > 5000).collect(Collectors.toList());
       // System.out.println(newData);
      // List<String> newData = data.stream().map(Employee::getName).map(String::toUpperCase).collect(Collectors.toList());
     //  List<String> newData = data.stream().map(e -> e.getName()).collect(Collectors.toList());
     /*   for (Employee emp : newData){
            System.out.println(emp.getId());
            System.out.println(emp.getName());
            System.out.println(emp.getSalary());
            }*/
       // Map<String, List<Employee>> newData = data.stream().collect(Collectors.groupingBy(e-> e.getName()));
      System.out.println(newData);
        }

    }


