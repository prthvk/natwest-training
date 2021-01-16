package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Artist;
import com.stackroute.muzixservice.domain.Image;
import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MuzixServiceTest {

    @Mock // creates a mock implementation for the class it is annotated with
    MuzixRepository muzixRepository;

    //display( int )

    //display( int id) , display(int empid)


    Track track;

    Optional optional;

    List<Track> listTrack=null;

    @InjectMocks //creates the mock implementation, additionally, injects
    //the dependent mocks marked with annotation @Mock
    //into it, in our case its repository layer
    MuzixServiceImpl muzixService;

    @Before
    public void setUp(){

//static which initializes the mock objects, upon initialization of junit test. 
        MockitoAnnotations.initMocks(this);
        Image image = new Image(1,"http:url","large");
        Image imageArray []= {image};

        Artist artist = new Artist(101,"Jonhn","new url",imageArray);
        track = new Track("Track1","Mynewtrack","new comments","123","new track url",artist);

        listTrack = new ArrayList<>();
        listTrack.add(track);
        optional = Optional.of(track);
    }

    @After
    public void tearDown(){

        muzixRepository.deleteAll();

    }

    @Test
    public void testSaveTrackSuccess() throws TrackAlreadyExistsException {
//

        when(muzixRepository.insert(track)).thenReturn(track);


        Track fetchTrack = muzixService.saveTrackToWishList(track);

        Assert.assertEquals(track,fetchTrack);

        verify(muzixRepository,times(1)).insert(track);

       verify(muzixRepository,times(1)).findById(track.getTrackId());

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void testSaveTrackFailure() throws TrackAlreadyExistsException {


        when(muzixRepository.insert(track)).thenReturn(track);

        when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);

        Track fetchTrack = muzixService.saveTrackToWishList(track);

       // Assert.assertEquals(track,fetchTrack);

        verify(muzixRepository,times(1)).insert(track);
        verify(muzixRepository,times(1)).findById(track.getTrackId());


    }

    @Test
    public void testUpdateCommentsSuccess() throws TrackNotFoundException {
        when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);
        track.setComments("comments updated");
        Track fetchTrack = muzixService.updateCommentForTrack(track.getComments(),track.getTrackId());
        Assert.assertEquals(fetchTrack.getComments() ,"comments updated" );

        verify(muzixRepository,times(1)).save(track);
        verify(muzixRepository,times(2)).findById(track.getTrackId());
    }

    @Test
    public void testDeleteTrack() throws TrackNotFoundException {
        when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);
        boolean fetchTrack = muzixService.deleteTrackFromWishList(track.getTrackId());
        Assert.assertEquals(true,fetchTrack);
    }

    public void testGetAllTrack() throws Exception {
        when(muzixRepository.findAll()).thenReturn(listTrack);
        List<Track> list = muzixService.getAllTrackFromWishList();
        Assert.assertEquals(list,listTrack);
    }




}
