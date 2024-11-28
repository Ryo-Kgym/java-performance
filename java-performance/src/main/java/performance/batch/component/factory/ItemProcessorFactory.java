package performance.batch.component.factory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import performance.usecase.UseCaseWithException;

@Component
public class ItemProcessorFactory {

    public <I,O> ItemProcessor<I,O> itemProcessor(UseCaseWithException<I, O> useCase) {
        return useCase::handle;
    }
}
