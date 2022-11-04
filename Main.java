import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws CustomException {
        System.out.println("Введите выражение:");
        Scanner scan = new Scanner(System.in);
        String InputExpression = scan.nextLine();
        System.out.println(calc(InputExpression));
    }

    static boolean isNum(String firstHalf, String secondHalf) {
        try {
            Integer.parseInt(firstHalf);
            Integer.parseInt(secondHalf);
            return true;
        } catch (NumberFormatException e) {

            return false;
        }
    }

    public static String calc(String input) throws CustomException {
        int a;
        int b;
        int PreResult = 0;
        String result;


        String[] ExpressionSplit = input.split(" ");

        if (!(ExpressionSplit.length == 3)) {
            throw new CustomException("Неверно введено выражение!");
        }


        if (isNum(ExpressionSplit[0], ExpressionSplit[2]))
        {
            a = Integer.parseInt(ExpressionSplit[0]);
            b = Integer.parseInt(ExpressionSplit[2]);

            if ((a < 1 | a > 10) | (b < 1 | b > 10)) {
                throw new CustomException("Неверно введено выражение!");
            }

            switch (ExpressionSplit[1]) {
                case ("+"):
                    PreResult = a + b;
                    break;
                case ("-"):
                    PreResult = a - b;
                    break;
                case ("/"):
                    PreResult = a / b;
                    break;
                case ("*"):
                    PreResult = a * b;
                    break;
                default:
                    throw new CustomException("Неверно введено выражение!");
            }
            result = Integer.toString(PreResult);

        } else {

            try {
                RomeNum1 romeNum1 = RomeNum1.valueOf(ExpressionSplit[0]);
                RomeNum1 romeNum2 = RomeNum1.valueOf(ExpressionSplit[2]);
                a = romeNum1.ordinal() + 1;
                b = romeNum2.ordinal() + 1;
            } catch (
                    IllegalArgumentException e)
            {
                throw new CustomException("Неверно введено выражение!");
            }

            switch (ExpressionSplit[1]) {
                case ("+"):
                    PreResult = a + b;
                    break;
                case ("-"):
                    PreResult = a - b;
                    break;
                case ("/"):
                    PreResult = a / b;
                    break;
                case ("*"):
                    PreResult = a * b;
                    break;
                default:
                    throw new CustomException("Неверно введено выражение!");
            }

            if (PreResult < 1) {
                throw new CustomException("Результат операции над римскими числами получился меньше единицы!");
            }

            int NumPos10 = PreResult / 10;
            int NumPos1 = PreResult % 10;

            RomeNum10[] ArrayNumPos10 = RomeNum10.values();
            RomeNum1[] ArrayNumPos1 = RomeNum1.values();
            if (NumPos10 == 0)
                result = ArrayNumPos1[NumPos1 - 1].toString();
            else {
                if (NumPos1 == 0)
                    result = ArrayNumPos10[NumPos10 - 1].toString();

                else

                    result = ArrayNumPos10[NumPos10 - 1].toString() + ArrayNumPos1[NumPos1 - 1].toString();
            }

        }

        return result;
    }
}

enum RomeNum1 {
    I, II, III, IV, V, VI, VII, VIII, IX, X
}

enum RomeNum10 {
    X, XX, XXX, XL, L, LX, LXX, LXXX, XC, C
}

class CustomException extends Exception {
    CustomException(String description) {
        super(description);
    }
}

