package performance.batch.component.factory;

import java.util.function.Function;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class ItemReaderFactory {

    public <I>ItemReader<I> countingItemReader(long maxIndex, Function<Long, I> inputDataFactory) {
        return new ItemReader<>() {
            long currentIndex = 0;

            @Override
            public I read() {
                currentIndex++;
                return currentIndex <= maxIndex ? inputDataFactory.apply(currentIndex) : null;
            }
        };
    }
}
