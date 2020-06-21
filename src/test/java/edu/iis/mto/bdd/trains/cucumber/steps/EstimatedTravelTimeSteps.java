package edu.iis.mto.bdd.trains.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import org.joda.time.LocalTime;

public class EstimatedTravelTimeSteps {

    @Zakładając("^chcę się dostać z (.*) do (.*)$")
    public void givenArrivingPoints(String beginning, String destination) {
        throw new PendingException();
    }

    @Zakładając("^następny pociąg odjeżdża o (.*) na linii (.*)$")
    public void givenTrainTimeAndLine(@Transform(JodaLocalTimeConverter.class) LocalTime startTime, String line) {
        throw new PendingException();
    }

    @Gdy("^zapytam o godzinę przyjazdu$")
    public void whenIAskAboutTrainArrivalTime() {
        throw new PendingException();
    }

    @Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu: (.*)$")
    public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) LocalTime expectedTrainArrivalTime) {
        throw new PendingException();
    }

}
