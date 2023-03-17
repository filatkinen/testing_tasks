import java.util.HashMap;

class Converter {

    static char[] rome = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
    static int[] arab = {1000, 500, 100, 50, 10, 5, 1};
    static HashMap<Character, Integer> romeToArabic = new HashMap<>();

    static  {
        for (int i = 0; i < rome.length; i++) {
            romeToArabic.put(rome[i], arab[i]);
        }
    }



    /***
     *
     * @param arabNumber - decimall number
     * @return rome number or null if @param <0 | @param>3999
     */

    static String arabToRomeConvert(int arabNumber) {

        if (arabNumber < 1 || arabNumber > 3999) return null;

        String[] rom1 = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] rom10 = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] rom100 = {"C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] rom1000 = {"M", "MM", "MMM"};

        StringBuilder romeNumber = new StringBuilder();
        int number = 0;
        int leftover = 0;

        number = arabNumber / 1000;
        leftover = arabNumber % 1000;
        if (number > 0) romeNumber.append(rom1000[number - 1]);

        number = leftover / 100;
        leftover = leftover % 100;
        if (number > 0) romeNumber.append(rom100[number - 1]);

        number = leftover / 10;
        leftover = leftover % 10;
        if (number > 0) romeNumber.append(rom10[number - 1]);

        if (leftover > 0) romeNumber.append(rom1[leftover - 1]);

        return romeNumber.toString();
    }

    /***
     *
     * @param rome Rome number
     * @return null if Rome is incorrect or decimal value
     */
    static Integer romeToArabConvert(String rome) {
        int arab = 0;
        int prevArab = 0;
        char prevRom = '\u0000';
        boolean switchPrevMore = false;
        int countRepeat = 0;
        for (int i = rome.length() - 1; i >= 0; i--) {
            char r = rome.charAt(i);
            int a = romeToArabic.getOrDefault(r, 0);

            //incorrect simbol
            if (a == 0) {
                return null;
            }

            // Цифра, уменьшающая значение не должна повторяться
            if (switchPrevMore && prevArab == a) {
                return null;
            }
            // Цифры V L D не должны повторяться
            if (r == prevRom && (r == 'V' || r == 'L' || r == 'D')) {
                return null;
            }

            //Остальные цифры не должны посторяться более 3-ех раз подряд
            if (r == prevRom) {
                countRepeat++;
                if (countRepeat == 3) {
                    return null;
                }
            } else {
                countRepeat = 0;
            }

            if (a < prevArab) {
                arab -= a;
                switchPrevMore = true;
            } else {
                arab += a;
                switchPrevMore = false;
            }
            prevArab = a;
            prevRom = r;
        }
        return arab;
    }
}