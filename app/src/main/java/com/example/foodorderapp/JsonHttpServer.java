package com.example.foodorderapp;

import java.io.File;
import java.io.FileInputStream;

import fi.iki.elonen.NanoHTTPD;

// NanoHTTPD is a library for creating HTTP severs.
public class JsonHttpServer extends NanoHTTPD {

    private final File jsonFile;
    public JsonHttpServer(int port, File jsonFile) {
        super(port); // the port number to which the server will listen.
        this.jsonFile = jsonFile;
    }

    @Override
    // this method is called whenever the server receives Http request.
    // IHTTPSession session Contains details of the incoming HTTP request.
    public Response serve(IHTTPSession session) {
        return super.serve(session);
        try{
            // FileInputStream is a class in java.io package.
            // it is used to read data from the files.
            FileInputStream fileInputStream = new FileInputStream(jsonFile);
            // newFixedLengthResponse creates a http response.
            //parameters are status,content type (o indicate that the response is a JSON file), fileContent (reads the file using fileInputStream,file length.
            return newFixedLengthResponse(Response.Status.OK,"application/json",fileInputStream,jsonFile.length());
        }catch (Exception e){
            //logs the error message
            e.printStackTrace();
            return newFixedLengthResponse(Response.Status.INTERNAL_ERROR,"text/plain", "Error serving file")
        }
    }
}
