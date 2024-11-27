/*
 * Copyright (c) 2023 Ryo-Kgym.
 */

package performance.batch.config.job;

import performance.batch.component.builder.CountingStepListener;
import performance.batch.component.factory.ItemReaderFactory;
import performance.batch.component.factory.ItemWriterBuilder;
import performance.batch.component.factory.JobBuilderFactory;
import performance.batch.component.factory.StepBuilderFactory;
import performance.persistence.SaveRepository;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InstanceComparisonJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ItemReaderFactory itemReaderFactory;
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
        @Qualifier(STEP_PREFIX + "ItemWriter") ItemWriter<InstanceComparisonInput> writer
    ) {
        return stepBuilderFactory.
            <InstanceComparisonInput, InstanceComparisonInput>create(STEP_PREFIX + "Step")
            .reader(reader)
            .writer(writer)
            .listener(new CountingStepListener<>())
            .build();
    }

    @Bean(name = STEP_PREFIX + "ItemReader")
    public ItemReader<InstanceComparisonInput> reader() {
        return itemReaderFactory.itemReader();
    }

    @Bean(name = STEP_PREFIX + "ItemWriter")
    public ItemWriter<InstanceComparisonInput> writer(
        SaveRepository saveGateway
    ) {
        return new ItemWriterBuilder<InstanceComparisonInput>()
            .writer(saveGateway)
            .build();
    }
}

