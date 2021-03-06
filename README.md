# Raddar
Raddar is the first social app where your posts could die. Your post could die or can be leaders of the world ranking. Swipe up or swipe down every post you find in your Raddar. Your votes and the votes of the entire community will decide if the posts survive or die.

This app was the product of a Startup from Seville (Spain) called Kreomi that did not work in the market. We made this application in our free time after work. We started in 2016 and finished in 2018.

    This project is deprecated
    We do not save any data, all data are mocks
    Mock images from https://pixabay.com
    Mock comments
    All our servers are down
    Fabric and Firebase are active for usage statistics
    You can see the source code and more information on GitHub
    If you have any question do not hesitate to contact us at raddarapp@gmail.com

❤️ THANK YOU TO EVERYONE WHO COLLABORATED WITH US ❤️

<br />
<a href="https://play.google.com/store/apps/details?id=com.raddarapp"><img src="./art/google_play.png" width="150"/></a>

## Summary

* Architecture
  * My own Clean Architecture with MVP using [Rosie][1] framework
  * Separate Android Framework and Java in <b>android</b> and <b>general</b> packages respectively to promote the backend developers can add new features
* Features
  * Login with Google, Facebook and user/pass
  * Splash, Welcome and Tutorial screens
  * Maps, manage geolocation, draw areas and detect fake location
  * Create and delete a geolocated post
  * Vote for a post with swipe up/down
  * Crop, rotate and zoom images
  * Create comments
  * Save your favourite posts
  * Fabric and Firebase for usage statistics
  * Push notifications with Firebase
  * Profile settings
  * Record and play audio
  * Animations and sounds

## Others

* <b><u>VERY IMPORTANT</u>: To launch this project, use the "debugFake" Build Variant</b>
* This project is a <b>Beta</b>, some features are not implemented. For example, to edit a post, follow users, delete comments, report a post and so on
* This project is <b>deprecated</b>, we started in 2016 and we have not tested on devices with Android >= 8.0
* All the keys and the google-services.json are fake except google maps api key for debugs build variants
* Tests are incomplete. There are some deprecated instrumentation tests with MockWebServer
* The idea of the empty interfaces, organization of packages, all the Activities with a Fragment and so on, was to homogenize the development for the backend developers. So, they could add new features easily
* We ❤️ Kotlin but in this old project we used Java 😭
* We ❤️ testing but this product was made in our free time after work... Sorry! 🙏 Moreover, you can see the making of and the previous versions <a href="https://github.com/jaimegc/raddarapp/blob/master/BehindTheScenes.md">here</a>. So creating new tests each week did not make sense 😅
* We ❤️ Clean Code & Clean Architecture but it is not always possible. Be pragmatic! ✌️

## Screens

### Splash & Welcome

<p align="left">
  <img src="./art/splash.jpg" width="280">
  <img src="./art/welcome1.jpg" width="280">
  <img src="./art/welcome2.jpg" width="280">
</p>

<p align="left">
  <img src="./art/welcome3.jpg" width="280">
  <img src="./art/welcome4.jpg" width="280">
  <img src="./art/welcome5.jpg" width="280">
</p>

### Login

<p align="left">
  <img src="./art/login1.jpg" width="280">
  <img src="./art/login2.jpg" width="280">
  <img src="./art/login3.jpg" width="280">
</p>

<p align="left">
  <img src="./art/login4.jpg" width="280">
  <img src="./art/login5.jpg" width="280">
</p>

### Posts & Rankings

<p align="left">
  <img src="./art/footprints1.jpg" width="280">
  <img src="./art/footprints2.jpg" width="280">
  <img src="./art/comments.jpg" width="280">
</p>

<p align="left">
  <img src="./art/ranking_footprints.jpg" width="280">
  <img src="./art/ranking_people1.jpg" width="280">
  <img src="./art/ranking_people2.jpg" width="280">
</p>

### Create Post

<p align="left">
  <img src="./art/create_footprint1.jpg" width="280">
  <img src="./art/create_footprint2.jpg" width="280">
  <img src="./art/create_footprint3.jpg" width="280">
</p>

<p align="left">
  <img src="./art/create_footprint4.jpg" width="280">
</p>

### Territories

<p align="left">
  <img src="./art/map1.jpg" width="280">
  <img src="./art/map2.jpg" width="280">
  <img src="./art/map3.jpg" width="280">
</p>

<p align="left">
  <img src="./art/map4.jpg" width="280">
</p>


### Profiles

<p align="left">
  <img src="./art/profile1.jpg" width="280">
  <img src="./art/profile2.jpg" width="280">
  <img src="./art/profile3.jpg" width="280">
</p>

### Profile Settings

<p align="left">
  <img src="./art/settings1.jpg" width="280">
  <img src="./art/settings2.jpg" width="280">
  <img src="./art/settings3.jpg" width="280">
</p>

<p align="left">
  <img src="./art/audio_record.jpg" width="280">
  <img src="./art/audio_recording.jpg" width="280">
</p>

### Crop & Rotate & Zoom Images

<p align="left">
  <img src="./art/crop1.jpg" width="280">
  <img src="./art/crop2.jpg" width="280">
  <img src="./art/rotate.jpg" width="280">
</p>

<p align="left">
  <img src="./art/zoom.jpg" width="280">
</p>

### Tutorial

<p align="left">
  <img src="./art/tutorial1.jpg" width="280">
  <img src="./art/tutorial2.jpg" width="280">
  <img src="./art/tutorial3.jpg" width="280">
</p>

