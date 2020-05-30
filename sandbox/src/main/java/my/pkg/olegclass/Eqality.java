package my.pkg.olegclass;

public class Eqality {

    public static void main (String[] args){
        String s1 = "FireFox 2.0";
        String s2 = "FireFox " + Math.sqrt(4.0);

        System.out.println(s1 == s2); // Сравниваем числа
        System.out.println(s1.equals(s2)); //Сравниваем объекты
    }
}
