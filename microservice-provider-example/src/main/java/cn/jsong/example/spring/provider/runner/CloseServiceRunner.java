package cn.jsong.example.spring.provider.runner;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 
 * 关闭服务时执行操作
 * 
 * @author S.J.
 * @date 2018/08/10
 */
@Component
public class CloseServiceRunner implements CommandLineRunner {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${system.preDestroyTime}")
    private long preDestroyTime;
    
    /**
     * 执行停止服务，销毁前休眠等待最后一个任务执行完成
     */
    @PreDestroy
    public void waitLastTaskComplete() {
        LOGGER.info("[程序停止前等待任务处理完成]-请等待[{}]秒钟,程序将自动退出!", (preDestroyTime / 1000));
        long startTime = System.currentTimeMillis();
        try {
            Thread.sleep(preDestroyTime);
            
        } catch (InterruptedException e) {
            LOGGER.error("[程序停止前等待任务处理完成]-异常, e:{}", e);
		}
        long endTime = System.currentTimeMillis();
        LOGGER.info("[程序停止前等待任务处理完成]-已完成,耗时[{}]ms！", (endTime - startTime));
    }
	
    @Override
    public void run(String... arg0) throws Exception {
    }

}
