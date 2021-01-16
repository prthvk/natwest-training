package com.stackroute.muzixservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixservice.domain.Artist;
import com.stackroute.muzixservice.domain.Image;
import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.service.MuzixService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MuzixController.class)
public class MuzixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MuzixService muzixService;

    Track track;
    List<Track> trackList;

    @Before
    public void setUp(){

        trackList = new ArrayList<>();
        Image image = new Image(1,"http:url","large");
        Image imageArray []= {image};

        Artist artist = new Artist(101,"Jonhn","new url",imageArray);
        track = new Track("Track1","Mynewtrack","new comments","123","new track url",artist);

        trackList.add(track);
        Image image1 = new Image(2,"http:url","large");
        Image imageArray1 []= {image};

        Artist artist1 = new Artist(102,"Jonhnny","new url",imageArray1);
        track = new Track("Track2","Mynewtrack123","new comments updated","123","new track url",artist1);
        trackList.add(track);



    }

    @Test
    public void testSaveTrackSuccess() throws Exception {

        when(muzixService.saveTrackToWishList(any())).thenReturn(track);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).
                content(jsonToString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(MockMvcResultHandlers.print());
        //include verify here as well

    }

    @Test
    public void testSaveTrackFailure() throws Exception {
        when(muzixService.saveTrackToWishList(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).
                        content(jsonToString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void testUpdateCommentSuccess() throws Exception {
        when(muzixService.updateCommentForTrack((track.getComments()),(track.getTrackId()))).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/track/Track2?comments=updatedcomments")
                .contentType(MediaType.APPLICATION_JSON).
                        content(jsonToString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDeleteTrack() throws Exception {
        when(muzixService.deleteTrackFromWishList(track.getTrackId())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/Track2")
                .contentType(MediaType.APPLICATION_JSON).
                        content(jsonToString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }







    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }


}
