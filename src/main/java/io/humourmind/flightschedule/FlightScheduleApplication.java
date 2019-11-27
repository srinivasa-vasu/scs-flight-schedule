package io.humourmind.flightschedule;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;

import io.humourmind.flightschedule.domain.FlightSchedule;
import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableBinding(Source.class)
public class FlightScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightScheduleApplication.class, args);
	}

	private String[] flights = { "SQ02", "SQ502", "SQ405", "GA03", "GA501" };
	private String[] destinations = { "SIN", "CGK", "BXW", "BDO", "SFO", "PCB" };
	private String[] bayIds = { "B01", "B22", "B40", "B32", "B20" };
	private long[] delay = { 0, 5, 10, 20, 30 };

	@Bean
	public Supplier<Flux<FlightSchedule>> generateSchedule() {
		LocalDateTime now = LocalDateTime.now();
		final Random rand = new Random();
		return () -> Flux
				.just(FlightSchedule.builder().flightNo(flights[rand.nextInt(5)])
						.destination(destinations[rand.nextInt(6)])
						.sta(now.plusMinutes(delay[rand.nextInt(5)]))
						.ata(now.plusMinutes(delay[rand.nextInt(5)]))
						.scheduledBayId(bayIds[rand.nextInt(5)])
						.actualBayId(bayIds[rand.nextInt(5)]).build())
				.delayElements(Duration.ofSeconds(1));
	}

}
