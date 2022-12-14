package numbers;

import java.util.*;

public class Main {

    public static List<String> properties = Arrays.asList("even", "odd", "buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny", "jumping", "happy", "sad");
    public static boolean Even = false;
    public static boolean Odd = false;
    public static boolean Buzz;
    public static boolean Duck;
    public static boolean Palindrome;
    public static boolean Gapful;
    public static boolean Spy;
    public static boolean PerfectSquare;
    public static boolean Sunny;
    public static boolean Jumping;
    public static boolean Happy;
    public static boolean Sad;


    public static void main(String[] args) {
//        write your code here
        Scanner scanner = new Scanner(System.in);
        //int inputNumber = 1;
        long firstNumber = 1;
        int secondNumber;
        String thirdParameter;
        String fourthParameter;

        System.out.println("Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
        while (firstNumber != 0) {
            System.out.println("Enter a request:");
            String numberInput = scanner.nextLine();
            String[] numbersToSplit = numberInput.split(" ");
            int parameterCounter = 0;

            if (numbersToSplit.length > 2) {
                try {
                    firstNumber = Long.parseLong(numbersToSplit[0]);
                    secondNumber = Integer.parseInt((numbersToSplit[1]));
                    String temp;
                    List<String> userProperties = new ArrayList<>();
                    List<String> userExclusiveProperties = new ArrayList<>();
                    for (int i = 2; i < numbersToSplit.length; i++) {
                        if (numbersToSplit[i].toLowerCase().startsWith("-")) {

                            if (!userExclusiveProperties.contains(numbersToSplit[i].toLowerCase().substring(1))) {
                                userExclusiveProperties.add(numbersToSplit[i].replace("-", "").toLowerCase());
                                continue;
                            }
                        } else if (!userProperties.contains(numbersToSplit[i].toLowerCase())) {
                            userProperties.add(numbersToSplit[i].toLowerCase());
                        }


                    }

                    if (firstNumber < 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                    } else if (secondNumber < 0) {
                        System.out.println("The second parameter should be a natural number.");
                    } else if (firstNumber == 0) {
                        System.out.println("Goodbye!");
                    } else if (userProperties.size() > 0 && userExclusiveProperties.size() > 0) {
                        printFourthRequestBothProperties(firstNumber, secondNumber, userProperties, userExclusiveProperties);
                    } else if (userProperties.size() > 0 && userExclusiveProperties.size() == 0) {
                        printFourthRequest(firstNumber, secondNumber, userProperties, userExclusiveProperties);
                    } else if (userExclusiveProperties.size() > 0 && userProperties.size() == 0) {
                        printFourthRequestWithoutUserProperties(firstNumber, secondNumber, userProperties, userExclusiveProperties);
                    }
                } catch (Exception e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }


            } else if (numbersToSplit.length > 1) {
                try {
                    firstNumber = Long.parseLong(numbersToSplit[0]);
                    secondNumber = Integer.parseInt((numbersToSplit[1]));
                    //System.out.println("firstnumber: "+firstNumber);
                    //System.out.println("secondnumber: "+secondNumber);

                    if (firstNumber < 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                    } else if (secondNumber < 0) {
                        System.out.println("The second parameter should be a natural number.");
                    } else if (firstNumber == 0) {
                        System.out.println("Goodbye!");
                    } else {
                        for (int i = 0; i < secondNumber; i++) {
                            printElementByLine(firstNumber + i);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }

            } else {
                try {
                    firstNumber = Long.parseLong((numbersToSplit[0]));
                    if (firstNumber < 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                    } else if (firstNumber == 0) {
                        System.out.println("Goodbye!");
                    } else {
                        printTheNumberResult(firstNumber);
                    }
                } catch (Exception e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }


            }
        }


    }

    public static void printProperties(long number) {
        List<String> resultsList = checkProperties(number);
        String result = String.join(", ", resultsList);
        System.out.println("\t" + number + " is " + result);
    }

    public static List<String> checkProperties(long number) {
        List<String> resultsList = new ArrayList<>();
        if (isBuzzNumber(number)) {
            resultsList.add("buzz");
        }
        if (isDuckNumber(number)) {
            resultsList.add("duck");
        }
        if (isPalindrome(number)) {
            resultsList.add("palindromic");
        }
        if (isGapful(number)) {
            resultsList.add("gapful");
        }
        if (isOddOrEven(number)) {
            resultsList.add("even");
        }
        if (!isOddOrEven(number)) {
            resultsList.add("odd");
        }
        if (isSpyNumber(number)) {
            resultsList.add("spy");
        }
        if (isPerfectSquare(number)) {
            resultsList.add("square");
        }
        if (isSunny(number)) {
            resultsList.add("sunny");
        }
        if (isJumping(number)) {
            resultsList.add("jumping");
        }
        if (isHappyOrSad(number)) {
            resultsList.add("happy");
        }
        if (!isHappyOrSad(number)) {
            resultsList.add("sad");
        }

        return resultsList;
    }

    public static void printFourthRequestBothProperties(long firstParameter, long secondParameter, List<String> userProperties, List<String> userExclusiveProperties) {
        long checkingNumber = firstParameter;
        long counter = 0;

        //see annab errori, kui need tingimused ei ole täidetud
        if (userInputPropertiesCheck(userProperties) && userInputPropertiesCheck(userExclusiveProperties)
                && checkExclusiveIfBoth(userProperties, userExclusiveProperties)) {
            //loobib nii kaua, kui teine parameeter on täidetud
            while (counter < secondParameter) {
                //saame listArray, kus uuritakse kas antud asi sisaldab
                //samas tuleks vaadata kas mingi asi ei sisalda
                if (checkProperties(checkingNumber).containsAll(userProperties) && checkExclusiveElement(checkingNumber, userExclusiveProperties)) {
                    printProperties(checkingNumber);
                    counter++;
                }
                checkingNumber++;
            }
        }
    }

    public static void printFourthRequest(long firstParameter, long secondParameter, List<String> userProperties, List<String> userExclusiveProperties) {
        long checkingNumber = firstParameter;
        long counter = 0;

        //see annab errori, kui need tingimused ei ole täidetud
        if (userInputPropertiesCheck(userProperties) && checkExclusive(userProperties)) {
            //loobib nii kaua, kui teine parameeter on täidetud
            while (counter < secondParameter) {
                //saame listArray, kus uuritakse kas antud asi sisaldab
                //samas tuleks vaadata kas mingi asi ei sisalda
                if (checkProperties(checkingNumber).containsAll(userProperties)) {
                    printProperties(checkingNumber);
                    counter++;
                }
                checkingNumber++;
            }
        }
    }

    public static void printFourthRequestWithoutUserProperties(long firstParameter, long secondParameter, List<String> userProperties, List<String> userExclusiveProperties) {
        long checkingNumber = firstParameter;
        long counter = 0;

        //see annab errori, kui need tingimused ei ole täidetud
        if (userInputPropertiesCheck(userExclusiveProperties) && checkExclusive(userExclusiveProperties)) {
            //loobib nii kaua, kui teine parameeter on täidetud
            while (counter < secondParameter) {
                //saame listArray, kus uuritakse kas antud asi sisaldab
                //samas tuleks vaadata kas mingi asi ei sisalda
                if (checkExclusiveElement(checkingNumber, userExclusiveProperties)) {
                    printProperties(checkingNumber);
                    counter++;
                }
                checkingNumber++;
            }
        }
    }

    public static boolean checkExclusiveElement(long checkingNumber, List<String> userExclusiveProperties) {
        for (String a : userExclusiveProperties) {
            if (checkProperties(checkingNumber).contains(a)) {
                return false;
            }
        }
        return true;

    }

    public static boolean checkExclusive(List<String> userProperties) {
        if (userProperties.contains("even") && userProperties.contains("odd")) {
            System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]\n" +
                    "There are no numbers with these properties.");
            return false;
        } else if (userProperties.contains("duck") && userProperties.contains("spy")) {
            System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]\n" +
                    "There are no numbers with these properties.");
            return false;
        } else if (userProperties.contains("sunny") && userProperties.contains("square")) {
            System.out.println("The request contains mutually exclusive properties: [SUNNY, SQUARE]\n" +
                    "There are no numbers with these properties.");
            return false;
        } else if (userProperties.contains("happy") && userProperties.contains("sad")) {
            System.out.println("The request contains mutually exclusive properties: [HAPPY, SAD]\n" +
                    "There are no numbers with these properties.");
            return false;
        }

