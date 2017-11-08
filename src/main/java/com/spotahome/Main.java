package com.spotahome;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.spotahome.model.SeatType;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            SeatType[] seatTypes = mapper.readValue(new File(args[0]), SeatType[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
