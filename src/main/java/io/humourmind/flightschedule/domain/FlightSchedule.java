package io.humourmind.flightschedule.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightSchedule {
	private String flightNo;
	private String destination;
	private LocalDateTime sta;
	private LocalDateTime ata;
	private String scheduledBayId;
	private String actualBayId;
}
