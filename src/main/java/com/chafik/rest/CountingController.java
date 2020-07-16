package com.chafik.rest;

import com.chafik.wordFrequency.WordFrequency;
import com.chafik.wordFrequency.WordFrequencyAnalyzerImpl;
import io.swagger.annotations.ApiOperation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("counting-words")
@RequestScoped
public class CountingController {

    @Inject
    WordFrequencyAnalyzerImpl wordFrequencyAnalyzer;

    @GET
    @Path("calculateHighestFrequency")
    @Produces(MediaType.TEXT_PLAIN)
    public Response calculateHighestFrequency(@QueryParam("text") String text) {
        try {
            return Response.status(200).entity(wordFrequencyAnalyzer.calculateHighestFrequency(text)).build();
        } catch (Exception exc) {
            return Response.status(400).entity("Something went wrong").build();
        }
    }

    @GET
    @Path("calculateFrequencyForWord")
    @Produces(MediaType.TEXT_PLAIN)
    public Response calculateFrequencyForWord(@QueryParam("text") String text, @QueryParam("word") String word) {
        try {
            return Response.status(200).entity(wordFrequencyAnalyzer.calculateFrequencyForWord(text, word)).build();
        } catch (Exception exc) {
            return Response.status(400).entity("Something went wrong").build();
        }
    }

    @GET
    @Path("calculateMostFrequentNWords")
    @Produces(MediaType.APPLICATION_JSON)
    public Response calculateMostFrequentNWords(@QueryParam("text") String text, @QueryParam("number") int number) {
        try {
            List<WordFrequency> words = wordFrequencyAnalyzer.calculateMostFrequentNWords(text, number);
            if (words != null) {
                String convertedWords = wordFrequencyAnalyzer.calculateMostFrequentNWordsAsList(words);

                return Response.status(200).entity(convertedWords).build();
            } else {
                return Response.status(200).entity(-1).build();
            }
        } catch (Exception exc) {
            return Response.status(400).entity("Something went wrong").build();
        }
    }
}
