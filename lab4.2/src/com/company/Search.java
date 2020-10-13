package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

    public Search() {
        System.out.println("1) ДАТА В ФОРМАТЕ дд.мм.гггг");
        System.out.println("2) СНИЛС");
        System.out.println("3) БИК");
        System.out.println("4) ВРЕМЯ 24-Х ЧАСОВОМ ФОРМАТЕ");
        System.out.println("5) IPv4 АДРЕС");
        System.out.print("Введите номер шаблона поиска: ");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            start(br.readLine());
        } catch (IOException e) {
            System.out.println("Неправильный ввод. Остановка программы.");
        }
    }

    private void start(String choice) {
        StringBuilder text = new StringBuilder();
        try (FileReader reader = new FileReader("/home/taiiga/IdeaProjects/lab4.2/src/com/company/text.txt")) {
            int c;
            while ((c = reader.read()) != -1) {
                text.append((char) c);
            }
            System.out.println();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        switch (choice) {
            case "1" -> searchItem(text.toString(), "(\\s*(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.[0-9]{4}\\s*)");
            case "2" -> searchItem(text.toString(), "((?:[- ]*\\d){11})");
            case "3" -> searchItem(text.toString(), "([0-9]{9})");
            case "4" -> searchItem(text.toString(), "(([01][0-9]|[2][0-4]):[0-5][0-9])");
            case "5" -> searchItem(text.toString(), "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
            default -> System.out.println("Неправильный ввод. Остановка программы.");
        }
    }

    private void searchItem(String text, String choice) {
        Pattern pattern = Pattern.compile(choice);
        System.out.println(text);
        Matcher matcher = pattern.matcher(text);
        System.out.println();
        int matchCounter = 0;
        int start;
        int end;
        while (matcher.find()) {
            matchCounter++;
            start = matcher.start();
            end = matcher.end();
            System.out.println("Найдено совпадение " + text.substring(start, end));
            System.out.println("Номер совпадения: " + matchCounter);
            System.out.print("Позиция: c " + start);
            System.out.print(" по " + end);
            System.out.println();
            System.out.println();
        }
    }
}
