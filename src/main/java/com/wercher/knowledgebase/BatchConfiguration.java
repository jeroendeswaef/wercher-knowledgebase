package com.wercher.knowledgebase;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class BatchConfiguration {

    @Value("#{environment.BASE_PATH}")
    private String knowledgeBaseFilePath;

    @Bean
    public JdbcBatchItemWriter<Item> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Item>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO kb_item (relative_path) VALUES (:relativePath)")
                .dataSource(dataSource)
                .build();
    }
    @Bean
    public Job importUserJob(Step step1, JobRepository jobRepository) {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, JdbcBatchItemWriter<Item> writer) {
        try {
            return new StepBuilder("step1", jobRepository)
                    .<Item, Item> chunk(10, transactionManager)
                    .reader(new FilesystemImageItemReader(this.knowledgeBaseFilePath))
                    .writer(writer)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
