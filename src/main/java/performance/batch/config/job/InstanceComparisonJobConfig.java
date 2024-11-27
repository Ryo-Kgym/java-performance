/*
 * Copyright (c) 2023 Ryo-Kgym.
 */

package performance.batch.config.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.RequiredArgsConstructor;
import performance.batch.component.builder.CountingStepListener;
import performance.batch.component.factory.ItemProcessorFactory;
import performance.batch.component.factory.ItemReaderFactory;
import performance.batch.component.factory.ItemWriterBuilder;
import performance.batch.component.factory.JobBuilderFactory;
import performance.batch.component.factory.StepBuilderFactory;
import performance.core.interactor.instanceComparison.NewConstructorInteractor;
import performance.core.usecase.instanceComparison.InstanceComparisonInput;
import performance.core.usecase.instanceComparison.InstanceComparisonOutput;
import performance.core.usecase.instanceComparison.NewConstructorUsecase;
import performance.persistence.LogOutputRepository;

@Configuration
@RequiredArgsConstructor
public class InstanceComparisonJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ItemReaderFactory itemReaderFactory;
    private final ItemProcessorFactory itemProcessorFactory;
    private final String JOB_PREFIX = "instanceComparison";
    private final String STEP_PREFIX = "instanceComparison";

    @Bean(name = JOB_PREFIX + "Job")
    public Job job(
        Step exportMigrationPublicUserStep
    ) {
        return jobBuilderFactory.create(JOB_PREFIX + "Job")
            .start(exportMigrationPublicUserStep)
            .build();
    }

    @Bean(name = STEP_PREFIX + "Step")
    public Step step(
        @Qualifier(STEP_PREFIX + "ItemReader") ItemReader<InstanceComparisonInput> reader,
        @Qualifier(STEP_PREFIX + "ItemProcessor") ItemProcessor<InstanceComparisonInput, InstanceComparisonOutput> processor,
        @Qualifier(STEP_PREFIX + "ItemWriter") ItemWriter<InstanceComparisonOutput> writer
    ) {
        return stepBuilderFactory.
            <InstanceComparisonInput, InstanceComparisonOutput>create(STEP_PREFIX + "Step")
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .listener(new CountingStepListener<>())
            .build();
    }

    @Bean(name = STEP_PREFIX + "ItemReader")
    public ItemReader<InstanceComparisonInput> reader() {
        return itemReaderFactory.countingItemReader(1000000, (index) -> InstanceComparisonInput.builder()
            .index(index)
            .build());
    }

    @Bean(name = STEP_PREFIX + "ItemProcessor")
    public ItemProcessor<InstanceComparisonInput, InstanceComparisonOutput> processor(NewConstructorUsecase usecase) {
        return itemProcessorFactory.itemProcessor(usecase);
    }

    @Bean
    public NewConstructorUsecase usecase() {
        return new NewConstructorInteractor();
    }

    @Bean(name = STEP_PREFIX + "ItemWriter")
    public ItemWriter<InstanceComparisonOutput> writer(LogOutputRepository gateway
    ) {
        return new ItemWriterBuilder<InstanceComparisonOutput>()
            .writer(gateway)
            .build();
    }
}

