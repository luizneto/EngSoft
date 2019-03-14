package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
//import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerLog {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(TimerLog.class);

    public void log() throws InterruptedException {
        while(true) {
            try {
            	logger.info("Enviando Info Loggger para o Kafka: " + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
            	Thread.sleep(5000);
            }catch (Exception ex) {
        		logger.warn("Enviando Warn Loggger para o Kafka: " + ex);
        		logger.error("Enviando Error Loggger para o Kafka: " + ex);

			}
        }
    }
}
