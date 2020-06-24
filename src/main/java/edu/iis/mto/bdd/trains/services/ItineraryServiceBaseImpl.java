package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class ItineraryServiceBaseImpl implements ItineraryService {

    private TimetableService timetableService;
    private int restTime;

    ItineraryServiceBaseImpl(TimetableService timetableService, int restTime) {
        this.timetableService = timetableService;
        this.restTime = restTime;
    }

    @Override
    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
        List<LocalTime> foundDepartures = new ArrayList<>();

        for (Line line : timetableService.findLinesThrough(departure, destination)) {
            for (LocalTime localTime : timetableService.findArrivalTimes(line, departure)) {
                if (startTime.isBefore(localTime) && startTime.plusMinutes(restTime).isAfter(localTime)) {
                    foundDepartures.add(localTime);
                }
            }
        }

        return foundDepartures;
    }

}