        return true;
    }

    public static boolean checkExclusiveIfBoth(List<String> userProperties, List<String> userExclusiveProperties) {
        if (userProperties.contains("even") && userExclusiveProperties.contains("even")) {
            System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]\n" +
                    "There are no numbers with these properties.");
            return false;
        } else if (userProperties.contains("odd") && userExclusiveProperties.contains("odd")) {
            System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]\n" +
                    "There are no numbers with these properties.");
            return false;
        } else if (userProperties.contains("duck") && userExclusiveProperties.contains("duck")) {
            System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]\n" +
                    "There are no numbers with these properties.");
            return false;
        } else if (userProperties.contains("spy") && userExclusiveProperties.contains("spy")) {
            System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]\n" +
                    "There are no numbers with these properties.");
            return false;
        } else if (userProperties.contains("sunny") && userExclusiveProperties.contains("sunny")) {
            System.out.println("The request contains mutually exclusive properties: [SUNNY, SQUARE]\n" +
                    "There are no numbers with these properties.");
            return false;
        } else if (userProperties.contains("square") && userExclusiveProperties.contains("square")) {
            System.out.println("The request contains mutually exclusive properties: [SUNNY, SQUARE]\n" +
                    "There are no numbers with these properties.");
            return false;
        } else if (userProperties.contains("happy") && userExclusiveProperties.contains("happy")) {
            System.out.println("The request contains mutually exclusive properties: [HAPPY, SAD]\n" +
                    "There are no numbers with these properties.");
            return false;
        } else if (userProperties.contains("sad") && userExclusiveProperties.contains("sad")) {
            System.out.println("The request contains mutually exclusive properties: [HAPPY, SAD]\n" +
                    "There are no numbers with these properties.");
            return false;
        }

        return true;
    }


    public static boolean userInputPropertiesCheck(List<String> userProperties) {
        List<String> badProperties = new ArrayList<>();
        for (String str : userProperties) {
            if (!properties.contains(str)) {
                badProperties.add(str);
            }
        }
        if (badProperties.size() == 0) {
            return true;
        } else if (badProperties.size() > 1) {
            String joined = String.join(", ", badProperties);
            System.out.println("The properties [" + joined.toUpperCase() + "] are wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
        } else {
            System.out.println("The property " + badProperties.toString().toUpperCase() + " is wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
        }
        return false;
    }

    public static void printTheNumberResult(long inputNumber) {
        isBuzzNumber(inputNumber);
        isDuckNumber(inputNumber);
        isPalindrome(inputNumber);
        isOddOrEven(inputNumber);
        isGapful(inputNumber);
        isSpyNumber(inputNumber);
        isPerfectSquare(inputNumber);
        isJumping(inputNumber);
        isSunny(inputNumber);
        isHappyOrSad(inputNumber);
        System.out.println("Properties of " + inputNumber);
        System.out.println(
                "        buzz: " + Buzz + "\n" +
                        "        duck: " + Duck + "\n" +
                        "palindromic: " + Palindrome + "\n" +
                        "        gapful: " + Gapful + "\n" +
                        "        spy: " + Spy + "\n" +
                        "        square: " + PerfectSquare + "\n" +
                        "        sunny: " + Sunny + "\n" +
                        "        jumping: " + Jumping + "\n" +
                        "        even: " + Even + "\n" +
                        "        odd: " + Odd + "\n" +
                        "        happy: " + Happy + "\n" +
                        "        sad: " + Sad);
    }

    public static void printElementByLine(long inputNumber) {
        String printBuzz = isBuzzNumber(inputNumber) ? "buzz, " : "";
        String printDuck = isDuckNumber(inputNumber) ? "duck, " : "";
        String printPalindrome = isPalindrome(inputNumber) ? "palindromic, " : "";
        String printOddOrEven = isOddOrEven(inputNumber) ? "even, " : "odd, ";
        String printGapful = isGapful(inputNumber) ? "gapful, " : "";
        String printSpy = isSpyNumber(inputNumber) ? "spy, " : "";
        String printSquare = isPerfectSquare(inputNumber) ? "square, " : "";
        String printSunny = isSunny(inputNumber) ? "sunny, " : "";
        String printJumping = isJumping(inputNumber) ? "jumping, " : "";
        String printHappyOrSad = isHappyOrSad(inputNumber) ? "happy, " : "sad, ";
        System.out.println(inputNumber + " is " + printPalindrome + printJumping + printSpy + printSquare + printSunny + printBuzz + printDuck + printGapful + printOddOrEven + printHappyOrSad);
    }

    public static boolean isPerfectSquare(long inputNumber) {
        double x = (double) inputNumber;

        // finding the square root of given number
        double sq = Math.sqrt(x);

        if ((sq - Math.floor(sq)) == 0) {
            PerfectSquare = true;
            return true;
        } else {
            PerfectSquare = false;
            return false;
        }
    }

    public static boolean isSunny(long inputNumber) {
        double x = (double) inputNumber + 1;

        // finding the square root of given number
        double sq = Math.sqrt(x);

        if ((sq - Math.floor(sq)) == 0) {
            Sunny = true;
            return true;
        } else {
            Sunny = false;
            return false;
        }
    }

    public static boolean isSpyNumber(long inputNumber) {
        long remainder;
        long numbersSum = 0;
        long numbersMultiplication = 1;
        ArrayList<Long> numbers = new ArrayList<Long>();

        // get the reverse of originalNum
        // store it in variable
        while (inputNumber != 0) {
            remainder = inputNumber % 10;
            numbers.add(remainder);
            inputNumber /= 10;
        }
        for (long a : numbers) {
            numbersSum = numbersSum + a;
            numbersMultiplication = numbersMultiplication * a;
        }
        if (numbersSum == numbersMultiplication) {
            Spy = true;
            return true;
        } else {
            Spy = false;
            return false;
        }
    }

    public static boolean isDuckNumber(long inputNumber) {
        long temp = inputNumber;
        long duckNumber;
        int rightDigit = 0;
        int count = countDig(inputNumber);
        while (count > 0) {

            duckNumber = temp % 10;
            if (duckNumber == rightDigit) {
                Duck = true;
                return true;
            }
            temp = temp / 10;
            count--;
        }
        Duck = false;
        return false;
    }

    public static boolean isBuzzNumber(long inputNumber) {
        long lastNumber = inputNumber % 10;
        if (inputNumber % 7 != 0 && lastNumber != 7) {
            Buzz = false;
            return false;
        } else if (inputNumber % 7 == 0 && lastNumber != 7) {
            Buzz = true;
            return true;
        } else if (inputNumber % 7 != 0 && lastNumber == 7) {
            Buzz = true;
            return true;
        } else {
            Buzz = true;
            return true;
        }
    }

    public static int countDig(long n) {
        int count = 0;
        while (n != 0) {
            n = n / 10;
            count = count + 1;
        }
        return count;
    }

    public static boolean isOddOrEven(long num) {
        if (num % 2 == 0) {
            Even = true;
            Odd = false;
            return true;
        } else {
            Even = false;
            Odd = true;
            return false;
        }
    }

    public static boolean isPalindrome(long num) {
        long reversedNum = 0, remainder;

        // store the number to originalNum
        long originalNum = num;

        // get the reverse of originalNum
        // store it in variable
        while (num != 0) {
            remainder = num % 10;
            reversedNum = reversedNum * 10 + remainder;
            num /= 10;
        }

        // check if reversedNum and originalNum are equal
        if (originalNum == reversedNum) {
            Palindrome = true;
            return true;
        } else {
            Palindrome = false;
            return false;
        }
    }

    public static boolean isGapful(long num) {
        int numLength = countDig(num);
        long numToCalcFirstAndLast = num;
        long firstNumber = 0;
        long lastNumber = 0;
        for (int i = 0; i < numLength; i++) {
            if (i == 0) {
                lastNumber = numToCalcFirstAndLast % 10;
            } else if (i == numLength - 1) {
                firstNumber = numToCalcFirstAndLast % 10;
            }
            numToCalcFirstAndLast = numToCalcFirstAndLast / 10;
        }
        //System.out.println("firstnumber:"+firstNumber+" lastnumber:"+lastNumber);
        long numToDivide = 10 * firstNumber + lastNumber;
        if (num % numToDivide == 0 && numLength > 2) {
            Gapful = true;
            return true;
        } else {
            Gapful = false;
            return false;
        }
    }

    public static boolean isJumping(long number) {
        boolean temp = true;
        while (number != 0) {
            long digit1 = number % 10;
            number = number / 10;
            if (number != 0) {
                long digit2 = number % 10;
                if (Math.abs(digit1 - digit2) != 1) {
                    temp = false;
                    break;
                }
            }
        }
        Jumping = temp;
        return temp;
    }

    public static boolean isHappyOrSad(long inputNumber) {
        Set<Long> unique_num = new HashSet<Long>();

        while (unique_num.add(inputNumber)) {
            long value = 0;
            while (inputNumber > 0) {
                value += Math.pow(inputNumber % 10, 2);
                inputNumber /= 10;
            }
            inputNumber = value;
        }
        if (inputNumber == 1) {
            Happy = true;
            Sad = false;
            return true;
        } else {
            Happy = false;
            Sad = true;
            return false;
        }

    }

}
