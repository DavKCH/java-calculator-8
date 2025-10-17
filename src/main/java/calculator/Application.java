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

        // == 예외 부분 start==
        if (strInput == null) {
            System.out.println("잘못된 값을 입력 하셨습니다.");
            throw new IllegalArgumentException();
        }

        //음수 및 구분자 '-' 처리
        if (strInput.contains("-")) {
            System.out.println(" - (하이픈)은 입력 불가능 합니다.");
            throw new IllegalArgumentException();
        }

        // n이 여러번 있을시
        int index = strInput.indexOf("n");
        int count = 0;
        while (index >= 0) {
            index = strInput.indexOf("n", index + 1);
            count++;
        }

        String falseStr = ".*[a-mo-zA-Zㄱ-ㅎ가-힣].*";
        if (strInput.matches(falseStr) || count >= 2 || strInput.startsWith("n") ) {
            System.out.println("잘못된 값을 입력 하셨습니다.");
            throw new IllegalArgumentException();
        }
        // == 예외 부분 end ==

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

        //**\n1**2**3
        //커스텀 구분자
        if (strInput.startsWith("//") && strInput.contains("\\n")) {
            String[] split = strInput.split("n");
            String customValue = split[0].replace("//", "").replace("\\", "");

            if(customValue.equals(",") || customValue.equals(":")) {
                System.out.println("커스덤 구분자가 아닙니다. 프로그램을 종료 합니다.");
                throw new IllegalArgumentException();
            }

            if (customValue.length() >= 2){
                System.out.println("커스덤 구분자는 1개만 가능 합니다.");
                throw new IllegalArgumentException();
            }

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
