package com.example.classesandobjects;

public class Human {
    private int age;
    private String name;

    static int counter =0;
    int counter2 = 0;

    static void func1() {
        System.out.println(counter);
    }

    Human(int myage, String myName) {
        age = myage;
        name = myName;
    }

//    int getAge() {
//        return age;
//    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void tellYourAge() {
        System.out.println(age);
    }

    void singing() {
        System.out.println("I am Singing");
    }

//    public String toString() {
//        return this.name + " - " + this.age;
//    }


    @Override
    public String toString() {
        return "Human{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public void printSomething(Integer... ints) {
        for(Integer s: ints) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
//        Human yogesh = new Human();

        Human yogesh = new Human(21, "Yogesh");

        Human amit = new Human(23, "Amit");
        Human sumit = new Human(25, "Sumit");

        yogesh.counter2+=1;
        amit.counter2+=1;

        Human.counter+=1;
        Human.counter+=1;

        System.out.println(yogesh.counter2);
        System.out.println(yogesh.counter);

        Human.func1();


//        yogesh.age=21;
//        yogesh.name="Yogesh";
//
//        yogesh.singing();
//        yogesh.tellYourAge();
//
//        System.out.println(yogesh);
//
//        yogesh.printSomething( 5, 5,6, 7);
    }
}
