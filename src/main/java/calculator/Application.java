package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        int sum = calculator(Console.readLine());
        System.out.println("결과 : " + sum);

    }

    private static int calculator(String strInput) {
        int sum = 0;

        // 숫자만 입력 햇을때
        String trueNumbers ="[0-9]+";
        if (strInput.matches(trueNumbers)) {
            String[] strNumbers = strInput.split("");

            for (String strNumber : strNumbers) {
                sum += Integer.parseInt(strNumber);
            }

            return sum;
        }

        // 기본 구분자
        if (strInput.contains(",") || strInput.contains(":")) {
            String basicValue = strInput.replace(",", ".").replace(":",".");
            String[] strNumbers = basicValue.split("\\.");

            for (String strNumber : strNumbers) {
                sum += Integer.parseInt(strNumber);
            }
            return sum;
        }

        //커스텀 구분자
        if (strInput.startsWith("//") && strInput.contains("\\n")) {
            String[] split = strInput.split("n");
            String customValue = split[0].replace("//", "").replace("\\", "");


            String[] strNumbers = split[1].split( "\\"+ customValue); //구분 문자 처리하기 위해서 \\ 추가
            for (String strNumber : strNumbers) {
                sum += Integer.parseInt(strNumber);
            }
            return sum;
        }

        // 엔터만 입력시 결과:0
        return sum;
    }
}
