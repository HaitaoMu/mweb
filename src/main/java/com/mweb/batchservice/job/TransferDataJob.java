package com.mweb.batchservice.job;

import static com.mweb.common.constats.Constants.*;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.SessionFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mweb.batchservice.processor.EntityItemWriter;
import com.mweb.batchservice.processor.HibernateItemWriterProcessor;

@Component
public abstract class TransferDataJob<T extends Serializable, K extends Serializable> extends AbstractBatchJob
{

	private static final int CHUNCK_SIZE = 50;

	private static final int FETCH_SIZE = 1000;
	
	private static final AtomicLong COUNT = new AtomicLong(0);

	private Class<T> clazz;

	private Class<K> clacc;

	private ItemProcessor<T, K> expressionProcessor;

	private ItemProcessor<K, T> convertProcessor;

	@Autowired
	private StepExecutionListener stepListener;

	
	@Autowired
	private JobExecutionListener jobListener;
	
//	@Autowired
//	@Qualifier("TaskListener")
//	private ItemReadListener<T> itemReaderListener;

//	@Autowired
//	@Qualifier("TaskListener")
//	private ItemReadListener<? super T> taskReadListener;
//	
//	@Autowired
//	@Qualifier("TaskListener")
//	private ItemWriteListener<? super T> taskWriterListener;
//	
//	@Autowired
//	@Qualifier("TaskListener")
//	private ItemWriteListener<? super K> taskTargetWriterListener;
//	
//	@Autowired
//	@Qualifier("TaskListener")
//	private ItemProcessListener<T, K> taskProcessListener;
	
	@Autowired
	@Qualifier("TaskListener")
	private ChunkListener chunkListener;

	/**
	 * read data from source database to local database
	 * 
	 * @return
	 */
	@Bean
	public ItemReader<T> sourceReader()
	{
		SessionFactory sessionFactory = getSessionFactory(SOURCE_DB);
		HibernateCursorItemReader<T> reader = new HibernateCursorItemReader<T>();
		reader.setFetchSize(FETCH_SIZE);
		reader.setQueryString("from " + clazz.getName());
		reader.setSessionFactory(sessionFactory);
		return reader;
	}

	/**
	 * read data from local database to destination database
	 * 
	 * @return
	 */
	@Bean
	public ItemReader<K> localReader()
	{
		HibernateCursorItemReader<K> reader = new HibernateCursorItemReader<K>();
		reader.setFetchSize(FETCH_SIZE);
		reader.setQueryString("from " + clacc.getName());
		reader.setSessionFactory(localSessionFactory);
		return reader;
	}

	/**
	 * Write source database data to local database
	 */
	@Bean
	public ItemWriter<T> localWriter()
	{
		HibernateItemWriterProcessor<T> writer = new HibernateItemWriterProcessor<T>();
		writer.setSessionFactory(localSessionFactory);
		return writer;
	}

	/**
	 * Write source database data to local database
	 */
	@Bean
	public ItemWriter<K> localCopyWriter()
	{
		HibernateItemWriterProcessor<K> writer = new HibernateItemWriterProcessor<K>();
		writer.setSessionFactory(localSessionFactory);
		return writer;
	}

