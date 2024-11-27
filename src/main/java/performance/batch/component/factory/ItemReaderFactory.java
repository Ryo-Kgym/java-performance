package performance.batch.component.factory;

import performance.batch.config.job.InstanceComparisonInput;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class ItemReaderFactory {

    public ItemReader<InstanceComparisonInput> itemReader() {
        return new ItemReader<InstanceComparisonInput>() {
            @Override
            public InstanceComparisonInput read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                return InstanceComparisonInput.builder()
                    .index(1)
                    .build();
            }
        };
    }
}
