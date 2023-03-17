public class Calculator {


    static String Calculate(String in) {
        String romeSimbol = "MDCLXVI";
        String operationSimbol = "+-*/";
        RomArab ra;

        in = in.trim();
        String[] elemenets = in.split("\\s+");

        //Если не равен 3: 2 значения и операнд, то взвращаем null
        if (elemenets.length != 3) return null;
        //Если размер символа операции не равен 1 или не найден в строке с операндами, то возвращаем null
        if (elemenets[1].length() != 1 || operationSimbol.indexOf(elemenets[1].charAt(0)) == -1) return null;


        //Если 1-ый символ 1-го значения не найден в строке с римскими символами, то считаем что арабские цифры и наоборот
        ra = (romeSimbol.indexOf(elemenets[0].charAt(0)) == -1) ? RomArab.ARAB : RomArab.ROM;

        Integer a, b, val;
        String result = "";
        switch (ra) {
            case ROM:
                a = Converter.romeToArabConvert(elemenets[0]);
                b = Converter.romeToArabConvert(elemenets[2]);
                if (a == null || b == null)
                    return null;
                val = calc(a, b, elemenets[1].charAt(0));
                if (val == null) return null;
                if (val <= 0)
                    return null;
                result = Converter.arabToRomeConvert(val);

                break;
            case ARAB:
                try {
                    a = Integer.parseInt(elemenets[0]);
                    b = Integer.parseInt(elemenets[2]);
                } catch (NumberFormatException e) {
                    return null;
                }
                val = calc(a, b, elemenets[1].charAt(0));
                if (val == null) return null;
                result = Integer.toString(val);
                break;
        }
        return result;
    }

    static Integer calc(int a, int b, char operand) {

        switch (operand) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return (b == 0) ? null : a / b;
        }
        return 0;
    }


}

enum RomArab {
    ROM, ARAB
}