package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.ItineraryService;
import edu.iis.mto.bdd.trains.services.ItineraryServiceBaseImpl;
import edu.iis.mto.bdd.trains.services.TimetableService;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;

@RunWith(MockitoJUnitRunner.class)
public class WhenCalculatingArrivalTimes {

    @Mock
    private TimetableService timetableService;
    private ItineraryService itineraryService;


    private LocalTime localTime = new LocalTime(19, 30);
    private String departure = "test";
    private String destination = "test";

    private Line line;
    private int restTime = 10;

    @Before
    public void setUp() {
        this.line = Line.named("Line").departingFrom(this.departure).withStations(this.departure, this.destination);
        this.itineraryService = new ItineraryServiceBaseImpl(this.timetableService, this.restTime);

        when(this.timetableService.findLinesThrough(this.departure, this.destination)).thenReturn(Collections.singletonList(this.line));
    }

    @Test
    public void findNextDeparturesEmpty() {
        when(timetableService.findArrivalTimes(line, departure)).thenReturn(
            Arrays.asList(
                new LocalTime(11, 30),
                new LocalTime(14, 15)
            )
        );

        List<LocalTime> result = itineraryService.findNextDepartures(this.departure, this.destination, this.localTime);
        assertThat(result, hasSize(0));
    }

    @Test
    public void findNextDeparturesOneFound() {
        when(timetableService.findArrivalTimes(line, departure)).thenReturn(
            Arrays.asList(
                new LocalTime(19, 35),
                new LocalTime(19, 15)
            )
        );

        List<LocalTime> result = itineraryService.findNextDepartures(this.departure, this.destination, this.localTime);
        assertThat(result, hasSize(1));
    }

    @Test
    public void findNextDeparturesTwoFound() {
        when(timetableService.findArrivalTimes(line, departure)).thenReturn(
            Arrays.asList(
                new LocalTime(19, 35),
                new LocalTime(19, 37)
            )
        );

        List<LocalTime> result = itineraryService.findNextDepartures(this.departure, this.destination, this.localTime);
        assertThat(result, hasSize(2));
    }

    @Test
    public void findNextDeparturesThreeFound() {
        when(timetableService.findArrivalTimes(line, departure)).thenReturn(
            Arrays.asList(
                new LocalTime(19, 32),
                new LocalTime(19, 35),
                new LocalTime(19, 37)
            )
        );

        List<LocalTime> result = itineraryService.findNextDepartures(this.departure, this.destination, this.localTime);
        assertThat(result, hasSize(3));
    }

}
