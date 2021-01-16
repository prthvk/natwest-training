package com.stackroute.muzixservice.repository;


import com.stackroute.muzixservice.domain.Artist;
import com.stackroute.muzixservice.domain.Image;
import com.stackroute.muzixservice.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
//this annotaion used in combination with @RunWith(SpringRunner.class) for typical Mongodb test.

public class MuzixRepositoryTest {

    @Autowired
    MuzixRepository muzixRepository;

    Track track;
    Image image;

    @Before //1
    public void setUp(){

         image = new Image(1,"http:url","large");
        Image imageArray []= {image};

        Artist artist = new Artist(101,"Jonhn","new url",imageArray);
        track = new Track("Track1","Mynewtrack","new comments","123","new track url",artist);
    }

     @After //3
     public void tearDown(){
        
         image = null;
         track = null;
        muzixRepository.deleteAll();
     }

    @Test //2
    public void testSaveTrack(){

        muzixRepository.insert(track); //Track1
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get(); //Track1
        //ft.Track1 .equals(Track1)
        Assert.assertEquals(fetchTrack.getTrackName() ,track.getTrackName());
    }

    @Test//2
    public void testUpdateComments(){
        muzixRepository.insert(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        fetchTrack.setComments("updating the comments");
        muzixRepository.save(fetchTrack);
        Track fetchTrackobj = muzixRepository.findById(track.getTrackId()).get();
        Assert.assertEquals("updating the comments" , fetchTrackobj.getComments());
    }

    @Test
    public void testDeleteTrack(){
        //Save a track in db - Track1 - Track Id
        muzixRepository.insert(track);
        //give me back that data from db. - Track1
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
           //fetchTrack - Track1 as Track id;

           //Delete Track1 from
        muzixRepository.delete(fetchTrack);
        //There is no data in db (I Assume)
        Assert.assertEquals(Optional.empty(),muzixRepository.findById(track.getTrackId())); //Track1

    }

    @Test
    public void testGetAllTrack(){

//save Track1 in db
        muzixRepository.insert(track); //set Up
        Image image = new Image(2,"http:url:another","extralarge");
        Image imageArray []= {image};

        Artist artist = new Artist(102,"Joahna","new url",imageArray);
        track = new Track("Track2","Mynewanothertrack","new updated comments","123","new track url",artist);
       //2nd Track object created
       //save Track2 in db
       
        muzixRepository.insert(track);
        //At this point in db there are 2 Track objects

        List<Track> list = muzixRepository.findAll();

        Assert.assertEquals(2,list.size());
        //list - 0(Track1),1(Track2)
        Assert.assertEquals("Track2",list.get(1).getTrackId());

    }


}
