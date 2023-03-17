import java.util.Scanner;

public class Main {

    // Реализованный интерфейс, как заказывали
    public static String calc(String input){
        return Calculator.Calculate(input);
    }


    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        info();
        while(true){
            System.out.println("________________________________________________________________________________________");
            System.out.println("Введите ЧИСЛО1 Операция ЧИСЛО2 в нужной системе счисления либо пустую строку для выхода");
            System.out.println("Например: XX + DL   или   23 - 12  (не забудьте отделить значения пробелом)");
            String s=scanner.nextLine();
            s=s.trim();
            if (s.equals("")) break;
            String val=calc(s);
            if (val==null){
                //Здесь можно без Exception, но по условиям задачи требуется.
                try {
                    throw new Exception();
                } catch (Exception e){
                    System.out.println("Увы, здeсь калькулятор бессилен: либо неверные данные, либо деление на ноль, либо что-то еще...");
                }
            } else System.out.println("Ответ: "+val);
        }
        System.out.println("Удачного дня!");
    }

    public static void info(){
        System.out.println("Кальулятор для арабских и риских цифр.");
        System.out.println("Поддерживает операции: + - / *");
        System.out.println("Форамт ввода: ЧИСЛО1 Операция ЧИСЛО2");
        System.out.println("Числа могут быть либо арабские либо римские в рамках одной операции");

    }

}