<p align="left">
  <img src="./art/tutorial4.jpg" width="280">
  <img src="./art/tutorial5.jpg" width="280">
  <img src="./art/tutorial6.jpg" width="280">
</p>

<p align="left">
  <img src="./art/tutorial7.jpg" width="280">
  <img src="./art/tutorial8.jpg" width="280">
</p>

### Others

<p align="left">
  <img src="./art/others1.jpg" width="280">
  <img src="./art/others2.jpg" width="280">
  <img src="./art/others3.jpg" width="280">
</p>

### Behind the Scenes

You can see the making of and the previous versions <a href="https://github.com/jaimegc/raddarapp/blob/master/BehindTheScenes.md">here</a>

## Raddar Team

#### CEO & UX/UI Designer 

<table>
  <tr>
    <th height="50">Dan Bellamy</th>
    <th valign="center"><a href="https://mx.linkedin.com/in/danbellamy"><img src="./art/linkedin.png" height="30"/></a></th>  
  </tr>
</table>

#### CTO & Android Engineer

<table>
  <tr>
    <th height="50">Jaime GC</th>
    <th valign="center"><a href="https://es.linkedin.com/in/jaimeguerrerocubero"><img src="./art/linkedin.png" height="30"/></a></th>  
    <th valign="center"><a href="https://github.com/jaimegc"><img src="./art/github.png" height="30"/></a></th>  
  </tr>
</table>

#### PM & Backend Engineer

<table>
  <tr>
    <th height="50">Francisco Javier Delgado Vallano</th>
    <th valign="center"><a href="https://es.linkedin.com/in/francisco-javier-delgado-vallano-b28b1670"><img src="./art/linkedin.png" height="30"/></a></th>  
    <th valign="center"><a href="https://github.com/franvallano"><img src="./art/github.png" height="30"/></a></th>  
    <th valign="center"><a href="https://twitter.com/franvallano"><img src="./art/twitter.png" height="30"/></a></th>  
  </tr>
</table>

#### Backend Engineer

<table>
  <tr>
    <th height="50">Miguel Ángel Quintanilla</th>
    <th valign="center"><a href="https://es.linkedin.com/in/miguel-ángel-quintanilla-758a5b120"><img src="./art/linkedin.png" height="30"/></a></th>  
    <th valign="center"><a href="https://github.com/migangqui"><img src="./art/github.png" height="30"/></a></th>  
    <th valign="center"><a href="https://twitter.com/maq_dev"><img src="./art/twitter.png" height="30"/></a></th>  
  </tr>
</table>

#### Frontend Engineer

<table>
  <tr>
    <th height="50">José Iván Moreno Soto</th>
    <th valign="center"><a href="https://es.linkedin.com/in/jimoreno1"><img src="./art/linkedin.png" height="30"/></a></th>  
    <th valign="center"><a href="https://github.com/Josmorsot"><img src="./art/github.png" height="30"/></a></th>  
  </tr>
</table>

#### QA Engineer

<table>
  <tr>
    <th height="50">Eusebio Gómez Moreno</th>
    <th valign="center"><a href="https://es.linkedin.com/in/eugomor"><img src="./art/linkedin.png" height="30"/></a></th>  
  </tr>
</table>

## Libraries used in this project

* [Rosie][1]
* [Renderers][2]
* [Okhttp][3]
* [Retrofit][4]
* [Picasso][5]
* [Dagger][6]
* [Butterknife][7]
* [RoundedImageView][8]
* [TransitionImageView][9]
* [MockWebServer][10]
* [ApacheCommonsIO][11]
* [Calligrahy][12]
* [Opus][13]
* [RxAudio][14]
* [TapTargetView][15]
* [PhotoView][16]
* [PageIndicatorView][17]
* [StickySwitch][18]
* [Pulsator4droid][19]
* [CustomActivityOnCrash][20]
* [CircularImageView][21]
* [FloatingView][22]
* [Emoji][23]
* [Fabric][24]
* [Firebase][25]
* [Facebook SDK][26]
* [Google Play Services][27]
    * Maps
    * Maps Utils
    * Location
    
License
-------

    Copyright 2019 Jaime GC
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.    

[1]: https://github.com/Karumi/Rosie
[2]: https://github.com/pedrovgs/Renderers
[3]: https://github.com/square/okhttp
[4]: https://github.com/square/retrofit
[5]: https://github.com/square/picasso
[6]: https://github.com/square/dagger
[7]: https://github.com/JakeWharton/butterknife
[8]: https://github.com/vinc3m1/RoundedImageView
[9]: https://github.com/vikramkakkar/ImageTransition
[10]: https://github.com/square/okhttp/tree/master/mockwebserver
[11]: https://github.com/apache/commons-io
[12]: https://github.com/chrisjenx/Calligraphy
[13]: https://github.com/louisyonge/opus_android
[14]: https://github.com/Piasy/RxAndroidAudio
[15]: https://github.com/KeepSafe/TapTargetView
[16]: https://github.com/chrisbanes/PhotoView
[17]: https://github.com/romandanylyk/PageIndicatorView
[18]: https://github.com/GwonHyeok/StickySwitch
[19]: https://github.com/booncol/Pulsator4Droid
[20]: https://github.com/Ereza/CustomActivityOnCrash
[21]: https://github.com/Pkmmte/CircularImageView
[22]: https://github.com/UFreedom/FloatingView
[23]: https://github.com/vanniktech/Emoji
[24]: https://get.fabric.io/
[25]: https://firebase.google.com/docs/android/setup
[26]: https://developers.facebook.com/docs/android/componentsdks
[27]: https://developers.google.com/maps/documentation/android-sdk/start
