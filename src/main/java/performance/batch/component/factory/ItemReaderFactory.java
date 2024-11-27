package performance.batch.component.factory;

import performance.batch.config.job.InstanceComparisonInput;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class ItemReaderFactory {

    public ItemReader<InstanceComparisonInput> itemReader() {
        return null;
    }
}
