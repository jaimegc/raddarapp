package com.raddarapp.domain.model.util;

import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.UserBuilder;
import com.raddarapp.domain.model.enums.UserRelationshipType;

public class GenerateUsers {

    public User generateUser1(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("James")
                .withImage("https://cdn.pixabay.com/photo/2015/02/18/07/35/photographer-640419_1280.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(20001)
                .withFollowers(412)
                .withFollowing(200)
                .withUserRelationship(UserRelationshipType.UNKNOWN.getValue())
                .withLevel(40)
                .withTotalFootprints(20)
                .withUserCompliments(40)
                .withTotalLikes(100)
                .withTotalDislikes(20)
                .withTotalFootprintsDead(1)
                .build();

    }

    public User generateUser2(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Iván")
                .withImage("https://cdn.pixabay.com/photo/2016/02/19/13/35/photographer-1210243_1280.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(21081)
                .withFollowers(262)
                .withFollowing(100)
                .withUserRelationship(UserRelationshipType.FOLLOW_ME.getValue())
                .withLevel(62)
                .withTotalFootprints(55)
                .withUserCompliments(120)
                .withTotalLikes(100)
                .withTotalDislikes(20)
                .withTotalFootprintsDead(1)
                .build();
    }

    public User generateUser3(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Carmen")
                .withImage("https://cdn.pixabay.com/photo/2017/05/13/12/40/fashion-2309519_1280.jpg")
                .withRange(12031)
                .withFollowers(332)
                .withFollowing(240)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(23)
                .withTotalFootprints(11)
                .withUserCompliments(250)
                .withTotalLikes(100)
                .withTotalDislikes(20)
                .withTotalFootprintsDead(1)
                .build();
    }

    public User generateUser4(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Dani")
                .withImage("https://cdn.pixabay.com/photo/2016/05/23/23/32/human-1411499_1280.jpg")
                .withRange(21021)
                .withFollowers(332)
                .withFollowing(510)
                .withUserRelationship(UserRelationshipType.FRIEND.getValue())
                .withLevel(11)
                .withTotalFootprints(5)
                .withUserCompliments(520)
                .withTotalLikes(100)
                .withTotalDislikes(20)
                .withTotalFootprintsDead(1)
                .build();
    }

    public User generateUser5(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Fran")
                .withImage("https://cdn.pixabay.com/photo/2018/04/29/01/23/skin-3358873_1280.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(30222)
                .withFollowers(831)
                .withFollowing(1200)
                .withUserRelationship(UserRelationshipType.UNKNOWN.getValue())
                .withLevel(33)
                .withTotalFootprints(5255)
                .withUserCompliments(620)
                .withTotalLikes(100)
                .withTotalDislikes(20)
                .withTotalFootprintsDead(1)
                .build();
    }

    public User generateUser6(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("María")
                .withImage("https://cdn.pixabay.com/photo/2015/09/02/13/24/girl-919048_1280.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(38253)
                .withFollowers(444)
                .withFollowing(2340)
                .withUserRelationship(UserRelationshipType.UNKNOWN.getValue())
                .withLevel(66)
                .withTotalFootprints(71)
                .withUserCompliments(2330)
                .withTotalLikes(100)
                .withTotalDislikes(20)
                .withTotalFootprintsDead(1)
                .build();
    }

    public User generateUser7(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Migue")
                .withImage("https://cdn.pixabay.com/photo/2016/11/21/12/42/beard-1845166_1280.jpg")
                .withRange(22684)
                .withFollowers(133)
                .withFollowing(13400)
                .withUserRelationship(UserRelationshipType.UNKNOWN.getValue())
                .withLevel(88)
                .withTotalFootprints(555)
                .withUserCompliments(3320)
                .withTotalLikes(100)
                .withTotalDislikes(20)
                .withTotalFootprintsDead(1)
                .build();
    }

    public User generateUser8(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Eusebio")
                .withImage("https://cdn.pixabay.com/photo/2018/04/27/03/50/portrait-3353699__480.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(36677)
                .withFollowers(133)
                .withFollowing(111)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalFootprints(899)
                .withUserCompliments(20)
                .withTotalLikes(100)
                .withTotalDislikes(20)
                .withTotalFootprintsDead(1)
                .build();
    }

    public User generateUser9(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Mercelol")
                .withImage("https://cdn.pixabay.com/photo/2015/10/03/17/30/selfie-970042_1280.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(34677)
                .withFollowers(193)
                .withFollowing(11)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalFootprints(899)
                .withUserCompliments(20)
                .withTotalLikes(100)
                .withTotalDislikes(20)
                .withTotalFootprintsDead(19)
                .build();
    }

    public User generateUser10(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Carlos")
                .withImage("https://cdn.pixabay.com/photo/2016/01/03/00/13/selfie-1118885_1280.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(22677)
                .withFollowers(193)
                .withFollowing(11)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalFootprints(139)
                .withUserCompliments(3710)
                .withTotalFootprintsDead(12)
                .build();
    }

    public User generateUser11(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Sunny")
                .withImage("https://cdn.pixabay.com/photo/2015/09/25/21/36/selfie-958261_1280.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(22677)
                .withFollowers(193)
                .withFollowing(11)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalFootprints(639)
                .withUserCompliments(2710)
                .withTotalFootprintsDead(20)
                .build();
    }

    public User generateUser12(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Paco")
                .withImage("https://cdn.pixabay.com/photo/2014/05/23/23/42/man-352477_1280.jpg")
                .withRange(41677)
                .withFollowers(1293)
                .withFollowing(11)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalFootprints(249)
                .withUserCompliments(3710)
                .withTotalFootprintsDead(15)
                .build();
    }

    public User generateUser13(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Manoli")
                .withImage("https://cdn.pixabay.com/photo/2017/04/10/20/15/paragliding-2219652_1280.jpg")
                .withRange(32677)
                .withFollowers(193)
                .withFollowing(11)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalFootprints(339)
                .withUserCompliments(3710)
                .withTotalFootprintsDead(12)
                .build();
    }

    public User generateUser14(String primaryKey) {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Sandra")
                .withImage("https://cdn.pixabay.com/photo/2018/02/12/00/47/people-3147188_1280.jpg")
                .withRange(29977)
                .withFollowers(193)
                .withFollowing(11)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalFootprints(939)
                .withUserCompliments(3710)
                .withTotalFootprintsDead(18)
                .build();
    }
}
