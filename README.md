# Raddar
Raddar is the first social app where your posts could die. Your post could die or can be leaders of the world ranking. Swipe up or swipe down every post you find in your Raddar. Your votes and the votes of the entire community will decide if the posts survive or die forever.

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

## Summary

* Architecture
  * My own Clean Architecture with MVP using [Rosie][1] framework
  * Separate Android Framework and Java in <b>android</b> and <b>general</b> packages respectively to promote backend developers can develop new features
* Features
  * Login with Google, Facebook and user & pass
  * Splash, Welcome and Tutorial screens
  * Maps, manage geolocation, draw areas and detect fake location
  * Create and delete a geolocated publication
  * Vote publication with swipe up/down
  * Crop, rotate and zoom images
  * Create comments
  * Fabric and Firebase for usage statistics
  * Push notifications with Firebase
  * Profile settings
  * Record and reproduce audio
  * Animations and sounds

## Others

* Project
  * <b><u>VERY IMPORTANT</u>: To launch this project, use the "debugFake" Build Variant</b>
  * This project is a <b>Beta</b>, some features are not implemented. For example, edit a publication, follow users, delete comments, report a publication and so on
  * This project is <b>deprecated</b>, we started in 2016 and we have not tested devices with Android >= 8.0
  * Tests are incompleted. There are some deprecated instrumentation tests with MockWebServer
  * The idea of the empty interfaces, packages organization, all the Activities with a Fragment and so on, was to homogenize the development for the backend developers. So, they could develop new features easily
  * We ❤️ Kotlin but in this old project we used Java 😭
  * We ❤️ testing but for this product made in our free time after work... Sorry! 🙏 Moreover, you will see later a lot of changes in previous versions of Raddar, so create a new test each week it did not make sense 😅
  * We ❤️ Clean Code & Clean Architecture but it is not always possible. Be pragmatic! ✌️


[1]: https://github.com/Karumi/Rosie
