package chapter05;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Practice {

    public static void main(String[] args) {
        //
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1) 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리
        List<Transaction> answer1 = transactions.stream().filter(transaction -> transaction.getYear() == 2011).sorted(comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(answer1);

        //2)거래자가 근무하는 모든 도시를 중복없이 나열
        List<String> answer2 = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(Collectors.toList());
        System.out.println(answer2);

        //3)케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬
        List<Trader> answer3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge")).distinct()
                .sorted(comparing(Trader::getName)).collect(Collectors.toList());

        System.out.println(answer3);

        //4)모든 거래자의 이름을 알파벳순으로 정렬
        String answer4 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(a,b)-> a + b);
        System.out.println(answer4);

        //5)밀라노에 거래자가 있는가
        boolean answer5 = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(answer5);

        //6)케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue).forEach(System.out::println);

        //7)전체 트랜잭션 중 최댓값은 얼마인가?
        int answer7 = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0,Integer::max);
        System.out.println(answer7);

        //8)전체 트랜잭션 중 최솟값은 얼마인가?
        Optional<Transaction> answer8 = transactions.stream()
                .min(comparing(Transaction::getValue));
        System.out.println(answer8.map(String::valueOf).orElse("Not Found"));
    }
}