	/**
	 * Write local data from local database to destination database
	 */
	@Bean
	@Transactional(propagation = Propagation.NESTED, readOnly = true)
	public ItemWriter<T> destinationWriter()
	{
		SessionFactory sessionFactory = getSessionFactory(DESTINATION_DB);
		EntityItemWriter<T> writer = new EntityItemWriter<T>(sessionFactory);
		return writer;
	}

<<<<<<< HEAD
	//@formatter:off
	@Bean
	@Scope("prototype")
	public Step loadDataStep() {
		return stepBuilderFactory.get("loadDataStep").<T, T> chunk(CHUNCK_SIZE)
				.reader(sourceReader())
				.writer(localWriter())
=======
//	@formatter:off
	@Bean
	@Scope("prototype")
	public Step loadDataStep() {
		return stepBuilderFactory.get(LOAD_DATA_STEP).<T, T> chunk(CHUNCK_SIZE)
				.reader(sourceReader())
//				.listener(taskReadListener)
				.writer(localWriter())
//				.listener(chunkListener)
>>>>>>> 09da55d3286dfaf2c040c18af9b20099baa188a1
				.listener(stepListener)
				.build();
	}

	@Bean
	@Scope("prototype")
	public Step copyDataStep() {
<<<<<<< HEAD
		return stepBuilderFactory.get("copyDataStep").<T, K> chunk(CHUNCK_SIZE)
				.reader(sourceReader())
				.processor(getExpressionProcessor())
				.writer(localCopyWriter())
=======
		return stepBuilderFactory.get(COPY_DATA_STEP).<T, K> chunk(CHUNCK_SIZE)
				.reader(sourceReader())
//				.listener(taskReadListener)
				.processor(getExpressionProcessor())
//				.listener(taskProcessListener)
				.writer(localCopyWriter())
//				.listener(chunkListener)
//				.listener(taskTargetWriterListener)
>>>>>>> 09da55d3286dfaf2c040c18af9b20099baa188a1
				.listener(stepListener)
				.build();
	}

	@Bean
	@Scope("prototype")
	public Step releaseDataStep() {
<<<<<<< HEAD
		return stepBuilderFactory.get("releaseDataStep")
				.<K, T> chunk(CHUNCK_SIZE).reader(localReader())
=======
		return stepBuilderFactory.get(RELEASE_DATA_STEP)
				.<K, T> chunk(CHUNCK_SIZE)
				.reader(localReader())
>>>>>>> 09da55d3286dfaf2c040c18af9b20099baa188a1
				.processor(getConvertProcessor())
				.writer(destinationWriter())
				.listener(stepListener)
				.build();
	}

	@Bean
	@Scope("prototype")
	public Job dataTransferJob() {
<<<<<<< HEAD
		return jobBuilderFactory.get("dataTransferJob")
=======
		return jobBuilderFactory.get(DATA_TRANSFER_JOB)
>>>>>>> 09da55d3286dfaf2c040c18af9b20099baa188a1
				.incrementer(new RunIdIncrementer())
				.flow(loadDataStep())
				.next(copyDataStep())
				.end()
				.listener(jobListener)
				.build();
	}

	@Bean
	@Scope("prototype")
	public Job autoDataTransferJob() {
		return jobBuilderFactory.get(AUTO_DATA_TRANSFER_JOB)
				.incrementer(new RunIdIncrementer()).flow(loadDataStep())
				.next(copyDataStep())
				.next(releaseDataStep())
				.end()
				.listener(jobListener)
				.build();
	}
<<<<<<< HEAD
	//@formatter:on
	
	@BeforeJob
	public void initializeState(JobExecution jobExecution) {
		time = System.currentTimeMillis();
	}

	@AfterJob
	public void exploitState(JobExecution jobExecution) {
		System.out.println(System.currentTimeMillis() - time);
	}
=======

//	@formatter:on
>>>>>>> 09da55d3286dfaf2c040c18af9b20099baa188a1

	public ItemProcessor<T, K> getExpressionProcessor()
	{
		return expressionProcessor;
	}

	public void setExpressionProcessor(ItemProcessor<T, K> expressionProcessor)
	{
		this.expressionProcessor = expressionProcessor;
	}

	public ItemProcessor<K, T> getConvertProcessor()
	{
		return convertProcessor;
	}

	public void setConvertProcessor(ItemProcessor<K, T> convertProcessor)
	{
		this.convertProcessor = convertProcessor;
	}

	public void setClazz(final Class<T> clazz)
	{
		this.clazz = clazz;
	}

	public void setClacc(final Class<K> clacc)
	{
		this.clacc = clacc;
	}

}
