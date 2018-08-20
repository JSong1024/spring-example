package cn.jsong.example.spring.consumer.cfg;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 
 * 异步处理线程池配置
 * 
 * @author S.J.
 * @date 2018/08/10
 */
@Configuration
@EnableAsync
public class AsyncConfiguration extends AsyncConfigurerSupport {

	private int corePoolSize = 20;  
	private int maxPoolSize = 50;  
	private int queueCapacity = 300;  

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("AsyncExecutorThread-");
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setAwaitTerminationSeconds(20);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();	//如果不初始化，导致找到不到执行器
		return executor;
	}

}
