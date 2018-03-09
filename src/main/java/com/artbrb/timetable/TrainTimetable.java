package com.artbrb.timetable;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainTimetable {
    private Map<String, Train> trainMap = new HashMap<>();
    private Map<String, List<String>> stationMap = new HashMap<>();

    public Map<String, Train> getTrainMap(){
        Map<String, Train> copyTrainMap = new HashMap<>();
        copyTrainMap.putAll(trainMap);
        return copyTrainMap;
    }

    public Map<String, List<String>> getStationMap() {
        Map<String, List<String>> copyStationMap = new HashMap<>();
        copyStationMap.putAll(stationMap);
        return copyStationMap;
    }

    public void addNewTrain(String trainName, Instant departureTime, String arrivalStation) throws Exception {
        Train newTrain = new Train(trainName, departureTime, arrivalStation);
        trainMap.put(trainName, newTrain);
        addIntermediateStation(arrivalStation, trainName);
    }

    public void deleteTrain(String trainName) {
        trainMap.remove(trainName);
        for (Map.Entry<String, List<String>> entry : stationMap.entrySet()) {
            List<String> trains = entry.getValue();
            if (trains.contains(trainName)) {
                trains.remove(trainName);
            }
            if (trains.size() == 0) {
                stationMap.remove(entry.getKey());
            }
        }
    }

    public void addIntermediateStation(String stationName, String trainName) throws IllegalAccessException {
        if (!isTrainExist(trainName)) {
            throw  new IllegalAccessException("Train does not exist");
        }
        if (stationMap.containsKey(stationName)) {
            List<String> trains = stationMap.get(stationName);
            if (!trains.contains(trainName)) {
                trains.add(trainName);
            }
        } else {
            List<String> list = new ArrayList<>();
            list.add(trainName);
            stationMap.put(stationName, list);
        }
    }

    public void deleteIntermediateStation(String trainName, String stationName) {
        List<String> trains = stationMap.get(stationName);
        trains.remove(trainName);
        if (trains.size() == 0) {
            stationMap.remove(stationName);
        }
    }

    public String findNearestTrain(String stationName) throws Exception {
        List<String> trains = stationMap.get(stationName);
        Instant currentTime = Instant.now();
        Long nearestTrainTime = Long.MAX_VALUE;
        String nearestTrainName = "";
        for (String trainName : trains) {
            Train train = trainMap.get(trainName);
            long millisToNextTrain = Duration.between(currentTime, train.getDepartureTime()).toMillis();
            if (millisToNextTrain > 0) {
                if (millisToNextTrain < nearestTrainTime) {
                    nearestTrainName = trainName;
                    nearestTrainTime = millisToNextTrain;
                }
            }
        }

        if (nearestTrainName.isEmpty()) {
            throw new IllegalAccessException("No train for you");
        }
        return nearestTrainName;
    }

    private boolean isTrainExist(String trainName) {
        return trainMap.containsKey(trainName);
    }
}
