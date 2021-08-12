package chapter06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>,List<T>> {
    //
    @Override
    public Supplier<List<T>> supplier() {
        return ()->
            new ArrayList<T>();
    }

    @Override
    public BiConsumer<List<T>,T> accumulator() {
        return (list,item)->{
            list.add(item);
        };
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list,list2)->{
            list.addAll(list2);
            return list;
        };
    }

    @Override
    public Function<List<T>,List<T>> finisher() {
        return i->i;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
    }
}
